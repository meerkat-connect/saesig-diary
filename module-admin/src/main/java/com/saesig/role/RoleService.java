package com.saesig.role;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.common.RequestDto;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.member.Member;
import com.saesig.domain.member.MemberRepository;
import com.saesig.domain.member.QMember;
import com.saesig.domain.role.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final RoleResourceRepository roleResourceRepository;
    private final ResourceRepository resourceRepository;
    private final MemberRoleMapper memberRoleMapper;
    private final EntityManager em;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Transactional(readOnly = true)
    public List<RoleResponseDto> findAll() {
        return roleRepository
                .findAll()
                .stream()
                .map(RoleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(RoleInsertDto roleInsertDto) {
        Role findByName = roleRepository.findByName(roleInsertDto.getName());
        if (findByName != null && idDuplicatedRoleName(roleInsertDto.getName(), findByName.getName())) {
            throw new IllegalArgumentException("역할명이 중복됩니다.");
        }

        Role savedRole = roleRepository.save(roleInsertDto.toEntity());
        return savedRole.getId();
    }

    @Transactional
    public Long update(Long id, RoleUpdateDto roleUpdateDto) {
        Role roleById = roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("자원 아이디가 존재하지 않습니다."));

        if (!roleById.getName().equals(roleUpdateDto.getName())) {
            Role findByName = roleRepository.findByName(roleUpdateDto.getName());
            if (findByName != null && idDuplicatedRoleName(roleUpdateDto.getName(), findByName.getName())) {
                throw new IllegalArgumentException("역할명이 중복됩니다.");
            }
        }

        roleById.updateInfo(roleUpdateDto.getName()
                , roleUpdateDto.getIsEnabled()
                , roleUpdateDto.getDescription());

        return roleById.getId();
    }

    private boolean idDuplicatedRoleName(String srcName, String desName) {
        return srcName.equals(desName);
    }

    public RoleResponseDto fineById(Long id) {
        Role roleById = roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("역할 아이디가 존재하지 않습니다."));

        return new RoleResponseDto(roleById);
    }

    public List<MappedMemberDto> findAllMember() {
        return memberRepository
                .findAll()
                .stream()
                .map(MappedMemberDto::new)
                .collect(Collectors.toList());
    }

    public Page<Member> findAllMemberUsingPageable(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public DataTablesResponseDto findMappedMembersById(Long roleId, RequestDto request) {
        Integer pageNum = request.getStart() / request.getLength();
        PageRequest of = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "createdAt"));
        QMemberRole qMemberRole = QMemberRole.memberRole;
        QMember qMember = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();
        String searchType = request.getSearchType();
        if (StringUtils.hasText(searchType)) {
            if ("email".equals(searchType)) {
                builder.and(qMember.email.eq(request.getSearchName()));
            } else if ("nickname".equals(searchType)) {
                builder.and(qMember.nickname.eq(request.getSearchName()));
            }
        }
        builder.and(qMemberRole.role.id.eq(roleId));

        QueryResults<MappedMemberDto> mappedMembers = queryFactory.select(
                        Projections.fields(
                                MappedMemberDto.class,
                                qMember.id, qMember.email, qMember.nickname
                        )
                ).from(qMemberRole)
                .innerJoin(qMember)
                .on(qMemberRole.member.id.eq(qMember.id))
                .where(builder)
                .offset(of.getOffset())
                .limit(of.getPageSize())
                .fetchResults();

        PageImpl<MappedMemberDto> mappedMemberDtos = new PageImpl<>(mappedMembers.getResults(), of, mappedMembers.getTotal());

        return new DataTablesResponseDto(mappedMemberDtos, mappedMemberDtos.getContent());
    }

    @Transactional
    public void addCheckedMembers(Long roleId, Long[] memberIds, SessionMember member) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("역할 아이디가 존재하지 않습니다."));

        List<MemberRole> memberRoles = memberRoleRepository.findAll(roleId, List.of(memberIds));
        List<Long> filteredIds = new ArrayList<>(Arrays.asList(memberIds)); // List.of로 생성시 추가 ,삭제 불가

        for (MemberRole memberRole : memberRoles) {
            for (Long memberId : memberIds) {
                if (memberRole.getMember().getId().equals(memberId)) {
                    filteredIds.remove(memberId);
                    break;
                }
            }
        }

        if (filteredIds.size() > 0)
            memberRoleMapper.insertMemberRoles(roleId, filteredIds, member.getId());
    }

    public List<RoleResourceResponseDto> findMappedResources(Long roleId) {
        return roleResourceRepository.findMappedResources(roleId);
    }

    @Transactional
    public void mapResources(Long roleId, List<RoleResourceDto> roleResourceDtos) {
        List<RoleResource> mappedResources = roleResourceRepository.findAllByRoleId(roleId);

        for (RoleResourceDto roleResourceDto : roleResourceDtos) {
            boolean isAlreadyMapped = false;
            for (RoleResource mappedResource : mappedResources) {
                if (mappedResource.getId().equals(roleResourceDto.getId())) {
                    isAlreadyMapped = true;
                    roleResourceRepository.deleteById(mappedResource.getId());
                    break;
                }
            }

            if (!isAlreadyMapped) {
                Role role = roleRepository.findById(roleId).get();
                Resource resource = resourceRepository.findById(roleResourceDto.getId()).get();
                RoleResource roleResource = new RoleResource(role, resource);
                roleResourceRepository.save(roleResource);
            }
        }
    }
}

package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saesigDiary.domain.member.Member;
import saesigDiary.domain.member.MemberRepository;
import saesigDiary.domain.role.*;

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
        if (idDuplicatedRoleName(roleInsertDto.getName(), findByName.getName())) {
            throw new IllegalArgumentException("역할명이 중복됩니다.");
        }

        Role savedRole = roleRepository.save(roleInsertDto.toEntity());
        return savedRole.getId();
    }

    @Transactional
    public Long update(Long id, RoleUpdateDto roleUpdateDto) {
        Role findByName = roleRepository.findByName(roleUpdateDto.getName());
        if (idDuplicatedRoleName(roleUpdateDto.getName(), findByName.getName())) {
            throw new IllegalArgumentException("역할명이 중복됩니다.");
        }

        Role roleById = roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("자원 아이디가 존재하지 않습니다."));

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

    public Page<MemberRole> findMappedMembersById(Long roleId, Pageable pageable) {
        return memberRoleRepository.findByRoleId(roleId, pageable);
    }

    @Transactional
    public void addCheckedMembers(Long roleId, Long[] memberIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("역할 아이디가 존재하지 않습니다."));

        for (Long memberId : memberIds) {
            // 중복 제거 필요

            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원 아이디가 존재하지 않습니다."));
            MemberRole newMemberRole = MemberRole.builder().member(member).role(role).build();
            memberRoleRepository.save(newMemberRole);
        }
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

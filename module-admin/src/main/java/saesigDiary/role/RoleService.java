package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saesigDiary.domain.member.Member;
import saesigDiary.domain.member.MemberRepository;
import saesigDiary.domain.role.Role;
import saesigDiary.domain.role.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final MemberRepository memberRepository;

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
        Role savedRole = roleRepository.save(roleInsertDto.toEntity());
        return savedRole.getId();
    }

    @Transactional
    public Long update(Long id, RoleUpdateDto roleUpdateDto) {
        Role roleById = roleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("자원 아이디가 존재하지 않습니다."));

        roleById.updateInfo(roleUpdateDto.getName()
                , roleUpdateDto.getIsEnabled()
                , roleUpdateDto.getDescription());

        return roleById.getId();
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
}

package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saesigDiary.domain.role.Role;
import saesigDiary.domain.role.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

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
}

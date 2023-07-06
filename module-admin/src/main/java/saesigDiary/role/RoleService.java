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
        Role savedRole = roleRepository.save(roleInsertDto.toEntity());
        return savedRole.getId();
    }

    @Transactional
    public Long update(RoleUpdateDto roleUpdateDto) {

        return null;
    }

    public RoleResponseDto fineById(Long id) {
        return null;
    }
}

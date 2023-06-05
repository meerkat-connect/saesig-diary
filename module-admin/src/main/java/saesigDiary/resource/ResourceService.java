package saesigDiary.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saesigDiary.domain.role.Resource;
import saesigDiary.domain.role.ResourceRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public List<ResourceResponseDto> findAll() {
        return resourceRepository
                .findAll()
                .stream()
                .map(ResourceResponseDto::new)
                .collect(Collectors.toList());
    }

    public ResourceResponseDto findById(Long resourceId) {
        Resource resourceById = resourceRepository
                .findById(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("자원 아이디가 존재하지 않습니다."));

        return new ResourceResponseDto(resourceById);
    }

    public List<ResourceResponseDto> findAllWithRecursive() {
        return resourceRepository
                .findAllWithRecursive()
                .stream()
                .map(ResourceResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(ResourceRequestDto resourceRequestDto) {
        Resource savedResource = resourceRepository.save(resourceRequestDto.toEntity());
        return savedResource.getId();
    }
}

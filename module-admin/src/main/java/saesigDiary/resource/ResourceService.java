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
    public Long insert(ResourceInsertDto resourceInsertDto) {
        Resource savedResource = resourceRepository.save(resourceInsertDto.toEntity());
        return savedResource.getId();
    }

    @Transactional
    public Long update(Long id, ResourceUpdateDto resourceUpdateDto) {
        Resource resourceById = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("자원 아이디가 존재하지 않습니다."));

        resourceById.updateInfo(resourceUpdateDto.getName()
                , resourceUpdateDto.getUrl()
                , resourceUpdateDto.getHttpMethod()
                , resourceUpdateDto.getIsEnabled()
                , resourceUpdateDto.getType());

        return resourceById.getId();
    }

    @Transactional
    public void move(ResourceMoveDto resourceMoveDto) {
        if (isNotSameParent(resourceMoveDto)) {
            resourceRepository.decreaseOrderOfOriginalParentResource(resourceMoveDto.getOriginalParentIdOfSelectedNode(), resourceMoveDto.getOriginalOrdOfSelectedNode());
            resourceRepository.increaseOrderOfNewParentResource(resourceMoveDto.getNewParentIdOfSelectedNode(), resourceMoveDto.getNewOrdOfSelectedNode());

/*            Resource resource = resourceRepository.findById(resourceMoveDto.getIdOfSelectedNode()).get();
            resource.move(resourceMoveDto.getNewParentIdOfSelectedNode(), resourceMoveDto.getDepthOfNewParentId(), resourceMoveDto.getNewOrdOfSelectedNode());*/

            resourceRepository.changeParentId(
                    resourceMoveDto.getIdOfSelectedNode()
                    , resourceMoveDto.getNewParentIdOfSelectedNode()
                    , resourceMoveDto.getNewOrdOfSelectedNode()
                    , resourceMoveDto.getDepthOfNewParentId());

            resourceRepository.changeDepth(resourceMoveDto.getIdOfSelectedNode());

        } else {
            if (moveUp(resourceMoveDto)) {
                resourceRepository.increaseOrder(resourceMoveDto.getOriginalParentIdOfSelectedNode(), resourceMoveDto.getNewOrdOfSelectedNode(), resourceMoveDto.getOriginalOrdOfSelectedNode());
            } else {
                resourceRepository.decreaseOrder(resourceMoveDto.getOriginalParentIdOfSelectedNode(), resourceMoveDto.getNewOrdOfSelectedNode(), resourceMoveDto.getOriginalOrdOfSelectedNode());
            }

            Resource resource = resourceRepository.findById(resourceMoveDto.getIdOfSelectedNode()).get();
            resource.changeOrd(resourceMoveDto.getNewOrdOfSelectedNode());
        }

    }

    private boolean moveUp(ResourceMoveDto resourceMoveDto) {
        return resourceMoveDto.getOriginalOrdOfSelectedNode() > resourceMoveDto.getNewOrdOfSelectedNode();
    }

    private boolean isSameParent(ResourceMoveDto resourceMoveDto) {
        return resourceMoveDto.getOriginalParentIdOfSelectedNode().equals(resourceMoveDto.getNewParentIdOfSelectedNode());
    }

    private boolean isNotSameParent(ResourceMoveDto resourceMoveDto) {
        return !isSameParent(resourceMoveDto);
    }
}

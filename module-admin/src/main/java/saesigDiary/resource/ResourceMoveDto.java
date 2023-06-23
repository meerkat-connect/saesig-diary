package saesigDiary.resource;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourceMoveDto {
    private Long originalParentIdOfSelectedNode;

    private Integer originalOrdOfSelectedNode;

    private Long newParentIdOfSelectedNode;

    private Integer newOrdOfSelectedNode;

    private Long idOfSelectedNode;

    private Integer depthOfNewParentId;

    @Builder
    public ResourceMoveDto(Long originalParentIdOfSelectedNode, Integer originalOrdOfSelectedNode, Long newParentIdOfSelectedNode, Integer newOrdOfSelectedNode, Long idOfSelectedNode, Integer depthOfNewParentId) {
        this.originalParentIdOfSelectedNode = originalParentIdOfSelectedNode;
        this.originalOrdOfSelectedNode = originalOrdOfSelectedNode;
        this.newParentIdOfSelectedNode = newParentIdOfSelectedNode;
        this.newOrdOfSelectedNode = newOrdOfSelectedNode;
        this.idOfSelectedNode = idOfSelectedNode;
        this.depthOfNewParentId = depthOfNewParentId;
    }
}

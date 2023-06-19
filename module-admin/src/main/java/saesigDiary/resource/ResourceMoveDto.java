package saesigDiary.resource;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResourceMoveDto {
    private Long originalParentIdOfSelectedNode;

    private Integer originalOrdOfSelectedNode;

    private Long newParentIdOfSelectedNode;

    private Integer newOrdOfSelectedNode;

    private Long idOfSelectedNode;

    @Builder
    public ResourceMoveDto(Long originalParentIdOfSelectedNode, Integer originalOrdOfSelectedNode, Long newParentIdOfSelectedNode, Integer newOrdOfSelectedNode, Long idOfSelectedNode) {
        this.originalParentIdOfSelectedNode = originalParentIdOfSelectedNode;
        this.originalOrdOfSelectedNode = originalOrdOfSelectedNode;
        this.newParentIdOfSelectedNode = newParentIdOfSelectedNode;
        this.newOrdOfSelectedNode = newOrdOfSelectedNode;
        this.idOfSelectedNode = idOfSelectedNode;
    }
}

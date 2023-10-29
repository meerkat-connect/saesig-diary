package com.saesig.common.menu;

import com.saesig.resource.ResourceResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResourceNode {
    private ResourceResponseDto data;
    private ResourceNode parentNode;
    private List<ResourceNode> childNodes = new ArrayList<>();

    public void addChild(ResourceNode childNode) {
        childNode.setParentNode(this);
        this.childNodes.add(childNode);
    }
}

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
    private ResourceResponseDto parentNode;
    private List<ResourceResponseDto> childNode = new ArrayList<>();

    
}

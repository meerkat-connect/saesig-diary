package com.saesig.resource;


import com.saesig.common.menu.ResourceNode;
import com.saesig.common.menu.ResourceTree;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/resources")
public class ResourceController {
    private final ResourceService resourceService;

    private final ResourceTree resourceTree = new ResourceTree();

    public String menuPrint() {
        List<ResourceResponseDto> resources = resourceService.findAll();
        // 트리구조 형성

        //depth == 1
        ResourceTree resourceTree = getResourceTree("ADMIN");

        // depth == 2


        // depth == 3
        // <ul class="pcoded-submenu"> </ul>
        // sb.append("<li class=""> <a href=${url}><span class="pcoded-mtext">${resource_name}</span></a></li>")

        // depth >= 4

        return "ok";
    }

    private ResourceTree getResourceTree(String resourceCategory) {
        List<ResourceResponseDto> resources = resourceService.findAll();
        return makeResourcetree(resources);
    }

    private ResourceTree makeResourcetree(List<ResourceResponseDto> resources) {
        for (ResourceResponseDto resource : resources) {
            ResourceNode resourceNode = new ResourceNode();
            resourceNode.setData(resource);

            if (resource.getId().equals(0L)) {
                resourceTree.setRoot(resourceNode);
            } else {
                ResourceNode parentNode = getParentNodeById(resource.getUpperId());
                if(parentNode != null) {
                    parentNode.addChild(resourceNode);
                }
            }
        }

        return resourceTree;
    }

    private ResourceNode getParentNodeById(Long upperId) {
        return getParentNodeById(resourceTree.getRoot(), upperId);
    }

    private ResourceNode getParentNodeById(ResourceNode resourceNode, Long menuId) {

        if (resourceNode != null) {
            ResourceResponseDto data = resourceNode.getData();
            if (menuId.equals(data.getId())) {
                return resourceNode;
            } else {
                for (ResourceNode node : resourceNode.getChildNodes()) {
                    ResourceNode parentNodeById = getParentNodeById(node, menuId);
                    if (parentNodeById != null) {
                        return parentNodeById;
                    }
                }
            }
        }
        return null;
    }


    @GetMapping("/view")
    public String resourcesView() {
        return "resources/view";
    }

    @GetMapping("")
    @ResponseBody
    public List<ResourceResponseDto> getResources() {
        menuPrint();
        return resourceService.findAll();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long addResource(@RequestBody ResourceInsertDto resourceInsertDto) {
        return resourceService.insert(resourceInsertDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResourceResponseDto getResource(@PathVariable Long id) {
        return resourceService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Long updateResource(@PathVariable Long id, @RequestBody ResourceUpdateDto resourceUpdateDto) {
        return resourceService.update(id, resourceUpdateDto);
    }

    @PostMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> moveResource(@RequestBody ResourceMoveDto resourceMoveDto) {
        resourceService.move(resourceMoveDto);

        return ResponseEntity.noContent().build();
    }
}

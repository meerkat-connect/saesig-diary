package com.saesig.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/resources")
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/view")
    public String resourcesView() {
        return "resourcesView";
    }

    @GetMapping("")
    @ResponseBody
    public List<ResourceResponseDto> getResources() {
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
    public void moveResource(@RequestBody ResourceMoveDto resourceMoveDto) {
        resourceService.move(resourceMoveDto);
    }
}

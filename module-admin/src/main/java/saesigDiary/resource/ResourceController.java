package saesigDiary.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/resourcesView")
    public String resourcesView(Model model) {
        return "resourcesView";
    }

    @GetMapping("/resources")
    @ResponseBody
    public List<ResourceResponseDto> getResources() {
        return resourceService.findAll();
    }

    @GetMapping("/resourcesWithRecursive")
    @ResponseBody
    public List<ResourceResponseDto> getResourcesWithRecursive() {
        return resourceService.findAllWithRecursive();
    }

    @PostMapping(value = "/resources", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long addResource(@RequestBody ResourceInsertDto resourceInsertDto){
        return resourceService.insert(resourceInsertDto);
    }

    @GetMapping("/resources/{id}")
    @ResponseBody
    public ResourceResponseDto getResource(@PathVariable Long id) {
        return resourceService.findById(id);
    }

    @PutMapping("/resources/{id}")
    @ResponseBody
    public Long updateResource(@PathVariable Long id, @RequestBody ResourceUpdateDto resourceUpdateDto) {
        return resourceService.update(id, resourceUpdateDto);
    }


}

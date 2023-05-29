package saesigDiary.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saesigDiary.domain.role.ResourceResponseDto;

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

    @PostMapping("/resources")
    @ResponseBody
    public ResourceResponseDto addResource(){
        return null;
    }

    @GetMapping("/resources/{resourceId}")
    @ResponseBody
    public ResourceResponseDto getResource(@PathVariable Long resourceId) {
        return resourceService.findById(resourceId);
    }


}

package saesigDiary.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import saesigDiary.domain.role.Resource;
import saesigDiary.domain.role.ResourceRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class ResourceController {
    private final ResourceRepository resourceRepository;

    @GetMapping("/resourcesView")
    public String resourcesView(Model model) {
        return "resourcesView";
    }

    @GetMapping("/resources")
    @ResponseBody
    public List<Resource> getResources() {
        return resourceRepository.findParentResources();
    }

}

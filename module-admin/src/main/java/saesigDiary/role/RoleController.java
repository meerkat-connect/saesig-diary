package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import saesigDiary.domain.role.Role;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/view")
    public String view() {
        return "roles/view";
    }

    @GetMapping("")
    @ResponseBody
    public List<Role> findAll() {

        return null;
    }
}

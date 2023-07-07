package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<RoleResponseDto> findAll() {
        return roleService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RoleResponseDto findById(@PathVariable Long id) {
        return roleService.fineById(id);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long insert(@RequestBody RoleInsertDto roleInsertDto) {
        return roleService.insert(roleInsertDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody RoleUpdateDto roleUpdateDto) {
        return roleService.update(id, roleUpdateDto);
    }

}

package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import saesigDiary.domain.member.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/memberMapping/view")
    public String userMappingView() {
        return "roles/memberMapping";
    }

    @GetMapping("/memberMapping/members")
    @ResponseBody
    public Map<String, Object> findAllMembers(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        List<MappedMemberDto> allMember = roleService.findAllMember();

        result.put("data", allMember);
        result.put("recordsTotal", 10);
        result.put("recordsFiltered", 5);
        result.put("draw", 2);

        return result;
    }

    @GetMapping("/memberMapping/members/search")
    @ResponseBody
    public Map<String, Object> findUsers(Pageable pageable) {
        PageRequest of = PageRequest.of(1, 5);
        Page<Member> allMemberUsingPageable = roleService.findAllMemberUsingPageable(of);

        Map<String, Object> result = new HashMap<>();
        DataTablesResponseDto dataTablesResponseDto = new DataTablesResponseDto(allMemberUsingPageable, allMemberUsingPageable.stream().map(MappedMemberDto::new).collect(Collectors.toList()));
        result.put("data", dataTablesResponseDto.getList());
        result.put("draw", dataTablesResponseDto.getDraw());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }

}

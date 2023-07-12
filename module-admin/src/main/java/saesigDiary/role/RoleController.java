package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import saesigDiary.domain.member.Member;
import saesigDiary.domain.role.MemberRole;

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

    @GetMapping("/{id}/members")
    @ResponseBody
    public Map<String,Object> findMappedMembersById(HttpServletRequest request, @PathVariable Long id, Pageable pageable) {
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        Integer pageNum = start / length;
        PageRequest of = PageRequest.of(pageNum, length);
        Page<MemberRole> findMappedMembersById = roleService.findMappedMembersById(id, of);

        Map<String, Object> result = new HashMap<>();
        DataTablesResponseDto dataTablesResponseDto = new DataTablesResponseDto(findMappedMembersById, findMappedMembersById.stream().map(MappedMemberDto::new).collect(Collectors.toList()));
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());
//        result.put("error", "에러 발생시 사용할 메시지");
        return result;
    }

    @GetMapping("/userListModal")
    public String userListModel() {
        return "roles/userListModal";
    }

    @GetMapping("/memberMapping/members/search")
    @ResponseBody
    public Map<String, Object> findUsers(HttpServletRequest req, Pageable pageable) {
        Integer start = Integer.valueOf(req.getParameter("start"));
        Integer length = Integer.valueOf(req.getParameter("length"));
        Integer pageNum = start / length;
        PageRequest of = PageRequest.of(pageNum, length);
        Page<Member> allMemberUsingPageable = roleService.findAllMemberUsingPageable(of);

        Map<String, Object> result = new HashMap<>();
//        DataTablesResponseDto dataTablesResponseDto = new DataTablesResponseDto(allMemberUsingPageable, allMemberUsingPageable.stream().map(MappedMemberDto::new).collect(Collectors.toList()));
//        result.put("data", dataTablesResponseDto.getList());
//        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
//        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());
//        result.put("error", "에러 발생시 사용할 메시지");

        return result;
    }

}

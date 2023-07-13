package saesigDiary.role;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        Integer pageNum = start / length;
        PageRequest of = PageRequest.of(pageNum, length);
        Page<Member> members = roleService.findAllMemberUsingPageable(of);
        DataTablesResponseDto dataTablesResponseDto = new DataTablesResponseDto(members, members.stream().map(MappedMemberDto::new).collect(Collectors.toList()));

        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

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

    @GetMapping("/memberListModal")
    public String userListModel() {
        return "roles/memberListModal";
    }

    @PostMapping("/memberMapping/members")
    public ResponseEntity<Object> addCheckedMembers(@RequestParam Long roleId, @RequestParam Long[] memberIds){
        roleService.addCheckedMembers(roleId, memberIds);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

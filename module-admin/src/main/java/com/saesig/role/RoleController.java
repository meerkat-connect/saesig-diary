package com.saesig.role;

import com.saesig.common.RequestDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.role.RoleResourceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/roleView")
    public String roleView() {
        return "roles/roleView";
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

    //모달에서 사용되는 URL
    @GetMapping("/memberMapping/members")
    @ResponseBody
    public DataTablesResponseDto findAllMembers(RequestDto requestDto) {
        return roleService.findAllMemberUsingPageable(requestDto);
    }

    //역할에 매핑된 사용자 조회
    @GetMapping("/{id}/members")
    @ResponseBody
    public DataTablesResponseDto findMappedMembersById(@PathVariable Long id, RequestDto request) {
        return roleService.findMappedMembersById(id, request);
    }

    @GetMapping("/memberListModal")
    public String userListModel() {
        return "roles/memberListModal";
    }

    @PostMapping("/memberMapping/members")
    public ResponseEntity<Object> addCheckedMembers(@RequestParam Long roleId
            , @RequestParam Long[] memberIds
            , @LoginMember SessionMember member) {
        roleService.addCheckedMembers(roleId, memberIds, member.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/resourceMapping/view")
    public String resourceMappingView() {
        return "roles/resourceMapping";
    }

    @GetMapping("/{roleId}/mappedResources")
    @ResponseBody
    public List<RoleResourceResponseDto> findMappedResources(@PathVariable Long roleId) {
        return roleService.findMappedResources(roleId);
    }

    @PostMapping("/{roleId}/resources/mapping")
    @ResponseBody
    public ResponseEntity<Object> mapResources(@PathVariable Long roleId, @RequestBody List<RoleResourceDto> data) {
        roleService.mapResources(roleId, data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package com.saesig.faq;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class FaqController {
    private final FaqService faqService;
    private final EnumMapperFactory enumFactory;


    @GetMapping("/admin/faqs/view")
    public String faqView() {
        return "faq/view";
    }

    @GetMapping("/admin/faqs/")
    @ResponseBody
    public Map<String, Object> findAll(HttpServletRequest request) {
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        Integer pageNum = start / length;

        PageRequest of = PageRequest.of(pageNum, length);
        DataTablesResponseDto dataTablesResponseDto = faqService.findAll(of);

        Map<String, Object> result = new HashMap<>();
        result.put("data", dataTablesResponseDto.getList());
        result.put("recordsTotal", dataTablesResponseDto.getRecordsTotal());
        result.put("recordsFiltered", dataTablesResponseDto.getRecordsFiltered());

        return result;
    }

    @GetMapping("/admin/faqs/{id}")
    @ResponseBody
    public FaqResponseDto findById(@PathVariable Long id) {
        return faqService.findById(id);
    }

    @GetMapping({"/admin/faqs/{id}/form", "/admin/faqs/form"})
    public String findByIdView(@PathVariable(required = false) Optional<Long> id, Model model) {
        if (id.isPresent()) {
            FaqResponseDto byId = faqService.findById(id.get());
            model.addAttribute("faq", byId);
        } else {
            FaqResponseDto byId = new FaqResponseDto();
            model.addAttribute("faq", byId);
        }

        model.addAttribute("faqCategories", enumFactory.get("faqCategory"));
        return "faq/form";
    }

    @PostMapping(value = "/admin/faqs", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long insert(@RequestBody FaqInsertDto faqInsertDto) {
        return faqService.insert(faqInsertDto);
    }

    @PutMapping(value = "/admin/faqs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody FaqUpdateDto faqUpdateDto) {
        return faqService.update(id, faqUpdateDto);
    }

}

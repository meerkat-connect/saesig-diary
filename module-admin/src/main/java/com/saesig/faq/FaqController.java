package com.saesig.faq;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/faqs")
public class FaqController {
    private final FaqService faqService;
    private final EnumMapperFactory enumFactory;


    @GetMapping("/view")
    public String faqView(Model model) {
        model.addAttribute("faqCategories", enumFactory.get("faqCategory"));
        return "faq/view";
    }

    @GetMapping("")
    @ResponseBody
    public DataTablesResponseDto findAll(@ModelAttribute FaqRequestDto request) {
        return faqService.findAll(request);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FaqResponseDto findById(@PathVariable Long id) {
        return faqService.findById(id);
    }

    @GetMapping({"/{id}/form", "/form"})
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

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long insert(@RequestBody FaqInsertDto faqInsertDto) {
        return faqService.insert(faqInsertDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody FaqUpdateDto faqUpdateDto) {
        return faqService.update(id, faqUpdateDto);
    }

    @DeleteMapping("")
    @ResponseBody
    public void delete(@RequestParam Long[] deleteIds) {
        faqService.delete(deleteIds);
    }

    @PostMapping("/{id}/move")
    @ResponseBody
    public void move(@PathVariable Long id, @RequestParam String mode) {
        faqService.move(id, mode);
    }


}

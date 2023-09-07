package com.saesig.banner;

import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/banner")
public class BannerController {

    private final BannerService bannerService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping("view")
    public String bannerList(Model model) throws Exception {
        return "/banner/bannerList";
    }

    @GetMapping("form")
    public String bannerInsertPopup() throws Exception {
        return "/banner/form";
    }
}

package com.saesig.banner;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.common.Constant;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class BannerController {

    private final BannerService bannerService;

    private final EnumMapperFactory enumMapperFactory;


    @GetMapping({"/admin/banner","/admin/banner/bannerList.html"})
    public String bannerList(Model model) throws Exception {
        return "/banner/bannerList";
    }
}

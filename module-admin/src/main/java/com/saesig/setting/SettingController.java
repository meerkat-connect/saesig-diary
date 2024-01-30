package com.saesig.setting;

import com.saesig.global.file.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class SettingController {
    private final SettingService settingService;

    @Value("${kakao-thumbnail.file-dir}")
    private String kakaoThumbnailFileDir;

    @Value("${favicon.file-dir}")
    private String faviconFileDir;


    @GetMapping("/admin/setting")
    public String settingView(Model model) {
        FileDto kakaoThumbnail = settingService.getKakaoThumbnail();
        FileDto favicon = settingService.getFavicon();

        model.addAttribute("kakaoThumbnail", kakaoThumbnail);
        model.addAttribute("favicon", favicon);

        return "setting/view";
    }

    @PostMapping("/admin/setting/favicon")
    @ResponseBody
    public FileDto saveFavicon(MultipartFile faviconFile) {
        return settingService.saveFavicon(faviconFile);
    }


    @PostMapping("/admin/setting/kakaoThumbnail")
    @ResponseBody
    public FileDto saveKakaoThumbnail(MultipartFile kakaoThumbnail) {
        return settingService.saveKakaoThumbnail(kakaoThumbnail);
    }

    @GetMapping("/admin/setting/kakaoThumbnail")
    @ResponseBody
    public Resource downloadKakoThumbnail() throws MalformedURLException {
        FileDto kakaoThumbnail = settingService.getKakaoThumbnail();
        return new UrlResource("file:" + kakaoThumbnailFileDir + kakaoThumbnail.getSavedName());
    }

    @GetMapping("/admin/setting/favicon")
    @ResponseBody
    public Resource downloadFavicon() throws MalformedURLException {
        FileDto favicon = settingService.getFavicon();
        return new UrlResource("file:" + faviconFileDir + favicon.getSavedName());
    }
}
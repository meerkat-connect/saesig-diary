package com.saesig.setting;

import com.saesig.global.file.FileDto;
import com.saesig.global.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class SettingController {
    @Value("${favicon.file-name}")
    private String faviconFileName;

    @Value("${kakao-thumbnail.file-name}")
    private String kakaoThumbnailFileName;

    private final FileService fileService;

    @GetMapping("/admin/setting")
    public String settingView(Model model) {
        FileDto kakaoThumbnail = fileService.findByOriginName(kakaoThumbnailFileName);
        FileDto faviconFile = fileService.findByOriginName(faviconFileName + ".png");

        model.addAttribute("kakaoThumbnail", kakaoThumbnail);
        model.addAttribute("faviconFile", faviconFile);

        return "setting/view";
    }

    @PostMapping("/admin/setting/save/favicon")
    @ResponseBody
    public FileDto saveFavicon(MultipartFile faviconFile) {
        return fileService.saveFavicon(faviconFile);
    }


    @PostMapping("/admin/setting/save/kakaoThumbnail")
    @ResponseBody
    public FileDto saveKakaoThumbnail(MultipartFile kakaoFile) {
        return fileService.saveKakaoThumbnail(kakaoFile);
    }
}

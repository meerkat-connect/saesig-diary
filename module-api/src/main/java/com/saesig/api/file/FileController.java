package com.saesig.api.file;

import com.saesig.global.file.FileDto;
import com.saesig.global.file.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;

@Tag(name="File Controller", description = "파일 컨트롤러")
//@Hidden
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileController {
    @Value("${file.dir}")
    private String fileDir;

    private final FileService fileService;

    @Operation(summary="이미지 파일 다운로드", description = "이미지 파일을 다운로드 합니다.", tags = {"View"})
    @GetMapping("/images/{fileName}")
    @ResponseBody
    public Resource downloadImageFile(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(fileName));
    }

    @Operation(summary="첨부 파일 다운로드", description = "첨부 파일을 다운로드 합니다.")
    @Parameter(name ="fileName", description = "다운로드 할 파일명")
    @GetMapping("/attach/{fileName}")
    public ResponseEntity<Resource> downloadAttachFile(@PathVariable String fileName) throws MalformedURLException, NoSuchFileException {
        FileDto byName = fileService.findByName(fileName);

        UrlResource resource = new UrlResource("file:" + fileService.getFullPath(byName.getOriginName()));

        log.info("savedName = {}", byName.getSavedName());

        String encodedUploadFileName = UriUtils.encode(byName.getSavedName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @ExceptionHandler(NoSuchFileException.class)
    @ResponseBody
//    @RestControllerAdvice
    public Object noSuchFileException(Exception e) {
        log.info("================ noSuchFileException ============");
        return "noSuchFileException";
    }

    @RequestMapping("/ckeditorFileUpload/imageUpload.do")
    public Map<String, Object> uploadCkeditorFile(@RequestParam Map<String, Object> map, MultipartHttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        MultipartFile file = request.getFile("upload");
        FileDto result = fileService.storeFile(file);
        resultMap.put("uploaded",true);
        resultMap.put("url", "/images/"+result.getSavedName());
        return resultMap;
    }
}

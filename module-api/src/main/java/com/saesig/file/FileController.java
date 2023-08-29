package com.saesig.file;

import com.saesig.global.file.FileDto;
import com.saesig.global.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {
    @Value("${file.dir}")
    private String fileDir;

    private final FileService fileService;

    @GetMapping("/images/{fileName}")
    @ResponseBody
    public Resource downloadImageFile(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(fileName));
    }

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
}

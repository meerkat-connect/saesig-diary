package com.saesig.api.diary;

import com.saesig.api.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.CONTEXT_PATH)
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("diaries")
    public ResponseEntity<List<DiaryDto>> diaries(DiaryDto dd) throws Exception {
        List<DiaryDto> list = diaryService.getDiaries(dd);

        return ResponseEntity.ok(list);
    }
}




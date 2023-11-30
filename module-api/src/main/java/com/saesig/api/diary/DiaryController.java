package com.saesig.api.diary;

import com.saesig.api.util.Constants;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="DiaryController", description = "일상기록 컨트롤러")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.CONTEXT_PATH)
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary="일상기록 목록 조회", description = "")
    @GetMapping("/diaries")
    public ResponseEntity<Map<String, Object>> getDiaries(DiaryDto dd) {
        Map<String, Object> response = new HashMap<>();

        List<DiaryDto> list = diaryService.getDiaries(dd);

        response.put("status", HttpStatus.OK);
        response.put("message", "success");
        response.put("data", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary="일상기록 상세 조회", description = "추가 Diary 정보 => data, 댓글 정보 => comment")
    @GetMapping("/diaries/{id}")
    public ResponseEntity<Map<String, Object>> getDiary(DiaryDto dd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dd.setMember(member);
        DiaryDto info = diaryService.getDiary(dd);
        List<DiaryCommentDto> commentInfo = diaryService.getComment(dd);

        response.put("status", HttpStatus.OK);
        response.put("message", "success");
        response.put("data", info);
        response.put("comment", commentInfo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary="일상기록 등록 ", description = "")
    @PostMapping("/diaries")
    public ResponseEntity<Map<String, Object>> insertDiary(DiaryDto dd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dd.setMember(member);
        int retVal = diaryService.insertDiary(dd);

        response.put("status", HttpStatus.CREATED);
        response.put("message", "success");
        response.put("data", retVal);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary="댓글 수정", description = "")
    @PutMapping("/diaries/{id}")
    public ResponseEntity<Map<String, Object>> updateDiary(DiaryDto dd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dd.setMember(member);
        int retVal = diaryService.updateDiary(dd);

        response.put("status", HttpStatus.NO_CONTENT);
        response.put("message", "success");
        response.put("data", retVal);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Operation(summary="댓글 삭제 ", description = "")
    @DeleteMapping("/diaries/{id}")
    public ResponseEntity<Map<String, Object>> deleteDiary(DiaryDto dd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dd.setMember(member);
        int retVal = diaryService.deleteDiary(dd);

        response.put("status", HttpStatus.NO_CONTENT);
        response.put("message", "success");
        response.put("data", retVal);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Operation(summary="댓글 등록 ", description = "")
    @PostMapping("/comments")
    public ResponseEntity<Map<String, Object>> insertComment(DiaryCommentDto dcd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dcd.setMember(member);
        int retVal = diaryService.insertComment(dcd);

        response.put("status", HttpStatus.CREATED);
        response.put("message", "success");
        response.put("data", retVal);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary="댓글 수정", description = "")
    @PutMapping("/comments/{id}")
    public ResponseEntity<Map<String, Object>> updateComment(DiaryCommentDto dcd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dcd.setMember(member);
        int retVal = diaryService.updateComment(dcd);

        response.put("status", HttpStatus.NO_CONTENT);
        response.put("message", "success");
        response.put("data", retVal);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Operation(summary="댓글 삭제 ", description = "")
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Map<String, Object>> deleteComment(DiaryCommentDto dcd, @LoginMember SessionMember member) {
        Map<String, Object> response = new HashMap<>();

        dcd.setMember(member);
        int retVal = diaryService.deleteComment(dcd);

        response.put("status", HttpStatus.NO_CONTENT);
        response.put("message", "success");
        response.put("data", retVal);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}




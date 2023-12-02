package com.saesig.api.diary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryMapper diaryMapper;

    @Override
    public List<DiaryDto> getDiaries(DiaryDto dd) {
        return diaryMapper.getDiaries(dd);
    }

    @Override
    @Transactional
    public int insertDiaryLike(DiaryDto dd) {
        return diaryMapper.insertDiaryLike(dd);
    }

    @Override
    @Transactional
    public int deleteDiaryLike(DiaryDto dd) {
        return diaryMapper.deleteDiaryLike(dd);
    }

    @Override
    public DiaryDto getDiary(DiaryDto dd) {
        return diaryMapper.getDiary(dd);
    }

    @Override
    @Transactional
    public int insertDiary(DiaryDto dd) {
        int retVal = 0;
        retVal += diaryMapper.insertDiary(dd);
        retVal += diaryMapper.deleteDiaryTag(dd);
        retVal += diaryMapper.insertDiaryTag(dd);
        return retVal;
    }

    @Override
    @Transactional
    public int updateDiary(DiaryDto dd) {
        int retVal = 0;
        retVal += diaryMapper.updateDiary(dd);
        retVal += diaryMapper.deleteDiaryTag(dd);
        retVal += diaryMapper.insertDiaryTag(dd);
        return retVal;
    }

    @Override
    @Transactional
    public int deleteDiary(DiaryDto dd) {
        return diaryMapper.deleteDiary(dd);
    }

    @Override
    public List<DiaryCommentDto> getComment(DiaryDto dd) {
        return diaryMapper.getComment(dd);
    }

    @Override
    @Transactional
    public int insertComment(DiaryCommentDto dcd) {
        return diaryMapper.insertComment(dcd);
    }

    @Override
    @Transactional
    public int insertCommentLike(DiaryCommentDto dcd) {
        return diaryMapper.insertCommentLike(dcd);
    }

    @Override
    @Transactional
    public int deleteCommentLike(DiaryCommentDto dcd) {
        return diaryMapper.deleteCommentLike(dcd);
    }

    @Override
    @Transactional
    public int updateComment(DiaryCommentDto dcd) {
        return diaryMapper.updateComment(dcd);
    }

    @Override
    @Transactional
    public int deleteComment(DiaryCommentDto dcd) {
        return diaryMapper.deleteComment(dcd);
    }

    @Override
    @Transactional
    public int insertDiaryReport(DiaryDto dd) {
        return diaryMapper.insertDiaryReport(dd);
    }

}

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
    public DiaryDto getDiary(DiaryDto dd) {
        return diaryMapper.getDiary(dd);
    }

    @Override
    @Transactional
    public int insertDiary(DiaryDto dd) {
        int retVal = 0;
        retVal += diaryMapper.insertDiary(dd);
        retVal += diaryMapper.insertDiaryTag(dd);
        return retVal;
    }

    @Override
    public int updateDiary(DiaryDto dd) {
        return diaryMapper.updateDiary(dd);
    }

    @Override
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
    public int updateComment(DiaryCommentDto dcd) {
        return diaryMapper.updateComment(dcd);
    }

    @Override
    @Transactional
    public int deleteComment(DiaryCommentDto dcd) {
        return diaryMapper.deleteComment(dcd);
    }

}

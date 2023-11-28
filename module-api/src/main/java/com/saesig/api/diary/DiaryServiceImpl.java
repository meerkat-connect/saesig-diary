package com.saesig.api.diary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryMapper diaryMapper;

    @Override
    public List<DiaryDto> getDiaries(DiaryDto dd) {
        return diaryMapper.getDiaries(dd);
    }
}

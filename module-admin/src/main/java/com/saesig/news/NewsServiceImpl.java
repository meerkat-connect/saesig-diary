package com.saesig.news;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;

    @Override
    public List<NewsDto> getNewsList(Map<String, Object> param) throws Exception {
        return newsMapper.getNewsList(param);
    }

    @Override
    public boolean InsertAnswer(NewsAnswerDto param) throws Exception {
        newsMapper.insertAnswer(param);
        newsMapper.updateNewsAnswerStatus(param);
        return true;
    }

    @Override
    public List<NewsAnswerDto> selectAnswerById(Long id) throws Exception {
        return newsMapper.selectAnswerById(id);
    }
}
package com.saesig.news;

import java.util.List;
import java.util.Map;

public interface NewsService {

    public List<NewsDto> getNewsList(Map<String, Object> param) throws Exception;

    public boolean InsertAnswer(NewsAnswerDto param) throws Exception;

    public List<NewsAnswerDto> selectAnswerById(Long id) throws Exception;

}

package com.saesig.news;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsMapper {

    public List<NewsDto> getNewsList(Map<String, Object> param);

    public void insertNews(NewsDto param);

    public void updateNews(NewsDto param);

    public List<NewsAnswerDto> selectAnswerById(Long id);
}

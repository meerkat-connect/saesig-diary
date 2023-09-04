package com.saesig.news;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsMapper {

    public List<NewsDto> getNewsList(Map<String, Object> param);

    public void insertNews(NewsDto param);

    public void updateNews(NewsDto param);

    int deleteNews(@Param("ids") Long[] ids);

    List<NewsAnswerDto> selectAnswerById(Long id);
}

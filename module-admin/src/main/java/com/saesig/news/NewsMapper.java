package com.saesig.news;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsMapper {

    public List<NewsDto> getNewsList(NewsDto param);

    public void insertNews(NewsDto param);

    public void updateNews(NewsDto param);

    int deleteNews(@Param("ids") Long[] ids);

    NewsDto selectNewsById(NewsDto param);
}

package com.saesig.news;

import java.util.List;
import java.util.Map;

public interface NewsService {

    public List<NewsDto> getNewsList(NewsDto param) throws Exception;

    public boolean InsertNews(NewsDto param) throws Exception;

    public boolean UpdateNews(NewsDto param) throws Exception;

    public int DeleteNews(Long[] ids) throws Exception;

    public NewsDto selectNewsById(NewsDto param) throws Exception;

}

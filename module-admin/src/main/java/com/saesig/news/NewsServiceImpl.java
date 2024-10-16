package com.saesig.news;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    @Autowired
    private final NewsMapper newsMapper;

    @Override
    public List<NewsDto> getNewsList(NewsDto param) throws Exception {
        return newsMapper.getNewsList(param);
    }

    @Override
    public boolean InsertNews(NewsDto param) throws Exception {
        newsMapper.insertNews(param);
        return true;
    }

    @Override
    public boolean UpdateNews(NewsDto param) throws Exception {
        newsMapper.updateNews(param);
        return true;
    }

    @Override
    public int DeleteNews(Long[] ids) throws Exception {
        return newsMapper.deleteNews(ids);
    };

    @Override
    public NewsDto selectNewsById(NewsDto param) throws Exception{
        return newsMapper.selectNewsById(param);
    };

}
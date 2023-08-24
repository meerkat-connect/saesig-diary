package com.saesig.news;

import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.news.NewsCategory;
import com.saesig.global.enumCode.EnumMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class NewsController {
    private final EnumMapperFactory enumFactory;

    @Autowired
    private NewsService newsService;

    @GetMapping({"/admin/news/view", "/admin/news/newsList.html"})
    public String view(Model model, @LoginMember SessionMember user) {
//        String currentNickName = user.getNickname();
//        model.addAttribute("loginSession", currentNickName);
        model.addAttribute("newsCategories", enumFactory.get("newsCategory"));
        return "news/newsList";
    }

    @RequestMapping(value = "/admin/news/getNewsList.do")
    @ResponseBody
    public Map<String,Object> getNewsList(@RequestParam Map<String, Object> param) throws Exception {
        List<NewsDto> result = newsService.getNewsList(param);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",result);
        return resultMap;
    }

    @RequestMapping(value = "/admin/news/getFilterData.do")
    @ResponseBody
    public Map<String, Object> getNewsEnum() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Map<String,Map<String,String>> NewsStatusMap = new HashMap<>();
        NewsCategory[] newsCategory = NewsCategory.values();
        Map<String,Map<String,String>> NewsCategoryMap = new HashMap<>();
        resultMap.put("NewsStatus",NewsStatusMap);
        resultMap.put("NewsCategory",NewsCategoryMap);
        return resultMap;
    }

    @RequestMapping(value = "/admin/news/insertNews.do")
    @ResponseBody
    public boolean insertNews(@RequestBody NewsDto param) throws Exception {
        boolean result = newsService.InsertNews(param);
        return result;
    }

    @RequestMapping(value = "/admin/news/UpdateNews.do")
    @ResponseBody
    public boolean UpdateNews(@RequestBody NewsDto param) throws Exception {
        boolean result = newsService.UpdateNews(param);
        return result;
    }

}

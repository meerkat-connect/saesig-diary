package com.saesig.news;

import com.saesig.common.mybatis.DataTablesDto;
import com.saesig.config.auth.LoginMember;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.common.Constant;
import com.saesig.domain.news.NewsCategory;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.inquiry.InquiryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/admin/news")
@RequiredArgsConstructor
@Controller
public class NewsController {
    private final EnumMapperFactory enumFactory;

    @Autowired
    private NewsService newsService;

    @GetMapping({"/view", "/newsList.html"})
    public String view(Model model, @LoginMember SessionMember user) {
        model.addAttribute("loginSession", user);
        model.addAttribute("newsCategories", enumFactory.get("newsCategory"));
        return "news/newsList";
    }

    @RequestMapping(value = "/getNewsList.do")
    @ResponseBody
    public DataTablesDto getNewsList(NewsDto param) throws Exception {
        DataTablesDto dtd = new DataTablesDto();
        List<NewsDto> list = newsService.getNewsList(param);
        dtd.setData(list);
        dtd.setDraw(param.getDraw());
        if (list.size() > 0){
            dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
            dtd.setRecordsTotal(list.get(0).getRecordsTotal());
        }else{
            dtd.setRecordsFiltered(0);
            dtd.setRecordsTotal(0);
        }
        return dtd;
    }

    @RequestMapping(value = "/insertNews.do")
    @ResponseBody
    public boolean insertNews(@RequestBody NewsDto param) throws Exception {
        boolean result = newsService.InsertNews(param);
        return result;
    }

    @RequestMapping(value = "/UpdateNews.do")
    @ResponseBody
    public boolean UpdateNews(@RequestBody NewsDto param) throws Exception {
        boolean result = newsService.UpdateNews(param);
        return result;
    }

    @DeleteMapping("/deleteNews.do")
    @ResponseBody
    public Map<String, Object> deleteItems(@LoginMember SessionMember member, @RequestParam Long[] ids) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        int retVal = 0;
        retVal = newsService.DeleteNews(ids);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }

    @GetMapping({"/getNewsById.do"})
    public String getNewsById(Model model, NewsDto param) throws Exception{
        model.addAttribute("newsCategories", enumFactory.get("newsCategory"));
        model.addAttribute("news", newsService.selectNewsById(param));
        return "news/newsForm";
    }

    @GetMapping({"/getNewsForm.do"})
    public String getNewsForm(Model model) throws Exception{
        model.addAttribute("news", new NewsDto());
        return "news/newsForm.html";
    }

}



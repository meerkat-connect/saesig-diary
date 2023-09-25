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

@RequiredArgsConstructor
@Controller
public class NewsController {
    private final EnumMapperFactory enumFactory;

    @Autowired
    private NewsService newsService;

    @GetMapping({"/admin/news/view", "/admin/news/newsList.html"})
    public String view(Model model, @LoginMember SessionMember user) {
        model.addAttribute("loginSession", user);
        model.addAttribute("newsCategories", enumFactory.get("newsCategory"));
        return "news/newsList";
    }

    @RequestMapping(value = "/admin/news/getNewsList.do")
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

    @DeleteMapping("/admin/news/deleteNews.do")
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

}

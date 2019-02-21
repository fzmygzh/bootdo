package com.bootdo.homepage;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * author:丁锋
 * Date:2018/11/5
 * Time:10:34
 */
@RequestMapping("/hotnewspage")
@Controller
public class hotNewsPageController {
    @Autowired
    ContentService bContentService;


    @GetMapping()
    String hotnewspage() {
        return "blog/hotNews/main";
    }
    @ResponseBody
    @GetMapping("/list")
    public PageUtils opentList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        query.put("hotNews","0");
        List<ContentDO> bContentList = bContentService.list(query);
        int total = bContentService.count(query);
        PageUtils pageUtils = new PageUtils(bContentList, total);
        return pageUtils;
    }



}

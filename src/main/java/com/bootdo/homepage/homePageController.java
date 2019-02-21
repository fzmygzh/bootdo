package com.bootdo.homepage;

/**
 * author:丁锋
 * Date:2018/10/31
 * Time:14:41
 */

import com.bootdo.common.controller.BaseController;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequestMapping("/homepage")
@Controller
public class homePageController  {
    //项目外界访问系统首页
    @GetMapping()
    String homepage() {
        return "blog/index/homepage";
    }
    @Configuration
    public class DefaultView extends WebMvcConfigurerAdapter {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("blog/index/homepage");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
            super.addViewControllers(registry);
        }
    }


}

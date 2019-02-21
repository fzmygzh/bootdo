package com.bootdo.homepage;

import com.bootdo.artist.domain.InfoDO;
import com.bootdo.artist.service.InfoService;
import com.bootdo.artwork.domain.ArtworkDO;
import com.bootdo.artwork.service.ArtworkService;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:丁锋  用于外界访问艺术家世界的相关信息
 * Date:2018/11/1
 * Time:10:33
 */
@RequestMapping("/artistpage")
@Controller
public class artistPageController {
    @Autowired
    private InfoService infoService;
    @Autowired
    FileService fileService;
    @Autowired
    private ArtworkService artworkService;

    @GetMapping()
    String page(){
        return "artist/info/pageInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        /*Integer artstate=0;
        params.put("artstate",artstate);*/
        Query query = new Query(params);
        query.put("artstate","0");
        List<InfoDO> infoList = infoService.list(query);
        for (int i = 0; i < infoList.size(); i++)
            if (!"".equals(infoList.get(i).getArtistpic()) && null != infoList.get(i).getArtistpic() && infoList.get(i).getArtistpic().length() > 0) {
                Long fileid = Long.valueOf(infoList.get(i).getArtistpic()).longValue();
                FileDO fileDO = fileService.get(fileid);
                if (fileDO != null) {
                    infoList.get(i).setArtistpic(fileDO.getUrl());
                }

            }
        int total = infoService.count(query);
        PageUtils pageUtils = new PageUtils(infoList, total);
        return pageUtils;
    }
    @GetMapping("/artistWorks/{id}")
    String artistFile(@PathVariable("id")String id , Model model) {
        Map<String, Object> params = new HashMap<>(16);
        InfoDO info = infoService.get(id);
        model.addAttribute("info", info);
        params.put("id",id);
        return "artist/info/pageArtistImgList";
    }
    //用于展示艺术家下详细信息
    @GetMapping("/view/{id}")
    String edit(@PathVariable("id") String id,Model model){
        InfoDO info = infoService.get(id);
        if(info.getArtistsex()==1){
            info.setArtistphone("女");//暂时用于存储性别
        }else{
            info.setArtistphone("男");//暂时用于存储性别
        }
        if(("0").equals(info.getArtisttype())){
            info.setArtisttype("美术家");
        }else if(("1").equals(info.getArtisttype())){
            info.setArtisttype("收藏家");
        }else{
            info.setArtisttype("艺术家");
        }
        if(info.getArtistpic()!=null&&!"".equals(info.getArtistpic())){
            Long fileid = Long.valueOf(info.getArtistpic()).longValue();
            FileDO fileDO = fileService.get(fileid);
            if(fileDO!=null){
                info.setArtistpic(fileDO.getUrl());
            }else{
                info.setArtistpic("/img/moren.jpg");
            }

        }else{
            info.setArtistpic("/img/moren.jpg");
        }
        model.addAttribute("info", info);
        return "artist/info/artistIofo";
    }
    @ResponseBody
    @GetMapping("/artistImgList")
    PageUtils artistImgList(@RequestParam Map<String, Object> params,HttpServletRequest request) {
        //查询列表数据
        String artistid=request.getParameter("artistid");
        Query query = new Query(params);
        query.put("artistid",artistid);
        query.put("status","0");
        List<ArtworkDO> artworkList = artworkService.list(query);
        for (int i = 0; i < artworkList.size(); i++) {
            if (!"".equals(artworkList.get(i).getPicstr()) && (artworkList.get(i).getPicstr() != null)) {
                Long fileid = Long.valueOf(artworkList.get(i).getPicstr()).longValue();
                FileDO fileDO = fileService.get(fileid);
                if(fileDO!=null){
                    artworkList.get(i).setPicstr(fileDO.getUrl());
                }else{
                    artworkList.get(i).setPicstr("/img/moren.jpg");
                }

            }else{
                artworkList.get(i).setPicstr("/img/moren.jpg");
            }

        }
        int total = artworkService.count(query);
        PageUtils pageUtils = new PageUtils(artworkList, total);
        return pageUtils;
    }
}

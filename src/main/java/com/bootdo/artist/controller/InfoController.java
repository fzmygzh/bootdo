package com.bootdo.artist.controller;

import java.util.*;

import com.bootdo.artwork.domain.ArtworkDO;
import com.bootdo.artwork.service.ArtworkService;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.IDutil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.artist.domain.InfoDO;
import com.bootdo.artist.service.InfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 艺术家信息表
 *
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-11 15:37:02
 */

@Controller
@RequestMapping("/artist/info")
public class InfoController {
	@Autowired
	private InfoService infoService;
	@Autowired
	FileService fileService;
	@Autowired
	private ArtworkService artworkService;

	@GetMapping()
	@RequiresPermissions("artist:info:info")
	String Info(){
	    return "artist/info/info";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("artist:info:info")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
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
	@ResponseBody
	@GetMapping("/allList")
	public List<InfoDO> allList(){
		//查询列表数据
		Map<String, Object> map = new HashMap<>();
		List<InfoDO> infoList = infoService.list(map);
		return infoList;
	}

	@GetMapping("/add")
	@RequiresPermissions("artist:info:add")
	String add(){
	    return "artist/info/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("artist:info:edit")
	String edit(@PathVariable("id") String id,Model model){
		InfoDO info = infoService.get(id);
		model.addAttribute("info", info);
	    return "artist/info/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("artist:info:add")
	public R save( InfoDO info){
		IDutil  iDutil;
		iDutil = new IDutil();
		String id=iDutil.getIDutilHashString();
		info.setId(id);
		if(infoService.save(info)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("artist:info:edit")
	public R update( InfoDO info){
		infoService.update(info);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("artist:info:remove")
	public R remove( String id){
		if(infoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("artist:info:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		infoService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/chanagepic/{id}")
	@RequiresPermissions("artist:info:edit")
	String chanagepic(@PathVariable("id") String id,Model model){
		InfoDO info = infoService.get(id);
		model.addAttribute("info", info);
		return "artist/info/changepic";
	}
    @ResponseBody
    @PostMapping("/updateArtistImg")
	@RequiresPermissions("artist:info:edit")
    R updateArtistImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		String id=request.getParameter("infoid");
        Map<String, Object> result = new HashMap<>();
        try {
          result = infoService.updateArtistImg(file, avatar_data, id);
        } catch (Exception e) {
            return R.error("更新图像失败！");
        }
        if(result!=null && result.size()>0){
            return R.ok(result);
        }else {
            return R.error("更新图像失败！");
        }
    }

	@GetMapping("/artistFile/{id}")

	String artistFile(@PathVariable("id")String id ,Model model) {
		Map<String, Object> params = new HashMap<>(16);
		InfoDO info = infoService.get(id);
		model.addAttribute("info", info);
		params.put("id",id);
		return "artist/info/artistImgList";
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
				}

			}

		}
		int total = artworkService.count(query);
		PageUtils pageUtils = new PageUtils(artworkList, total);
		return pageUtils;
	}

}

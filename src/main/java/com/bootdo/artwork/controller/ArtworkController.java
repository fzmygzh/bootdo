package com.bootdo.artwork.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.artist.domain.InfoDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
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

import com.bootdo.artwork.domain.ArtworkDO;
import com.bootdo.artwork.service.ArtworkService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 艺术作品表
 * 
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-18 10:31:36
 */
 
@Controller
@RequestMapping("/artwork/artwork")
public class ArtworkController {
	@Autowired
	private ArtworkService artworkService;

	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	FileService fileService;
	@GetMapping()
	@RequiresPermissions("artwork:artwork:artwork")
	String Artwork(){
	    return "artwork/artwork";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("artwork:artwork:artwork")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ArtworkDO> artworkList = artworkService.list(query);
		for (int i = 0; i < artworkList.size(); i++) {
			if (!"".equals(artworkList.get(i).getPicstr()) && artworkList.get(i).getPicstr()!=null) {
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
	
	@GetMapping("/add")
	@RequiresPermissions("artwork:artwork:add")
	String add(){
	    return "artwork/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("artwork:artwork:edit")
	String edit(@PathVariable("id") String id,Model model){
		ArtworkDO artwork = artworkService.get(id);
		model.addAttribute("artwork", artwork);
	    return "artwork/edit";
	}
	@GetMapping("/chanagepic/{id}")
	@RequiresPermissions("artwork:artwork:edit")
	String chanagepic(@PathVariable("id") String id,Model model){
		ArtworkDO artworkDO = artworkService.get(id);
		model.addAttribute("artwork", artworkDO);
		return "artwork/changepic";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("artwork:artwork:add")
	public R save( ArtworkDO artwork){
		IDutil iDutil=new IDutil();
		String id=iDutil.getIDutilHashString();
		artwork.setId(id);
		artwork.setCreattime(new Date());
		if(artworkService.save(artwork)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("artwork:artwork:edit")
	public R update( ArtworkDO artwork){
		artworkService.update(artwork);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("artwork:artwork:remove")
	public R remove( String id){
		if(artworkService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("artwork:artwork:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		artworkService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@PostMapping("/uploadArtImg")
	@RequiresPermissions("artwork:artwork:edit")
	R uploadArtImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		String id=request.getParameter("artwordid");
		Map<String, Object> result = new HashMap<>();
		try {
			result = artworkService.uploadArtImg(file, avatar_data, id);
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}

}

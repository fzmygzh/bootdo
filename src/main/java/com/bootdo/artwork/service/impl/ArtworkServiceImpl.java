package com.bootdo.artwork.service.impl;

import com.bootdo.artist.domain.InfoDO;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.artwork.dao.ArtworkDao;
import com.bootdo.artwork.domain.ArtworkDO;
import com.bootdo.artwork.service.ArtworkService;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;


@Service
public class ArtworkServiceImpl implements ArtworkService {
	@Autowired
	private ArtworkDao artworkDao;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@Override
	public ArtworkDO get(String id){
		return artworkDao.get(id);
	}
	
	@Override
	public List<ArtworkDO> list(Map<String, Object> map){
		return artworkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return artworkDao.count(map);
	}
	
	@Override
	public int save(ArtworkDO artwork){
		return artworkDao.save(artwork);
	}
	
	@Override
	public int update(ArtworkDO artwork){
		return artworkDao.update(artwork);
	}
	
	@Override
	public int remove(String id){
		return artworkDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return artworkDao.batchRemove(ids);
	}

	@Override
	public Map<String, Object> uploadArtImg(MultipartFile file, String avatar_data, String id) throws Exception {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		//获取图片后缀
		String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
		String[] str = avatar_data.split(",");
		//获取截取的x坐标
		int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
		//获取截取的y坐标
		int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
		//获取截取的高度
		int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
		//获取截取的宽度
		int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
		//获取旋转的角度
		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
		try {
			BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
			BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(rotateImage, prefix, out);
			//转换后存入数据库
			byte[] b = out.toByteArray();
			FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			throw new Exception("图片裁剪错误！！");
		}
		Map<String, Object> result = new HashMap<>();
		if (sysFileService.save(sysFile) > 0) {
			ArtworkDO artwork=new ArtworkDO();
			artwork.setId(id);
			String picid=Long.toString(sysFile.getId());
			artwork.setPicstr(picid);
			if (artworkDao.update(artwork) > 0) {
				result.put("url", sysFile.getUrl());
			}
		}
		return result;
	}

}

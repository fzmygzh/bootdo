package com.bootdo.artist.service;

import com.bootdo.artist.domain.InfoDO;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;
import java.util.List;
import java.util.Map;

/**
 * 艺术家信息表
 * 
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-11 15:37:02
 */
public interface InfoService {
	
	InfoDO get(String id);
	
	List<InfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InfoDO info);
	
	int update(InfoDO info);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	Map<String,Object> updateArtistImg(MultipartFile file,String avatar_data,String id) throws Exception;
}

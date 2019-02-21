package com.bootdo.artwork.service;

import com.bootdo.artwork.domain.ArtworkDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 艺术作品表
 * 
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-18 10:31:36
 */
public interface ArtworkService {
	
	ArtworkDO get(String id);
	
	List<ArtworkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ArtworkDO artwork);
	
	int update(ArtworkDO artwork);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	Map<String,Object> uploadArtImg(MultipartFile file, String avatar_data, String id) throws Exception;
}

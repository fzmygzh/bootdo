package com.bootdo.artwork.dao;

import com.bootdo.artwork.domain.ArtworkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 艺术作品表
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-18 10:31:36
 */
@Mapper
public interface ArtworkDao {

	ArtworkDO get(String id);
	
	List<ArtworkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ArtworkDO artwork);
	
	int update(ArtworkDO artwork);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

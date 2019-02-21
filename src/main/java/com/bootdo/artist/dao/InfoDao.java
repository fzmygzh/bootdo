package com.bootdo.artist.dao;

import com.bootdo.artist.domain.InfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 艺术家信息表
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-11 15:37:02
 */
@Mapper
public interface InfoDao {

	InfoDO get(String id);
	
	List<InfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InfoDO info);
	
	int update(InfoDO info);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<InfoDO> allList();
}

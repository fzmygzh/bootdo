package com.bootdo.artwork.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 艺术作品表
 * 
 * @author fzmy
 * @email fzmygzh@163.com
 * @date 2018-10-26 21:09:21
 */
public class ArtworkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//关联艺术家ID
	private String artistid;
	//创建时间
	private Date creattime;
	//图片地址，多张
	private String picstr;
	//标题
	private String title;
	//文件类型   用于排队
	private String type;
	//作品描述
	private String disc;
	//奖状
	private String award;
	//状态
	private String status;
	//艺术家艺名
	private String artistartname;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：关联艺术家ID
	 */
	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}
	/**
	 * 获取：关联艺术家ID
	 */
	public String getArtistid() {
		return artistid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreattime() {
		return creattime;
	}
	/**
	 * 设置：图片地址，多张
	 */
	public void setPicstr(String picstr) {
		this.picstr = picstr;
	}
	/**
	 * 获取：图片地址，多张
	 */
	public String getPicstr() {
		return picstr;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：文件类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：文件类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：作品描述
	 */
	public void setDisc(String disc) {
		this.disc = disc;
	}
	/**
	 * 获取：作品描述
	 */
	public String getDisc() {
		return disc;
	}
	/**
	 * 设置：奖状
	 */
	public void setAward(String award) {
		this.award = award;
	}
	/**
	 * 获取：奖状
	 */
	public String getAward() {
		return award;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	public String getArtistartname() {
		return artistartname;
	}

	public void setArtistartname(String artistartname) {
		this.artistartname = artistartname;
	}
}

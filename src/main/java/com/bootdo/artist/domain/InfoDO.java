package com.bootdo.artist.domain;

import com.bootdo.artwork.domain.ArtworkDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 艺术家信息表fzmy
 * 
 * @author
 * @email fzmygzh@163.com
 * @date 2018-10-11 15:37:02
 */
public class InfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//艺术家姓名
	private String artistname;
	//艺术家艺名
	private String artistartname;
	//艺术家联系方式
	private String artistphone;
	//艺术家邮箱
	private String artistemil;
	//艺术家车牌号
	private String artistplatenumber;
	//艺术家年龄
	private Integer artistage;
	//艺术家生日
	private Date artistdate;
	//艺术家性别 0:男；1女；2其他
	private Integer artistsex;
	//艺术家称誉
	private String artistprise;
	//艺术家种类
	private String artisttype;
	//艺术家描述
	private String artistdisc;
	//艺术家图片
	private String artistpic;
	//关联艺术品id，，用于排序
	private String artwork;
	//艺术家入驻时间
	private Date artistindate;
	//艺术家状态，0启用；1停用
	private Integer artstate;
	//艺术家作品
	private ArtworkDO artworkDO;
	//头像地址
	private  String url;

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
	 * 设置：艺术家姓名
	 */
	public void setArtistname(String artistname) {
		this.artistname = artistname;
	}
	/**
	 * 获取：艺术家姓名
	 */
	public String getArtistname() {
		return artistname;
	}
	/**
	 * 设置：艺术家艺名
	 */
	public void setArtistartname(String artistartname) {
		this.artistartname = artistartname;
	}
	/**
	 * 获取：艺术家艺名
	 */
	public String getArtistartname() {
		return artistartname;
	}
	/**
	 * 设置：艺术家联系方式
	 */
	public void setArtistphone(String artistphone) {
		this.artistphone = artistphone;
	}
	/**
	 * 获取：艺术家联系方式
	 */
	public String getArtistphone() {
		return artistphone;
	}
	/**
	 * 设置：艺术家邮箱
	 */
	public void setArtistemil(String artistemil) {
		this.artistemil = artistemil;
	}
	/**
	 * 获取：艺术家邮箱
	 */
	public String getArtistemil() {
		return artistemil;
	}
	/**
	 * 设置：艺术家车牌号
	 */
	public void setArtistplatenumber(String artistplatenumber) {
		this.artistplatenumber = artistplatenumber;
	}
	/**
	 * 获取：艺术家车牌号
	 */
	public String getArtistplatenumber() {
		return artistplatenumber;
	}
	/**
	 * 设置：艺术家年龄
	 */
	public void setArtistage(Integer artistage) {
		this.artistage = artistage;
	}
	/**
	 * 获取：艺术家年龄
	 */
	public Integer getArtistage() {
		return artistage;
	}
	/**
	 * 设置：艺术家生日
	 */
	public void setArtistdate(Date artistdate) {
		this.artistdate = artistdate;
	}
	/**
	 * 获取：艺术家生日
	 */
	public Date getArtistdate() {
		return artistdate;
	}
	/**
	 * 设置：艺术家性别 0:男；1女；2其他
	 */
	public void setArtistsex(Integer artistsex) {
		this.artistsex = artistsex;
	}
	/**
	 * 获取：艺术家性别 0:男；1女；2其他
	 */
	public Integer getArtistsex() {
		return artistsex;
	}
	/**
	 * 设置：艺术家称誉
	 */
	public void setArtistprise(String artistprise) {
		this.artistprise = artistprise;
	}
	/**
	 * 获取：艺术家称誉
	 */
	public String getArtistprise() {
		return artistprise;
	}
	/**
	 * 设置：艺术家种类
	 */
	public void setArtisttype(String artisttype) {
		this.artisttype = artisttype;
	}
	/**
	 * 获取：艺术家种类
	 */
	public String getArtisttype() {
		return artisttype;
	}
	/**
	 * 设置：艺术家描述
	 */
	public void setArtistdisc(String artistdisc) {
		this.artistdisc = artistdisc;
	}
	/**
	 * 获取：艺术家描述
	 */
	public String getArtistdisc() {
		return artistdisc;
	}
	/**
	 * 设置：艺术家图片
	 */
	public void setArtistpic(String artistpic) {
		this.artistpic = artistpic;
	}
	/**
	 * 获取：艺术家图片
	 */
	public String getArtistpic() {
		return artistpic;
	}
	/**
	 * 设置：关联艺术品id
	 */
	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}
	/**
	 * 获取：关联艺术品id
	 */
	public String getArtwork() {
		return artwork;
	}
	/**
	 * 设置：艺术家入驻时间
	 */
	public void setArtistindate(Date artistindate) {
		this.artistindate = artistindate;
	}
	/**
	 * 获取：艺术家入驻时间
	 */
	public Date getArtistindate() {
		return artistindate;
	}
	/**
	 * 设置：艺术家状态，0启用；1停用
	 */
	public void setArtstate(Integer artstate) {
		this.artstate = artstate;
	}
	/**
	 * 获取：艺术家状态，0启用；1停用
	 */
	public Integer getArtstate() {
		return artstate;
	}

	public ArtworkDO getArtworkDO() {
		return artworkDO;
	}

	public void setArtworkDO(ArtworkDO artworkDO) {
		this.artworkDO = artworkDO;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

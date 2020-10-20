package com.qa.practise.pojoObject;

import java.util.List;

public class YoutubePojo {
	
	private String kind;
	private String etag;
	private String nextPageToken;
	private String regionCode;
	private PageInfo pageInfo;
	private List<Items> items;
	
	public YoutubePojo() {
		//This is Default constructor needed for De-serialization
	}
	
	public YoutubePojo(String kind, String etag, String nextPageToken, String regionCode, PageInfo pageInfo, List<Items> items) {
		this.kind = kind;
		this.etag = etag;
		this.nextPageToken = nextPageToken;
		this.regionCode = regionCode;
		this.pageInfo = pageInfo;
		this.items = items;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	

}

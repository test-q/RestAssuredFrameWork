package com.qa.practise.pojoObject;

public class Id {

	private String kind;
	private String channelId;
	private String videoId;
	
	public Id() {
		//This is Default constructor needed for De-serialization
	}
	public Id(String kind, String channelId, String videoId) {
		this.kind = kind;
		this.channelId = channelId;
		this.videoId = videoId;
	}
	
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}	
	
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}	
}

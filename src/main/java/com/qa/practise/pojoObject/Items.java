package com.qa.practise.pojoObject;

public class Items {
	private String kind;
	private String etag;
	private Id id;

	public Items() {
		//This is Default constructor needed for De-serialization
	}
	public Items(String kind,String etag, Id id) {
		this.kind = kind;
		this.etag = etag;
		this.id = id;
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
	
	
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	
	
}

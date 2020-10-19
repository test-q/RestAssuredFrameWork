package com.qa.practise.pojoObject;

public class ExampleOnePojo {
	private String id;
	private String type;
	private String name;
	private Image image;
	private Thumbnail thumbnail;
	
	//Covert Pojo to json => Serialization
	//Image  Thumbnail and FinalPayload this classes are used.
	
	public ExampleOnePojo() {
		// This default constructor created for de-serialization.
	}
	
	public ExampleOnePojo(String id, String type, String name, Image image, Thumbnail thumbnail) {
		this.id= id;
		this.type = type;
		this.name = name;
		this.image = image;
		this.thumbnail = thumbnail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	

}

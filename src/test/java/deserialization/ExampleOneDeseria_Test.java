package deserialization;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.gorest.util.TestUtil;
import com.qa.practise.pojoObject.ExampleOnePojo;

public class ExampleOneDeseria_Test {
	String filePath = "src/main/java/com/qa/practise/json/exampleOne.json";
	ExampleOnePojo exOne = null; 
	
	
	@Test
	public void deserializOne_Test() {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			exOne = mapper.readValue(new File(filePath),ExampleOnePojo.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(exOne);
		
		System.out.println(exOne.getId());
		System.out.println(exOne.getName());
		System.out.println(exOne.getType());
		System.out.println();
		System.out.println("Image URL: " + exOne.getImage().getUrl());
		System.out.println("Image width: " + exOne.getImage().getWidth());
		System.out.println("Image height: " + exOne.getImage().getHeight());
		System.out.println();
		System.out.println("Thumbnail URL: " + exOne.getThumbnail().getUrl());
		System.out.println("Thumbnail width: " + exOne.getThumbnail().getWidth());
		System.out.println("Thumbnail height: " + exOne.getThumbnail().getHeight());
	}

}

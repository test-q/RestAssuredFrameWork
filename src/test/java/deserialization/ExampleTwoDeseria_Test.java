package deserialization;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.practise.pojoObject.ExampleOnePojo;
import com.qa.practise.pojoObject.ExampleTwoPojo;

public class ExampleTwoDeseria_Test {
	String filePath = "src/main/java/com/qa/practise/json/exampleTwo.json";
	ExampleTwoPojo exTwo = null; 
	
	@Test
	public void deserializTwo_Test() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			exTwo = mapper.readValue(new File(filePath), ExampleTwoPojo.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(exTwo);
		System.out.println("Id: " +exTwo.getId());
		System.out.println("Type: " +exTwo.getType());
		System.out.println("Name: " +exTwo.getName());
		System.out.println("PPU: " +exTwo.getPpu());
		System.out.println();
		
		int batterSize = exTwo.getBatters().getBatter().size();
		for(int i=0 ; i<batterSize; i++ ) {
			System.out.println("Batter Id " + i + " : "+exTwo.getBatters().getBatter().get(i).getId());
			System.out.println("Batter Type " + i + " : "+exTwo.getBatters().getBatter().get(i).getType());
		}
		System.out.println();
		
		int toppingSize = exTwo.getTopping().size();
		for(int i=0 ; i<toppingSize; i++) {
			System.out.println("Topping Id " + i + " : "+exTwo.getTopping().get(i).getId());
			System.out.println("Topping Type " + i + " : "+exTwo.getTopping().get(i).getType());
		}
	
	}

}

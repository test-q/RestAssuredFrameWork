package serialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.gorest.util.TestUtil;
import com.qa.practise.pojoObject.ExampleOnePojo;
import com.qa.practise.pojoObject.Image;
import com.qa.practise.pojoObject.Thumbnail;

//Covert object to json => Serialization
public class ExampleOneSeria_Test {
	String jsonPayload;
	@Test
	public void exampleOne_Test() {
		
	Image img = new Image("images/0001.jpg", 200, 200);
	Thumbnail thumb = new Thumbnail("images/thumbnails/0001.jpg", 32, 32);
	
	ExampleOnePojo pojoObj = new ExampleOnePojo("001", "donut", "Cake", img, thumb);
	
	jsonPayload = TestUtil.getSerializedJSON(pojoObj);
	System.out.println("************************************************************************");
	System.out.println(jsonPayload);
	
	}
}

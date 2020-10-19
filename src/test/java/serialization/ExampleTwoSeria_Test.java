package serialization;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.qa.api.gorest.util.TestUtil;
import com.qa.practise.pojoObject.Batter;
import com.qa.practise.pojoObject.Batters;
import com.qa.practise.pojoObject.ExampleTwoPojo;
import com.qa.practise.pojoObject.Topping;

public class ExampleTwoSeria_Test {
	List<Batter> batterList = new ArrayList<Batter>();
	List<Topping> toppingList = new ArrayList<Topping>();
	
	@Test
	public void exampleTwo_Test() {
	
		Topping t1 = new Topping("5001", "None");
		Topping t2 = new Topping("5002", "Glazed");
		Topping t3 = new Topping("5005", "Sugar");
		Topping t4 = new Topping("5007", "Powdered Sugar");
		Topping t5 = new Topping("5006", "Chocolate with Sprinkles");
		Topping t6 = new Topping("5003", "Chocolate");
		toppingList.add(t1);
		toppingList.add(t2);
		toppingList.add(t3);
		toppingList.add(t4);
		toppingList.add(t5);
		toppingList.add(t6);
		
		Batter b1 = new Batter("1001", "Regular");
		Batter b2 = new Batter("1002", "Chocolate");
		Batter b3 = new Batter("1003", "Blueberry");
		Batter b4 = new Batter("1004", "Devil's Food");
		batterList.add(b1);
		batterList.add(b2);
		batterList.add(b3);
		batterList.add(b2);
		Batters batters = new Batters(batterList);
		
		ExampleTwoPojo obj = new ExampleTwoPojo("0001", "donut", "Cake", 0.55, batters, toppingList);
		
		String jsonPayload = TestUtil.getSerializedJSON(obj);
		System.out.println("************************************************************");
		System.out.println(jsonPayload);
		
	}

}

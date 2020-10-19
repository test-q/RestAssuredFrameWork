package serialization;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.qa.api.gorest.util.TestUtil;
import com.qa.practise.pojoObject.Batter;
import com.qa.practise.pojoObject.Batters;
import com.qa.practise.pojoObject.ExampleTwoPojo;
import com.qa.practise.pojoObject.ExampleThreePojo;
import com.qa.practise.pojoObject.Topping;

public class ExampleThreeSeria_Test {
	List<Batter> batterList = new ArrayList<Batter>();
	List<Topping> toppingList = new ArrayList<Topping>();
	List<ExampleThreePojo> outerList = new ArrayList<ExampleThreePojo>();
	String jsonPayload;

	@Test
	public void exampleThree_Test() {
		
		Topping t1 = new Topping("5001", "None");
		Topping t2 = new Topping("5002", "Glazed");
		Topping t3 = new Topping("5005", "Sugar");
		Topping t4 = new Topping("5007", "Powdered Sugar");
		Topping t5 = new Topping("5006", "Chocolate with Sprinkles");
		toppingList.add(t1);
		toppingList.add(t2);
		toppingList.add(t3);
		toppingList.add(t4);
		toppingList.add(t5);
		
		Batter b1 = new Batter("1001", "Regular");
		Batter b2 = new Batter("1002", "Chocolate");
		Batter b3 = new Batter("1003", "Blueberry");
		Batter b4 = new Batter("1004", "Devil's Food");
		batterList.add(b1);
		batterList.add(b2);
		batterList.add(b3);
		batterList.add(b4);
		Batters batterParent = new Batters(batterList);
		ExampleTwoPojo f1 = new ExampleTwoPojo("0001", "donut", "Cake", 0.55, batterParent, toppingList);
		
		Topping t6 = new Topping("5001", "None");
		Topping t7 = new Topping("5002", "Glazed");
		Topping t8 = new Topping("5005", "Sugar");
		Topping t9 = new Topping("5007", "Powdered Sugar");
		Topping t10 = new Topping("5006", "Chocolate with Sprinkles");
		toppingList.add(t6);
		toppingList.add(t7);
		toppingList.add(t8);
		toppingList.add(t9);
		toppingList.add(t10);
		
		Batter b5 = new Batter("1001", "Regular");
		Batter b6 = new Batter("1002", "Chocolate");
		Batter b7 = new Batter("1003", "Blueberry");
		Batter b8 = new Batter("1004", "Devil's Food");
		batterList.add(b5);
		batterList.add(b6);
		batterList.add(b7);
		batterList.add(b8);
		Batters batterParent1 = new Batters(batterList);
		ExampleTwoPojo f2 = new ExampleTwoPojo("0001", "donut", "Cake", 0.55, batterParent1, toppingList);
		
		//Final Pojo Object
		ExampleThreePojo  o1 = new ExampleThreePojo(f1);
		ExampleThreePojo  o2 = new ExampleThreePojo(f2);
		outerList.add(o1);
		outerList.add(o2);
		
		//Convert Pojo object to json
		jsonPayload = TestUtil.getSerializedJSON(outerList);
		System.out.println("***************************************************************************************");
		System.out.println(jsonPayload);
		
		
		
	}

}

package com.qa.practise.pojoObject;

import java.util.List;

public class ExampleThreePojo {
	
	private ExampleTwoPojo innerFinal;
	
	public ExampleThreePojo(ExampleTwoPojo innerFinal) {
		this.innerFinal = innerFinal;
	}

	public ExampleTwoPojo getInnerFinal() {
		return innerFinal;
	}

	public void setInnerFinal(ExampleTwoPojo innerFinal) {
		this.innerFinal = innerFinal;
	}
	
	
  
	

}

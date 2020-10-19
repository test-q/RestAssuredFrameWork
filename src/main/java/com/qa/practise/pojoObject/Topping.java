
package com.qa.practise.pojoObject;

import java.util.HashMap;
import java.util.Map;

public class Topping {

    private String id;
    private String type;
    
	public Topping() {
	// This default constructor created for de-serialization.
	}
 
    public Topping(String id, String type) {
        this.id = id;
        this.type = type;
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


}

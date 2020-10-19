
package com.qa.practise.pojoObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Batters {

    private List<Batter> batter = null;
    
	public Batters() {
	// This default constructor created for de-serialization.
	}

    public Batters(List<Batter> batter) {
        this.batter = batter;
    }

    public List<Batter> getBatter() {
        return batter;
    }

    public void setBatter(List<Batter> batter) {
        this.batter = batter;
    }

}

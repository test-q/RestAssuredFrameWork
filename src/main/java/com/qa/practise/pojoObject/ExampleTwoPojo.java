
package com.qa.practise.pojoObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleTwoPojo {

    private String id;
    private String type;
    private String name;
    private Double ppu;
    private Batters batters;
    private List<Topping> topping;
    
	public ExampleTwoPojo() {
	// This default constructor created for de-serialization.
	}

    public ExampleTwoPojo(String id, String type, String name, Double ppu, Batters batters, List<Topping> topping) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ppu = ppu;
        this.batters = batters;
        this.topping = topping;
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

    public Double getPpu() {
        return ppu;
    }

    public void setPpu(Double ppu) {
        this.ppu = ppu;
    }

    public Batters getBatters() {
        return batters;
    }

    public void setBatters(Batters batters) {
        this.batters = batters;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }


}

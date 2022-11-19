package com.example.prices.entity.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PaisResponse {
	private Long id;
	private String name;
	private String continent;
	private Long landArea;
	private Long seaArea;

    @JsonCreator
    public PaisResponse(
    		@JsonProperty(required = true,value="id") Long id, 
    		@JsonProperty(required = true,value="name") String name, 
    		@JsonProperty(required = true,value="continent") String continent,
    		@JsonProperty(required = true,value ="landArea") Long landArea, 
    		@JsonProperty(required = true, value= "seaArea") Long seaArea) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.landArea = landArea;
        this.seaArea = seaArea;
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Long getLandArea() {
		return landArea;
	}

	public void setLandArea(Long landArea) {
		this.landArea = landArea;
	}

	public Long getSeaArea() {
		return seaArea;
	}

	public void setSeaArea(Long seaArea) {
		this.seaArea = seaArea;
	}

    
}
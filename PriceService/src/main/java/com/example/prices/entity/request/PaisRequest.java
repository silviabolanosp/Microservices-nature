package com.example.prices.entity.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaisRequest {
    private final Long idPais;


    @JsonCreator
    public PaisRequest(
            @JsonProperty(required = true, value = "idPais") Long idPais) {
        this.idPais = idPais;

    }


	public Long getIdPais() {
		return idPais;
	}



}
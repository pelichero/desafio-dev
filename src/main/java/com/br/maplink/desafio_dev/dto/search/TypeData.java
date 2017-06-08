package com.br.maplink.desafio_dev.dto.search;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypeData {
	STREET("street"), 
	ADDRESS("address"), 
	GEOGRAPHY("geography"),	
	CROSSSTREET("crossstreet"), 
	POI("poi"), 
	POSTALCODE("postalcode");

	private String typeData;
	
	TypeData(String typeData) {
		this.typeData = typeData;
	}
	
	@JsonCreator
    public static TypeData getEnumFromValue(String value) {
        for (TypeData testEnum : values()) {
            if (testEnum.typeData.equals(value)) {
                return testEnum;
            }
        }
        throw new IllegalArgumentException();
    }
}

package com.br.maplink.desafio_dev.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @see http://dev.maplink.com.br/en/v2/maplinkapi-search/#searchTypes
 * @author felipe.pelichero
 *
 * 7 de jun de 2017
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoData {

	@JsonProperty("type")
	private TypeData type;
	
	@JsonProperty("matchingScore")
	private Double matchingScore;
	
	@JsonProperty("country")
	private Country country;
	
	@JsonProperty("countrySubdivisionAbbreviation")
	private String countrySubdivisionAbbreviation;
	
	@JsonProperty("countrySubdivision")
	private String countrySubdivision;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("streetName")
	private String streetName;
	
	@JsonProperty("number")
	private String number;
	
	@JsonProperty("displayName")
	private String displayName;
	
	@JsonProperty("location")
	private Location location;
	
	@JsonProperty("precision")
	private String precision;
	
	public TypeData getType() {
		return type;
	}
	
	public void setType(TypeData type) {
		this.type = type;
	}
	
	public Double getMatchingScore() {
		return matchingScore;
	}
	
	public void setMatchingScore(Double matchingScore) {
		this.matchingScore = matchingScore;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public String getCountrySubdivisionAbbreviation() {
		return countrySubdivisionAbbreviation;
	}
	
	public void setCountrySubdivisionAbbreviation(String countrySubdivisionAbbreviation) {
		this.countrySubdivisionAbbreviation = countrySubdivisionAbbreviation;
	}
	
	public String getCountrySubdivision() {
		return countrySubdivision;
	}
	
	public void setCountrySubdivision(String countrySubdivision) {
		this.countrySubdivision = countrySubdivision;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getPrecision() {
		return precision;
	}
	
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getGeoLocation(){
		return this.getLocation().getLat() + "," +this.getLocation().getLng();
	}
	
	@Override
	public String toString() {
		return "GeoData [type=" + type + ", matchingScore=" + matchingScore + ", country=" + country
				+ ", countrySubdivisionAbbreviation=" + countrySubdivisionAbbreviation + ", countrySubdivision="
				+ countrySubdivision + ", city=" + city + ", streetName=" + streetName + ", number=" + number
				+ ", displayName=" + displayName + ", location=" + location + ", precision=" + precision + "]";
	}	
}

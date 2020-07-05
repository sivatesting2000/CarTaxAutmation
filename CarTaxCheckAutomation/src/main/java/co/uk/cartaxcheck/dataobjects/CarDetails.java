package co.uk.cartaxcheck.dataobjects;

public class CarDetails {
	public String registrationNumber;
	public String make;
	public String model;
	public String colour;
	public String year;

	public CarDetails(String registrationNumber, String make, String model, String colour, String year) {
		this.registrationNumber = registrationNumber;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.year = year;
	}

}

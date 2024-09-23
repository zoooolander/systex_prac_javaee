package com.systex.model;

public class Member {
	private int id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	
	public Member() {
		super();
	}

	public Member(String firstName, String lastName, String street, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
	}

	public Member(int id, String firstName, String lastName, String street, String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street
				+ ", city=" + city + "]";
	}
}

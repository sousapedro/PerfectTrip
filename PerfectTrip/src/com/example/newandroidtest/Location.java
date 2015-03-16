package com.example.newandroidtest;

public class Location {
	String name_;
	String address_;
	
	
	Location(String name, String address)
	{
		name_ = name;
		address_ = address;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public String getAddress()
	{
		return address_;
	}
}

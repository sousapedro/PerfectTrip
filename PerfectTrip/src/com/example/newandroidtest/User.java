package com.example.newandroidtest;

import java.util.ArrayList;
import java.util.List;

public class User {
	String name_;
	List<Trip> myTrips_;
	
	User(String name)
	{
		name_ = name;
		myTrips_ = new ArrayList<Trip>();
	}
	
	public String getName()
	{
		return name_;
	}
	
	public List<Trip> getMyTrips()
	{
		return myTrips_;
	}
	
	public void addTrip(Trip trip)
	{
		//Need to check if trip already on list first!!
		myTrips_.add(trip);
	}
}
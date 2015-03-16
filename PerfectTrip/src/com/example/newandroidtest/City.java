package com.example.newandroidtest;

import java.util.ArrayList;
import java.util.List;

public class City {
	String name_;
	List<Trip> tripList_;
	
	
	City(String name)
	{
		name_ = name;
		tripList_ = new ArrayList<Trip>();
	}
	
	public String getName()
	{
		return name_;
	}
	
	public int getTripCount()
	{
		return tripList_.size();
	}
	
	public void addTrip(Trip trip)
	{
		tripList_.add(trip);
	}
	
	public List<Trip> getTripList()
	{
		return tripList_;
	}
}
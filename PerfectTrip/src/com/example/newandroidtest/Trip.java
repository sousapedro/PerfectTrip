package com.example.newandroidtest;

import java.util.ArrayList;
import java.util.List;

public class Trip {
	String name_;
	List<Location> locationList_;
	int days_;
	int liked_;
	
	Trip(String name, int days)
	{
		name_ = name;
		days_ = days;
		liked_ = 0;
		locationList_ = new ArrayList<Location>();
	}
	
	
	public int getDays()
	{
		return days_;
	}
	
	public String getName()
	{
		return name_;
	}
	
	public List<Location> getLocationList()
	{
		return locationList_;
	}
	
	public void addLocation(Location location)
	{
		locationList_.add(location);
	}
	
	public void addLike()
	{
		liked_ = liked_ +1;
	}
	
	public int getLikes()
	{
		return liked_;
	}
}

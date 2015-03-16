package com.example.newandroidtest;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	 
    private String[] titles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle drawerToggle;
    static CityAdapter cityAdapter;
    public static List<City> database_;
    public static int Locationselected_;
    public static User currentUser_;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentUser_ = new User("UserName");
        
        populateDataBase();
        
        titles = new String[]{"Home Screen", "Search A City", "My Trips"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
 
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, titles));
        
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
 
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {
 
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            	
            }
 
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            }
        };
 
        drawerLayout.setDrawerListener(drawerToggle);
 
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
 
        
		cityAdapter = new CityAdapter(this, R.layout.city_view);
		ListView listViewCity = (ListView) findViewById(R.id.cityListView);
		listViewCity.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Log.d("TAG", "CLICOU");
				Locationselected_ = position;
	        	LaunchTripActivity();
			}
		});
		listViewCity.setAdapter(cityAdapter);
		for (City city : database_) {
			cityAdapter.add(city);
		}
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
 
    private void selectItem(int position) {
        drawerList.setItemChecked(position, true);
        if(position == 1)
        {
        	Intent intent = new Intent(this, SearchActivity.class);
    		startActivity(intent);
        }
        if(position == 2)
        {
        	Intent intent = new Intent(this, MyTripActivity.class);
    		startActivity(intent);
        }
        drawerLayout.closeDrawer(drawerList);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
 
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    
    private void LaunchTripActivity()
    {
    	Intent intent = new Intent(this, TripActivity.class);
		startActivity(intent);
    }
    
    
    private void populateDataBase()
    {
    	database_ = new ArrayList<City>();
    	database_.add(new City("Rio De Janeiro"));
    	database_.add(new City("Philadelphia"));
    	database_.add(new City("Los Angeles"));
    	database_.add(new City("New York"));
    	database_.add(new City("London"));
    	database_.add(new City("Paris"));
    	
    	//Rio
    	database_.get(0).addTrip(new Trip("Awesome Trip!!",7));
	    	database_.get(0).getTripList().get(0).addLocation(new Location("Copacabana","RJ - Brazil"));
	    	database_.get(0).getTripList().get(0).addLocation(new Location("Ipanema","RJ - Brazil"));
	    	database_.get(0).getTripList().get(0).addLocation(new Location("Cristo Redentor","Parque Nacional da Tijuca"));
	    	database_.get(0).getTripList().get(0).addLocation(new Location("Pao de Acucar","Avenida Pasteur, 520 - Urca"));
	    database_.get(0).addTrip(new Trip("Beach time",3));
	    	database_.get(0).getTripList().get(1).addLocation(new Location("Copacabana","RJ - Brazil"));
	    	database_.get(0).getTripList().get(1).addLocation(new Location("Ipanema","RJ - Brazil"));
    	database_.get(0).addTrip(new Trip("Cuisine Adventure!",4));
    		database_.get(0).getTripList().get(2).addLocation(new Location("Ipanema","RJ - Brazil"));    	
    	
    	//Philadelphia
    	database_.get(1).addTrip(new Trip("Historical Time!",2));
    		database_.get(1).getTripList().get(0).addLocation(new Location("Old City Hall","Philadelphia, PA 19106"));
    		database_.get(1).getTripList().get(0).addLocation(new Location("Liberty Bell","6th St & Market St"));
	    	database_.get(1).getTripList().get(0).addLocation(new Location("Independence Hall","Philadelphia, PA 19106"));
	    	database_.get(1).getTripList().get(0).addLocation(new Location("Rocky Steps","2600 Benjamin Franklin Pkwy"));

    	database_.get(1).addTrip(new Trip("All City in 3 days",3));
    		database_.get(1).getTripList().get(1).addLocation(new Location("Love Park","1599 John F Kennedy Blvd"));
    		database_.get(1).getTripList().get(1).addLocation(new Location("Liberty Bell"," 6th St & Market St"));
    		database_.get(1).getTripList().get(1).addLocation(new Location("Rocky Steps","2600 Benjamin Franklin Pkwy"));
    		database_.get(1).getTripList().get(1).addLocation(new Location("Independence Hall","6th St & Market St"));

    	//LA
    	database_.get(2).addTrip(new Trip("Fun at LA!",3));
    		database_.get(2).getTripList().get(0).addLocation(new Location("Universal Studios Hollywood","100 Universal City Plaza, Universal City"));
    		database_.get(2).getTripList().get(0).addLocation(new Location("Hollywood Walk of Fame","Los Angeles, CA 90028"));
    		database_.get(2).getTripList().get(0).addLocation(new Location("Getty Center","1200 Getty Center Dr #1100"));
    		database_.get(2).getTripList().get(0).addLocation(new Location("Hollywood Sign","Los Angeles, CA"));
    	database_.get(2).addTrip(new Trip("Los Angeles in 4 days",4));
    		database_.get(2).getTripList().get(1).addLocation(new Location("Hollywood Sign","Los Angeles, CA"));
    		database_.get(2).getTripList().get(1).addLocation(new Location("Universal Studios Hollywood","100 Universal City Plaza, Universal City"));
    		database_.get(2).getTripList().get(1).addLocation(new Location("Madame Tussauds Hollywood","Los Angeles, CA"));
    		database_.get(2).getTripList().get(1).addLocation(new Location("Hollywood Walk of Fame","Los Angeles, CA 90028"));
    	database_.get(2).addTrip(new Trip("Great time!",10));
    		database_.get(2).getTripList().get(2).addLocation(new Location("Universal Studios Hollywood","100 Universal City Plaza, Universal City"));
    		database_.get(2).getTripList().get(2).addLocation(new Location("Hollywood Walk of Fame","Los Angeles, CA 90028"));
    		database_.get(2).getTripList().get(2).addLocation(new Location("Getty Center","1200 Getty Center Dr #1100"));
    	database_.get(2).addTrip(new Trip("Week of fun",7));
    		database_.get(2).getTripList().get(3).addLocation(new Location("Hollywood Walk of Fame","Los Angeles, CA 90028"));
    		database_.get(2).getTripList().get(3).addLocation(new Location("Universal Studios Hollywood","100 Universal City Plaza, Universal City"));
    	
    	//New York
    	database_.get(3).addTrip(new Trip("A LOT to do",5));
    		database_.get(3).getTripList().get(0).addLocation(new Location("Central Park","New York, NY"));
	    	database_.get(3).getTripList().get(0).addLocation(new Location("Empire State Building","350 5th Ave"));
	    	database_.get(3).getTripList().get(0).addLocation(new Location("Statue of Liberty","Liberty Island"));
	    	database_.get(3).getTripList().get(0).addLocation(new Location("Rockefeller Center","45 Rockefeller Plaza"));
    	database_.get(3).addTrip(new Trip("Relaxing!!",2));
    		database_.get(3).getTripList().get(1).addLocation(new Location("Empire State Building","350 5th Ave"));
    		database_.get(3).getTripList().get(1).addLocation(new Location("Central Park","New York, NY"));

    }
}
package com.example.newandroidtest;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class LocationActivity extends ActionBarActivity {
	 
    private String[] titles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle drawerToggle;
    static LocationAdapter locationAdapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        titles = new String[]{"Home Screen", "Search A City", "My Trips"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
 
        // Set the adapter for the list view
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, titles));
        
        // Set the list's click listener
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
 
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {
 
            public void onDrawerClosed(View view) {
            	getSupportActionBar().setTitle(MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_).getName());
            }
 
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(MainActivity.currentUser_.getName());
            }
        };
 
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setTitle(MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
		locationAdapter = new LocationAdapter(this, R.layout.location_view);
		ListView listViewLocation = (ListView) findViewById(R.id.locationListView);
		listViewLocation.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Log.d("TAG", "CLICOU");	
			}
		});
		
		
		listViewLocation.setAdapter(locationAdapter);
		for (Location location : MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_).getLocationList()) {
			locationAdapter.add(location);
		}

		final Button button = (Button) findViewById(R.id.toMyListButton);
		if(MainActivity.currentUser_.getMyTrips().contains(MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_)))
			button.setVisibility(android.view.View.INVISIBLE);
		button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	popUp();
            	MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_).addLike();
            	MainActivity.currentUser_.addTrip(MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_));
            }
        });
    }
 
    private void popUp()
    {
    	Toast.makeText(this, "Trip Added to Your List", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
     
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
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
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
 
        drawerList.setItemChecked(position, true);
        if(position == 0)
        {
        	Intent intent = new Intent(this, MainActivity.class);
    		startActivity(intent);
        }
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
 
}
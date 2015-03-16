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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class SearchActivity extends ActionBarActivity {
	 
    private String[] titles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private CharSequence mTitle;
    private ActionBarDrawerToggle drawerToggle;
    static TripAdapter tripAdapter;
    private ListView listViewTrip;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        
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
 
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle("Search for the Perfect Trip");
            }
 
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            	getSupportActionBar().setTitle(MainActivity.currentUser_.getName());
            }
        };
 
        drawerLayout.setDrawerListener(drawerToggle);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        tripAdapter = new TripAdapter(this, R.layout.trip_view);
		listViewTrip = (ListView) findViewById(R.id.tripListView);
		listViewTrip.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Log.d("TAG", "CLICOU");
				TripActivity.selectedTrip_ = position;
				LaunchLocationActivity();
			}
		});
		
		final Button button = (Button) findViewById(R.id.buttonId1);
		if(MainActivity.currentUser_.getMyTrips().contains(MainActivity.database_.get(MainActivity.Locationselected_).getTripList().get(TripActivity.selectedTrip_)))
			button.setVisibility(android.view.View.INVISIBLE);
		button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	setList();
            }
        });
    }
    
    private void setList()
    {
    	listViewTrip.setAdapter(tripAdapter);
    	EditText txtBox = (EditText) findViewById(R.id.entertext);
    	String str = txtBox.getText().toString();
    	City cityAux = null;
    	int i = 0;
    	for (City city : MainActivity.database_) {
			if(city.getName().contains(str))
			{
				cityAux = city;
				MainActivity.Locationselected_ = i;
			}
			i++;
		}
    	if(cityAux != null)
    	{
    		Intent intent = new Intent(this, TripActivity.class);
    		startActivity(intent);
    	}
    	else
    		Toast.makeText(this, "Nothing Found", Toast.LENGTH_SHORT).show();
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
    
    private void LaunchLocationActivity()
    {
    	Intent intent = new Intent(this, LocationActivity.class);
		startActivity(intent);
    }
}
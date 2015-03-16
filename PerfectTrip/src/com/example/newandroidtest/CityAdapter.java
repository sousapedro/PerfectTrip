package com.example.newandroidtest;

import com.example.newandroidtest.R;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CityAdapter extends ArrayAdapter<City> {

	Context mContext;
	int mLayoutResourceId;

	public CityAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);

		mContext = context;
		mLayoutResourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		final City currentItem = getItem(position);
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(mLayoutResourceId, parent, false);
		}

		TextView cityName = (TextView) row.findViewById(R.id.city_title);
		cityName.setText(currentItem.getName());
		TextView tripCount = (TextView) row.findViewById(R.id.trip_count);
		tripCount.setText(""+currentItem.getTripCount());
		row.setTag(currentItem);
		
		return row;
	}	
}
package com.example.newandroidtest;

import com.example.newandroidtest.R;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocationAdapter extends ArrayAdapter<Location> {

	Context mContext;
	int mLayoutResourceId;

	public LocationAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);

		mContext = context;
		mLayoutResourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		final Location currentItem = getItem(position);
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(mLayoutResourceId, parent, false);
		}

		TextView locationName = (TextView) row.findViewById(R.id.location_title);
		locationName.setText(currentItem.getName());
		TextView addressLine = (TextView) row.findViewById(R.id.addressLine);
		addressLine.setText(currentItem.getAddress());
		row.setTag(currentItem);
		
		return row;
	}
}
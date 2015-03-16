package com.example.newandroidtest;

import com.example.newandroidtest.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TripAdapter extends ArrayAdapter<Trip> {

	Context mContext;
	int mLayoutResourceId;

	public TripAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);

		mContext = context;
		mLayoutResourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		final Trip currentItem = getItem(position);
		
		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(mLayoutResourceId, parent, false);
		}

		TextView tripName = (TextView) row.findViewById(R.id.trip_title);
		tripName.setText(currentItem.getName());		
		TextView tripCount = (TextView) row.findViewById(R.id.trip_count);
		tripCount.setText(""+currentItem.getDays());
		
		TextView favoriteCount = (TextView) row.findViewById(R.id.favorite_count);
		favoriteCount.setText(""+currentItem.getLikes());
		row.setTag(currentItem);
		
		return row;
	}	
	

}

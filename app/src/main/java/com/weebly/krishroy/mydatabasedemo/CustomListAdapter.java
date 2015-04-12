package com.weebly.krishroy.mydatabasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomListAdapter extends ArrayAdapter<String> {

    CustomListAdapter(Context context, String[] items) {
        super(context, R.layout.custom_list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_list_item, parent, false);

        String singleItem = getItem(position);
        TextView mCustomListItemTextView = (TextView) customView.findViewById(R.id.CustomListItemTextView);
        mCustomListItemTextView.setText(singleItem);



        return customView;
    }
}

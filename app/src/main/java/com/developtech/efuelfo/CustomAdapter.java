package com.developtech.efuelfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    int flags[];
    String[] countryitem;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] flags,String[] countryNames) {
        this.context = applicationContext;
        this.flags=flags;
        this.countryitem = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryitem.length;
    }

    @Override
    public Object getItem(int position) {
        return countryitem[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon=(ImageView) view.findViewById(R.id.imageflag);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(flags[position]);
        names.setText(countryitem[position]);
        return view;
    }
}

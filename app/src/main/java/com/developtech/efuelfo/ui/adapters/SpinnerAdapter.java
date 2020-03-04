package com.developtech.efuelfo.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.developtech.efuelfo.R;

import java.util.List;

/**
 * Created by root on 5/31/17.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + getContext().getResources().getString(R.string.font_regular));

    TextView view, dropDownView;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] items) {
        super(context, resource, items);
    }

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull String[] items) {
        super(context, resource, textViewResourceId, items);
    }

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> items) {
        super(context, resource, items);
    }

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<String> items) {
        super(context, resource, textViewResourceId, items);
    }

    @Override
    public boolean isEnabled(int position){
        if(position == 0)
        {
            // Disable the first item from Spinner
            // First item will be use for hint

            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(font);
        view.setTextSize(15);
        view.setPadding(15,0,0,2);
        view.setTextColor(getContext().getResources().getColor(R.color.adapter_text));
        this.view = view;
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTypeface(font);
        view.setTextSize(16);
        view.setWidth(100);
        view.setPadding(30,20,30,20);
        dropDownView = view;
        return view;
    }

    public void setItemTextColor(int color)
    {
        if(view!=null)
        {
            view.setTextColor(color);
        }
    }
}


package com.developtech.efuelfo.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import com.developtech.efuelfo.ui.dialogFragments.AddLocationDialog;

import java.util.ArrayList;

/**
 * Created by root on 5/31/17.
 */

public class GooglePlacesAutocompleteAdapter extends ArrayAdapter implements Filterable
{
    ArrayList resultListPlaces;
    AddLocationDialog locationDialog;

    public GooglePlacesAutocompleteAdapter(Context context,
                                           int resource, int textViewResourceId,
                                           ArrayList resultListPlaces, AddLocationDialog locationDialog)
    {
        super(context, resource, textViewResourceId);
        this.resultListPlaces = resultListPlaces;
        this.locationDialog = locationDialog;
    }

    public int getCount()
    {
        return resultListPlaces.size();
    }

    public String getItem(int index)
    {
        return (String) resultListPlaces.get(index);
    }

    public android.widget.Filter getFilter()
    {
        android.widget.Filter filter = new android.widget.Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults filterResults = new FilterResults();
                if (constraint != null)
                {
                    locationDialog.autoComplete(constraint.toString());
                }
                filterResults.values = resultListPlaces;
                if(resultListPlaces !=null)
                {
                    filterResults.count = resultListPlaces.size();
                }
                else
                {
                    filterResults.count = 0;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }

            }


        };

        return filter;
    }

}

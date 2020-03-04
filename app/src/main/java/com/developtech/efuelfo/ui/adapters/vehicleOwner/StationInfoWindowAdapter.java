package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.model.responseModel.FuelStationResponseModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

/**
 * Created by dt on 1/20/18.
 */

public class StationInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    View view;
    Context context;
    FuelStationResponseModel model;
    AppComponent appComponent;
    public StationInfoWindowAdapter(Context context, AppComponent appComponent)
    {
        this.appComponent = appComponent;
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.lyt_station_marker_popup, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if (marker != null
                && marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.showInfoWindow();
        }
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        model = (FuelStationResponseModel) marker.getTag();
        ImageView ivUser = view.findViewById(R.id.ivUser);

        CustomTextView tvDistance = view.findViewById(R.id.tvDistance);
        CustomTextView tvStationName = view.findViewById(R.id.tvStationName);
        Picasso.with(context).load(appComponent.getUtilFunctions().getImageFullUrl(model.getImage())).into((ImageView)view.findViewById(R.id.ivUser));

        tvDistance.setText(model.getDistance()+" km");
        tvStationName.setText(model.getName());

        return view;
    }
}

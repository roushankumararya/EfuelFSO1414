package com.developtech.efuelfo.ui.adapters.vehicleOwner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.app.AppComponent;
import com.developtech.efuelfo.model.responseModel.GetDriverResponseModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

/**
 * Created by developtech on 1/22/18.
 */

public class TrackDriverInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    View view;
    Context context;
    AppComponent appComponent;

    public TrackDriverInfoWindowAdapter(Context context, AppComponent appComponent) {
        this.context = context;
        this.appComponent = appComponent;
        view = LayoutInflater.from(context).inflate(R.layout.lyt_track_driver_marker_popup, null);
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

        GetDriverResponseModel model = (GetDriverResponseModel) marker.getTag();
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvMobile = view.findViewById(R.id.tvMobile);
        TextView tvVehicleNumber = view.findViewById(R.id.tvVehicleNumber);
        TextView tvDateTime = view.findViewById(R.id.tvDateTime);

        tvName.setText(model.getFirstName());
        tvMobile.setText(model.getMobileNumber());
        tvVehicleNumber.setText(model.getVehicleNumber());
        tvDateTime.setText(model.getUpdatedAt());
        Picasso.with(context).load(appComponent.getUtilFunctions().getImageFullUrl(model.getImage())).placeholder(R.drawable.place_holder).into((ImageView) view.findViewById(R.id.ivUser));
//        tvVehicleNumber.setText(model.v);
        tvDateTime.setText(appComponent.getUtilFunctions().timeFormat(model.getUpdatedAt()));
        return view;

    }
}

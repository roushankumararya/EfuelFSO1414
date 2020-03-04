package com.developtech.efuelfo.ui.dialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.listeners.OnItemClickListener;
import com.developtech.efuelfo.ui.adapters.vehicleOwner.VehicleModelSearchAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleModelSearchDialog extends DialogFragment implements OnItemClickListener
{


    @BindView(R.id.etModelSearch)
    EditText etModelSearch;

    @BindView(R.id.rvVehicleModels)
    RecyclerView rvVehicleModels;

    private View view;
    private AlertDialog alertDialog;

    VehicleModelSearchAdapter adapter;

    ArrayList<String> modelsList;

    AddVehicleDialog addVehicleDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_vehicle_search_model, null);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        alertDialog = builder.create();
        init();
        return alertDialog;
    }

    public void setData(ArrayList<String> modelsList, AddVehicleDialog addVehicleDialog)
    {
        this.modelsList = modelsList;
        this.addVehicleDialog = addVehicleDialog;
    }

    void init()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvVehicleModels.setLayoutManager(layoutManager);
        adapter = new VehicleModelSearchAdapter(modelsList, this);

        etModelSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = s.toString();
                ArrayList<String> sortedList = new ArrayList<>();
                for (String model : modelsList)
                {
                    if (model.toLowerCase().contains(search.toUpperCase()))
                    {
                        sortedList.add(model);
                    }
                }

                adapter.updateList(sortedList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

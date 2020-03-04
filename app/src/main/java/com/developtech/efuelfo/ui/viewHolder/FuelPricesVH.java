package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.developtech.efuelfo.R;
import com.developtech.efuelfo.model.responseModel.FuelDetailModel;
import com.developtech.efuelfo.ui.adapters.stationOwner.FuelPricesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 1/3/18.
 */

public class FuelPricesVH extends RecyclerView.ViewHolder{

    @BindView(R.id.spinnerFuelType)
    public Spinner spinnerFuelType;

    @BindView(R.id.etPrice)
    public EditText etPrice;

    @BindView(R.id.ivDelete)
    public ImageView ivDelete;

    public FuelPricesAdapter.CustomEditTextListener listener;

    public FuelPricesVH(View itemView, FuelPricesAdapter.CustomEditTextListener listener, boolean isDisabled) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listener = listener;
        etPrice.addTextChangedListener(listener);

        if (isDisabled)
        {
            if (spinnerFuelType.getSelectedView()!=null)
            {
                spinnerFuelType.getSelectedView().setEnabled(false);
                spinnerFuelType.getSelectedView().setClickable(false);
                spinnerFuelType.getSelectedView().setFocusable(false);
            }
            spinnerFuelType.setEnabled(false);
            spinnerFuelType.setClickable(false);
            spinnerFuelType.setFocusable(false);
            ivDelete.setVisibility(View.GONE);
            etPrice.setClickable(false);
            etPrice.setFocusable(false);

        }
    }

    public void unregisterListener()
    {
        etPrice.removeTextChangedListener(listener);
    }

    public void registerListener()
    {
        etPrice.addTextChangedListener(listener);
    }

    public void bindData(FuelDetailModel model)
    {

        if(model.getPrice()!=null && !model.getPrice().isEmpty())
        {
            etPrice.setText(model.getPrice());
        }
        else
        {
            etPrice.setText("");
        }

    }
}

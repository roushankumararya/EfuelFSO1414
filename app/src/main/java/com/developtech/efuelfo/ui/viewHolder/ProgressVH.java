package com.developtech.efuelfo.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dt on 3/21/18.
 */

public class ProgressVH extends RecyclerView.ViewHolder {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public ProgressVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(boolean isProgress)
    {
        if (isProgress)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
}

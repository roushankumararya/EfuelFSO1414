package com.developtech.efuelfo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.developtech.efuelfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorialFragment extends Fragment {

    @BindView(R.id.ivImg)
    ImageView ivImg;

    private int position = 0;
    private Bundle bundle;
    View view;

    public TutorialFragment() {
        // Required empty public constructor
    }

    public TutorialFragment setPos(int position) {
        TutorialFragment tutorialFragment = new TutorialFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        tutorialFragment.setArguments(bundle);
        return tutorialFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tutorial, container, false);
        ButterKnife.bind(this, view);
        initialise();
        return view;
    }

    public void initialise() {
        ivImg = (ImageView) view.findViewById(R.id.ivImg);
        bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("pos");
            switch (position) {
                case 0: {
                    ivImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.warning_screen));
                    break;
                }
                case 1: {
                    ivImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.credit_agreement_walkthrough));
                    break;
                }
                case 2: {
                    ivImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.fuel_price_walkthrough));
                    break;
                }
                case 3: {
                    ivImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.sale_walkthrough));
                    break;
                }
                case 4: {
                    ivImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.transaction_walkthrough));
                    break;
                }
            }
        }
    }

}

package com.developtech.efuelfo.ui.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.developtech.efuelfo.HomePage;
import com.developtech.efuelfo.R;
import com.developtech.efuelfo.customs.CustomTextView;
import com.developtech.efuelfo.ui.adapters.TutorialSliderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorialAcitvity extends FragmentActivity {

    @BindView(R.id.tvHeading)
    CustomTextView tvHeading;
    @BindView(R.id.tvDesc)
    CustomTextView tvDesc;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.ivHint1)
    ImageView ivHint1;
    @BindView(R.id.ivHint2)
    ImageView ivHint2;
    @BindView(R.id.ivHint3)
    ImageView ivHint3;
    @BindView(R.id.ivHint4)
    ImageView ivHint4;
    @BindView(R.id.ivHint5)
    ImageView ivHint5;
//    @BindView(R.id.ivHint6)
//    ImageView ivHint6;
    private TutorialSliderAdapter sliderAdapter;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_acitvity);
        ButterKnife.bind(this);

        if (getIntent().getExtras()!=null)
        {
            bundle = getIntent().getExtras();
            btnStart.setText(getResources().getString(R.string.continue_caps));
        }

        initialise();
    }

    private void initialise() {
        sliderAdapter = new TutorialSliderAdapter(getSupportFragmentManager());
        view_pager.setAdapter(sliderAdapter);
        view_pager.setOffscreenPageLimit(5);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        setHeaders(getString(R.string.quick_fill), "");
                        changeHint(0);
                        break;
                    }
                    case 1: {
                        setHeaders(getString(R.string.creditagreements), "");
                        changeHint(1);
                        break;
                    }
                    case 2: {
                        setHeaders(getString(R.string.fuel_price), "");
                        changeHint(2);
                        break;
                    }
                    case 3: {
                        setHeaders(getString(R.string.saleinitiation), "");
                        changeHint(3);
                        break;
                    }
                    case 4: {
                        setHeaders(getString(R.string.transactions), "");
                        changeHint(4);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setHeaders(String head, String desc) {
        tvHeading.setText(head);
        tvDesc.setText(desc);
    }

    @OnClick(R.id.btnStart)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart: {
                if (bundle!=null && bundle.getBoolean("settings"))
                {
                    finish();
                }
                else {
                    Intent intent = new Intent(TutorialAcitvity.this, HomePage.class);
                    startActivity(intent);
                }
                break;
            }
        }
    }

    public void changeHint(int pos) {
        switch (pos) {
            case 0: {
                ivHint1.setImageResource(R.drawable.ellipse_white);
                ivHint2.setImageResource(R.drawable.ellipse_green);
                ivHint3.setImageResource(R.drawable.ellipse_green);
                ivHint4.setImageResource(R.drawable.ellipse_green);
                ivHint5.setImageResource(R.drawable.ellipse_green);
//                ivHint6.setImageResource(R.drawable.ellipse_green);
                break;
            }
            case 1: {
                ivHint1.setImageResource(R.drawable.ellipse_green);
                ivHint2.setImageResource(R.drawable.ellipse_white);
                ivHint3.setImageResource(R.drawable.ellipse_green);
                ivHint4.setImageResource(R.drawable.ellipse_green);
                ivHint5.setImageResource(R.drawable.ellipse_green);
//                ivHint6.setImageResource(R.drawable.ellipse_green);
                break;
            }
            case 2: {
                ivHint1.setImageResource(R.drawable.ellipse_green);
                ivHint2.setImageResource(R.drawable.ellipse_green);
                ivHint3.setImageResource(R.drawable.ellipse_white);
                ivHint4.setImageResource(R.drawable.ellipse_green);
                ivHint5.setImageResource(R.drawable.ellipse_green);
//                ivHint6.setImageResource(R.drawable.ellipse_green);
                break;
            }
            case 3: {
                ivHint1.setImageResource(R.drawable.ellipse_green);
                ivHint2.setImageResource(R.drawable.ellipse_green);
                ivHint3.setImageResource(R.drawable.ellipse_green);
                ivHint4.setImageResource(R.drawable.ellipse_white);
                ivHint5.setImageResource(R.drawable.ellipse_green);
//                ivHint6.setImageResource(R.drawable.ellipse_green);
                break;
            }
            case 4: {
                ivHint1.setImageResource(R.drawable.ellipse_green);
                ivHint2.setImageResource(R.drawable.ellipse_green);
                ivHint3.setImageResource(R.drawable.ellipse_green);
                ivHint4.setImageResource(R.drawable.ellipse_green);
                ivHint5.setImageResource(R.drawable.ellipse_white);
//                ivHint6.setImageResource(R.drawable.ellipse_green);
                break;
            }
            case 5: {
                ivHint1.setImageResource(R.drawable.ellipse_green);
                ivHint2.setImageResource(R.drawable.ellipse_green);
                ivHint3.setImageResource(R.drawable.ellipse_green);
                ivHint4.setImageResource(R.drawable.ellipse_green);
                ivHint5.setImageResource(R.drawable.ellipse_green);
//                ivHint6.setImageResource(R.drawable.ellipse_white);
                break;
            }
        }
    }
}

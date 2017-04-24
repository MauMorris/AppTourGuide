package com.example.mauriciogodinez.tourguideapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TourActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        TourAdapter mAdapter = new TourAdapter(this, getSupportFragmentManager());

        Button omitir = (Button) findViewById(R.id.boton_omitir);
        Button siguiente = (Button) findViewById(R.id.boton_siguiente_actividad);

        Button b = (Button) findViewById(R.id.appCompatButton);
        b.setVisibility(Button.INVISIBLE);

        omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(3, true);
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CategoriasActivity.class);
                startActivity(i);
                finish();
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Integer pos = position;
                ButtonBarLayout buttonBar = (ButtonBarLayout) findViewById(R.id.buttonbar);
                Button b = (Button) findViewById(R.id.appCompatButton);

                switch (pos) {
                    case 0:
                        buttonBar.setVisibility(ButtonBarLayout.VISIBLE);
                        b.setVisibility(Button.INVISIBLE);
                        break;
                    case 1:
                        buttonBar.setVisibility(ButtonBarLayout.VISIBLE);
                        b.setVisibility(Button.INVISIBLE);
                        break;
                    case 2:
                        buttonBar.setVisibility(ButtonBarLayout.GONE);
                        b.setVisibility(Button.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

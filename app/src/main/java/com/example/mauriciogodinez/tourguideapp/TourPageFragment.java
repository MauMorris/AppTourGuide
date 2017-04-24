package com.example.mauriciogodinez.tourguideapp;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TourPageFragment extends Fragment {

    public TourPageFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_tour, container, false);

        ImageView im = (ImageView) rootView.findViewById(R.id.image_view_tour);
        TextView tvt = (TextView) rootView.findViewById(R.id.text_view_title);
        TextView tvst = (TextView) rootView.findViewById(R.id.text_view_subtitle);

        im.setImageResource(this.getArguments().getInt("image_message"));
        tvt.setText(getString(this.getArguments().getInt("title_message")));
        tvst.setText(getString(this.getArguments().getInt("subtitle_message")));
        int color = ContextCompat.getColor(getContext(), this.getArguments().getInt("color_message"));
        // Set the background color of the text container View
        rootView.setBackgroundColor(color);
        //extraemos el drawable en un bitmap


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
package com.flairinfosystems.b2bmarket.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.flairinfosystems.b2bmarket.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {
    private ViewFlipper viewFlipper;


    public SellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sell, container, false);
        viewFlipper = (ViewFlipper)view.findViewById(R.id.viewflipper);
       final ImageView imageView1= (ImageView) view.findViewById(R.id.img1);
        final ImageView imageView2= (ImageView) view.findViewById(R.id.img2);
        final ImageView imageView3= (ImageView) view.findViewById(R.id.img3);
        imageView1.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        Button button1 = (Button) view.findViewById(R.id.next_personal);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
                imageView2.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                imageView1.clearColorFilter();
            }
        });

        Button button2 = (Button) view.findViewById(R.id.next_business);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
                imageView3.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                imageView2.clearColorFilter();
            }
        });
        Button button3 = (Button) view.findViewById(R.id.previous_business);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
                imageView1.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                imageView2.clearColorFilter();
            }
        });
        Button button4 = (Button) view.findViewById(R.id.submit_product);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Submitted Successfully",Toast.LENGTH_SHORT).show();
            }
        });
        Button button5 = (Button) view.findViewById(R.id.previous_product);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
                imageView2.setColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary));
                imageView3.clearColorFilter();
            }
        });

        return view;
    }

}

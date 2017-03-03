package com.flairinfosystems.b2bmarket.fragments;


import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flairinfosystems.b2bmarket.R;
import com.flairinfosystems.b2bmarket.adapters.HomeAdapter;
import com.flairinfosystems.b2bmarket.models.Product;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
RecyclerView recyclerView;

    public HomeFragment()  {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_view_home);
        //recyclerView.setHasFixedSize(true);



        ArrayList<Product> productArrayList= generatedProducts();

        HomeAdapter homeAdapter= new HomeAdapter(getActivity(),productArrayList);
        RecyclerView.LayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;

    }

    private ArrayList<Product> generatedProducts() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                Product product = new Product();
                product.id = i;
                product.text = "Product" + i;
                product.image = "http://www.ikea.com/PIAimages/0122106_PE278491_S5.JPG";

                productArrayList.add(product);
            } else {
                Product product = new Product();
                product.id = i;
                product.text = "Product" + i;
                product.image = "http://www.ikea.com/PIAimages/0106117_PE253936_S5.JPG";

                productArrayList.add(product);
            }

        }
        return productArrayList;
    }



}
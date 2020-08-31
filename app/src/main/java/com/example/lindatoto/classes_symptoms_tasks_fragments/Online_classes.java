package com.example.lindatoto.classes_symptoms_tasks_fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lindatoto.R;
import com.example.lindatoto.ui.home.HomeViewModel;
import com.example.lindatoto.ui.home.VideosAdapter;
import com.example.lindatoto.ui.home.MyVideos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Online_classes extends Fragment {
    private RecyclerView videoRecycler;
    private Context ctx;
    private View root;
    private HomeViewModel homeViewModel;


    public Online_classes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ctx=container.getContext();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root= inflater.inflate( R.layout.fragment_online_classes, container, false );
        videoRecycler=root.findViewById(R.id.videosRecycler);
        homeViewModel.getVideos().observe(getViewLifecycleOwner(), new Observer<ArrayList<MyVideos>>() {
            @Override
            public void onChanged(ArrayList<MyVideos> myVideos) {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ctx,2);
                videoRecycler.setLayoutManager(layoutManager);
                videoRecycler.setAdapter(new VideosAdapter(ctx,myVideos));
                videoRecycler.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

}

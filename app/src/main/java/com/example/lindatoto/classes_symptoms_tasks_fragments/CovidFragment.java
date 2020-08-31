package com.example.lindatoto.classes_symptoms_tasks_fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lindatoto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CovidFragment extends Fragment {


    public CovidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_covid, container, false );
    }

}

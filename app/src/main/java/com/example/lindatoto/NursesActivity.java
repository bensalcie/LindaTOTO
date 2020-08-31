package com.example.lindatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lindatoto.models.Patient;
import com.example.lindatoto.ui.home.HomeViewModel;
import com.example.lindatoto.ui.home.MyVideos;
import com.example.lindatoto.ui.home.NursesViewModel;
import com.example.lindatoto.ui.home.PatientsAdapter;
import com.example.lindatoto.ui.home.VideosAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class NursesActivity extends AppCompatActivity {
    private RecyclerView patientsRecycler;
    private ProgressBar patientsprogressBar;
//    private DatabaseReference patientsDatabase;
    private NursesViewModel nursesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurses);

        patientsRecycler = findViewById(R.id.patientsRecycler);
        patientsprogressBar = findViewById(R.id.patientsprogressBar);
//        patientsDatabase = FirebaseDatabase.getInstance().getReference().child("LINDATOTO/users");
        nursesViewModel = ViewModelProviders.of(this).get(NursesViewModel.class);
        loadPatients();
    }

    private void loadPatients() {
        patientsprogressBar.setVisibility(View.VISIBLE);
        nursesViewModel.getPatients().observe(this, new Observer<ArrayList<Patient>>() {
            @Override
            public void onChanged(ArrayList<Patient> myVideos) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NursesActivity.this);
                patientsRecycler.setLayoutManager(layoutManager);
                patientsRecycler.setAdapter(new PatientsAdapter(NursesActivity.this, myVideos));
                patientsRecycler.setVisibility(View.VISIBLE);
                patientsprogressBar.setVisibility(View.GONE);
            }
        });

    }
}

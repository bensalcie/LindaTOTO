package com.example.lindatoto.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lindatoto.R;
import com.example.lindatoto.classes_symptoms_tasks_fragments.CovidFragment;
import com.example.lindatoto.classes_symptoms_tasks_fragments.Daily_tasksFragment;
import com.example.lindatoto.classes_symptoms_tasks_fragments.Online_classes;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private Context ctx;
    private DatabaseReference database;
    private RecyclerView videosRecycler;
    private LinearLayout covid;
    private LinearLayout tasks;
    private LinearLayout counsel;
    private CardView search;
    private ImageView call;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ctx = container.getContext();
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        videosRecycler = root.findViewById(R.id.videosRecycler);
        LinearLayout classes = root.findViewById(R.id.classes);
        covid = root.findViewById(R.id.covid);
        tasks = root.findViewById(R.id.DailyTask);
        counsel = root.findViewById(R.id.counseling);
        call=root.findViewById( R.id.call_image );

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlineclasses();
            }
        });
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidresults();
            }
        });
        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailytasks();
            }
        });
        counsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counseling();
            }
        });
        homeViewModel.getVideos().observe(getViewLifecycleOwner(), new Observer<ArrayList<MyVideos>>() {
            @Override
            public void onChanged(ArrayList<MyVideos> myVideos) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
                videosRecycler.setLayoutManager(layoutManager);
                videosRecycler.setAdapter(new VideosAdapter(ctx,myVideos));
                videosRecycler.setVisibility(View.VISIBLE);
            }
        });


        covidresults();
        return root;
    }

    private void counseling( ) {

        PopupMenu popupMenu = new PopupMenu( getActivity().getApplicationContext(), counsel );
        popupMenu.inflate( R.menu.calls_menu );
        if (this != null) {
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.police:
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            callIntent.setData(Uri.parse("tel:" + "999"));
                            getActivity().startActivity(callIntent);
                            return true;
                        case R.id.child_help:
                        callChildHelp();
                        //
                            //calling method childhelp
                            return true;
                        case R.id.gender_violence:
                            ///
                            callGenderViolence();
                            ///
                            return true;
                        case R.id.lvct_health:
                            //
                            callLVCT();
                            //
                            return true;
                        case R.id.nacada:
                            //
                            callNACADA();
                            //
                            return true;
                        case R.id.gvrc_pyscologist:
                            //
                            callGVRC();
                            //
                            return true;
                        case R.id.siki:
                            //
                            callSIKI();
                            //
                            return true;
                        default:
                            return false;
                    }

                }
            } );

        }
    }

    private void callSIKI() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + "0796129555"));
        getActivity().startActivity(callIntent);
    }

    private void callGVRC() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + "0800720565"));
        getActivity().startActivity(callIntent);
    }

    private void callNACADA() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + "1192"));
        getActivity().startActivity(callIntent);
    }

    private void callLVCT() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + "1190"));
        getActivity().startActivity(callIntent);
    }

    private void callGenderViolence() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + "1195"));
        getActivity().startActivity(callIntent);
    }

    private void callChildHelp() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + "116"));
        getActivity().startActivity(callIntent);
    }

    private void dailytasks() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Daily_tasksFragment fragment = new Daily_tasksFragment();
        fragmentTransaction.replace(R.id.fragcontainer, fragment);
        fragmentTransaction.commit();
    }

    private void covidresults() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CovidFragment fragment = new CovidFragment();
        fragmentTransaction.replace(R.id.fragcontainer, fragment);
        fragmentTransaction.commit();
    }

    private void onlineclasses() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Online_classes fragment = new Online_classes();
        fragmentTransaction.replace(R.id.fragcontainer, fragment);
        fragmentTransaction.commit();
    }


}
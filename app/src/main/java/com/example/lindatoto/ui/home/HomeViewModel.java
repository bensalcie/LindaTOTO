package com.example.lindatoto.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MyVideos>> myVideos;
    final ArrayList<MyVideos> videoList = new ArrayList<>();

    public HomeViewModel() {
        myVideos = new MutableLiveData<>();
        DatabaseReference videosDatabase = FirebaseDatabase.getInstance().getReference().child("LINDATOTO").child("videos");
        videosDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren() ) {
//                    MyVideos video = new MyVideos(dataSnapshot.child("description").getValue().toString(), dataSnapshot.child("thumb").getValue().toString(), dataSnapshot.child("title").getValue().toString(), dataSnapshot.child("videoid").getValue().toString());
                      MyVideos video = postSnapshot.getValue(MyVideos.class);
                    videoList.add(video);
                }
                myVideos.setValue(videoList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public LiveData<ArrayList<MyVideos>> getVideos() {
        return myVideos;
    }
}
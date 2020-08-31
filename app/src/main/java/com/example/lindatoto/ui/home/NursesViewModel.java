package com.example.lindatoto.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lindatoto.models.Patient;
import com.example.lindatoto.ui.notifications.MyNotification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NursesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Patient>> myNots;
    final ArrayList<Patient> notificationList = new ArrayList<>();
    private FirebaseAuth mAuth;

    public NursesViewModel() {
        myNots = new MutableLiveData<>();
        DatabaseReference videosDatabase = FirebaseDatabase.getInstance().getReference().child("LINDATOTO/users");
        mAuth=FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser!=null){
            videosDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren() ) {
                        Patient not = postSnapshot.getValue(Patient.class);
                        notificationList.add(not);
                    }
                    myNots.setValue(notificationList);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
//        }

    }
    public LiveData<ArrayList<Patient>> getPatients() {
        return myNots;
    }
}
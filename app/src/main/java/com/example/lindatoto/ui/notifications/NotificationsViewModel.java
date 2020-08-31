package com.example.lindatoto.ui.notifications;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lindatoto.ui.home.MyVideos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MyNotification>> myNots;
    final ArrayList<MyNotification> notificationList = new ArrayList<>();
    private FirebaseAuth mAuth;

    public NotificationsViewModel() {
        myNots = new MutableLiveData<>();
        DatabaseReference videosDatabase = FirebaseDatabase.getInstance().getReference().child("LINDATOTO/nurses/notifications");
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            videosDatabase.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren() ) {
                        MyNotification not = postSnapshot.getValue(MyNotification.class);
                        notificationList.add(not);
                    }
                    myNots.setValue(notificationList);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

    }
    public LiveData<ArrayList<MyNotification>> getNotifications() {
        return myNots;
    }
}
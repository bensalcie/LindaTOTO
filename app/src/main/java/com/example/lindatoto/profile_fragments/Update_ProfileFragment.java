package com.example.lindatoto.profile_fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lindatoto.ProfileActivity;
import com.example.lindatoto.R;
import com.example.lindatoto.Users;
import com.example.lindatoto.ui.login_register_fragments.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update_ProfileFragment extends Fragment {
    private Button updateprofile;
     private EditText username, aboutuser,userAge,workingstatus,schoolstatus,location;
     private FirebaseDatabase mFirebaseDatabase;
     private DatabaseReference mDatabaseReference;
     private FirebaseAuth mAuth;
     private ProgressBar profileprogressBar;
private Context ctx;


public  Update_ProfileFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_update__profile, container, false);
        // Inflate the layout for this fragment
        ctx =container.getContext();
        updateprofile=root.findViewById( R.id.btnUpdate_profile );
        username=root.findViewById( R.id.user_profile_name );
        aboutuser=root.findViewById( R.id.about_user );
        userAge=root.findViewById( R.id.user_age );
        workingstatus=root.findViewById( R.id.user_working_status );
        schoolstatus=root.findViewById( R.id.user_school_status );
        location=root.findViewById( R.id.location );
        profileprogressBar = root.findViewById(R.id.profileprogressBar);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference().child( "LINDATOTO/users" );
        retrieveUserData(mDatabaseReference);


        updateprofile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDB();
            }
        } );
       
        return root;
    }

    private void retrieveUserData(DatabaseReference mDatabaseReference) {

    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser!=null){
        profileprogressBar.setVisibility(View.VISIBLE);
        mDatabaseReference.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();

                String age = dataSnapshot.child("age").getValue().toString();
                String location1 = dataSnapshot.child("location").getValue().toString();
                String educationstatus = dataSnapshot.child("educationstatus").getValue().toString();

                String workingstatus1 = dataSnapshot.child("workingstatus").getValue().toString();
                String profilepic = dataSnapshot.child("profilepic").getValue().toString();
                String about = dataSnapshot.child("about").getValue().toString();

                username.setText(name);
                aboutuser.setText(about);
                userAge.setText(age);
                workingstatus.setText(workingstatus1);
                schoolstatus.setText(educationstatus);
                location.setText(location1);
                profileprogressBar.setVisibility(View.GONE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    }


    private void sendToDB() {
        updateprofile.setText(getString(R.string.processing));
        FirebaseUser currentUser =mAuth.getCurrentUser();
        if (currentUser!=null){
            String UserName=username.getText().toString();
            String UserAbout=aboutuser.getText().toString();
            String UserAge=userAge.getText().toString();
            String UserWorking=workingstatus.getText().toString();
            String UserSchool=schoolstatus.getText().toString();
            String Location=location.getText().toString();
            HashMap<String,Object> userMap =new HashMap<>();
            userMap.put("name",UserName);
            userMap.put("email",currentUser.getEmail());
            userMap.put("age",UserAge);
            userMap.put("location",Location);
            userMap.put("educationstatus",UserSchool);
            userMap.put("workingstatus",UserWorking);
            userMap.put("profilepic","");
            userMap.put("about",UserAbout);
            userMap.put("userId",currentUser.getUid());
            mDatabaseReference.child(currentUser.getUid()).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        updateprofile.setText(getString(R.string.success));
                        Toast.makeText(ctx, "Profile Updated Successfully...", Toast.LENGTH_SHORT).show();
                    }else {
                        updateprofile.setText(getString(R.string.tryAgain));
                        Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}

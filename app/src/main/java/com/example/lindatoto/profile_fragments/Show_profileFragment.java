package com.example.lindatoto.profile_fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lindatoto.ProfileActivity;
import com.example.lindatoto.R;
import com.example.lindatoto.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Show_profileFragment extends Fragment {

    private TextView profile_name, about_user, work_status, school_status,location,tvage;
    private Button update_profile;
    private ImageView profile_pic;
   private Context ctx;
   private FirebaseDatabase mFirebaseDatabase;
   private DatabaseReference mDatabaseReference;
   private ChildEventListener mChildEventListener;
   ArrayList<Users> mUsers;
   private FirebaseStorage mFirebaseStorage;
   private StorageReference mStorageReference;
   private FirebaseAuth mAuth;
   private ProgressBar profileprogressBar;
    public Show_profileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_show_profile, container, false);
        // Inflate the layout for this fragment

        profile_pic=root.findViewById( R.id.profile_pic );
        profileprogressBar= root.findViewById(R.id.profileprogressBar);
        update_profile=root.findViewById( R.id.updateprofile );
        profile_name=root.findViewById( R.id.user_profile_name );
        about_user=root.findViewById( R.id.user_story );
        work_status=root.findViewById( R.id.work_status );
        school_status=root.findViewById( R.id.School_status );
        location=root.findViewById( R.id.location );
        tvage=root.findViewById( R.id.age );
        mAuth=FirebaseAuth.getInstance();
        update_profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///moves to the update profile fragment
                Update_ProfileFragment fragment=new Update_ProfileFragment();
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.senior_layout,fragment );
                fragmentTransaction.commit();

            }
        } );

        profile_pic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_profile_pic();
            }
        } );
        mFirebaseStorage=FirebaseStorage.getInstance();
        mStorageReference=mFirebaseStorage.getReference().child( "User_profile" );
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference().child( "LINDATOTO/users" );
       retrieveUserData(mDatabaseReference);
        return root;
    }



    private void update_profile_pic() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == Activity.RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profile_pic.setImageBitmap(selectedImage);
                mStorageReference.child( imageUri.getLastPathSegment() );
                mStorageReference.putFile( imageUri );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText( ctx, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(ctx, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private void retrieveUserData(DatabaseReference mDatabaseReference) {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            profileprogressBar.setVisibility(View.VISIBLE);
            mDatabaseReference.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child("name").getValue().toString();
                    String age =dataSnapshot.child("age").getValue().toString();
                    String location1 = dataSnapshot.child("location").getValue().toString();
                    String educationstatus = dataSnapshot.child("educationstatus").getValue().toString();
                    String workingstatus1 = dataSnapshot.child("workingstatus").getValue().toString();
                    String profilepic = dataSnapshot.child("profilepic").getValue().toString();
                    String about = dataSnapshot.child("about").getValue().toString();
                    profile_name.setText(name);
                    about_user.setText(about);
                    tvage.setText(age);
                    work_status.setText(workingstatus1);
                    school_status.setText(educationstatus);
                    location.setText(location1);
                    profileprogressBar.setVisibility(View.GONE);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

}

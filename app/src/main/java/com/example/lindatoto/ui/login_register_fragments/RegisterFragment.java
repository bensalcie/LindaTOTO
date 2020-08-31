package com.example.lindatoto.ui.login_register_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lindatoto.Login_RegisterActivity;
import com.example.lindatoto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.Executor;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class RegisterFragment extends Fragment {

private EditText name,email,retrypassword,password,etAge,etLocation;
private Button Register;
    private FirebaseAuth mAuth;
    private Context ctx;
    private View root;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ctx=container.getContext();
        root= inflater.inflate( R.layout.fragment_register, container, false );
        name=root.findViewById( R.id.et_name );
        email=root.findViewById( R.id.et_email );
        password=root.findViewById( R.id.et_password );
        retrypassword=root.findViewById( R.id.et_repassword );
        Register=root.findViewById( R.id.btn_register );
        etAge=root.findViewById(R.id.etAge);
        etLocation=root.findViewById(R.id.etLocation);



        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference().child( "LINDATOTO/users" );
        Register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
//                sendUserDetailsToDB();

            }
        } );
        return root;
    }

    private boolean isEmailValid(CharSequence Email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }


    private void checkUser() {
        Vibrator mVibrator = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
        String Name = name.getText().toString();
        String mailbox = email.getText().toString();
        String pass = password.getText().toString();
        String retrypass = retrypassword.getText().toString();
        String age =etAge.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (Name.isEmpty() || mailbox.isEmpty() || pass.isEmpty() || retrypass.isEmpty()) {
            Toast.makeText( getContext(), "Please fill all the fields", Toast.LENGTH_LONG ).show();
        } else {
            if (!isEmailValid( mailbox )) {
                email.setError( "Enter a valid email" );
                mVibrator.vibrate( 100 );
            } else {
         if (!pass.equals( retrypass )) {
                    Toast.makeText( getContext(), "your retry password does not match your password", Toast.LENGTH_LONG ).show();
                } else {

             if (!TextUtils.isEmpty(age) && !TextUtils.isEmpty(location)){
                 Register.setText(R.string.processing);
                 registerUser(Name,mailbox,pass,age,location);
             }
                }
            }
        }
    }


    private void updateUI(FirebaseUser currentUser, String name, String mailbox, String age, String location) {
       if (currentUser!=null){
           String userID = currentUser.getUid();
           HashMap<String,Object> userMap =new HashMap<>();
           userMap.put("name",name);
           userMap.put("email",mailbox);
           userMap.put("age",age);
           userMap.put("location",location);
           userMap.put("educationstatus","Not set");
           userMap.put("workingstatus","Not set");
           userMap.put("profilepic","Not set");
           userMap.put("about","Not set");
           userMap.put("userId",userID);
           mDatabaseReference.child(userID).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()){
                       mAuth.signOut();
                       Intent loginIntent = new Intent( ctx, Login_RegisterActivity.class );
                       startActivity( loginIntent );
                       requireActivity().finish();
                       Toast.makeText( ctx, "Please verify your details...", Toast.LENGTH_SHORT ).show();
                   }else {
                       Register.setText(getString(R.string.tryAgain));
                       Toast.makeText(ctx, "We had a problem creating your account : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }

               }
           });
       }
    }


    private void registerUser(final String name, final String mailbox, String pass, final String age, final String location) {

        mAuth.createUserWithEmailAndPassword( mailbox,pass ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Register.setText(R.string.success);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,name,mailbox,age,location);
                        } else {
                    Register.setText(R.string.error);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
            }
        } );


    }

}

package com.example.lindatoto.ui.login_register_fragments;


import android.app.Activity;
import android.app.FragmentBreadCrumbs;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lindatoto.HomeActivity;
import com.example.lindatoto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText email, password;
    private Button login;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Context ctx;
    private View root;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ctx = container.getContext();

        root = inflater.inflate( R.layout.fragment_login, container, false );

        mAuth = FirebaseAuth.getInstance();
        email = root.findViewById( R.id.et_email );
        password = root.findViewById( R.id.et_password );
        login = root.findViewById( R.id.btn_login );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        } );


        return root;
    }


    private boolean isEmailValid(CharSequence Email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher( Email ).matches();
    }

    private void checkUser() {
        Vibrator mVibrator = (Vibrator) ctx.getSystemService( Context.VIBRATOR_SERVICE );
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if (mail.isEmpty() || pass.isEmpty()) {
            if (mail.isEmpty() || !isEmailValid( mail )) {
                email.setError( "Enter a valid Email" );
                email.findFocus();
                mVibrator.vibrate( 100 );
                login.setEnabled( true );
            } else if (pass.isEmpty()) {
                password.setError( "Enter Password" );
                password.findFocus();
                mVibrator.vibrate( 100 );
                login.setEnabled( true );
            }
        } else {
            login.setText(R.string.processing);
            loginUser();
        }
    }


    private void updateUI(FirebaseUser currentUser) {
        Log.d( "LOGIN", "Login status: Logged in as "+currentUser.getEmail() );
       sendToHome();

    }

    private void sendToHome() {
        Intent homePage =new Intent( ctx, HomeActivity.class );
        startActivity( homePage );
        requireActivity().finish();
    }

    private void loginUser() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();


        mAuth.signInWithEmailAndPassword( mail,pass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    login.setText(R.string.success);
                    mUser = mAuth.getCurrentUser();
                    updateUI( mUser );
                }else{
                    login.setText(R.string.error);

                    Toast.makeText( ctx, "Authentication Failed...", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    @Override
    public void onStart() {
        super.onStart();
        mUser= mAuth.getCurrentUser();
        if (mUser!=null){
            sendToHome();
        }
    }
}
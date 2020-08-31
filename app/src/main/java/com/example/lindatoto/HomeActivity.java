package com.example.lindatoto;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.example.lindatoto.R.menu.logout_profilemenu;
import static com.example.lindatoto.R.menu.nurses_menu;
import static com.example.lindatoto.R.menu.search_menu;

public class HomeActivity extends AppCompatActivity {
private MenuInflater mInflater;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        BottomNavigationView navView = findViewById( R.id.nav_view );
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications ,R.id.navigation_hospital)
                .build();
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController( this, navController, appBarConfiguration );
        NavigationUI.setupWithNavController( navView, navController );
        mAuth = FirebaseAuth.getInstance();
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent( intent );
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate( logout_profilemenu,menu );
        MenuInflater inflater=getMenuInflater();
        inflater.inflate( search_menu,menu );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       String mail= user.getEmail();
        if (mail.equals("nurse@gmail.com")) {
            MenuInflater  mInflater=getMenuInflater();
            mInflater.inflate( nurses_menu,menu );

        }


        //
        SearchManager searchManager =
                (SearchManager) getSystemService(ctx.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                try {
                    mAuth.signOut();
                    startActivity( new Intent( HomeActivity.this,Login_RegisterActivity.class ) );
                    Toast.makeText(this, "User Sign out!", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Log.e("tag", "onClick: Exception "+e.getMessage(),e );
                }

                return true;
            case R.id.profile:
startActivity( new Intent( HomeActivity.this,ProfileActivity.class ) );

                return true;
            case R.id.patient_lists:
                startActivity( new Intent( HomeActivity.this,NursesActivity.class ) );
                return  true;

                default:
                    return super.onOptionsItemSelected( item );
        }

    }


}

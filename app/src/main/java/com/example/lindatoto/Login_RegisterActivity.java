package com.example.lindatoto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.lindatoto.ui.login_register_fragments.LoginFragment;
import com.example.lindatoto.ui.login_register_fragments.RegisterFragment;

import java.util.ArrayList;

public class Login_RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_register );
        ViewPager viewPager=findViewById( R.id.viewpager );
        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);

    }
    public class  AuthenticationPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(@NonNull FragmentManager fm) {
            super( fm );
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}

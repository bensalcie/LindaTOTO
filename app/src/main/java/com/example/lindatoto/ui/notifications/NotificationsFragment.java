package com.example.lindatoto.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lindatoto.R;
import com.example.lindatoto.ui.home.VideosAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Context ctx;
    private View root;
    private ProgressBar notificationsprogressBar;
    private RecyclerView notificationsRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of( this ).get( NotificationsViewModel.class );
        ctx = container.getContext();
         root = inflater.inflate( R.layout.fragment_notifications, container, false );
         notificationsprogressBar=root.findViewById(R.id.notificationsprogressBar);
         notificationsRecycler=root.findViewById(R.id.notificationsRecycler);
         notificationsprogressBar.setVisibility(View.VISIBLE);

        notificationsViewModel.getNotifications().observe(getViewLifecycleOwner(), new Observer<ArrayList<MyNotification>>() {
            @Override
            public void onChanged(ArrayList<MyNotification> myNotifications) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
                notificationsRecycler.setLayoutManager(layoutManager);
                notificationsRecycler.setAdapter(new NotificationsAdapter(ctx,myNotifications));
                notificationsRecycler.setVisibility(View.VISIBLE);
                notificationsprogressBar.setVisibility(View.GONE);

            }
        });
        return root;
    }
}
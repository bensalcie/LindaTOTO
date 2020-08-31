package com.example.lindatoto.ui.hospital;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lindatoto.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;

public class HospitalsFragment extends Fragment implements EasyPermissions.PermissionCallbacks  {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 993;
    private static final int RC_CAMERA_AND_LOCATION = 985;
    private Context ctx;
    private View root;
    private  LocationManager locationManager;
    private GoogleMap mMap;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap=googleMap;
            googleMap.setMapType(MAP_TYPE_HYBRID);
            LatLng sydney = new LatLng(0.5165592999999999, 35.279658647049274);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Eldoret Hospital"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

            LatLng lh = new LatLng(0.5605864, 35.2917425);
            googleMap.addMarker(new MarkerOptions().position(lh).title("Light House"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lh));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lh, 15));

            LatLng mtrh = new LatLng(0.5138078, 35.2630556);
            googleMap.addMarker(new MarkerOptions().position(mtrh).title("Moi Teaching And Referral Hospital"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(mtrh));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mtrh, 15));

            LatLng whm = new LatLng(0.5138078, 35.2630555);
            googleMap.addMarker(new MarkerOptions().position(whm).title("West Maternity Hospital"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(whm));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(whm, 15));


            LatLng eq = new LatLng(0.5165839, 35.277095);
            googleMap.addMarker(new MarkerOptions().position(eq).title("Equra Health Kenya Eldoret Comprehensive Cancer Centrel"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(eq));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eq, 15));

            LatLng eg = new LatLng(0.5165839, 35.277097);
            googleMap.addMarker(new MarkerOptions().position(eg).title("Aga Khan University Hospital - Eldoret Medical Centre"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eg, 15));

//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(eg));
            requestLocationPermisions(googleMap);



        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        assert container != null;
        ctx = container.getContext();
        root = inflater.inflate(R.layout.fragment_hospitals, container, false);
        return root;
    }

    private void requestLocationPermisions(GoogleMap googleMap) {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(ctx, perms)) {
            // Already have permission, do the thing
            // ...
            setUpMapIfNeeded(googleMap);

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.text_location_permission),
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void setUpMapIfNeeded(final GoogleMap mMap) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            assert mMap != null;
            mMap.setMyLocationEnabled(true);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location arg0) {
                        mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
                    }
                });
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        setUpMapIfNeeded(mMap);

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(ctx, "Permission denied", Toast.LENGTH_SHORT).show();
    }
}
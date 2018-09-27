package hr.josip.bilac.mapsalgebra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.popic.danije.mapsalgebra.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView tvResult;
    private LocationManager locationManager = null;
    boolean locationFetched = false;
    private static final long MIN_TIME = 0;
    private static final long MIN_DISTANCE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initLocationManager();
    }


    @OnClick(R.id.btnGoogleMaps)
    public void btnGoogleMaps() {
//        Intent i = new Intent(MainActivity.this, MapsActivity.class);
//        startActivity(i);
    }

    @OnClick(R.id.btnGps)
    public void btnGps() {
        locate(LocationManager.GPS_PROVIDER);
    }

    @OnClick(R.id.btnGpsNetwork)
    public void btnGpsNetwork() {
        locate(LocationManager.NETWORK_PROVIDER);
    }

    private void initLocationManager() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    private void locate(String provider) {
        locationFetched = false;
        locationManager.requestLocationUpdates(provider, MIN_TIME, MIN_DISTANCE,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        if (!locationFetched) {
                            String result = "" + location.getLatitude() + (char) 13 + (char) 10 +
                                    location.getLongitude() + (char) 13 + (char) 10 +
                                    location.getAccuracy();
                            tvResult.setText(result);
                            locationFetched = true;
                            locationManager.removeUpdates(this);
                        }
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {
                    }

                    @Override
                    public void onProviderEnabled(String s) {
                    }

                    @Override
                    public void onProviderDisabled(String s) {
                    }
                });
    }
}

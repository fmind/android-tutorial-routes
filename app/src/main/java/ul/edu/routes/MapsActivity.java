package ul.edu.routes;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, RoutingListener {
    private static final String TAG = "MapsActivity";

    private GoogleMap map;
    private MarkerOptions origin;
    private MarkerOptions destination;
    private ArrayList<Polyline> polylines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // initialize the object attributes
        map = null;
        origin = null;
        destination = null;
        polylines = new ArrayList<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapLongClickListener(this);

        // create the origin marker
        LatLng point = new LatLng(49.626271, 6.158536);
        origin = new MarkerOptions().position(point).title("Origin");
        origin.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));

        // add the marker to the map
        markOrigin();
    }

    @Override
    public void onMapLongClick(LatLng point) {
        // remove all markers from the map
        map.clear();

        // create the destination marker
        destination = new MarkerOptions().position(point).title("Destination");
        destination.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));

        // add the both markers to the map
        markOrigin();
        markDestination();

        // display the route and log its duration/distance
        traceRoute(origin, destination);
    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        // remove the previous polylines
        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        // create a new array of polylines
        polylines = new ArrayList<>();

        // select the shortest path
        Route path = route.get(shortestRouteIndex);

        // add polylines to the map and the array
        PolylineOptions polyOptions = new PolylineOptions();
        polyOptions.color(R.color.colorPrimary);
        polyOptions.width(13);
        polyOptions.addAll(path.getPoints());
        Polyline polyline = map.addPolyline(polyOptions);
        polylines.add(polyline);

        // log the distance and duration
        Log.i(TAG, "[Route] distance: " + path.getDistanceText() + ", duration: " + path.getDurationValue() + " sec");
    }

    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingCancelled() {

    }

    private void markOrigin() {
        // safe guard
        if (origin == null)
            return;

        // add the marker to the map
        map.addMarker(origin);

        // center the camera with a zoom of 15
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(origin.getPosition(), 15));
    }

    private void markDestination() {
        // safe guard
        if (destination == null)
            return;

        // add the marker to the map
        map.addMarker(destination);

        // center the camera with a zoom of 15
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination.getPosition(), 15));
    }

    private void traceRoute(MarkerOptions origin, MarkerOptions destination) {
        // initialize an asynchronous request using the direction api
        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.WALKING)
                .withListener(this)
                .waypoints(origin.getPosition(), destination.getPosition())
                .build();

        // execute the request
        routing.execute();
    }
}

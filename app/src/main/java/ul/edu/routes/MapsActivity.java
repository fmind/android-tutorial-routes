package ul.edu.routes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {
    private static final String TAG = "MapsActivity";

    private MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: inflate and create map

        // TODO: add default zoom buttons

        // TODO: coordinate of Maison du Nombre, Luxembourg

        // TODO: set the default zoom and position of the map

        // TODO: add a marker at the position of Maison du Nombre

        // TODO: setup an event listener to add and remove overlay items

        // TODO add an overlay to the map
    }

    @Override
    public void onResume(){
        super.onResume();

        map.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();

        map.onPause();
    }
}

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

        //inflate and create map
        setContentView(R.layout.activity_maps);
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        // add default zoom buttons
        map.setMultiTouchControls(true);
        map.setBuiltInZoomControls(true);

        // coordinate of Maison du Nombre, Luxembourg
        GeoPoint startPoint = new GeoPoint(49.50381,5.94776);

        // set the default zoom and position of the map
        IMapController mapController = map.getController();
        mapController.setCenter(startPoint);
        mapController.setZoom(20.0);

        // add a marker at the position of Maison du Nombre
        final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem("Maison du Nombre", "Belval", startPoint));

        // setup an event listener to add and remove overlay items
        final ItemizedOverlayWithFocus<OverlayItem> overlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items,
                new OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        Toast.makeText(getApplicationContext(), "SINGLE TAP: " + Integer.toString(index), Toast.LENGTH_LONG).show();
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Toast.makeText(getApplicationContext(), "LONG PRESS:" + Integer.toString(index), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
        overlay.setFocusItemsOnTap(true);

        // add an overlay to the map
        map.getOverlays().add(overlay);
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

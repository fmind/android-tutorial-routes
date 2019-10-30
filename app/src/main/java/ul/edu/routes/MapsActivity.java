package ul.edu.routes;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
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

        Context context = getApplicationContext();
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));

        // TODO: inflate and create map
        setContentView(R.layout.activity_maps);
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        // TODO: add default zoom buttons
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        // TODO: coordinate of Maison du Nombre, Luxembourg
        GeoPoint startPoint = new GeoPoint(49.503759, 5.948007);

        // TODO: set the default zoom and position of the map
        IMapController mapController = map.getController();
        mapController.setCenter(startPoint);
        mapController.setZoom(13.0);

        // TODO: add a marker at the position of Maison du Nombre
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem("Maison du Nombre", "You are here", startPoint));

        // TODO: setup an event listener to add and remove overlay items
        ItemizedOverlayWithFocus<OverlayItem> overlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                }, context);

        // TODO add an overlay to the map
        overlay.setFocusItemsOnTap(true);
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

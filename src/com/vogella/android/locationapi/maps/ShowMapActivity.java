package com.vogella.android.locationapi.maps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapActivity extends Activity {
  static final LatLng TC = new LatLng(10.35410, 123.91145);
  static final LatLng MAIN = new LatLng(10.30046, 123.88822);
  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activitymap);
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    Marker TC = map.addMarker(new MarkerOptions().position(TC).title("TC"));
    Marker MAIN = map.addMarker(new MarkerOptions().position(MAIN).title("MAIN").snippet("MAIN BASE of Operations").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
    
   
    // Move the camera instantly to TC with with maximum zoom
    map.animateCamera(CameraUpdateFactory.newLatLngZoom(TC, map.getMaxZoomLevel()));

    // setting up listeners
    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
      
          AlertDialog.Builder alert = new AlertDialog.Builder(ShowMapActivity.this);
          
      @Override
      public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
        alert.setTitle("Select Destination: ");
        
        if(marker.equals(TC)){
          alert.setMessage("Destination Selection: MAIN")
          .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
        	  
            public void onClick(DialogInterface dialog,int id) {
              // Animate to usc main
              map.animateCamera(CameraUpdateFactory.newLatLngZoom(MAIN, map.getMaxZoomLevel()));
            }
            })
          .setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {}
          });
        } 
        else 
        {
          alert.setMessage("Destination Selection: TC")
          .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
              // Animate to usc main
              map.animateCamera(CameraUpdateFactory.newLatLngZoom(TC, map.getMaxZoomLevel()));
            }
            })
          .setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {}
          });
          
        }
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
        
        return false;
      }
    });
    }

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.show_map, menu);
    return true;
  }

} 
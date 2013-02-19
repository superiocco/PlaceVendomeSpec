package com.example.placevendomespec;

import java.io.IOException;

import com.example.grilla.DataBaseHelper;
import com.example.guardarSelecciones.Recomendaciones;
import com.example.placeVendome.tiendas.DataBaseTiendaHelper;
import com.example.placeVendome.tiendas.Tienda;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.database.SQLException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MapaStock extends Activity implements LocationListener {

	private GoogleMap map;
	private LocationManager locationManager;
	private static final long MIN_TIME = 400;
	private static final float MIN_DISTANCE = 1000;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
     // TODO Auto-generated method stub
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
       
        if(savedInstanceState==null){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
	    
	    
        }
        
        else{
    		LatLng latLng = new LatLng(savedInstanceState.getDouble("latitud"),savedInstanceState.getDouble("longitud"));
    	    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,savedInstanceState.getFloat("zoom"));
    	    map.animateCamera(cameraUpdate);
        }
        
	    Recomendaciones.getStocks(this);

        

    }


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
	    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 9);
	    map.animateCamera(cameraUpdate);
	    map.addMarker(new MarkerOptions()
	    .position(latLng)
		.title("Tienda Actual")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerpink)));
	    
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	
	public void  setMarkers(String result){
		
		int i=0;
		Gson gson = new Gson();
		final Stock[] recibidos = gson.fromJson(result, Stock[].class);
		DataBaseTiendaHelper myDbHelper = new DataBaseTiendaHelper(this);
        myDbHelper = new DataBaseTiendaHelper(this);
 
        try {
        	Log.d("DB", "TRY");
        	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 	Log.d("DB", "IO");

 		throw new Error("Unable to create database");
 
 	}
 
 	try {
 	Log.d("DB", "TRY OPEN");

 		myDbHelper.openDataBase();
 
 	}catch(SQLException sqle){
 	Log.d("DB", "FAIL OPEN");

 		throw sqle;
 
 	}
 	Tienda tienda = new Tienda();
 	for(i=0;i<recibidos.length;i++){
 		tienda= myDbHelper.getTienda(recibidos[i].getTienda().trim());
 		if(tienda!=null){
 		if (recibidos[i].getCantidad()!=0){
 		map.addMarker(new MarkerOptions()
 		.position(new LatLng(tienda.getLatitud(), tienda.getLongitud()))
 		.title(tienda.getNombre())
 		.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
 		.snippet(tienda.getDireccion()+" " +tienda.getUbicacion() + " Stock: " + Integer.toString(recibidos[i].getCantidad())));	
 	}
 		}
 		
 	}
	
 	myDbHelper.close();

 	
	}
	
	public void showError(String result){
		
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
	
		outState.putDouble("latitud", map.getCameraPosition().target.latitude);
		outState.putDouble("longitud", map.getCameraPosition().target.longitude);
		outState.putFloat("zoom", map.getCameraPosition().zoom);
		super.onSaveInstanceState(outState);
		
	}

	

	
	
}

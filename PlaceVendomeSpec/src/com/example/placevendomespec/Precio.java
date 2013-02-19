package com.example.placevendomespec;


import com.example.guardarSelecciones.Selecciones;
import com.example.placevendomespec.RangeSeekBar.OnRangeSeekBarChangeListener;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Precio extends Activity{

	Context context;
	private static SectionsPageAdapter Adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("ACTIVITY", "On Create View");
		setContentView(R.layout.precio);	
		Log.d("ACTIVITY", "Inflater");
		
		ActionBar bar = getActionBar();

		bar.setTitle("Precio");
		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);


		
		setListeners();

			
		Log.d("ACTIVITY", "Root");

	}
	


	private void setListeners(){
		RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(0, 300000, this);
		
		
		seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {
		        @Override
		        public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
		                // handle changed range values
		            TextView valor = (TextView)findViewById(R.id.precio);    
		    		valor.setText("Valor ("+ String.valueOf(minValue) +"-"+ String.valueOf(maxValue) +")");
		    		Selecciones.setPrecioMinimo(minValue);
		    		Selecciones.setPrecioMaximo(maxValue);
		        	Log.i("Precio", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
		        }
		});
		TextView valor = new TextView(this);
        valor = (TextView)findViewById(R.id.precio);    
		
		seekBar.setSelectedMaxValue(Selecciones.getPrecioMaximo());
		seekBar.setSelectedMinValue(Selecciones.getPrecioMinimo());
		valor.setText("Valor ("+ String.valueOf(seekBar.getSelectedMinValue()) +"-"+ String.valueOf(seekBar.getSelectedMaxValue()) +")");

		seekBar.setMinimumWidth(10000);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.bar);
		layout.addView(seekBar);


	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		
		case android.R.id.home:
			Log.d("click","home");
			final Intent home = new Intent(this.getApplication(), MainActivity.class);
			startActivity(home);
			MainActivity.mSectionsPagerAdapter.setTrans(0);
			finish();

		
		}
		
		
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.next, menu);
        return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.siguiente:
			final Intent carac = new Intent(this.getApplication(), Resultados.class);
			startActivity(carac);
			finish();
			return true;
			
		}
		return false;
	}
	

	
	
}

package com.example.placevendomespec;


import com.example.placevendomespec.RangeSeekBar.OnRangeSeekBarChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Peso extends Activity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.peso);
		getContents();
	
	}

	private void getContents(){

		RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(10, 120, this.getBaseContext());
		
		
		seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {
		        @Override
		        public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
		                // handle changed range values
		            TextView valor = (TextView)findViewById(R.id.pesoSel);    
		    		valor.setText("Peso ("+ String.valueOf(minValue) +"-"+ String.valueOf(maxValue) +")");

		        	Log.i("Precio", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
		        }
		});
		TextView valor = new TextView(this);
        valor = (TextView)findViewById(R.id.pesoSel);    
		valor.setText("Peso ("+ String.valueOf(seekBar.getSelectedMinValue()) +"-"+ String.valueOf(seekBar.getSelectedMaxValue()) +")");
		
		Button next = (Button) findViewById(R.id.next2);
		next.setText("Guardar");
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
					finish();
				
			}
		});
		seekBar.setMinimumWidth(500);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.barw);
		layout.addView(seekBar);


	}
	

}

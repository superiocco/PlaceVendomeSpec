package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DistanciaPupilar extends DialogFragment {

	static TextView textToChange;
	int value;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.list, container, false);

		getContents(v);
		return v;
	}

	static DistanciaPupilar newInstance(TextView text){
		DistanciaPupilar f = new DistanciaPupilar();
		textToChange = text;
		return f;
		
	}
	
	private void getContents(View v){
		
		String[] marcas;
		TextView[] Lista;
		int i;
		Lista = new TextView[31];
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.lista);
		for(i=0;i<30;i++){
			
			Lista[i] = new TextView(this.getActivity()); 
			Lista[i].setText(Integer.toString(i+50));
			Lista[i].setTextColor(Color.WHITE);
			Lista[i].setId(100+i);
			Lista[i].setTextSize(40);
			layout.addView(Lista[i]);
			
			Lista[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					int id = v.getId();
					v.setBackgroundColor(Color.YELLOW);
					Log.d("Touch", Integer.toString(id));
					value = (int) (id-50);
					if(value>0.0){
						textToChange.setTextColor(Color.BLUE);
					}
					if(value<0.0){
						textToChange.setTextColor(Color.RED);

						
					}
					textToChange.setText(Integer.toString(value));
					Selecciones.setDistanciaPupilar(value);
					closeDialog();
					
				}
			});
			
		}
		
	
		
	}
	
	public void closeDialog(){
		
		dismiss();
	}

	
}

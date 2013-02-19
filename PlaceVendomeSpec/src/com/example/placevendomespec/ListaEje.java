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

public class ListaEje extends DialogFragment {
	
	
	static TextView textToChange;
	float value;
	static int cercaLejos;
	static int botonId;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.list, container, false);

		getContents(v);
		return v;
	}

	static ListaEje newInstance(TextView text, int cerca, int id){
		ListaEje f = new ListaEje();
		textToChange = text;
		cercaLejos = cerca;
		botonId = id;
		return f;
		
	}
	
	private void getContents(View v){
		
		String[] marcas;
		TextView[] Lista;
		int i;
		int size = getResources().getStringArray(R.array.Grados).length;
		marcas = new String[size];
		Lista = new TextView[size];
		marcas = getResources().getStringArray(R.array.Grados);
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.lista);
		for(i=0;i<size;i++){
			
			Lista[i] = new TextView(this.getActivity()); 
			Lista[i].setText(marcas[i]);
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
					value = (int) (5*(id-100));
					if(value>0.0){
						textToChange.setTextColor(Color.BLUE);
					}
					if(value<0.0){
						textToChange.setTextColor(Color.RED);

						
					}
					textToChange.setText(Integer.toString((int)value));
						if(botonId == 1)
						Selecciones.setEjeDerecho(value);
						if(botonId == 2)
						Selecciones.setEjeIzquierdo(value);
							
							
					
					closeDialog();
					
				}
			});
			
		}
		
	
		
	}
	
	public void closeDialog(){
		
		dismiss();
	}

}

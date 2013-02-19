package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Lista extends DialogFragment {
	
	static TextView textToChange;
	float value;
	static int cercaLejos;
	static int botonId;
	ScrollView myView;
	static Receta receta;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.list, container, false);

		getContents(v);
		myView = (ScrollView)v.findViewById(R.id.scrollLista);
		myView.post(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				myView.scrollBy(0,3200);
			}
			
			
		});
	
		
		return v;
	}
	


	static Lista newInstance(TextView text, int cerca, int id, Receta r){
		Lista f = new Lista();
		textToChange = text;
		cercaLejos = cerca;
		botonId = id;
		receta = r;

		return f;
		
	}
	
	private void getContents(View v){
		
		String[] marcas;
		TextView[] Lista;
		int i;
		int size = getResources().getStringArray(R.array.Dioptria).length;
		marcas = new String[size];
		Lista = new TextView[size];
		marcas = getResources().getStringArray(R.array.Dioptria);
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.lista);
		
		for(i=0;i<size;i++){
			
			Lista[i] = new TextView(this.getActivity()); 
			if(Float.parseFloat(marcas[size-i-1])>0){
			Lista[i].setText('+'+marcas[size-i-1]);
			}
			else{
				
				Lista[i].setText(marcas[size - i-1]);

			}
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
					
					value = (float) -(((id-100)*0.25)-12.0);
					if(value>0.0){
						textToChange.setTextColor(Color.BLUE);
					}
					if(value<0.0){
						textToChange.setTextColor(Color.RED);

						
					}
					if(value==0.0){
						
						textToChange.setTextColor(Color.BLACK);
						value=0;
						
					}
					
					if(value>0){
					textToChange.setText("+" + Float.toString(value));
					}
					else{
					textToChange.setText(Float.toString(value));	
					}
					
					if(cercaLejos==0){
						if(botonId==1)
						Selecciones.setEsferaDerechaCerca(value);
						if(botonId==2)
						Selecciones.setCilindroDerecho(value);
						if(botonId==4)
						Selecciones.setEsferaIzquierdaCerca(value);		
						if(botonId==5)
						Selecciones.setCilindroIzquierdo(value);
					}
					
					if(cercaLejos==1){
						
						if(botonId==1)
							Selecciones.setEsferaDerechaLejos(value);
							if(botonId==2)
							Selecciones.setCilindroDerecho(value);
							if(botonId==4)
							Selecciones.setEsferaIzquierdaLejos(value);	
							if(botonId==5)
							Selecciones.setCilindroIzquierdo(value);

						
						
						
					}
					receta.calcularEnfermedad();
					closeDialog();
					
					
				}
			});


		}	
	}
	
	public void closeDialog(){
		
		dismiss();
	}

}

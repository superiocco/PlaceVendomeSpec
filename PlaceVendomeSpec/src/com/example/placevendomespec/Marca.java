package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.graphics.Color;


public class Marca extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

       setContentView(R.layout.marca);

		getContents();
	}


	
	private void getContents(){
		
		String[] marcas;
		CheckBox[] Lista;
		int i;
		final int size = getResources().getStringArray(R.array.Marca).length;
		marcas = new String[size];
		Lista = new CheckBox[size];
		marcas = getResources().getStringArray(R.array.Marca);
		Button  next = new Button(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.listaMarcas);
		LinearLayout all = (LinearLayout)findViewById(R.id.all);
		for(i=0;i<size;i++){
			
			Lista[i] = new CheckBox(this); 
			Lista[i].setText(marcas[i]);
			Lista[i].setTextColor(Color.BLACK);
			Lista[i].setChecked(false);
			Lista[i].setId(100+i);
			layout.addView(Lista[i]);
			Log.d("Layout added", marcas[i]);
			
		}
		
		next.setText("Guardar");
		next.setTextColor(Color.BLACK);
		all.addView(next);
		
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String seleccionados = "";
				int i=0;
				int prim=0;
				for(i=0;i<size;i++){
					CheckBox marca = (CheckBox) findViewById(100+i);
					if(marca.isChecked()==true){
						if(prim==0){
							seleccionados=(String) marca.getText();
							
						}
						
						else{
							seleccionados = seleccionados +","+ marca.getText();
						}
						
					}
				}
				
				Selecciones.setMarcas(seleccionados);
				finish();
				
			}
		});
		
	}
	
	
}

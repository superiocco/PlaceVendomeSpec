package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class Calidad extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.calidad);

		getContents();
		
	}




	
	private void getContents(){
		
		String[] calidades;
		final CheckBox[] Lista;
		int i;
		final int size = getResources().getStringArray(R.array.Calidad).length;
		calidades = new String[size];
		Lista = new CheckBox[size];
		calidades = getResources().getStringArray(R.array.Calidad);
		Button  next = new Button(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.ListaCalidad);
		for(i=0;i<size;i++){
			
			Lista[i] = new CheckBox(this); 
			Lista[i].setText(calidades[i]);
			Lista[i].setTextColor(Color.BLACK);
			Lista[i].setChecked(true);
			layout.addView(Lista[i]);
			Log.d("Layout added", calidades[i]);
			
		}
		
		next.setText("Guardar");
		next.setTextColor(Color.BLACK);
		layout.addView(next);
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String selected="";
				int j=0;
				// TODO Auto-generated method stub
				for(j=0;j<size;j++){
					
					
					if(Lista[j].isChecked()){
						selected=selected+Lista[j];
					if(j!=size-1){
					selected=selected+',';
					}
					
					
					Log.d("Layout added", (String)Lista[j].getText());
					
					}
				}
				Selecciones.setCalidad(selected);
				finish();
				
				
			}
		});
		
	}
	
	
}

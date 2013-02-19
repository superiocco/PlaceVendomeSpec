package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.graphics.Color;

public class Colores extends Activity {
	


 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color);
		getContents();
	}

	private void getContents(){
		
		String[] colores;
		CheckBox[] Lista;
		int i;
		final int size = getResources().getStringArray(R.array.Colores).length;
		colores = new String[size];
		Lista = new CheckBox[size];
		colores = getResources().getStringArray(R.array.Colores);
		Button  next = new Button(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.ListaColores);
		for(i=0;i<size;i++){
			
			Lista[i] = new CheckBox(this); 
			Lista[i].setText(colores[i]);
			Lista[i].setTextColor(Color.BLACK);
			Lista[i].setId(100+i);
			Lista[i].setChecked(true);

			layout.addView(Lista[i]);
			Log.d("Layout added", colores[i]);
			
		}
		
		next.setText("Guardar");
		//next.setTextColor();
		layout.addView(next);
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
				
				Selecciones.setColores(seleccionados);

				finish();
			}
		});
		
	}

}

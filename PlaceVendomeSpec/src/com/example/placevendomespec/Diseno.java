package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.Activity;
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

public class Diseno extends Activity{

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.diseno);
		getContents();
	}


	
	

	
	private void getContents(){
		
		String[] formas;
		CheckBox[] Lista;
		int i;
		final int size = getResources().getStringArray(R.array.Diseno).length;
		formas = new String[size];
		Lista = new CheckBox[size];
		formas = getResources().getStringArray(R.array.Diseno);
		Button  next = new Button(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.ListaDiseno);
		for(i=0;i<size;i++){
			
			Lista[i] = new CheckBox(this); 
			Lista[i].setText(formas[i]);
			Lista[i].setTextColor(Color.BLACK);
			Lista[i].setChecked(true);
			Lista[i].setId(100+i);
			layout.addView(Lista[i]);
			Log.d("Layout added", formas[i]);
			
		}
		
		next.setText("Guardar");
		next.setTextColor(Color.BLACK);
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
				
				Selecciones.setDiseno(seleccionados);
				finish();
				
			}
		});
		
	}
	
}

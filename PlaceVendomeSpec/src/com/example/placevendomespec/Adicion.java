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
import android.widget.ScrollView;
import android.widget.TextView;

public class Adicion extends DialogFragment {
	
	static TextView textToChange;
	static TextView textToChange2;
	float value;
	ScrollView myView;
	static Receta r;
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
				myView.scrollBy(0,1000);
			}
			
			
		});
		return v;
	}

	static Adicion newInstance(TextView text, TextView text2, Receta rec){
		Adicion f = new Adicion();
		textToChange = text;
		textToChange2 = text2;
		r = rec;
		return f;
		
	}
	
	private void getContents(View v){
		
		String[] marcas;
		TextView[] Lista;
		int i;
		Lista = new TextView[41];
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.lista);
		for(i=0;i<40;i++){
			
			Lista[i] = new TextView(this.getActivity()); 
			
			Lista[i].setText(Float.toString((float) ((float) (i*0.5) - 10.0)));
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
					id = id-100;
					value = (float) ((float) (id*0.5)-10.0);
					if(value>0.0){
						textToChange2.setTextColor(Color.BLUE);
						textToChange.setTextColor(Color.BLUE);
						textToChange.setText("+" + Float.toString(value));
						textToChange2.setText("+" + Float.toString(value));

					}
					if(value<0.0){
						textToChange2.setTextColor(Color.RED);
						textToChange.setTextColor(Color.RED);
						textToChange.setText(Float.toString(value));
						textToChange2.setText(Float.toString(value));
	
					}
					
					if(value==0.0){
						
						textToChange2.setTextColor(Color.BLACK);
						textToChange.setTextColor(Color.BLACK);
						textToChange.setText(Float.toString(value));
						textToChange2.setText(Float.toString(value));
					}
					
					Selecciones.setAdicion(value);
					r.calcularEnfermedad();
					closeDialog();
					
				}
			});
			
		}
		
	
		
	}
	
	public void closeDialog(){
		
		dismiss();
	}

}

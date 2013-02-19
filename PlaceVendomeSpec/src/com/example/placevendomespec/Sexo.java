package com.example.placevendomespec;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guardarSelecciones.*;

public class Sexo extends Fragment {
	
	private static SectionsPageAdapter Adapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View root = (View) inflater.inflate(R.layout.sexo, null);	
		Log.d("Activity", "Sexo");
		Button hombre = (Button) root.findViewById(R.id.hombre);
		Button mujer = (Button) root.findViewById(R.id.mujer);
		

		
		hombre.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Selecciones.setSexo("h");
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();

				
			}
		});
		
		mujer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Selecciones.setSexo("f");
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();


				
			}
		});
		
		return root;
		
		
	}
	
	
	public static Fragment newInstance(SectionsPageAdapter sec){
		
		Sexo f = new Sexo();
		Adapter=sec;
		return f;
		
		
	}
	
	

}

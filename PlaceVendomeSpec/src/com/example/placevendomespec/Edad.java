package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Edad extends Fragment {
	
	private static SectionsPageAdapter Adapter;
	
	public static Fragment newInstance(SectionsPageAdapter sec){
		
		Edad f = new Edad();
		Adapter=sec;
		return f;	
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View root = (View) inflater.inflate(R.layout.edad, null);	
		Log.d("Activity", "Sexo");
				
		Button adultoJoven = (Button)root.findViewById(R.id.adultoJoven);
		Button adultoMayor = (Button)root.findViewById(R.id.adultoMayor);
		Button nino = (Button)root.findViewById(R.id.Nino);

		adultoJoven.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Selecciones.setEdad("adulto");
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();
				
			}
		});
		adultoMayor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				
				Selecciones.setEdad("adultoMayor");
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();
				
			}
		});
		
		nino.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Selecciones.setEdad("nino");
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();
				
			}
		});
		
		
		
		return root;
	}
	
}

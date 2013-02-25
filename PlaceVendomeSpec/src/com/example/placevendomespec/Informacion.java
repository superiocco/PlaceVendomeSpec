package com.example.placevendomespec;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Informacion extends Fragment implements android.view.View.OnClickListener {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
	ViewGroup root = (ViewGroup) inflater.inflate(R.layout.informacion, null);
	Button cristales = (Button) root.findViewById(R.id.cristales);
	cristales.setOnClickListener(Informacion.this);
	Button dioptrias = (Button) root.findViewById(R.id.dioptrias);
	dioptrias.setOnClickListener(Informacion.this);
		
		return root;
		
	}
	
	public static Fragment newInstance() {
		Informacion f = new Informacion();	
		
		return f;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id){
		
		case R.id.cristales:
			
			Intent cristales = new Intent(getActivity(),MenuCristales.class);
			startActivity(cristales);
			
			break;
			
		case R.id.dioptrias:
			
			Intent dioptrias = new Intent(getActivity(),MenuDioptrias.class);
			startActivity(dioptrias);
			break;
		
		
		}
		
	}

}

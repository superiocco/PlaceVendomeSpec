package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Tipo extends Fragment {
	
	
	private static SectionsPageAdapter Adapter;

	
	public static Fragment newInstance(SectionsPageAdapter sec){
		
		Tipo f = new Tipo();
		Adapter=sec;
		return f;	
	}

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = (View) inflater.inflate(R.layout.tipo, null);
		Button sol = (Button)root.findViewById(R.id.sol);
		Button opticos =  (Button) root.findViewById(R.id.opticos);
		Button sku = (Button) root.findViewById(R.id.button1);

		sku.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Sku p = Sku.newInstance();
				p.show(ft, "dialog");
				
			}
		});
		sol.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Selecciones.setTipo("sol");
				Selecciones.setBases("0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,6.5,7.0,7.5,8.0,8.5,9.0");
				Adapter.setTrans(Adapter.getTrans()+2);
				Adapter.notifyDataSetChanged();
				
			}
		});
		
		opticos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Selecciones.setTipo("opticos");
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();
				
			}
		});
		
		return root;
		
	}
	
	
}

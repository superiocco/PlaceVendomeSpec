package com.example.placevendomespec;


import com.example.guardarSelecciones.Selecciones;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class CaraActivity extends Fragment {
	

	private static SectionsPageAdapter Adapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("ACTIVITY", "On Create View Cara");
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.cara, null);	
		Log.d("ACTIVITY", "Inflater Cara");


		
		
		setClickListeners(root);

			
		Log.d("ACTIVITY", "Root Cara");

			return root;
	}

	public static Fragment newInstance(SectionsPageAdapter sec) {
		CaraActivity f = new CaraActivity();	
		Adapter=sec;
		return f;
	}

	

  private void setClickListeners (final View root){
	  
	 final Button ovalada = (Button)root.findViewById(R.id.ovalada);
	 final Button cuadrada = (Button)root.findViewById(R.id.cuadrada);
	 final Button redonda = (Button)root.findViewById(R.id.redonda);
	 final Button corazon = (Button)root.findViewById(R.id.corazon);
	 final Button siguiente = (Button)root.findViewById(R.id.button1);
	 final Button rectangular = (Button)root.findViewById(R.id.rectangular);
	 final Intent resultados = new Intent(this.getActivity(),Resultados.class);
	 
	 siguiente.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Selecciones.setCara("");
			Selecciones.setResultsLoaded(0);
			/*Adapter.setTrans(Adapter.getTrans()+1);
			Adapter.notifyDataSetChanged();	*/
			startActivity(resultados);
			getActivity().finish();
			
			
		}
	});
	 

	 rectangular.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d("Click", "Rectangular");
			// TODO Auto-generated method stub
			Selecciones.setCara("rectangular");
			Selecciones.setResultsLoaded(0);
			//Adapter.setTrans(Adapter.getTrans()+1);
			//Adapter.notifyDataSetChanged();
			startActivity(resultados);
			getActivity().finish();
		}
	});

	 ovalada.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Log.d("Click", "Ovalada");
			// TODO Auto-generated method stub
			Selecciones.setCara("ovalada");
			Selecciones.setResultsLoaded(0);
			//Adapter.setTrans(Adapter.getTrans()+1);
			//Adapter.notifyDataSetChanged();
			startActivity(resultados);
			getActivity().finish();

			

		}
	});
	  
	 cuadrada.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Selecciones.setCara("cuadrada");
				Selecciones.setResultsLoaded(0);
				//Adapter.setTrans(Adapter.getTrans()+1);
				//Adapter.notifyDataSetChanged();
				Adapter.setTrans(Adapter.getTrans()+1);
				startActivity(resultados);
				getActivity().finish();



			}
		});
	 
	 
	 redonda.setOnClickListener(new View.OnClickListener() {
		 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Selecciones.setCara("redonda");
				Selecciones.setResultsLoaded(0);
				//Adapter.setTrans(Adapter.getTrans()+1);

				// Commit the transaction
				//Adapter.notifyDataSetChanged();
				Adapter.setTrans(Adapter.getTrans()+1);
				startActivity(resultados);
				getActivity().finish();

			}
		});
	 
	 

	 
	 corazon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Selecciones.setCara("corazon");
				Selecciones.setResultsLoaded(0);
				//Adapter.setTrans(Adapter.getTrans()+1);;
				//Adapter.notifyDataSetChanged();
				Adapter.setTrans(Adapter.getTrans()+1);
				startActivity(resultados);
				getActivity().finish();



			}
		});
  
		Log.d("ACTIVITY", "Listeners Cara");
		
  
  }

  	
  

   
  
	  
  }
  
   


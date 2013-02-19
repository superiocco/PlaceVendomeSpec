package com.example.placevendomespec;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Presupuesto extends Fragment {

	private static SectionsPageAdapter Adapter;

	public static Fragment newInstance(SectionsPageAdapter sec) {
		
		Presupuesto f = new Presupuesto();	
		Adapter = sec;
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("ACTIVITY", "On Create View");
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.presupuesto, null);	
		Log.d("ACTIVITY", "Inflater");
		setListeners(root);


		

			
		Log.d("ACTIVITY", "Root");

			return root;
	}
	

	private void setListeners (final ViewGroup root){
		
		final Button si = (Button) root.findViewById(R.id.Si); 
		final Button no = (Button) root.findViewById(R.id.No);

		
		si.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Clck", "Boton Si");
				Adapter.setTrans(4);
				Adapter.notifyDataSetChanged();

				
			}
		});
		
		no.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Click", "Boton No");
				Log.d("Clck", "Boton Si");
				Adapter.setTrans(5);
				
				Adapter.notifyDataSetChanged();


			}
		});
		
		
		
	}
	

	
}

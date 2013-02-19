package com.example.placevendomespec;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Informacion extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
	ViewGroup root = (ViewGroup) inflater.inflate(R.layout.informacion, null);
		
		return root;
		
	}
	
	public static Fragment newInstance() {
		Informacion f = new Informacion();	
		
		return f;
	}

}

package com.example.placevendomespec;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuLeft extends Fragment {
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View root = (View) inflater.inflate(R.layout.menuleft, null);
		return root;
	}
	
	public static Fragment newInstance(){
		
		return new MenuLeft();
		
	}

}

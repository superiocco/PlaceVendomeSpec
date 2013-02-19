package com.example.placevendomespec;

import java.util.Locale;

import com.example.guardarSelecciones.Selecciones;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Sku extends DialogFragment {

	private static String skuDig="";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.sku, container, false);
        	Button buscar = (Button) v.findViewById(R.id.button1);
        	final EditText sku = (EditText) v.findViewById(R.id.editText1);
        	sku.setText(skuDig);
        	sku.setOnKeyListener(new View.OnKeyListener() {
				
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
				
					skuDig = sku.getText().toString();// TODO Auto-generated method stub
					return false;
				}
			});
        	buscar.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					skuDig = sku.getText().toString();
					
					buscarSimilares(skuDig);
					
				}
			});
        	return v;
        }
	
	
	static Sku newInstance (){

		Sku f = new Sku();
		
		return f;
				
	}
	
	public void buscarSimilares(String skuCode){
		Selecciones.setSkuElegido(skuCode.toUpperCase());
		final Intent i = new Intent(this.getActivity(), Similares.class);
		startActivity(i);
		this.getActivity().finish();
		Log.d("Buscar Similares", skuCode);
		
	}
	
	
}

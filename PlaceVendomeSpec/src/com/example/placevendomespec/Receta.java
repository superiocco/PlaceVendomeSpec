package com.example.placevendomespec;

import java.io.IOException;

import com.example.grilla.DataBaseHelper;
import com.example.guardarSelecciones.Selecciones;


import android.R.color;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Receta extends Fragment {
	
	private static int cerca=0;
	private View vista;
	private static Receta r;

	
	

	
	private static SectionsPageAdapter Adapter;

	
	public static Fragment newInstance(SectionsPageAdapter sec){
		
		Receta f = new Receta();
		Adapter=sec;
		r = f;
		return f;	
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View root = (View) inflater.inflate(R.layout.receta, null);
		vista = root;
		Button guardarReceta = (Button)root.findViewById(R.id.guardarReceta);
		final TextView esferaDerecha = (TextView) root.findViewById(R.id.esferaDerecha);
		final TextView cilindroDerecho = (TextView) root.findViewById(R.id.cilindroDerecho);
		final TextView ejeDerecho = (TextView) root.findViewById(R.id.ejeDerecho);
		final TextView adicionDerecho = (TextView) root.findViewById(R.id.adicionDerecho);
		final TextView adicionIzquierdo = (TextView) root.findViewById(R.id.adicionIzquierdo);
		final TextView esferaIzquierda = (TextView) root.findViewById(R.id.esferaIzquierda);
		final TextView cilindroIzquierdo = (TextView) root.findViewById(R.id.cilindroIzquierdo);
		final TextView ejeIzquierdo = (TextView) root.findViewById(R.id.ejeIzquierdo);
		final TextView distanciaPupilar = (TextView) root.findViewById(R.id.distanciaPupilar);
		setTexts(cerca, root);
		

		
		
		
			
		ImageButton info =  (ImageButton) root.findViewById(R.id.info);
		final Intent i = new Intent(this.getActivity(),Info.class);
		info.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!Selecciones.getEnfermedad().equals("Ninguna")){
				startActivity(i);
				}
			}
		});
		
		
		guardarReceta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.d("Click", "Next Receta");
				
				DataBaseHelper myDbHelper = new DataBaseHelper(getActivity());
		        myDbHelper = new DataBaseHelper(getActivity());
		 
		        try {
		        	Log.d("DB", "TRY");
		        	myDbHelper.createDataBase();
		 
		 	} catch (IOException ioe) {
	     	Log.d("DB", "IO");

		 		throw new Error("Unable to create database");
		 
		 	}
		 
		 	try {
	     	Log.d("DB", "TRY OPEN");

		 		myDbHelper.openDataBase();
		 
		 	}catch(SQLException sqle){
	     	Log.d("DB", "FAIL OPEN");

		 		throw sqle;
		 
		 	}
		 	
		 	Selecciones.setBases(myDbHelper.getBases(Selecciones.getCilindroDerecho(), Selecciones.getEsferaDerechaCerca(), Selecciones.getEsferaIzquierdaCerca(), Selecciones.getCilindroIzquierdo()));
		 	Log.d("Bases",Selecciones.getBases());
		 	if(Selecciones.getBases().equals("")){
		 		Selecciones.setError("Bases");
		 		Intent mensaje = new Intent(getActivity(),Mensaje.class);
		 		startActivity(mensaje);
		 	}
			
		 	else{
				Adapter.setTrans(Adapter.getTrans()+1);
				Adapter.notifyDataSetChanged();
				
		 	}
		 	myDbHelper.close();

		 	
			}
		});
		
		
		
		distanciaPupilar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				DistanciaPupilar p = DistanciaPupilar.newInstance(distanciaPupilar);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");
				
			}
		});
		
			
		esferaDerecha.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				Lista p = Lista.newInstance(esferaDerecha, cerca, 1, r);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");

				
				
			}
		});
		cilindroDerecho.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				Lista p = Lista.newInstance(cilindroDerecho, cerca, 2, r);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");

				
			}
		});
		ejeDerecho.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				ListaEje p = ListaEje.newInstance(ejeDerecho,cerca,1);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");
				
			}
		});
		adicionDerecho.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				Adicion p = Adicion.newInstance(adicionDerecho,adicionIzquierdo, r);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");				
			}
		});
		
		adicionIzquierdo.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				Adicion p = Adicion.newInstance(adicionDerecho,adicionIzquierdo, r);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");				
			}
		});
		
		esferaIzquierda.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				Lista p = Lista.newInstance(esferaIzquierda, cerca, 4, r);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");
			}
		});
		cilindroIzquierdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				Lista p = Lista.newInstance(cilindroIzquierdo, cerca, 5, r);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");
				
			}
		});
		ejeIzquierdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    FragmentTransaction ft = getFragmentManager().beginTransaction();
				ListaEje p = ListaEje.newInstance(ejeIzquierdo,cerca,2);
				p.show(ft, "Lista");
				Log.d("Click", "Lista");
				
			}
		});
		
		
		
		return root;
	}

	private void setTexts(int cerca2, View root) {
		// TODO Auto-generated method stub
		setAdicion(Selecciones.getAdicion());
		final TextView esferaDerecha = (TextView) root.findViewById(R.id.esferaDerecha);
		final TextView cilindroDerecho = (TextView) root.findViewById(R.id.cilindroDerecho);
		final TextView ejeDerecho = (TextView) root.findViewById(R.id.ejeDerecho);
		final TextView adicionDerecho = (TextView) root.findViewById(R.id.adicionDerecho);
		final TextView adicionIzquierdo = (TextView) root.findViewById(R.id.adicionIzquierdo);

		final TextView esferaIzquierda = (TextView) root.findViewById(R.id.esferaIzquierda);
		final TextView cilindroIzquierdo = (TextView) root.findViewById(R.id.cilindroIzquierdo);
		final TextView ejeIzquierdo = (TextView) root.findViewById(R.id.ejeIzquierdo);
		final TextView titulo = (TextView) root.findViewById(R.id.titulo);
		final TextView distanciaPupilar = (TextView) root.findViewById(R.id.distanciaPupilar);

		if(Selecciones.getCilindroIzquierdo()>0){
			cilindroIzquierdo.setText("+" + Float.toString(Selecciones.getCilindroIzquierdo()));
			
		}
		
		else{
			cilindroIzquierdo.setText(Float.toString(Selecciones.getCilindroIzquierdo()));
		}
		
		
		if(Selecciones.getEsferaDerechaCerca()>0){
			esferaDerecha.setText("+" + Float.toString(Selecciones.getEsferaDerechaCerca()));

			
		}
		
		else{
			
			esferaDerecha.setText(Float.toString(Selecciones.getEsferaDerechaCerca()));

			
		}
		
		if(Selecciones.getEsferaIzquierdaCerca()>0){
			esferaIzquierda.setText("+" + Float.toString(Selecciones.getEsferaIzquierdaCerca()));

			
		}
		
		else{
			esferaIzquierda.setText(Float.toString(Selecciones.getEsferaIzquierdaCerca()));

			
		}
		
		if(Selecciones.getCilindroDerecho()>0){
			cilindroDerecho.setText("+" + Float.toString(Selecciones.getCilindroDerecho()));

			
		}
		
		else{
			
			cilindroDerecho.setText(Float.toString(Selecciones.getCilindroDerecho()));

			
		}
		
			titulo.setText("Ingrese la Receta");
			ejeDerecho.setText(Integer.toString((int)Selecciones.getEjeDerecho()));
			ejeIzquierdo.setText(Integer.toString((int)Selecciones.getEjeIzquierdo()));
		adicionDerecho.setText(Float.toString(Selecciones.getAdicion()));
		adicionIzquierdo.setText(Float.toString(Selecciones.getAdicion()));
		distanciaPupilar.setText(Integer.toString(Selecciones.getDistanciaPupilar()));
		colorText(esferaDerecha);
		colorText(esferaIzquierda);
		colorText(distanciaPupilar);
		colorText(ejeIzquierdo);
		colorText(ejeDerecho);
		colorText(cilindroIzquierdo);
		colorText(cilindroDerecho);
		colorText(adicionDerecho);
		colorText(adicionIzquierdo);
		calcularEnfermedad();
	}
	
	public void colorText(TextView text){
		
		
		float value = Float.parseFloat((String)text.getText());
		if(value>0)
			text.setTextColor(Color.BLUE);
		if(value<0)
			text.setTextColor(Color.RED);
		if(value==0)
			text.setTextColor(Color.BLACK);
		
		
	}
	
	public void setAdicion(float value){
		if (cerca==0){
		Selecciones.setEsferaDerechaLejos(Selecciones.getEsferaDerechaCerca()-value);
		Selecciones.setEsferaIzquierdaLejos(Selecciones.getEsferaIzquierdaCerca()-value);
		}
		if(cerca!=0){
		Selecciones.setEsferaDerechaLejos(Selecciones.getEsferaDerechaCerca()+value);
		Selecciones.setEsferaIzquierdaLejos(Selecciones.getEsferaIzquierdaCerca()+value);
		}		
	}
	
	public void calcularEnfermedad(){
		
		View root = vista;
		final TextView esferaDerecha = (TextView) root.findViewById(R.id.esferaDerecha);
		final TextView cilindroDerecho = (TextView) root.findViewById(R.id.cilindroDerecho); 
		final TextView esferaIzquierda = (TextView) root.findViewById(R.id.esferaIzquierda);
		final TextView cilindroIzquierdo = (TextView) root.findViewById(R.id.cilindroIzquierdo);
		final TextView enfermedad = (TextView) root.findViewById(R.id.enfermedad);
		final TextView adicionDerecho = (TextView) root.findViewById(R.id.adicionDerecho);
		final TextView adicionIzquierdo = (TextView) root.findViewById(R.id.adicionIzquierdo);
		float esferaDer = Float.parseFloat((String) esferaDerecha.getText());
		float cilindroDer = Float.parseFloat((String) cilindroDerecho.getText());
		float esferaIzq = Float.parseFloat((String) esferaIzquierda.getText());
		float cilindroIzq = Float.parseFloat((String) cilindroIzquierdo.getText());
		float add = Float.parseFloat((String) adicionDerecho.getText());
		String enfermedadIzq= "Ninguna";
		String enfermedadDer = "Ninguna";
		
		if(esferaDer<0 && cilindroDer==0){
			enfermedadDer="Miopia";
			
		}
		
		if(esferaIzq<0 && cilindroIzq==0){
			enfermedadIzq="Miopia";
			
		}
		
		if(esferaDer>0 && cilindroDer==0){
			enfermedadDer="Hipermetropia";
			
		}
		
		if(esferaIzq>0 && cilindroIzq==0){
			enfermedadIzq="Hipermetropia";
			
		}
		if(esferaDer==0 && cilindroDer<0){
			enfermedadDer="Astigmatismo Miopico Simple";
			
		}
		
		if(esferaIzq==0 && cilindroIzq<0){
			enfermedadIzq="Astigmatismo Miopico Simple";
			
		}
		
		if(esferaDer<0 && cilindroDer<0){
			enfermedadDer="Astigmatismo Miopico Compuesto";
			
		}
		
		if(esferaIzq<0 && cilindroIzq<0){
			enfermedadIzq="Astigmatismo Miopico Compuesto";
			
		}
		
		if(esferaDer==0 && cilindroDer>0){
			enfermedadDer="Astigmatismo Hipermetropico Simple";
			
		}
		
		if(esferaIzq==0 && cilindroIzq>0){
			enfermedadIzq="Astigmatismo Hipermetropico Simple";
			
		}
		
		if(esferaDer>0 && cilindroDer>0){
			enfermedadDer="Astigmatismo Hipermetropico Compuesto";
			
		}
		
		if(esferaIzq>0 && cilindroIzq>0){
			enfermedadIzq="Astigmatismo Hipermetropico Compuesto";
			
		}
		
		if(esferaDer>0 && cilindroDer<0){
			enfermedadDer="Astigmatismo Mixto";
			
		}
		
		if(esferaIzq>0 && cilindroIzq<0){
			enfermedadIzq="Astigmatismo Mixto";
			
		}
		
		if(esferaDer<0 && cilindroDer>0){
			enfermedadDer="Astigmatismo Mixto";
			
		}
		
		if(esferaIzq<0 && cilindroIzq>0){
			enfermedadIzq="Astigmatismo Mixto";
			
		}
		
		if(add>0){
			
			enfermedadDer = "Presbicia";
			enfermedadIzq="Presbicia";
		}
		
		if(enfermedadDer.equals(enfermedadIzq)){
			
			enfermedad.setText("Enfermedad: "+enfermedadDer);
			Selecciones.setEnfermedad(enfermedadDer);
			
		}
		
		
		if(!enfermedadDer.equals(enfermedadIzq)){
			
			enfermedad.setText("Enfermedad: "+enfermedadDer + " - " + enfermedadIzq );
			Selecciones.setEnfermedad(enfermedadDer);

			
		}
	}
	
	
}

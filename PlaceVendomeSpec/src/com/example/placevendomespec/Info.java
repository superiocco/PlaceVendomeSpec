package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class Info extends Activity {
	
	String enfermedad;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.info);
		ImageView imagen = (ImageView) findViewById(R.id.imagenesEnfermedad);
		ImageView ojo = (ImageView)findViewById(R.id.imagenOjo);
		TextView textoEnfermedad = (TextView ) findViewById(R.id.texto);
		TextView causaEnfermedad = (TextView ) findViewById(R.id.causaEnfermedad);
		TextView sintomaEnfermedad = (TextView ) findViewById(R.id.sintomasEnfermedad);
		String[] textos = null; 
		Drawable book = null;
		Drawable eye = null;
			
		if(Selecciones.getEnfermedad().contains("Astigmatismo")){
			
			enfermedad = "astigmatismo";
			book = getResources().getDrawable(R.drawable.astigmatismobook);
			eye = getResources().getDrawable(R.drawable.astigmatismoeye);
			textos = getResources().getStringArray(R.array.Astigmatismo);

			
		}
		if(Selecciones.getEnfermedad().contains("Presbicia")){
			
			enfermedad = "presbicia";
			book = getResources().getDrawable(R.drawable.presbiciabook);
			eye = getResources().getDrawable(R.drawable.presbiciaeye);
			textos = getResources().getStringArray(R.array.Presbicia);



			
		}
		if(Selecciones.getEnfermedad().contains("Hipermetropia")){
	
	
			enfermedad = "hipermetropia";
			book = getResources().getDrawable(R.drawable.hipermetropiabook);
			eye = getResources().getDrawable(R.drawable.hipermetropiaeye);
			textos = getResources().getStringArray(R.array.Hipermetropia);



			
		}
		if(Selecciones.getEnfermedad().contains("Miopia")){
			
			enfermedad = "miopia";
			
			book = getResources().getDrawable(R.drawable.miopiabook);
			eye = getResources().getDrawable(R.drawable.miopiaeye);
			textos = getResources().getStringArray(R.array.Miopia);

			

			
		}
		
		book = resizeSmall(book);
		eye = resizeSmall(eye);
		ojo.setImageDrawable(eye);
		imagen.setImageDrawable(book);
		SpannableString causas = new SpannableString("Causa: "+textos[1]);
  	    causas.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, causas.length()- textos[1].length(), 0);
  	  SpannableString sintomas = new SpannableString("Sintomas: "+textos[2]);
	    sintomas.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, sintomas.length()-textos[2].length(), 0);
	    
		
		textoEnfermedad.setText(textos[0]);
		causaEnfermedad.setText(causas,BufferType.SPANNABLE);
		sintomaEnfermedad.setText(sintomas,BufferType.SPANNABLE);
		
		
		
	}
	
	private Drawable resizeSmall(Drawable image){
		 {
			    Bitmap d = ((BitmapDrawable)image).getBitmap();
			    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 500, 500, false);
			    return new BitmapDrawable(bitmapOrig);
			}
		
	}
	

}

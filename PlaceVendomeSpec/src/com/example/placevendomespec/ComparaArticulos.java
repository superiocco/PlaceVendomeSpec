package com.example.placevendomespec;

import java.io.IOException;
import java.io.InputStream;

import com.example.guardarSelecciones.Selecciones;
import com.google.gson.Gson;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class ComparaArticulos extends Activity {

	int i=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.comparar);
		ActionBar bar = getActionBar();

		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);
		bar.setTitle("Comparacion " + Integer.toString(Selecciones.getCantidadElegidos()) + " Productos");
		getContents(Selecciones.getPreviousResult());
		
		
		
		
	}
	
	private Drawable resize(Drawable image) {
	    Bitmap d = ((BitmapDrawable)image).getBitmap();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 600, 400, false);
	    return new BitmapDrawable(bitmapOrig);
	}

	private void getContents(String previousResult) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		final Modelo[] recibidos = gson.fromJson(previousResult, Modelo[].class);
		int total = recibidos.length;
		int idProd=0;
		int seleccionados = Selecciones.getCantidadElegidos();
		boolean[]elegidos = Selecciones.getElegidos();
		LinearLayout skus = (LinearLayout)findViewById(R.id.skus);
		LinearLayout items = (LinearLayout)findViewById(R.id.items);
		LinearLayout fotos = (LinearLayout)findViewById(R.id.fotos);
		LinearLayout precios = (LinearLayout)findViewById(R.id.precios);
		LinearLayout marcas = (LinearLayout)findViewById(R.id.marcas);
		LinearLayout formas = (LinearLayout)findViewById(R.id.formas);
		LinearLayout medidas = (LinearLayout)findViewById(R.id.medidas);
		int width;
		TextView[] sku = new TextView[seleccionados];
		TextView[] item = new TextView[seleccionados];
		TextView[] marca = new TextView [seleccionados];
		TextView[] precio = new TextView[seleccionados];
		TextView[] forma = new TextView [seleccionados];
		TextView[] aroAncho = new TextView [seleccionados];
		TextView[] aroAlto = new TextView[seleccionados];
		TextView[] puente = new TextView [seleccionados];
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
			     LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

			layoutParams.setMargins(0, 0, 30, 0);
		ImageView imagenProducto[] = new ImageView[seleccionados];

		for(i=0;i<total;i++){
			
			if(elegidos[i]){
				
				sku[idProd] = new TextView(this);
				item[idProd] = new TextView(this);
				marca[idProd] = new TextView (this);
				precio[idProd] = new TextView(this);
				forma[idProd] = new TextView (this);
				aroAncho[idProd] = new TextView (this);
				aroAlto[idProd] = new TextView(this);
				puente[idProd] = new TextView (this);
				imagenProducto[idProd] = new ImageView(this);
				
				LinearLayout medidas2 = new LinearLayout(this);
				medidas2.setOrientation(1);
				width = 550;
				SpannableString skuText = new SpannableString(recibidos[i].getSKU());
				skuText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, skuText.length(), 0);
				sku[idProd].setText(skuText, BufferType.SPANNABLE);
				SpannableString itemText = new SpannableString(recibidos[i].getModelo().trim());
				itemText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, itemText.length(), 0);				
				item[idProd].setText(itemText,BufferType.SPANNABLE);
				SpannableString marcaText = new SpannableString(recibidos[i].getMarca());
				marcaText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, marcaText.length(), 0);
				marca[idProd].setText(marcaText, BufferType.SPANNABLE);
				SpannableString precioText = new SpannableString("$" + recibidos[i].getPrecio());
				precioText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, precioText.length(), 0);
				precioText.setSpan(new ForegroundColorSpan(Color.RED), 0, precioText.length(), 0);
				precio[idProd].setText(precioText, BufferType.SPANNABLE);
				SpannableString formaText = new SpannableString(recibidos[i].getForma());
				formaText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, formaText.length(), 0);
				forma[idProd].setText(formaText, BufferType.SPANNABLE);

				SpannableString aroAnchoText = new SpannableString("Aro Ancho: " + Float.toString(recibidos[i].getAroAncho()));
				aroAnchoText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, aroAnchoText.length(), 0);
				aroAncho[idProd].setText(aroAnchoText, BufferType.SPANNABLE);
				SpannableString aroAltoText = new SpannableString("Aro Alto: " + Float.toString(recibidos[i].getAroAlto()));
				aroAltoText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, aroAltoText.length(), 0);
				aroAlto[idProd].setText(aroAltoText, BufferType.SPANNABLE);
				SpannableString puenteText = new SpannableString("Puente: " + Float.toString(recibidos[i].getPuente()));
				puenteText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, puenteText.length(), 0);
				puente[idProd].setText(puenteText, BufferType.SPANNABLE);
				sku[idProd].setTextSize(20);
				item[idProd].setTextSize(20);
				marca[idProd].setTextSize(20);
				precio[idProd].setTextSize(20);
				forma[idProd].setTextSize(20);
				aroAncho[idProd].setTextSize(20);
				aroAlto[idProd].setTextSize(20);
				puente[idProd].setTextSize(20);
				sku[idProd].setWidth(width);
				item[idProd].setWidth(width);
				marca[idProd].setWidth(width);
				precio[idProd].setWidth(width);
				forma[idProd].setWidth(width);
				aroAncho[idProd].setWidth(width);
				aroAlto[idProd].setWidth(width);
				puente[idProd].setWidth(width);
				sku[idProd].setGravity(17);
				item[idProd].setGravity(17);
				marca[idProd].setGravity(17);
				precio[idProd].setGravity(17);
				forma[idProd].setGravity(17);
				aroAncho[idProd].setGravity(17);
				aroAlto[idProd].setGravity(17);
				puente[idProd].setGravity(17);
				
		    	try {

					Log.d("Path", "images/"+Selecciones.getSkuElegido()+"_1.jpg");			            // get input stream
		            InputStream ims = getAssets().open("images/"+Selecciones.getSkuElegido()+".png");
		            // load image as Drawable
		            
		            Drawable d = Drawable.createFromStream(ims, null);
		            Drawable e = resize(d);
		            
		            
		            
		            
		            // set image to ImageView
		            imagenProducto[idProd].setImageDrawable(e);
		        }
		        catch(IOException ex) {

		        imagenProducto[idProd].setImageResource(R.drawable.opticageneral);
		        	
		        }
				
		        
				skus.addView(sku[idProd],layoutParams);
				items.addView(item[idProd],layoutParams);
				fotos.addView(imagenProducto[idProd],layoutParams);
				marcas.addView(marca[idProd],layoutParams);
				precios.addView(precio[idProd],layoutParams);
				formas.addView(forma[idProd],layoutParams);
				medidas2.addView(aroAncho[idProd]);
				medidas2.addView(aroAlto[idProd]);
				medidas2.addView(puente[idProd]);
				
				medidas.addView(medidas2,layoutParams);
				idProd++;

			}
		
			
		}
		
		
		
		
	}
}

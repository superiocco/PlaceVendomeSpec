package com.example.placevendomespec;
import java.io.IOException;
import java.io.InputStream;



import com.example.guardarSelecciones.Recomendaciones;
import com.example.guardarSelecciones.Selecciones;
import com.google.gson.Gson;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;


@TargetApi(17)
public class Similares extends Activity{
	String json;
	int i=0;
	int j=0;
	int elegido;
	String clicked;
	
	/*
	 * Inicializa
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.similares);
	
		
		ActionBar bar = getActionBar();
		if(savedInstanceState==null){

		bar.setTitle("    Productos Similares");
		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);
		InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
		Log.d("Conexion", "before");
		Log.d("SKU", Selecciones.getSkuElegido());
		
			Recomendaciones.getSimilares(this);
			
		}
		else{
			setContents(savedInstanceState.getString("json"));
			
		}
		
		
		
		
	}




	/* Guarda el contenido
	 * Para cuando la pantalla se gire no llame al HttpRequest nuevamente
	 * (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		Log.d("Save", "state");
		super.onSaveInstanceState(outState);
		if(json!=null){
		outState.putString("json", json);
		}
	}




	/*Manejo de botones 
	 * 
	 * Principalmente Back
	 * */
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == 4){
			if(Selecciones.getReturns()==0){
			final Intent main = new Intent (this,MainActivity.class);
			startActivity(main);
			MainActivity.mSectionsPagerAdapter.setTrans(2);
			finish();
			}
			else{
				Selecciones.setReturns(Selecciones.getReturns()-1);
				return super.onKeyDown(keyCode, event);
			}
			
			
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	/*Resize para la foto primaria */
	
	private Drawable resize(Drawable image) {
	    Bitmap d = ((BitmapDrawable)image).getBitmap();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 600, 400, false);
	    return new BitmapDrawable(bitmapOrig);
	}
	
	
	/*
	 * Resize para el resto de las fotos 
	 */
	
	private Drawable resizeSmall(Drawable image){
		 {
			    Bitmap d = ((BitmapDrawable)image).getBitmap();
			    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 200, 200, false);
			    return new BitmapDrawable(bitmapOrig);
			}
		
	}
	
	
	/*Metodo llamado desde el AsyncHttpRequest */
	
	private void setContents(String result){
		json =  result;
		Gson gson = new Gson();
		final Modelo[] recibidos = gson.fromJson(result, Modelo[].class);
		int total2 = recibidos.length;
		clicked = Selecciones.getSkuElegido();
		Log.d("clicked", clicked);
		ActionBar bar = getActionBar();
		
		bar.setTitle("    " + Integer.toString(total2-1) + "   Productos Similares");
		
		final int total;

		if(total2>10){
			total = 10;
		}
		else{
			total=total2;
		
		}
		Log.d("Total", Integer.toString(total));
		final Intent preview = new Intent (this,Preview.class);

		Button principal = (Button) findViewById(R.id.imageButton1);
		principal.setBackgroundColor(Color.BLACK);
		LinearLayout imageBox = (LinearLayout) findViewById(R.id.imagenBox);
		imageBox.setBackgroundColor(Color.WHITE);
		LinearLayout otros = (LinearLayout) findViewById(R.id.otrosBox);
		otros.setBackgroundColor(Color.WHITE);
		try {

						Log.d("Path", "images/"+clicked+"_1.jpg");			            // get input stream
			            InputStream ims = getAssets().open("images/"+clicked+".png");
			            
			            // load image as Drawable
			            Drawable d = Drawable.createFromStream(ims, null);
			            Drawable e=resize(d);
			            // set image to ImageView
			            principal.setCompoundDrawablesRelativeWithIntrinsicBounds(null, e, null, null);
			            
		}
			        catch(IOException ex) {
			        	principal.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.opticageneral, 0, 0);
			        
			        }

		
		principal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Selecciones.setPrecioElegido(recibidos[i].get);
				for(j=0;j<recibidos.length;j++){
				if(clicked.equals(recibidos[j].getSKU())){
				elegido = j;
				Log.d("Clicked View", clicked);
				Selecciones.setMarcaElegida(recibidos[elegido].getMarca());
				Selecciones.setModeloElegido(recibidos[elegido].getModelo());
				Selecciones.setColorElegido(recibidos[elegido].getColor());
				Selecciones.setFormaElegida(recibidos[elegido].getForma());
				Selecciones.setMaterialElegido(recibidos[elegido].getMaterial());
				Selecciones.setPrecioElegido(Integer.parseInt(recibidos[elegido].getPrecio().trim()));
				Selecciones.setSkuElegido(clicked);
				Selecciones.setStockElegido(recibidos[elegido].getStock());
				Selecciones.setBaseElegida(recibidos[elegido].getBase());
				Selecciones.setAroAltoElegido(recibidos[elegido].getAroAlto());
				Selecciones.setAroAnchoElegido(recibidos[elegido].getAroAncho());
				Selecciones.setDiagonalElegida(recibidos[elegido].getDiagonal());
				Selecciones.setPuenteElegido(recibidos[elegido].getPuente());
				

				}
				


				}
				startActivity(preview);

				
			}
		});
		for(j=0;j<recibidos.length;j++){
			if(clicked.equals(recibidos[j].getSKU())){

				elegido = j;
				SpannableString text1 = new SpannableString("Lente: " + recibidos[j].getModelo() + "\n SKU: #" + recibidos[j].getSKU() + "        $" +  recibidos[j].getPrecio().trim());
			    text1.setSpan(new ForegroundColorSpan(Color.WHITE), 0,text1.length() - recibidos[j].getPrecio().trim().length() - 1, 0);
			    text1.setSpan(new ForegroundColorSpan(Color.RED), text1.length() - recibidos[j].getPrecio().trim().length() - 1, text1.length(), 0);
			    text1.setSpan(new RelativeSizeSpan(1.5f), text1.length() - recibidos[j].getPrecio().trim().length() - 1, text1.length(), 0);

			    principal.setText(text1, BufferType.SPANNABLE);
			}
		}
		LinearLayout lay = (LinearLayout) findViewById(R.id.otros);
		ProgressBar barra = (ProgressBar)findViewById(R.id.loadingBar);
		barra.setVisibility(8);

		TextView texto = (TextView)findViewById(R.id.masProductos);
		texto.setText("Mas Productos Relacionados");
		
		Button[] parecidos = new Button[total];

		for(this.i=0;i<total;i++){
			Log.d("SKU", recibidos[i].getSKU());
			Log.d("Elegido", Integer.toString(elegido));
			if(i!=elegido){
			
			parecidos[i] = new Button(this);
			try {

				Log.d("Path", "images/"+recibidos[i].getSKU()+"_1.jpg");			            // get input stream
	            InputStream ims = getAssets().open("images/"+recibidos[i].getSKU()+".jpg");
	            // load image as Drawable
	            Drawable d = Drawable.createFromStream(ims, null);
	            // set image to ImageView
	            Drawable e = resizeSmall(d);
	            parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(null, e, null, null);
	
	        }
	        catch(IOException ex) {
	        	parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.opticageneralsmall, 0, 0);
	        
	        }
			parecidos[i].setId(i+100);
			SpannableString stock = new SpannableString("Lentes: " + recibidos[i].getModelo().trim());
	  	    stock.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, stock.length(), 0);
	    	parecidos[i].setText(stock, BufferType.SPANNABLE);
			parecidos[i].setTextColor(Color.WHITE);
			parecidos[i].setBackgroundColor(Color.BLACK);
			parecidos[i].setTextSize(20);
			parecidos[i].setOnClickListener(new View.OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int id = v.getId()-100;
					Selecciones.setMarcaElegida(recibidos[id].getMarca());
					Selecciones.setModeloElegido(recibidos[id].getModelo());
					Selecciones.setColorElegido(recibidos[id].getColor());
					Selecciones.setFormaElegida(recibidos[id].getForma());
					Selecciones.setMaterialElegido(recibidos[id].getMaterial());
					Selecciones.setSkuElegido(recibidos[id].getSKU());
					Selecciones.setPrecioElegido(Integer.parseInt(recibidos[id].getPrecio().trim()));
					Selecciones.setStockElegido(recibidos[id].getStock());
					Selecciones.setBaseElegida(recibidos[id].getBase());
					Selecciones.setAroAltoElegido(recibidos[id].getAroAlto());
					Selecciones.setAroAnchoElegido(recibidos[id].getAroAncho());
					Selecciones.setDiagonalElegida(recibidos[id].getDiagonal());
					Selecciones.setPuenteElegido(recibidos[id].getPuente());
					
					Log.d("Producto", Integer.toString(id));
					startActivity(preview);

					
				}
			});
			
			lay.addView(parecidos[i]);
			
			}
		}
		
		//Button foto3 = new Button(this);
		//LinearLayout lay= (LinearLayout) findViewById(R.id.linearLayout1);
		//foto3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.four, 0, 0);
		//lay.addView(foto3);
	}
	
	
	/*En caso que la conexion falle o similar
	 
	 */
	
	public void showError(String result){
		
		
		Selecciones.setError(result);
		
		  final Intent main = new Intent (this,MainActivity.class);
			MainActivity.mSectionsPagerAdapter.setTrans(2);
			Selecciones.setError(result);
			startActivity(main);
	  Intent mensaje = new Intent(this, Mensaje.class);
	  startActivity(mensaje);

	  finish();
	  
		
	}
	
	public void loadResults(String result){
		Log.d("Similares", "Loaded");
		
		if(result!=null){
			setContents(result);
		}
	}
	
	
	/*
	 * Home Button
	 * (non-Javadoc)
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		
		case android.R.id.home:
			Log.d("click","home");
			final Intent home = new Intent(this.getApplication(), MainActivity.class);
			startActivity(home);
			MainActivity.mSectionsPagerAdapter.setTrans(0);
			finish();

		
		}	
		
		return super.onMenuItemSelected(featureId, item);
	}
	

}

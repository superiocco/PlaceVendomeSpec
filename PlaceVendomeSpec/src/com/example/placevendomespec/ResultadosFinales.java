package com.example.placevendomespec;

import java.io.IOException;
import java.io.InputStream;

import com.example.grilla.DataBaseHelper;
import com.example.guardarSelecciones.Recomendaciones;
import com.example.guardarSelecciones.Selecciones;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class ResultadosFinales extends Activity {
	
	private static SectionsPageAdapter Adapter;
	private static int idProducto;
	int i=0;
	String json;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultadostest);
		ActionBar bar = getActionBar();

		
		bar.setTitle("    Recomendaciones");
		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);
		
		
		if(savedInstanceState==null){
		 DataBaseHelper myDbHelper = new DataBaseHelper(this);
	        myDbHelper = new DataBaseHelper(this);
	 
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
	 	
	 	Recomendaciones.getCompleteResultados(this);

	 	myDbHelper.close();
		}
		
		else{
			
			getContents(savedInstanceState.getString("json"));
		}
		

		
		
		
		
		
	}
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void getContents(String result){
		json = result;
		int j=0;
		String clicked;
		Gson gson = new Gson();
		final Modelo[] recibidos = gson.fromJson(result, Modelo[].class);
		int total = recibidos.length;
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
		HorizontalScrollView scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
		scroll.setBackgroundColor(Color.BLACK);
		}
		clicked = Selecciones.getSkuElegido();
		Log.d("clicked", clicked);
		ActionBar bar = getActionBar();
		bar.setTitle("     "+Integer.toString(total) + "   Recomendaciones");


		LinearLayout lay = (LinearLayout) findViewById(R.id.linearLayout1);
		lay.removeAllViews();
		lay.setBackgroundColor(Color.BLACK);
		LinearLayout yellowLayout = (LinearLayout) findViewById(R.id.content);
		LinearLayout greenLayout = (LinearLayout) findViewById(R.id.content2);
		LinearLayout blueLayout = (LinearLayout) findViewById(R.id.content3);
		
		final Button[] parecidos = new Button[total];
		final Intent preview = new Intent (this,Preview.class);

		for(this.i=0;i<total;i++){
		
			parecidos[i] = new Button(this);
			try {

	            InputStream ims = getAssets().open("images/"+recibidos[i].getSKU()+".png");
	            // load image as Drawable
	            Drawable d = Drawable.createFromStream(ims, null);
	       
	            
	            if(recibidos[i].getTab()!=0){
	            	
	            	d = resizeSmall(d);
	            }
	            
	            else{
	            	
	            	d = resize(d);
	            }
	            

	            
	            
	            // set image to ImageView
	            parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(null, d, null, null);

	            
	       
	            
	        }
	        catch(IOException ex) {
	        	if(recibidos[i].getTab()==0){
	        		parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.opticageneral, 0, 0);
	        	}
	        	else{
	        		parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.opticageneralsmall, 0, 0);
	        	}		
	        }
			parecidos[i].setId(i+100);
				recibidos[i].setModelo(recibidos[i].getModelo().trim());
			    SpannableString text1 = new SpannableString(recibidos[i].getModelo() + "\n$" + recibidos[i].getPrecio().trim());
			    text1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, recibidos[i].getModelo().length(), 0);
			    text1.setSpan(new ForegroundColorSpan(Color.RED), recibidos[i].getModelo().length(), 2 + recibidos[i].getModelo().length()+recibidos[i].getPrecio().trim().length(), 0);
			    parecidos[i].setText(text1, BufferType.SPANNABLE);
			parecidos[i].setBackgroundColor(Color.BLACK);
			parecidos[i].setTextSize(20);
			parecidos[i].setClickable(true);

			
	
				
				
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
					
					startActivity(preview);

					
				}
			});
			
			if(total<5){
				if(recibidos[i].getTab()==1)
					parecidos[i].setBackgroundColor(Color.MAGENTA);
				if(recibidos[i].getTab()==3)
					parecidos[i].setBackgroundColor(Color.CYAN);
			
				try {

		            InputStream ims = getAssets().open("images/"+recibidos[i].getSKU()+".png");
		            // load image as Drawable
		            Drawable d = Drawable.createFromStream(ims, null);
		            

		            
		            
		            // set image to ImageView

		            
		            
		            	
		            	d = resize(d);
		            
		            
		            parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(null, d, null, null);

		        }
		        catch(IOException ex) {
		        		parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.opticageneral, 0, 0);
		        	
	
		        }
				
				
				
				lay.addView(parecidos[i]);
			}
			
			else{
			if(recibidos[i].getTab()==0)
			lay.addView(parecidos[i]);
			if(recibidos[i].getTab()==1)
			greenLayout.addView(parecidos[i]);
			if(recibidos[i].getTab()==2)
			yellowLayout.addView(parecidos[i]);
			if(recibidos[i].getTab()==3)
			blueLayout.addView(parecidos[i]);
			
			}
		}
		@SuppressWarnings("deprecation")
		final SlidingDrawer yellow = (SlidingDrawer) findViewById(R.id.drawer);
		final SlidingDrawer green = (SlidingDrawer) findViewById(R.id.drawer2);
		final SlidingDrawer blue = (SlidingDrawer) findViewById(R.id.drawer3);
		final ImageView yellowTab = (ImageView) findViewById(R.id.handle);
		final ImageView greenTab = (ImageView) findViewById(R.id.handle2);
		final ImageView blueTab = (ImageView) findViewById(R.id.handle3);
		yellow.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

				
				yellowTab.setImageResource(R.drawable.yellow_up);
				blue.close();
				blueTab.setImageResource(R.drawable.budget_blue);
				green.close();
				greenTab.setImageResource(R.drawable.budget_pink);
				blockButtons(recibidos);
				
				}
				
				else{
					yellowTab.setImageResource(R.drawable.yellow_up_rotate);
					blue.close();
					blueTab.setImageResource(R.drawable.budget_blue_rotate);
					green.close();
					greenTab.setImageResource(R.drawable.budget_pink_rotate);
					blockButtons(recibidos);
					
					
				}
				
			}
		});
		
		
		green.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				greenTab.setImageResource(R.drawable.pink_up);
				blue.close();
				blueTab.setImageResource(R.drawable.budget_blue);
				yellow.close();
				yellowTab.setImageResource(R.drawable.budget_yellow);
				blockButtons(recibidos);
				
				}
				
				else{
					
					greenTab.setImageResource(R.drawable.pink_up_rotate);
					blue.close();
					blueTab.setImageResource(R.drawable.budget_blue_rotate);
					yellow.close();
					yellowTab.setImageResource(R.drawable.budget_yellow_rotate);
					blockButtons(recibidos);
					
					
				}

			}
		});
		
		blue.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				blueTab.setImageResource(R.drawable.blue_up);
				yellow.close();
				yellowTab.setImageResource(R.drawable.budget_yellow);
				green.close();
				greenTab.setImageResource(R.drawable.budget_pink);
				blockButtons(recibidos);
				}
				
				else{
					
					blueTab.setImageResource(R.drawable.blue_up_rotate);
					yellow.close();
					yellowTab.setImageResource(R.drawable.budget_yellow_rotate);
					green.close();
					greenTab.setImageResource(R.drawable.budget_pink_rotate);
					blockButtons(recibidos);
					
				}
			}
		});
	
		yellow.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
			
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				yellowTab.setImageResource(R.drawable.budget_yellow);
				freeButtons(recibidos);
				
				}

				else{
					yellowTab.setImageResource(R.drawable.budget_yellow_rotate);
					freeButtons(recibidos);
				}
			}
		});
		
		
		green.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
			
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				greenTab.setImageResource(R.drawable.budget_pink);
				freeButtons(recibidos);
				}
				
				else{
					greenTab.setImageResource(R.drawable.budget_pink_rotate);
					freeButtons(recibidos);
					
				}
				
			}
		});
		
		
		blue.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
			
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				blueTab.setImageResource(R.drawable.budget_blue);
				freeButtons(recibidos);
				}
			
				else{
				
					blueTab.setImageResource(R.drawable.budget_blue_rotate);
					freeButtons(recibidos);
				}
			}
		});
	}
	
@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if(json!=null){
			outState.putString("json", json);
			
		}
	}
public void showError(String result){
		
  		  Selecciones.setError(result);
		  Intent mensaje = new Intent(this, Mensaje.class);
		  startActivity(mensaje);
		  finish();
		
	}
	
   public void blockButtons(Modelo[] botones){
	 
	   int total = botones.length;
	   int i=0;
	   Button[] article = new Button[total];
	   for(i = 0; i<total; i++){
		   article[i] = new Button(this);
		   article[i] = (Button) findViewById(100 + i);
		   if(botones[i].getTab()==0){
		   article[i].setClickable(false);
		   
		   }
	   }
	   
	   
	   
   }
   
   public void freeButtons(Modelo[] botones){
	
	   int total = botones.length;
	   int i=0;
	   Button[] article = new Button[total];
	   for(i = 0; i<total; i++){
		   article[i] = new Button(this);
		   article[i] = (Button) findViewById(100 + i);
		   if(botones[i].getTab()==0){
		   article[i].setClickable(true);
		   
		   }
	   }
	   
	   
   }
	

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

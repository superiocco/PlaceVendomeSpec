package com.example.placevendomespec;

import java.io.IOException;
import java.io.InputStream;

import com.example.guardarSelecciones.Selecciones;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView.BufferType;

@TargetApi(17)
public class CompararResultados extends Activity {
	
	int i=0;
	boolean[] elegidos;
	int seleccionados=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultadostest);
		ActionBar bar = getActionBar();

		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);
		getContents(Selecciones.getPreviousResult());
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comparar, menu);
        return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.mostrarResultados:
			Selecciones.setElegidos(elegidos);
			Selecciones.setCantidadElegidos(seleccionados);
			if(seleccionados>1){
			final Intent carac = new Intent(this.getApplication(), ComparaArticulos.class);
			startActivity(carac);
			
			return true;
			}
			else{
				return false;
			}
			
		}
		return false;
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
	
	
	@SuppressLint("NewApi")
	public void getContents(String result){
		Selecciones.setPreviousResult(result);
		int j=0;
		String clicked;
		Gson gson = new Gson();
		final Modelo[] recibidos = gson.fromJson(result, Modelo[].class);
		int total = recibidos.length;
		elegidos = new boolean[total];
		ActionBar bar = getActionBar();
		bar.setTitle("0 Seleccionados");
		for(i=0;i<total;i++){
			
			elegidos[i]=false;
		}
		i=0;
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
		HorizontalScrollView scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
		scroll.setBackgroundColor(Color.BLACK);
		}
		clicked = Selecciones.getSkuElegido();
		Log.d("clicked", clicked);
		ActionBar bar2 = getActionBar();


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
	            

	            
	            
	            // set image to ImageView
	            parecidos[i].setCompoundDrawablesRelativeWithIntrinsicBounds(null, d, null, null);

	            
	            if(recibidos[i].getTab()!=0){
	            	
	            	parecidos[i].setMaxHeight(150);
	            	parecidos[i].setMaxWidth(150);
	            	
	            }
	            
	            
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
					
					ActionBar bar = getActionBar();
					
					
					if(!elegidos[id]){
						elegidos[id]=true;
						parecidos[id].setBackgroundColor(Color.GRAY);
						seleccionados++;
						bar.setTitle(Integer.toString(seleccionados) + " Seleccionados");
						
					}
					
					else{
						elegidos[id]=false;
						parecidos[id].setBackgroundColor(Color.BLACK);
						seleccionados--;
						bar.setTitle(Integer.toString(seleccionados) + " Seleccionados");

					}
					
					
					
				}
			});
			if(recibidos[i].getTab()==0)
			lay.addView(parecidos[i]);
			if(recibidos[i].getTab()==1)
			greenLayout.addView(parecidos[i]);
			if(recibidos[i].getTab()==2)
			yellowLayout.addView(parecidos[i]);
			if(recibidos[i].getTab()==3)
			blueLayout.addView(parecidos[i]);
			
			
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
	
}

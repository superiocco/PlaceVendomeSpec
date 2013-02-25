package com.example.placevendomespec;

import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.guardarSelecciones.Selecciones;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.res.Configuration;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturingListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class Preview extends SherlockActivity {
	
	private static String clicked;
	private static int precio;
	private static String descripcion;
	private static SlidingMenu menu;
	private static SlidingMenu menuLeft;
	boolean menuOpen = false;
	boolean menuLeftOpen = false;

	
	private Drawable resize(Drawable image) {
	    Bitmap d = ((BitmapDrawable)image).getBitmap();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 600, 400, false);
	    return new BitmapDrawable(bitmapOrig);
	}
	
	private Drawable resizeIcon(Drawable image) {
	    Bitmap d = ((BitmapDrawable)image).getBitmap();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 60, 60, false);
	    return new BitmapDrawable(bitmapOrig);
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
		ActionBar bar = getActionBar();
		FragmentTransaction t = getFragmentManager().beginTransaction();
		t.replace(R.id.productosCarro, CarritoCompras.newInstance());
		t.replace(R.id.menuLeft, MenuLeft.newInstance());

		Log.d("Sku Elegido", Selecciones.getSkuElegido());
		
		t.commit();
		clicked = Selecciones.getSkuElegido();
		
		

		// customize the SlidingMenu
		//this.setSlidingActionBarEnabled(true);
		//SlidingMenu menu = getSlidingMenu();
		/*menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.actionbar_home_width);
		menu.setBehindScrollScale(0.25f);*/
		//menu.setMode(SlidingMenu.RIGHT);
		
		menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setFadeDegree(0.25f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.carrito);
        
		menuLeft = new SlidingMenu(this);
        menuLeft.setMode(SlidingMenu.LEFT);
        menuLeft.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menuLeft.setShadowWidthRes(R.dimen.shadow_width);
        menuLeft.setShadowDrawable(R.drawable.shadow);
        menuLeft.setFadeDegree(0.25f);
        menuLeft.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menuLeft.setMenu(R.layout.menuleft);
        
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
        menu.setBehindOffsetRes(R.dimen.actionbar_home_width);
        menuLeft.setBehindOffsetRes(R.dimen.actionbar_home_width2);
        }
        
        else{
        	
            menu.setBehindOffsetRes(R.dimen.actionbar_home_vertical);
            menuLeft.setBehindOffsetRes(R.dimen.actionbar_home_vertical2);	
        }

        
        menu.setOnOpenedListener(new OnOpenedListener() {
    		
			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				Log.d("Menu", "OPEN");

		        menuLeft.setSlidingEnabled(false);
				
				if(menuLeftOpen){
				
					Log.d("Menu", "Menu Is Open");
					menu.showContent(false);
					menuLeft.showContent(false);
					menuOpen=false;
					
					
				}
				
				else{
					Log.d("Menu", "Menu Is Closed");
					menuOpen=true;
					
				}
				
			}
		});
        
        
  menu.setOnOpenListener(new OnOpenListener() {
    		
			@Override
			public void onOpen() {
				// TODO Auto-generated method stub
				Log.d("Menu", "OPEN");
				menuLeft.setSlidingEnabled(false);

				if(menuLeftOpen){
				
					Log.d("Menu", "Menu Is Open");
					menuOpen=false;
					
					
				}
				
				else{
					Log.d("Menu", "Menu Is Closed");
					menuOpen=true;
					
				}
				
			}
		});
        
        
        menuLeft.setOnOpenListener(new OnOpenListener(){

			@Override
			public void onOpen() {
				// TODO Auto-generated method stub
				menu.setSlidingEnabled(false);
			}
        	
        	
        	
        	
        }
        
        		
        		
        		
        		);
        	
        	
        
        
        
        menuLeft.setOnOpenedListener(new OnOpenedListener() {
		
			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				menu.setSlidingEnabled(false);

				Log.d("MenuLeft", "OPEN");
				if(menuOpen){
				
					Log.d("MenuLeft", "Menu Is Open");
					menuLeftOpen=false;

					
					
				}
				
				else{
					Log.d("MenuLeft", "Menu Is Closed");
					menuLeftOpen=true;
					
				}
				
			}
		});
        
        menuLeft.setOnCloseListener(new OnCloseListener(){

			@Override
			public void onClose() {
				// TODO Auto-generated method stub
				Log.d("MenuLeft", "Close");
				menuLeftOpen=false;
				menu.setSlidingEnabled(true);
				
			}
        
        		
        		
        		
        });
        
        menu.setOnCloseListener(new OnCloseListener(){

    			@Override
    			public void onClose() {
    				// TODO Auto-generated method stub
    				Log.d("Menu", "Close");
    		        menuLeft.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    		        menuLeft.setSlidingEnabled(true);

    				menuOpen=false;
    				
    			}
            
            		
            		
            		
            });
            
        
        
        
        
        
        
		//setBehindContentView(R.layout.carrito);


		bar.setTitle("Lentes: " + Selecciones.getModeloElegido());
		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);

		/*TEXT FORMAT AND ALL THAT SHIT */
		
		TextView nombreProducto = (TextView) findViewById(R.id.nombreProducto);
    	TextView precioProducto = (TextView) findViewById(R.id.precioProducto);
    	TextView colorProducto = (TextView) findViewById(R.id.colorProducto);
    	TextView marcaProducto = (TextView) findViewById(R.id.marcaProducto);
    	TextView materialProducto = (TextView) findViewById(R.id.materialProducto);
    	TextView formaProducto = (TextView) findViewById(R.id.formaProducto);
    	TextView stockProducto = (TextView) findViewById(R.id.stockProducto);
       	nombreProducto.setText("#"+Selecciones.getSkuElegido());

	    SpannableString precio = new SpannableString("Precio: $" + Integer.toString(Selecciones.getPrecioElegido()));
	    precio.setSpan(new ForegroundColorSpan(Color.RED),precio.length()- Integer.toString(Selecciones.getPrecioElegido()).length()-1, precio.length(), 0);
	    precio.setSpan(new RelativeSizeSpan(1.5f),precio.length()- Integer.toString(Selecciones.getPrecioElegido()).length()-1, precio.length(), 0);
	    precio.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),precio.length()- Integer.toString(Selecciones.getPrecioElegido()).length()-1, precio.length(), 0);
	    precio.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),0, precio.length()- Integer.toString(Selecciones.getPrecioElegido()).length()-1, 0);

	    precioProducto.setText(precio, BufferType.SPANNABLE);
    	SpannableString color = new SpannableString("Color: " + Selecciones.getColorElegido());
  	    color.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), color.length()- Selecciones.getColorElegido().length(), color.length(), 0);
    	colorProducto.setText(color, BufferType.SPANNABLE);
    	SpannableString marca = new SpannableString("Marca: " + Selecciones.getMarcaElegida());
  	    marca.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), marca.length()- Selecciones.getMarcaElegida().length(), marca.length(), 0);
    	marcaProducto.setText(marca, BufferType.SPANNABLE);
    	SpannableString material = new SpannableString("Material: " + Selecciones.getMaterialElegido());
  	    material.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), material.length()- Selecciones.getMaterialElegido().length(), material.length(), 0);
    	materialProducto.setText(material, BufferType.SPANNABLE);
    	SpannableString forma = new SpannableString("Forma: " + Selecciones.getFormaElegida());
  	    forma.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), forma.length()- Selecciones.getFormaElegida().length(), forma.length(), 0);
    	formaProducto.setText(forma, BufferType.SPANNABLE);
    	final Intent mapa = new Intent(this,MapaStock.class);
    	SpannableString stock = new SpannableString("Stock: " + Integer.toString(Selecciones.getStockElegido()) + " Disponible");
    	 stock.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), "Stock: ".length(), stock.length(), 0);
     	stockProducto.setText(stock, BufferType.SPANNABLE);
    	if(Selecciones.getStockElegido()==0){
    		LinearLayout linearStock = (LinearLayout)findViewById(R.id.linearStock);
        	Drawable mapaIcon = getResources().getDrawable( R.drawable.mapsboton);
    		mapaIcon = resizeIcon(mapaIcon);
        	ImageButton imageMap = new ImageButton(this);
    		imageMap.setImageDrawable(mapaIcon);
    		
    		
    		imageMap.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(mapa);
					
				}
			});
    		
    		linearStock.addView(imageMap);
    	}

  	   
    	
    	
    	Log.d("Marca", Selecciones.getMarcaElegida());
    	TextView medidas = (TextView)findViewById(R.id.medidas);
    	LinearLayout linearMedidas = (LinearLayout)findViewById(R.id.linearMedidas);
    	Drawable medidasIcon = getResources().getDrawable( R.drawable.medidasboton);
		medidasIcon = resizeIcon(medidasIcon);
    	ImageButton imageMedidas = new ImageButton(this);
		imageMedidas.setImageDrawable(medidasIcon);
	   	medidas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent medidas = new Intent(getApplicationContext(),Medidas.class);
				
				startActivity(medidas);
			}
		});
    	imageMedidas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent medidas = new Intent(getApplicationContext(),Medidas.class);
				
				startActivity(medidas);
			}
		});
    	
    	linearMedidas.addView(imageMedidas);
    	
    	ImageView imagenProducto = (ImageView) findViewById(R.id.imagenProducto);
    	
    	
    	try {

			Log.d("Path", "images/"+Selecciones.getSkuElegido()+"_1.jpg");			            // get input stream
            InputStream ims = getAssets().open("images/"+Selecciones.getSkuElegido()+".png");
            // load image as Drawable
            
            Drawable d = Drawable.createFromStream(ims, null);
            Drawable e = resize(d);
            
            
            
            
            // set image to ImageView
            imagenProducto.setImageDrawable(e);
        }
        catch(IOException ex) {

        imagenProducto.setImageResource(R.drawable.opticageneral);
        	
        }
    	
        carasUtiles();

		
	}

	
	/*Home Button */
	
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

	
	/*
	 * Carga el menu
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.similares, menu);
        return true;
	}
	
	/*
	 * Home Button
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.similares:
			Selecciones.setSkuElegido(clicked);
			final Intent carac = new Intent(this.getApplication(), Similares.class);
			startActivity(carac);
			Selecciones.setReturns(Selecciones.getReturns()+1);
			return true;
			
		case R.id.carro:
			
			
			if(Selecciones.isSelected(clicked) || !menuOpen){
				Log.d("Click Carro", "Entra");
				if(menuOpen){
					menu.showContent();
				}
				else{
					menu.showMenu();
					menuOpen = !menuOpen;

				}
				
			}
			
			Selecciones.addModelo();
			
			
			FragmentTransaction t = getFragmentManager().beginTransaction();
			t.replace(R.id.productosCarro, CarritoCompras.newInstance());
			t.commit();
			return true;
			
		}
		
		
		return false;
	}
	
	
	/*
	 * Busca que caras son compatibles segun la formula del lente
	 * esto no esta 100% fidedigno aun
	 */
	
	private void carasUtiles(){
		   
		   LinearLayout carasCompatibles = (LinearLayout) findViewById(R.id.carasCompatibles);
		   ImageView cuadrada = new ImageView(this);
		   cuadrada.setImageResource(R.drawable.faceshapesquaresmall);
		   cuadrada.setMaxHeight(150);
		   cuadrada.setAdjustViewBounds(true);
		   ImageView redonda = new ImageView(this);
		   redonda.setImageResource(R.drawable.faceshaperoundsmall);
		   redonda.setAdjustViewBounds(true);
		   ImageView corazon = new ImageView(this);
		   corazon.setImageResource(R.drawable.faceshapeheartsmall);
		   corazon.setMaxHeight(150);
		   corazon.setAdjustViewBounds(true);
		   ImageView ovalada = new ImageView (this);
		   ovalada.setImageResource(R.drawable.faceshapeovalsmall);
		   ovalada.setMaxHeight(150);
		   ovalada.setAdjustViewBounds(true);
		   ImageView rectangular = new ImageView(this);
		   rectangular.setImageResource(R.drawable.faceshaperectangularsmall);
		   rectangular.setMaxHeight(150);
		   rectangular.setAdjustViewBounds(true);
		   TextView cuadradaText = new TextView(this);
		   TextView redondaText = new TextView(this);
		   TextView corazonText = new TextView(this);
		   TextView ovaladaText = new TextView(this);
		   TextView rectangularText = new TextView(this);
		   cuadradaText.setText("Cuadrada");
		   redondaText.setText("Redonda");
		   corazonText.setText("Corazon");
		   ovaladaText.setText("Ovalada");
		   rectangularText.setText("Rectangular");
		   String forma = Selecciones.getFormaElegida();
		   if(forma.equals("RECTANGULAR")){
			   
			   carasCompatibles.addView(cuadrada);
			   carasCompatibles.addView(cuadradaText);
			   carasCompatibles.addView(redonda);
			   carasCompatibles.addView(redondaText);
			   carasCompatibles.addView(ovalada);
			   carasCompatibles.addView(ovaladaText);
			   
		   }
		   if(forma.equals("OVALADO")){
			   
			   carasCompatibles.addView(cuadrada);
			   carasCompatibles.addView(cuadradaText);
			   carasCompatibles.addView(ovalada);
			   carasCompatibles.addView(ovaladaText);

			   
		   }
		   
		   if(forma.equals("PILOTO")){
			   
			   carasCompatibles.addView(rectangular);
			   carasCompatibles.addView(rectangularText);

			   carasCompatibles.addView(corazon);
			   carasCompatibles.addView(corazonText);
			   carasCompatibles.addView(ovalada);
			   carasCompatibles.addView(ovaladaText);
			   
			   
		   }
		   
		   if(forma.equals("CUADRADO")){
			   
			   carasCompatibles.addView(redonda);
			   carasCompatibles.addView(redondaText);
			   carasCompatibles.addView(ovalada);
			   carasCompatibles.addView(ovaladaText);
			   
			   
		   }
		   
		   
		   if(forma.equals("REDONDO")){
			   
			   carasCompatibles.addView(cuadrada);
			   carasCompatibles.addView(cuadradaText);
			   
			   
		   }
		   
		   
		   
		   
	   }


	
}

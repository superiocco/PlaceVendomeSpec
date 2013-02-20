package com.example.placevendomespec;

import java.io.IOException;
import java.io.InputStream;



import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;


import com.example.grilla.DataBaseHelper;
import com.example.guardarSelecciones.Recomendaciones;
import com.example.guardarSelecciones.Selecciones;
import com.google.gson.Gson;


@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class Resultados extends SherlockActivity {
	String json;
	int i=0;
	String previousResult;
	android.view.Menu options;
	Modelo[] anteojos;
	boolean[] elegidos;
	int seleccionados=0;
	ActionMode action=null;
	
	
	/*
	 * Key Handler
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == 4){
			
			final Intent main = new Intent (this,MainActivity.class);
			startActivity(main);
			MainActivity.mSectionsPagerAdapter.setTrans(4);
			MainActivity.ini=1;
			finish();
			
			
			
		}
		
		return super.onKeyDown(keyCode, event);
	}


	/*
	 * inicializa
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultadostest);
		ActionBar bar = getSupportActionBar();
		bar.setTitle("Recomendaciones");
		bar.setIcon(R.drawable.home_icon_normal_invert);
		bar.setHomeButtonEnabled(true);
		
		if(savedInstanceState==null){

	 	Recomendaciones.getResultados(this);

		}
		
		else{
			
			getContents(savedInstanceState.getString("json"));
			
		}
		
	
			
	}

	/*
	 * Guarda componentes al momento de que la pantalla se gire
	 * (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if(json!=null){
		outState.putString("json", json);
		}
	}

	/*
	 * 
	 *  Maneja los clicks en algun item del menu
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.buscarMas:
			final Intent carac = new Intent(this.getApplication(), Caracteristicas.class);
			startActivity(carac);
			return true;
			
		case R.id.presupuesto:
			final Intent precio =  new Intent (this, Precio.class);
			startActivity(precio);
			return true;
			
		case R.id.comparar:

			/*options.clear();
			getSupportMenuInflater().inflate(R.menu.comparar, options);*/
			startActionMode(mContentSelectionActionModeCallback);

			return true;
		/*case R.id.deshacer:
			options.clear();
			getSupportMenuInflater().inflate(R.menu.option, options);
			deshacer(anteojos);
			return true;*/
		case R.id.mostrarResultados:
			Selecciones.setElegidos(elegidos);
			Selecciones.setCantidadElegidos(seleccionados);
			if(seleccionados>1){
			final Intent compara = new Intent(this.getApplication(), ComparaArticulos.class);
			startActivity(compara);
			
			return true;
			}
			else{
				return false;
			}
		
		}
		
		return false;
	}
	
	/*
	 * Vuelve al estado inicial donde el click en un articulo 
	 * logra su previsualizacion
	 * 
	 */
	
	private void deshacer(final Modelo[] recibidos) {
		
		int j=0;
		int total = recibidos.length;
		elegidos = new boolean[total];
		ActionMode bar = action;
		bar.setTitle(Integer.toString(total) + "   Recomendaciones");
		for(i=0;i<total;i++){
			
			elegidos[i]=false;
		}
		i=0;
		final Button[] parecidos = new Button[total];
		   for(i = 0; i<total; i++){
			   parecidos[i] = new Button(this);
			   parecidos[i] = (Button) findViewById(100 + i);
			   parecidos[i].setBackgroundColor(Color.BLACK);
				final   Intent preview = new Intent(getApplication(),Preview.class);

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
			   
			   
		   }
		
		
	}
	
	/*
	 * 
	 * Settea que los botones sean seleccionados para comparacion
	 * 
	 */

	private void getClicks(Modelo[] recibidos) {
		// TODO Auto-generated method stub
		int total = recibidos.length;
		elegidos = new boolean[total];
		ActionMode bar = action;
		bar.setTitle("0 Seleccionados");
		for(i=0;i<total;i++){
			elegidos[i]=false;
		}
		i=0;
		final Button[] parecidos = new Button[total];
		   for(i = 0; i<total; i++){
			   parecidos[i] = new Button(this);
			   parecidos[i] = (Button) findViewById(100 + i);
			   parecidos[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						int id = v.getId()-100;
					
					ActionMode bar = action;
					
					
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
			   
			   
		   }
	}

	
	/*
	 * 
	 *  Maneja el homeButton
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

	/*
	 * Genera de forma inicial el menu
	 * en la action bar
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.option, menu);

        
        return true;
	}

	
    private ActionMode.Callback mContentSelectionActionModeCallback = 
        new ActionMode.Callback() {
     public boolean onCreateActionMode(ActionMode actionMode, Menu menu) 
     {
             //Here you can inf         MenuInflater inflater = getSupportMenuInflater();
         action = actionMode;
    	 getSupportMenuInflater().inflate(R.menu.comparar, menu);
         getClicks(anteojos);
         seleccionados=0;

         return true;
     }

     public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
         return false;
     }

     public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) 
     {
    	 
    	 switch(menuItem.getItemId()) {
    	 case R.id.mostrarResultados:
 			Selecciones.setCantidadElegidos(seleccionados);
 			Selecciones.setElegidos(elegidos);
 			if(seleccionados>1){
 			final Intent compara = new Intent(getApplication(), ComparaArticulos.class);
 			startActivity(compara);
 			return true;
 			
 			}    		 
        	
 			return false;

         
 			}
    	 return false;
     }

     public void onDestroyActionMode(ActionMode actionMode){
    	 seleccionados=0;
    	 deshacer(anteojos);
		 
    	 
 	  }
 };
	
	
	/* Metodo llamado desde el Http Request, 
		busca las imagenes en assets/images
	 * Crea los botones con las imagenes y las reparte segun el tab correspondiente
	 * del parametro Tab que contiene el JSON
	 */
	
	public void getContents(String result){
		
		
		json = result;
		Selecciones.setPreviousResult(result);
		int totalTab=0;
		String clicked;
		Gson gson = new Gson();
		final Modelo[] recibidos = gson.fromJson(result, Modelo[].class);
		anteojos = recibidos;
		int total = recibidos.length;
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
		HorizontalScrollView scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
		scroll.setBackgroundColor(Color.BLACK);
		}
		clicked = Selecciones.getSkuElegido();
		Log.d("clicked", clicked);
		ActionBar bar = getSupportActionBar();
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
	            

	            
	            
	            // set image to ImageView

	            
	            if(recibidos[i].getTab()!=0){
	            	
	            	d = resizeSmall(d);
	            }
	            
	            else{
	            	
	            	d = resize(d);
	            }
	            
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
		drawersHandler();
			
			
		
		
		

	}
	/*
	 * Contiene las logicas de los sliders
	 */
	
	private void drawersHandler() {
		// TODO Auto-generated method stub
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
				Log.d("slide", "yellow");

				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

				
				yellowTab.setImageResource(R.drawable.yellow_up);
				blue.close();
				blueTab.setImageResource(R.drawable.budget_blue);
				green.close();
				greenTab.setImageResource(R.drawable.budget_pink);
				blockButtons(anteojos);
				
				}
				
				else{
					yellowTab.setImageResource(R.drawable.yellow_up_rotate);
					blue.close();
					blueTab.setImageResource(R.drawable.budget_blue_rotate);
					green.close();
					greenTab.setImageResource(R.drawable.budget_pink_rotate);
					blockButtons(anteojos);
					
					
				}
				
			}
		});
		
		
		green.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				Log.d("slide", "green");

				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				greenTab.setImageResource(R.drawable.pink_up);
				blue.close();
				blueTab.setImageResource(R.drawable.budget_blue);
				yellow.close();
				yellowTab.setImageResource(R.drawable.budget_yellow);
				blockButtons(anteojos);
				
				}
				
				else{
					
					greenTab.setImageResource(R.drawable.pink_up_rotate);
					blue.close();
					blueTab.setImageResource(R.drawable.budget_blue_rotate);
					yellow.close();
					yellowTab.setImageResource(R.drawable.budget_yellow_rotate);
					blockButtons(anteojos);
					
					
				}

			}
		});
		
		blue.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				Log.d("slide", "blue");
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				blueTab.setImageResource(R.drawable.blue_up);
				yellow.close();
				yellowTab.setImageResource(R.drawable.budget_yellow);
				green.close();
				greenTab.setImageResource(R.drawable.budget_pink);
				blockButtons(anteojos);
				}
				
				else{
					
					blueTab.setImageResource(R.drawable.blue_up_rotate);
					yellow.close();
					yellowTab.setImageResource(R.drawable.budget_yellow_rotate);
					green.close();
					greenTab.setImageResource(R.drawable.budget_pink_rotate);
					blockButtons(anteojos);
					
				}
			}
		});
	
		yellow.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				yellowTab.setImageResource(R.drawable.budget_yellow);
				freeButtons(anteojos);
				
				}

				else{
					yellowTab.setImageResource(R.drawable.budget_yellow_rotate);
					freeButtons(anteojos);
				}
			}
		});
		
		
		green.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				greenTab.setImageResource(R.drawable.budget_pink);
				freeButtons(anteojos);
				}
				
				else{
					greenTab.setImageResource(R.drawable.budget_pink_rotate);
					freeButtons(anteojos);
					
				}
				
			}
		});
		
		
		blue.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			
			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
			
				if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
				blueTab.setImageResource(R.drawable.budget_blue);
				freeButtons(anteojos);
				}
			
				else{
				
					blueTab.setImageResource(R.drawable.budget_blue_rotate);
					freeButtons(anteojos);
				}
			}
		});
		
		
	}

	public void showError(String result){
		
	Selecciones.setError(result);
	
	  final Intent main = new Intent (this,MainActivity.class);
		MainActivity.mSectionsPagerAdapter.setTrans(4);
		Selecciones.setError(result);
		startActivity(main);
		  Intent mensaje = new Intent(this, Mensaje.class);
		  startActivity(mensaje);
		  finish();
	
	}
	
	
	/*
	 * Bloquea los botones del Scroll caundo un Slider se abre
	 * 
	 */
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
   
   /*
    * Permite que los botones en el scroll sean clickeados nuevamente
    */
   
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

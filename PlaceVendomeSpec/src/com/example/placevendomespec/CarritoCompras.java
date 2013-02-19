package com.example.placevendomespec;

import java.io.IOException;
import java.io.InputStream;

import com.example.guardarSelecciones.Selecciones;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView.BufferType;

@TargetApi(17)
public class CarritoCompras extends Fragment {


	View v;
	Modelo elegidos[];
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View root = (View) inflater.inflate(R.layout.carrito, null);
		v = root;
		getSelecciones();
		return root;
	}
	
	private Drawable resizeSmall(Drawable image){
		 {
			    Bitmap d = ((BitmapDrawable)image).getBitmap();
			    Bitmap bitmapOrig = Bitmap.createScaledBitmap(d, 300, 300, false);
			    return new BitmapDrawable(bitmapOrig);
			}
		
	}

	public void getSelecciones(){
		
		
		if(Selecciones.getModelosElegidos()!=null){
		int i=0;
		LinearLayout lay = (LinearLayout) v.findViewById(R.id.productosCarro);
		lay.removeAllViews();
		elegidos = Selecciones.getModelosElegidos();
		Button[] producto = new Button[elegidos.length];
		for(i=0;i<elegidos.length;i++){
			
			producto[i] = new Button(getActivity());
			SpannableString text1 = new SpannableString("Lente: " + elegidos[i].getModelo() + "\n SKU: #" + elegidos[i].getSKU() + "        $" +  elegidos[i].getPrecio().trim());
		    text1.setSpan(new ForegroundColorSpan(Color.BLACK), 0,text1.length() - elegidos[i].getPrecio().trim().length() - 1, 0);
		    text1.setSpan(new ForegroundColorSpan(Color.RED), text1.length() - elegidos[i].getPrecio().trim().length() - 1, text1.length(), 0);
		    text1.setSpan(new RelativeSizeSpan(1.5f), text1.length() - elegidos[i].getPrecio().trim().length() - 1, text1.length(), 0);
		    producto[i].setText(text1, BufferType.SPANNABLE);

		    producto[i].setId(100+i);
			
			try {

				Log.d("Path", "images/"+elegidos[i].getSKU()+"_1.jpg");			            // get input stream
	            InputStream ims = getActivity().getAssets().open("images/"+elegidos[i].getSKU()+".jpg");
	            // load image as Drawable
	            Drawable d = Drawable.createFromStream(ims, null);
	            // set image to ImageView
	            Drawable e = resizeSmall(d);
	            producto[i].setCompoundDrawablesRelativeWithIntrinsicBounds(null, e, null, null);
	
	        }
	        catch(IOException ex) {
	        	producto[i].setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.opticageneralsmall, 0, 0);
	        
	        }
			
			producto[i].setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					int id = v.getId()-100;
					Log.d("Long Click",Integer.toString(v.getId()));
					Selecciones.removeModelo(elegidos[id].getSKU());
					getSelecciones();
					
					return true;
				}
			});
			final Intent preview = new Intent(getActivity(),Preview.class);
			producto[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int id = v.getId()-100;
					Selecciones.setProductoElegido(elegidos[id]);
					startActivity(preview);
				}
			});
			
			lay.addView(producto[i]);
			
		}
	
		}
	
	}
	
	
	public static Fragment newInstance(){
		
		return new CarritoCompras();
		
	}


	
	
}

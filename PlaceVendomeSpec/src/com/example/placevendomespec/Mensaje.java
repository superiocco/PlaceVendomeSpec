package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class Mensaje extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		showAsPopup(this);
		ActionBar bar = getActionBar();
		
		TextView texto = (TextView) findViewById(R.id.mensaje);
		texto.setTextColor(Color.WHITE);
		Log.d("error", Selecciones.getError());
		if(Selecciones.getError().equalsIgnoreCase("SKU no encontrado")){
		bar.setIcon(R.drawable.atencionpopup);
		bar.setTitle("Atención! Codigo SKU NO encontrado");
		
		SpannableString nosku = new SpannableString("El código ingresado, NO se encuentra en los registros, asegúrese de haber ingresado el código correctamente y/o intente nuevamente");
	    nosku.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),"El código ingresado,".length(), "El código ingresado, NO ".length(), 0);

		texto.setText(nosku,BufferType.SPANNABLE);
		
		}
		if(Selecciones.getError().equalsIgnoreCase("failed")){
			bar.setIcon(R.drawable.atencionpopup);
			bar.setTitle("Fallo en la conexion del servidor");
			SpannableString nowifi = new SpannableString("El código ingresado, NO se encuentra en los registros, asegúrese de haber ingresado el código correctamente y/o intente nuevamente");
		    nowifi.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),"Verifique el".length(), "Verifique el, WIFI".length(), 0);

			texto.setText(nowifi,BufferType.SPANNABLE);
			
			texto.setText("Verifique el WIFI del tablet, e intente nuevamente");
			
			
			}
		if(Selecciones.getError().equalsIgnoreCase("bases")){
			bar.setIcon(R.drawable.atencionpopup);
			bar.setTitle("Atención! Dioptría inusual - Verifique");
			texto.setText("NO existen marcos compatibles para esta Dioptría, asegúrese de haber ingresado la receta con sus respectivas cifras correctamente y/o intente nuevamente");
			
			}
		
		
		Button ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				// TODO Auto-generated method stub
				finish();
			}
		});
		//getWindow().setTitleColor(Color.BLACK);
	}
	
	



	public static void showAsPopup(Activity activity) {
	    //To show activity as dialog and dim the background, you need to declare android:theme="@style/PopupTheme" on for the chosen activity on the manifest
	    activity.requestWindowFeature(Window.FEATURE_ACTION_BAR);
	    /*activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
	            WindowManager.LayoutParams.FLAG_DIM_BEHIND);*/
	    LayoutParams params = activity.getWindow().getAttributes(); 
	    params.height = 400; //fixed height
	    params.width = 800; //fixed width
	    activity.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params); 
	    activity.setContentView(R.layout.mensaje);
	}
	
	
	
}

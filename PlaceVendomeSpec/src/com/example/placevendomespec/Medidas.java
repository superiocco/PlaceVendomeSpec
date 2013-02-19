package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class Medidas extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		showAsPopup(this);
		ActionBar bar = getActionBar();
		bar.setTitle("         Medidas");
		bar.setIcon(R.drawable.logo_opvtrans);
	    TextView aroAlto = (TextView) findViewById(R.id.aroAlto);
	    TextView aroAncho = (TextView) findViewById(R.id.aroAncho);
	    TextView puente = (TextView) findViewById(R.id.puente);
	    TextView base = (TextView) findViewById(R.id.base);
	    TextView diagonal = (TextView) findViewById(R.id.diagonal);
	    
	    SpannableString aroAltoText = new SpannableString("Aro Alto: " + Float.toString(Selecciones.getAroAltoElegido()));
	    aroAltoText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),aroAltoText.length()- Float.toString(Selecciones.getAroAltoElegido()).length()-1, aroAltoText.length(), 0);
	    aroAlto.setText(aroAltoText,BufferType.SPANNABLE);
	    aroAlto.setTextSize(20);
	    SpannableString aroAnchoText = new SpannableString("Aro Ancho: " + Float.toString(Selecciones.getAroAnchoElegido()));
	    aroAnchoText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),aroAnchoText.length()- Float.toString(Selecciones.getAroAnchoElegido()).length()-1, aroAnchoText.length(), 0);
	    aroAncho.setText(aroAnchoText,BufferType.SPANNABLE);
	    aroAncho.setTextSize(20);
	    SpannableString puenteText = new SpannableString("Puente: " + Float.toString(Selecciones.getPuenteElegido()));
	    puenteText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),puenteText.length()- Float.toString(Selecciones.getPuenteElegido()).length()-1, puenteText.length(), 0);
	    puente.setText(puenteText,BufferType.SPANNABLE);
	    puente.setTextSize(20);
	    SpannableString baseText = new SpannableString("Base: " + Float.toString(Selecciones.getBaseElegida()));
	    baseText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),baseText.length()- Float.toString(Selecciones.getBaseElegida()).length()-1, baseText.length(), 0);
	    base.setText(baseText,BufferType.SPANNABLE);
	    base.setTextSize(20);
	    SpannableString diagonalText = new SpannableString("Diagonal: " + Float.toString(Selecciones.getDiagonalElegida()));
	    diagonalText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),diagonalText.length()- Float.toString(Selecciones.getDiagonalElegida()).length()-1, diagonalText.length(), 0);
	    diagonal.setText(aroAltoText,BufferType.SPANNABLE);
	    diagonal.setTextSize(20);
	    
	    
	}
	

	

	public static void showAsPopup(Activity activity) {
	    //To show activity as dialog and dim the background, you need to declare android:theme="@style/PopupTheme" on for the chosen activity on the manifest
	    activity.requestWindowFeature(Window.FEATURE_ACTION_BAR);
	    LayoutParams params = activity.getWindow().getAttributes(); 
	    params.height = 500; //fixed height
	    params.width = 800; //fixed width
	    activity.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params); 
	    activity.setContentView(R.layout.medidas);
	}



}

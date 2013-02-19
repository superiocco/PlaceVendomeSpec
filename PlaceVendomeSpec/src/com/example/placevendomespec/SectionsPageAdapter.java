package com.example.placevendomespec;



import com.example.guardarSelecciones.Selecciones;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

public class SectionsPageAdapter extends FragmentStatePagerAdapter {

	

	private static int trans=0;
	private static int transInfo=0;
	
	
	/*
	 * 
	 */
	
	void setTransInfo(int t){
		transInfo=t;
	}
	
	/*
	 *   Cuando haya mas de una pantalla en el tab de informacion
	 */
	
	int getTransInfo(){
		return transInfo;
	}
	
	void setTrans(int t ){
		trans=t;
	}
	
	
	int getTrans(){
		
		return trans;
	}
	

	/*
	 * Llamado con notify
	 * borra fragments que ya no estan siendo utilizados
	 * guiados por trans
	 * 
	 * (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getItemPosition(java.lang.Object)
	 */
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub

		if(object instanceof Sexo && trans!=0 )
			return POSITION_NONE;
		if(object instanceof Edad && trans==1)
			return 1;
		if(object instanceof Tipo && trans==2 )
			return 1;
		if(object instanceof Edad && trans!=1)
			return POSITION_NONE;
		
		if(object instanceof Tipo && trans!=2 )
			return POSITION_NONE;
		if(object instanceof Receta && trans==3)
			return 1;
		if(object instanceof Receta && trans!=3 )
			return POSITION_NONE;
		if(object instanceof CaraActivity && trans==4)
			return 1;
		if(object instanceof CaraActivity && trans!=4 )
			return POSITION_NONE;
		if(object instanceof Resultados&& trans==5)
			return 1;
		if(object instanceof Resultados && trans!=5)
			return POSITION_NONE;
		if(object instanceof Caracteristicas && trans ==6)
			return 1;
		if(object instanceof Caracteristicas && trans !=6)
			return POSITION_NONE;
		if(object instanceof ResultadosFinales && trans ==7)
			return 1;
		if(object instanceof ResultadosFinales && trans !=7)
			return POSITION_NONE;
		
		
		
		
		if(object instanceof oferta)
			return POSITION_UNCHANGED;
		if(object instanceof Informacion)
			return POSITION_UNCHANGED;
		
		
		return super.getItemPosition(object);
	}

	public SectionsPageAdapter(FragmentManager fm) {
		super(fm);
	}
	

	
	/*
	 * 
	 * Recupera el Fragment segun el tab en que este, ademas de trans que indica
	 * el activo actualmente
	 * 
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentStatePagerAdapter#getItem(int)
	 */
	
	
	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = null;
		
		switch(position){
		

		case 0:
			fragment=oferta.newInstance();	

			break;
		case 1:
			
			if(trans==0){
				fragment=Sexo.newInstance(this);	
				
			}
			if(trans==1){
				fragment=Edad.newInstance(this);	

				
			}
			if(trans==2){
				fragment = Tipo.newInstance(this);
			}
			if(trans==3){
				fragment = Receta.newInstance(this);
			}
			if(trans==4){
				fragment = CaraActivity.newInstance(this);
			}

			
			break;
		case 2:
			fragment=Informacion.newInstance();	
			break;	
		}
		Log.d("Adapter", "getItem");
		
		return fragment;
	}

	/*numero de tabs */
	
	@Override
	public int getCount() {
		// Show 3 total pages.
		return 3;
	}

	/*titulos-*/
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Ofertas";
		case 1:
			return "Tienda";
		case 2:
			return "Información";
		}
		return null;
	}
	
}

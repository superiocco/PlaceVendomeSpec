package com.example.placevendomespec;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class Caracteristicas extends Activity {
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vistas);
		setClickListeners();


	}


	private void setClickListeners(){
		 final ImageButton marca = (ImageButton)findViewById(R.id.Marca);
		 final ImageButton color = (ImageButton)findViewById(R.id.Color);
		 final ImageButton diseno = (ImageButton)findViewById(R.id.Diseno);
		 final ImageButton material = (ImageButton)findViewById(R.id.Material);
		 final Button Next = (Button)findViewById(R.id.next);
		 final Intent brand = new Intent(this, Marca.class);
		 final Intent colour = new Intent (this, Colores.class);
		 final Intent design = new Intent (this, Diseno.class);
		 final Intent weight = new Intent (this, Peso.class);
		 final Intent quality = new Intent (this, Calidad.class);
		 final Intent material1 = new Intent (this, Material.class);
		 
		 final Intent resultadosFinales = new Intent (this,ResultadosFinales.class);
		 Next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(resultadosFinales);
			}
		});
	
		 marca.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Log.d("Click", "marca");
				startActivity(brand);

			}
		});
	
		 color.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Click", "colores");
				startActivity(colour);

			}
		});
	

		 diseno.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Click", "diseno");
				startActivity(design);

			}
		});
		
		 
		 material.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Click", "material");
				startActivity(material1);

			}
		});
		 
		
	}


/*	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK && requestCode==1){
			  Log.d("Resultado", "ok");
		}
	}*/
	

	
}

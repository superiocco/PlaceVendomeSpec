package com.example.placevendomespec;



import com.example.guardarSelecciones.Selecciones;
import com.origamilabs.library.views.StaggeredGridView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class oferta extends Fragment {
	
	private String urls[] = { 
			"http://www.opv.cl/wp-content/uploads/Banner2.jpg",
			"http://www.opv.cl/wp-content/uploads/Banner31.jpg",
			"http://farm8.staticflickr.com/7232/6913504132_a0fce67a0e_c.jpg",
			"http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg",
			"http://farm9.staticflickr.com/8462/8005636463_0cb4ea6be2.jpg",
			"http://img.blumex.net/wp-content/uploads/2011/03/anteojos.jpg",
			"http://farm9.staticflickr.com/8306/7987149886_6535bf7055.jpg",
			"http://farm9.staticflickr.com/8444/7947923460_18ffdce3a5.jpg",
			"http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg",
			"http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg",
			"http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg",
			"http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg",
			"http://2.bp.blogspot.com/-gdrRm1KnO0M/TiGFnVt91qI/AAAAAAAAHwM/CyqTc7D3TUw/s1600/paisajes.jpg",
			"http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg",
			"http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg",
			"http://farm9.staticflickr.com/8060/8173387478_a117990661.jpg",
			"http://farm9.staticflickr.com/8056/8144042175_28c3564cd3.jpg",
			"http://farm9.staticflickr.com/8183/8088373701_c9281fc202.jpg",
			"http://farm9.staticflickr.com/8182/7941954368_3c88ba4a28.jpg"
			
			
	};
	

	private static SectionsPageAdapter Adapter;
	private static int idProducto;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("ACTIVITY", "On Create ViewGroup");
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.ofertas, null);	
		Log.d("ACTIVITY", "Inflater");
		

		
		Log.d("ACTIVITY", "Root");
		
		StaggeredGridView gridView = (StaggeredGridView)root.findViewById(R.id.staggeredGridView1);
	/*	ImageView image1 = new ImageView(this.getActivity());
		ImageView image2 = new ImageView(this.getActivity());
		image1.setBackgroundResource(R.drawable.one);
		image2.setBackgroundResource(R.drawable.two);
		
		

		gridView.addView(image1);
		gridView.addView(image2);*/
		
		
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);
		
		gridView.setItemMargin(margin); // set the GridView margin
		
		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well 
		
		StaggeredAdapter adapter = new StaggeredAdapter(this.getActivity(), R.id.imageView1, urls);
		
		gridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
			return root;
	}

	public static Fragment newInstance() {
		oferta f = new oferta();	
		
		return f;
	}
	


	
	

}

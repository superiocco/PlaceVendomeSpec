package com.example.placevendomespec;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.NumberPicker;

public class FloatPicker extends NumberPicker {

	
	static NumberPicker numberPicker;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("TOUCH",event.toString());
		return super.onTouchEvent(event);
	}

	public FloatPicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public NumberPicker newInstance(){
		
	
		return numberPicker;
		
		
	}
	

	
	
}

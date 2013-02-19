package com.example.placevendomespec;

import com.example.guardarSelecciones.Selecciones;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.res.Configuration;
import android.graphics.Color;

public class MainActivity extends FragmentActivity {

	static int ini = 1;
	ViewPager mViewPager;
	public static SectionsPageAdapter mSectionsPagerAdapter;

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		ini = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem(ini);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("Keycode",Integer.toString(keyCode));
		if(keyCode ==  4){
		int trans = mSectionsPagerAdapter.getTrans();
		int actual;
		Log.d("KEY", Integer.toString(trans));
		mViewPager = (ViewPager) findViewById(R.id.pager);
		actual = mViewPager.getCurrentItem();
		if(actual == 1){
		if(trans!=0){
			
			if(Selecciones.getTipo().equals("sol") && trans==4){
				mSectionsPagerAdapter.setTrans(trans-2);
				
				
			}
			else{
		mSectionsPagerAdapter.setTrans(trans-1);		
			}
			
			mSectionsPagerAdapter.notifyDataSetChanged();

		}
		else{
			return super.onKeyDown(keyCode, event);
		}
		
		}
		else{
		return super.onKeyDown(keyCode, event);
		}
		}
		else{
		return super.onKeyDown(keyCode, event);
		}
		return false;
	}

	

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPageAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOffscreenPageLimit(2);
		mViewPager.setCurrentItem(ini);
		Log.d("Main", "OnCreate");

	
	}
	
	/*
	 * En caso de que se necesite una actionBar
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}	
}

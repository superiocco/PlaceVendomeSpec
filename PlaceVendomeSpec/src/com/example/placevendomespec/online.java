package com.example.placevendomespec;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class online extends Fragment{
	
	

	private static WebView web; 
	private static String actualUrl="http://www.placevendome.cl/";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
	ViewGroup root = (ViewGroup) inflater.inflate(R.layout.online, null);
	
	loadUrl(root);
	
		return root;
		
	}
	
	
	
	public void loadUrl(View root){
		WebView myWebView = (WebView) root.findViewById(R.id.webView1);
		myWebView.loadUrl(actualUrl);
		web = myWebView;
		myWebView.setWebViewClient(new WebViewClient(){
			
			
			
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				
				actualUrl = url;
				view.loadUrl(url);
				
				return true;
			}
		});
		
		
	}
	
	public void goBackUrl(){
		
		web.goBack();
		
	}
	
	public static Fragment newInstance() {
		online f = new online();	
		
		return f;
	}




}

package com.example.guardarSelecciones;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;

import com.example.placevendomespec.MapaStock;
import com.example.placevendomespec.Resultados;
import com.example.placevendomespec.ResultadosFinales;
import com.example.placevendomespec.Similares;


import android.os.AsyncTask;
import android.util.Log;

public class AsyncHttpPost extends AsyncTask<String, String, String> {
    private HashMap<String, String> mData = null;// post data

    /**
     * constructor
     */
    public String httpResult;
    public Similares simi;
    public Resultados resu;
    public ResultadosFinales finResu;
    public MapaStock map;
    public int requestType;
    
    public AsyncHttpPost(HashMap<String, String> data) {
        mData = data;
    }

    /**
     * background
     */
    @Override
    protected String doInBackground(String... params) {
        byte[] result = null;
        String str = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(params[0]);// in this case, params[0] is URL
        try {
            // set up post data
            ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            Iterator<String> it = mData.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
            }

            post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            Log.d("response",statusLine.toString());
            if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
            	Log.d("Result","ok");
                result = EntityUtils.toByteArray(response.getEntity());
                Log.d("RESULT LENGTH", Integer.toString(result.length));
                str = new String(result, "UTF-8");
            }
            
            else{
            	return "failed";
            	
            }
            
            
            
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "failed";
        }
        catch (Exception e) {
        	e.printStackTrace();
        	return "failed";
        	
        }
        return str;
    }

    /**
     * on getting result
     */
    @Override
    protected void onPostExecute(String result) {
        // something...
    	Log.d("Conexion Result","Done!");
    	if(result.equals("failed") || result.equals("SKU no encontrado")){
    		Log.d("Conexion Result",result);
    		if (requestType==1){
    			simi.showError(result);
    		
    		}
    		if(requestType==2){
    			//funcion;
    			resu.showError(result);
    		}
    		
    		if(requestType==3){
    			finResu.showError(result);
    		}
    		if(requestType==4){
    			map.showError(result);
    		}
    		
    	}
    	else{
    		Log.d("Conexion Result",result);
    		if (requestType==1){
    			simi.loadResults(result);
    		}
    		if(requestType==2){
    			resu.getContents(result);
    		}
    		if(requestType==3){
    			finResu.getContents(result);
    		}
    		if(requestType==4){
    			map.setMarkers(result);
    		}
    	}
    }
    
    public String getResult(){
    	
    	return httpResult;
    }
}
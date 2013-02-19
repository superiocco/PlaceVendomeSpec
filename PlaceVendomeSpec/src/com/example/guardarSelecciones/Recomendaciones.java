package com.example.guardarSelecciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.placevendomespec.MapaStock;
import com.example.placevendomespec.Resultados;
import com.example.placevendomespec.ResultadosFinales;
import com.example.placevendomespec.Similares;

import android.util.Log;
import android.widget.Toast;

public class Recomendaciones {
	
	
	public static void getResultados(Resultados res) {

		
		HashMap<String, String> p = new HashMap<String, String>();
        p.put("sexo", Selecciones.getSexo());
        p.put("formaCara", Selecciones.getCara());
        p.put("base", Selecciones.getBases());
        p.put("valorMin", Integer.toString(Selecciones.getPrecioMinimo()));
        p.put("valorMax", Integer.toString(Selecciones.getPrecioMaximo()));
        p.put("edad", Selecciones.getEdad());
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(p);
		Log.d("Conexion", "Parameters");
		asyncHttpPost.requestType=2;
		asyncHttpPost.resu=res;
		Log.d("Params Sexo", Selecciones.getSexo());
		Log.d("Params Cara", Selecciones.getCara());
		Log.d("Params Bases", Selecciones.getBases());
		Log.d("Params Minimo", Integer.toString(Selecciones.getPrecioMinimo()));
		Log.d("Params Maximo", Integer.toString(Selecciones.getPrecioMaximo()));
		Log.d("Params", Selecciones.getEdad());
		asyncHttpPost.execute("http://166.78.31.135:8001");
		
		Log.d("Conexion", "Executed");

	       
	}
	
	public static void getCompleteResultados(ResultadosFinales res){
		
		HashMap<String, String> p = new HashMap<String, String>();
        p.put("sexo", Selecciones.getSexo());
        p.put("formaCara", Selecciones.getCara());
        p.put("base", Selecciones.getBases());
        p.put("valorMin", Integer.toString(Selecciones.getPrecioMinimo()));
        p.put("valorMax", Integer.toString(Selecciones.getPrecioMaximo()));
        p.put("formaArmazon", Selecciones.getDiseno());
        p.put("color", Selecciones.getColores());
        p.put("material", Selecciones.getMateriales());
        p.put("marca", Selecciones.getMarcas());
        p.put("edad", Selecciones.getEdad());
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(p);
		Log.d("Conexion", "Parameters");
		asyncHttpPost.requestType=3;
		asyncHttpPost.finResu=res;
		Log.d("Param Sexo", Selecciones.getSexo());
		Log.d("Param Cara", Selecciones.getCara());
		Log.d("Param Bases", Selecciones.getBases());
		Log.d("Param Minimo", Integer.toString(Selecciones.getPrecioMinimo()));
		Log.d("Param Maximo", Integer.toString(Selecciones.getPrecioMaximo()));
		Log.d("Param Forma", Selecciones.getDiseno());
        Log.d("Param Colores", Selecciones.getColores());
        Log.d("Param Materiales", Selecciones.getMateriales());
        Log.d("Param Marcas", Selecciones.getMarcas());
        Log.d("Param Edad", Selecciones.getEdad());
		
		asyncHttpPost.execute("http://166.78.31.135:8002");
		
		Log.d("Conexion", "Executed");
		
		
	}

	public static void getSimilares(Similares sim){
	
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("idProd", Selecciones.getSkuElegido());
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.simi=sim;
		asyncHttpPost.requestType=1;
		Log.d("Conexion", "Parameters");
		Log.d("Conexion", Selecciones.getSkuElegido());
		asyncHttpPost.execute("http://166.78.31.135:8000");
		Log.d("Conexion", "Executed");		
		}
	
	public static void getStocks(MapaStock map){
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("idProd", Selecciones.getSkuElegido());
		AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
		asyncHttpPost.map=map;
		asyncHttpPost.requestType=4;
		Log.d("Conexion", "Parameters");
		Log.d("Conexion", Selecciones.getSkuElegido());
		asyncHttpPost.execute("http://166.78.31.135:8006");
		Log.d("Conexion", "Executed");		
		}
}

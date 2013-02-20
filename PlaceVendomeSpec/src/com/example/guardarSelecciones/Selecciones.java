package com.example.guardarSelecciones;

import android.util.Log;

import com.example.placevendomespec.Modelo;

/*
 * Contiene todos los datos de selecciones echas y calculos realizados
 * 
 */


public class Selecciones {
	
	private static int stockElegido=0;
	private static int resultsLoaded=0;
	private static String previousResult;
	private static String error;
	private static boolean[] elegidos; 
	private static int cantidadElegidos=0;
	private static Modelo[] modelosElegidos=null;

	public static void setProductoElegido(Modelo modelo){
		
		Selecciones.setAroAltoElegido(modelo.getAroAlto());
		Selecciones.setAroAnchoElegido(modelo.getAroAncho());
		Selecciones.setPuenteElegido(modelo.getPuente());
		Selecciones.setSkuElegido(modelo.getSKU());
		Selecciones.setModeloElegido(modelo.getModelo());
		Selecciones.setMarcaElegida(modelo.getMarca());
		Selecciones.setMaterialElegido(modelo.getMaterial());
		Selecciones.setPrecioElegido(Integer.parseInt(modelo.getPrecio().trim()));
		Selecciones.setStockElegido(modelo.getStock());
		Selecciones.setColorElegido(modelo.getColor());
		Selecciones.setBaseElegida(modelo.getBase());
		Selecciones.setDiagonalElegida(modelo.getDiagonal());
		Selecciones.setFormaElegida(modelo.getForma());
		Selecciones.setSexo(modelo.getSexo());
		
		
	}
	public static boolean isSelected(String Sku){
	
		boolean selected=false;
		int i;
		Log.d("clicked", Sku);

		if(modelosElegidos == null){
			
			return selected;
			
		}
		
		else{
		
		for(i=0;i<modelosElegidos.length;i++){
			
			if(Sku.equals(modelosElegidos[i].getSKU())){
				
				selected=true;
				
			}
			
		}
		return selected;
		}
	}
	
	public static void removeModelo(String sku){
		
		Modelo modelo = new Modelo();

		
		int i=0;
		int j=0;
		boolean encontrado=false;
		if(modelosElegidos==null){
			
			modelosElegidos = new Modelo[0];
		}
		
		Modelo[] aux = new Modelo[modelosElegidos.length-1];

		
		for(i=0;i<modelosElegidos.length;i++){
		
			if(!sku.equals(modelosElegidos[i].getSKU())){
				
				aux[j] = modelosElegidos[i];
				j++;
			}
			

			
		}
		
		modelosElegidos = aux;
		
		
		
		
	}
	
	public static void addModelo(){
		
		
		Modelo modelo = new Modelo();
		modelo.setAroAlto(Selecciones.getAroAltoElegido());
		modelo.setAroAncho(Selecciones.getAroAnchoElegido());
		modelo.setPuente(Selecciones.getPuenteElegido());
		modelo.setSKU(Selecciones.getSkuElegido());
		modelo.setModelo(Selecciones.getModeloElegido());
		modelo.setMarca(Selecciones.getMarcaElegida());
		modelo.setMaterial(Selecciones.getMaterialElegido());
		modelo.setPrecio(Integer.toString(Selecciones.getPrecioElegido()));
		modelo.setStock(Selecciones.getStockElegido());
		modelo.setColor(Selecciones.getColorElegido());
		modelo.setBase(Selecciones.getBaseElegida());
		modelo.setDiagonal(Selecciones.getDiagonalElegida());
		modelo.setForma(Selecciones.getFormaElegida());
		modelo.setSexo(Selecciones.getSexo());
		
		int i=0;
		boolean encontrado=false;
		if(modelosElegidos==null){
			
			modelosElegidos = new Modelo[0];
		}
		
		for(i=0;i<modelosElegidos.length;i++){
		
			if(modelo.getSKU().equals(modelosElegidos[i].getSKU())){
				
				encontrado=true;
				
			}
			
		}
		
		if(!encontrado){
			
			Log.d("Total", Integer.toString(i));
			Modelo[] aux = new Modelo[modelosElegidos.length+1];
			for(i=0;i<modelosElegidos.length;i++){
			aux[i] = modelosElegidos[i];
			
			}
			aux[modelosElegidos.length]=new Modelo();
			aux[modelosElegidos.length] = modelo;
			modelosElegidos = new Modelo[i+1];
			modelosElegidos = aux;

			
		}
		
	}
	
	public static Modelo[] getModelosElegidos() {
		return modelosElegidos;
	}

	public static int getCantidadElegidos() {
		return cantidadElegidos;
	}
	public static void setCantidadElegidos(int cantidadElegidos) {
		Selecciones.cantidadElegidos = cantidadElegidos;
	}
	public static boolean[] getElegidos() {
		return elegidos;
	}
	public static void setElegidos(boolean[] elegidos) {
		Selecciones.elegidos = elegidos;
	}
	public static String getError() {
		return error;
	}
	public static void setError(String error) {
		Selecciones.error = error;
	}
	public static String getPreviousResult() {
		return previousResult;
	}
	public static void setPreviousResult(String previousResult) {
		Selecciones.previousResult = previousResult;
	}
	public static int getResultsLoaded() {
		return resultsLoaded;
	}
	public static void setResultsLoaded(int resultsLoaded) {
		Selecciones.resultsLoaded = resultsLoaded;
	}
	public static int getStockElegido() {
		return stockElegido;
	}
	public static void setStockElegido(int stockElegido) {
		Selecciones.stockElegido = stockElegido;
	}
	
	
	public static float getBaseElegida() {
		return baseElegida;
	}
	public static void setBaseElegida(float baseElegida) {
		Selecciones.baseElegida = baseElegida;
	}

	private static int returns = 0;
	public static int getReturns() {
		return returns;
	}
	public static void setReturns(int returns) {
		Selecciones.returns = returns;
	}

	private static String enfermedad="";
	private static float baseElegida=0;
	private static int precioMinimo=50000;
	private static int precioMaximo=100000;
	private static String marcaElegida="";
	private static String colorElegido="";
	private static int precioElegido;
	private static String modeloElegido="";
	private static float aroAltoElegido=0;
	private static float aroAnchoElegido=0;
	private static float diagonalElegida = 0;
	private static float puenteElegido=0;
	public static float getAroAltoElegido() {
		return aroAltoElegido;
	}
	public static void setAroAltoElegido(float aroAltoElegido) {
		Selecciones.aroAltoElegido = aroAltoElegido;
	}
	public static float getAroAnchoElegido() {
		return aroAnchoElegido;
	}
	public static void setAroAnchoElegido(float aroAnchoElegido) {
		Selecciones.aroAnchoElegido = aroAnchoElegido;
	}
	public static float getDiagonalElegida() {
		return diagonalElegida;
	}
	public static void setDiagonalElegida(float diagonalElegida) {
		Selecciones.diagonalElegida = diagonalElegida;
	}
	public static float getPuenteElegido() {
		return puenteElegido;
	}
	public static void setPuenteElegido(float puenteElegido) {
		Selecciones.puenteElegido = puenteElegido;
	}


	public static String getEnfermedad() {
		return enfermedad;
	}
	public static void setEnfermedad(String enfermedad) {
		Selecciones.enfermedad = enfermedad;
	}

	private static String formaElegida="";
	private static String materialElegido="";
	private static String sexo="h";
	private static String tipo="";
	private static String cara="";
	private static String edad;
	private static float esferaDerechaCerca=0;
	private static float esferaDerechaLejos=0;
	private static float esferaIzquierdaCerca=0;
	private static float esferaIzquierdaLejos=0;
	private static float cilindroDerecho=0;
	private static float cilindroIzquierdo=0;
	private static float ejeDerecho=0;
	private static float ejeIzquierdo=0;
	private static float adicion=0;
	private static int distanciaPupilar=60;
	private static String sku;
	private static String marcas="";
	private static String materiales="";
	private static String colores="";
	private static String diseno="";
	private static String bases="";
	

	public static int getPrecioMinimo() {
		return precioMinimo;
	}
	public static void setPrecioMinimo(int precioMinimo) {
		Selecciones.precioMinimo = precioMinimo;
	}
	public static int getPrecioMaximo() {
		return precioMaximo;
	}
	public static void setPrecioMaximo(int precioMaximo) {
		Selecciones.precioMaximo = precioMaximo;
	}

	public static String getFormaElegida() {
		return formaElegida;
	}
	public static void setFormaElegida(String formaElegida) {
				if(Selecciones.formaElegida.equals("RECTANGU")){
			
			Selecciones.formaElegida = "RECTANGULAR";
		}
		Selecciones.formaElegida = formaElegida;
	}
	public static String getModeloElegido() {
		return modeloElegido;
	}
	public static void setModeloElegido(String modeloElegido) {
		Selecciones.modeloElegido = modeloElegido;
	}
	public static String getMarcaElegida() {
		return marcaElegida;
	}
	public static void setMarcaElegida(String marcaElegida) {
		Selecciones.marcaElegida = marcaElegida;
	}
	public static String getColorElegido() {
		return colorElegido;
	}
	public static void setColorElegido(String colorElegido) {
		Selecciones.colorElegido = colorElegido;
	}
	public static int getPrecioElegido() {
		return precioElegido;
	}
	public static void setPrecioElegido(int precioElegido) {
		Selecciones.precioElegido = precioElegido;
	}
	private static String skuElegido="000";
	private static String calidad="";
	public static String getCalidad() {
		return calidad;
	}
	public static void setCalidad(String calidad) {
		Selecciones.calidad = calidad;
	}
	public static String getSkuElegido() {
		return skuElegido;
	}
	public static void setSkuElegido(String skuElegido) {
		if(skuElegido.equals("")){
	
			Selecciones.skuElegido="000";
			
		}
		else{
	 Selecciones.skuElegido = skuElegido;
		}
	}
	
	public static String getBases() {
		return bases;
	}
	public static void setBases(String bases) {
		Selecciones.bases = bases;
	}
	public static String getSexo() {
		return sexo;
	}
	public static void setSexo(String sexo) {
		Selecciones.sexo = sexo;
	}
	
	public static float getEsferaDerechaCerca() {
		return esferaDerechaCerca;
	}
	public static void setEsferaDerechaCerca(float esferaDerechaCerca) {
		Selecciones.esferaDerechaCerca = esferaDerechaCerca;
	}
	public static float getEsferaDerechaLejos() {
		return esferaDerechaLejos;
	}
	public static void setEsferaDerechaLejos(float esferaDerechaLejos) {
		Selecciones.esferaDerechaLejos = esferaDerechaLejos;
	}
	public static float getEsferaIzquierdaCerca() {
		return esferaIzquierdaCerca;
	}
	public static void setEsferaIzquierdaCerca(float esferaIzquierdaCerca) {
		Selecciones.esferaIzquierdaCerca = esferaIzquierdaCerca;
	}
	public static float getEsferaIzquierdaLejos() {
		return esferaIzquierdaLejos;
	}
	public static void setEsferaIzquierdaLejos(float esferaIzquierdaLejos) {
		Selecciones.esferaIzquierdaLejos = esferaIzquierdaLejos;
	}
	
	
	public static int getDistanciaPupilar() {
		return distanciaPupilar;
	}
	public static void setDistanciaPupilar(int distanciaPupilar) {
		Selecciones.distanciaPupilar = distanciaPupilar;
	}
	
	public static float getCilindroDerecho() {
		return cilindroDerecho;
	}
	public static void setCilindroDerecho(float cilindroDerecho) {
		Selecciones.cilindroDerecho = cilindroDerecho;
	}
	public static float getCilindroIzquierdo() {
		return cilindroIzquierdo;
	}
	public static void setCilindroIzquierdo(float cilindroIzquierdo) {
		Selecciones.cilindroIzquierdo = cilindroIzquierdo;
	}
	public static float getEjeDerecho() {
		return ejeDerecho;
	}
	public static void setEjeDerecho(float ejeDerecho) {
		Selecciones.ejeDerecho = ejeDerecho;
	}
	public static float getEjeIzquierdo() {
		return ejeIzquierdo;
	}
	public static void setEjeIzquierdo(float ejeIzquierdo) {
		Selecciones.ejeIzquierdo = ejeIzquierdo;
	}
	public static float getAdicion() {
		return adicion;
	}
	public static void setAdicion(float adicion) {
		Selecciones.adicion = adicion;
	}
	public static String getSku() {
		return sku;
	}
	public static void setSku(String sku) {
		
		Selecciones.sku = sku;
		
	}
	public static String getMarcas() {
		return marcas;
	}
	public static void setMarcas(String marcas) {
		Selecciones.marcas = marcas;
	}
	public static String getMateriales() {
		return materiales;
	}
	public static void setMateriales(String materiales) {
		Selecciones.materiales = materiales;
	}
	public static String getColores() {
		return colores;
	}
	public static void setColores(String colores) {
		Selecciones.colores = colores;
	}
	
	public static String getTipo() {
		return tipo;
	}
	public static void setTipo(String tipo) {
		Selecciones.tipo = tipo;
	}
	public static String getCara() {
		return cara;
	}
	public static void setCara(String cara) {
		Selecciones.cara = cara;
	}
	public static String getEdad() {
		return edad;
	}
	public static void setEdad(String edad) {
		Selecciones.edad = edad;
	}
	public static String getDiseno() {
		return diseno;
	}
	public static void setDiseno(String diseno) {
		Selecciones.diseno = diseno;
	}
	public static void setMaterialElegido(String materialElegido) {
		Selecciones.materialElegido = materialElegido;
	}
	public static String getMaterialElegido() {
		return materialElegido;
	}


	
		
	
}

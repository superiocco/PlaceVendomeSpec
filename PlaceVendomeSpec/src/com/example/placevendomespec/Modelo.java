package com.example.placevendomespec;

public class Modelo {
	
	private String SKU;
	private String color;
	private String material;
	private float diagonal;
	private String sexo;
	private float puente;
	private float base;
	private String modelo;
	private float aroAlto;
	private String forma;
	private String marca;
	private String precio;
	private float aroAncho;
	private int stock;
	private int tab;
	
	
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public float getDiagonal() {
		return diagonal;
	}
	public void setDiagonal(float diagonal) {
		this.diagonal = diagonal;
	}
	public float getPuente() {
		return puente;
	}
	public void setPuente(float puente) {
		this.puente = puente;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public float getBase() {
		return base;
	}
	public void setBase(float base) {
		this.base = base;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public float getAroAncho() {
		return aroAncho;
	}
	public void setAroAncho(float aroAncho) {
		this.aroAncho = aroAncho;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo.trim();
	}
	public float getAroAlto() {
		return aroAlto;
	}
	public void setAroAlto(float aroAlto) {
		this.aroAlto = aroAlto;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		if(forma.equals("RECTANGU")){
			
			this.forma = "RECTANGULAR";
		}
		this.forma = forma;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
}
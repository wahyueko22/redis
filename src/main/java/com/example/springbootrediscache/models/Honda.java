package com.example.springbootrediscache.models;

public class Honda {
	private String name;
	private int nomor;
	private boolean isRed;
	
	public Honda() {
	}
	
	public Honda(String name, int nomor, boolean isRed) {
		this.name = name;
		this.nomor = nomor;
		this.isRed = isRed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNomor() {
		return nomor;
	}
	public void setNomor(int nomor) {
		this.nomor = nomor;
	}
	public boolean isRed() {
		return isRed;
	}
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}
	
	
	
	
}

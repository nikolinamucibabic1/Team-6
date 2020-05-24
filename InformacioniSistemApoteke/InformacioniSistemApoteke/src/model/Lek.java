package model;

import java.io.Serializable;

public class Lek implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sifra;
	private String ime;
	private String prozivodjac;
	private boolean recept;
	private float cena;
	private boolean obrisan;
	
	public Lek() {
		obrisan = false;
	}
	
	public Lek(String sifra, String ime, String proizvodjac, boolean recept, float cena, boolean obrisan) {
		this.sifra = sifra;
		this.ime = ime;
		this.prozivodjac = proizvodjac;
		this.recept = recept;
		this.cena = cena;
		this.obrisan = obrisan;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getProzivodjac() {
		return prozivodjac;
	}

	public void setProzivodjac(String prozivodjac) {
		this.prozivodjac = prozivodjac;
	}

	public boolean isRecept() {
		return recept;
	}

	public void setRecept(boolean recept) {
		this.recept = recept;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	

}

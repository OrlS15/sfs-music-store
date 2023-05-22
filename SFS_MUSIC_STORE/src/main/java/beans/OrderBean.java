package beans;

import java.io.Serializable;

public class OrderBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProductBean pb;
	private String data;
	private String indirizzo;
	private int id_utente;
	
	public OrderBean(ProductBean pb, String data, String indirizzo, int id_utente) {
		this.pb = pb;
		this.data = data;
		this.indirizzo = indirizzo;
		this.id_utente=id_utente;
	}

	public ProductBean getPb() {
		return pb;
	}

	public void setPb(ProductBean pb) {
		this.pb = pb;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public int getIdUtente() {
		return id_utente;
	}

	public void setIdUtente(int id_utente) {
		this.id_utente = id_utente;
	}

}

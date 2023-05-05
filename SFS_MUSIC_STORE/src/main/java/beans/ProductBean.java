package beans;

import java.io.InputStream;
import java.io.Serializable;

public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	String nome;
	String descrizione;
	String tipo;
	double prezzo;
	int quantita;
	String condizione;
	InputStream immagineIS;

	public ProductBean() {
		id = -1;
		nome = "";
		descrizione = "";
		prezzo = 0;
		quantita = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getCondizione() {
		return condizione;
	}

	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public InputStream getImmagineIS() {
		return immagineIS;
	}

	public void setImmagineIS(InputStream immagineIS) {
		this.immagineIS = immagineIS;
	}
}

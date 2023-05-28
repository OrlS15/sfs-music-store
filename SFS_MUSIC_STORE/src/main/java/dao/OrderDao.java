package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beans.OrderBean;
import beans.ProductBean;
import interfaces.IOrderDao;
import others.CartItem;

public class OrderDao implements IOrderDao{
	//private static final String TABLE_INFO_PRODOTTO = "INFO_PRODOTTO";
	private static final String TABLE_PROD_IN_VENDITA = "PROD_IN_VENDITA";
	private static final String TABLE_ORDINE = "ORDINE";
	private static final String TABLE_ORDER_ITEM = "ORDER_ITEM";
	private DataSource ds = null;

	public OrderDao(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public void effettuaOrdine(List<CartItem> cis, int id_utente, String indirizzo) throws SQLException {
		Connection c = null;
		PreparedStatement p = null;

		String query1 = 
				"INSERT INTO " + TABLE_ORDINE + " (id_utente, indirizzo)"
				+ " VALUES (?,?)";
		String query2 = 
				"INSERT INTO " + TABLE_ORDER_ITEM + " (id_ordine, id_info_prodotto, quantita, prezzo)"
				+ " VALUES (?,?,?,?)";
		String queryDiminuisciQuantita = 
				"UPDATE " + TABLE_PROD_IN_VENDITA
				+ " SET quantita = quantita - ?"
				+ " WHERE id_info_prodotto = ?";
		String queryRemoveProdottiFiniti = 
				"DELETE FROM " + TABLE_PROD_IN_VENDITA
				+ " WHERE quantita = 0 ";
				
		
		for(int i=1;i<cis.size();i++) {
			query2+=",(?,?,?,?)";
		}

		try {
			c = ds.getConnection();
			// prendo l'id della nuova row creata
			p = c.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			p.setInt(1, id_utente);
			p.setString(2, indirizzo);
			p.executeUpdate();
			ResultSet rs = p.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			p.close();
			
			p = c.prepareStatement(query2);
			for(int i=0;i<cis.size();i++) {
				p.setInt((i*4)+1, id);
				p.setInt((i*4)+2, cis.get(i).getProductBean().getId());
				p.setInt((i*4)+3, cis.get(i).getQuantita());
				p.setDouble((i*4)+4, cis.get(i).getProductBean().getPrezzo());
				PreparedStatement p_diminuisci = c.prepareStatement(queryDiminuisciQuantita);
				p_diminuisci.setInt(1, cis.get(i).getQuantita());
				p_diminuisci.setInt(2, cis.get(i).getProductBean().getId());
				p_diminuisci.executeUpdate();
				p_diminuisci.close();
			}
			p.executeUpdate();
			p.close();
			
			p = c.prepareStatement(queryRemoveProdottiFiniti);
			p.executeUpdate();			
		} finally {
			try {
				if (p != null)
					p.close();
			} finally {
				if (c != null)
					c.close();
			}
		}		
	}

	@Override
	public List<ProductBean> getOrdersFromUser(int id_utente) throws SQLException {
		Connection c = null;
		PreparedStatement p = null;
		
		List<ProductBean> pbs = new ArrayList<>();

		ProductBean pb = null;

		String query = 
				"SELECT quantita, prezzo, id_info_prodotto, ip.nome, ip.descrizione, ip.tipo, id_ordine FROM ORDER_ITEM as oi"
				+ " INNER JOIN ORDINE as o ON o.id = oi.id_ordine"
				+ " INNER JOIN UTENTE as u ON o.id_utente = u.id"
				+ " INNER JOIN INFO_PRODOTTO as ip ON ip.id = oi.id_info_prodotto"
				+ " WHERE id_utente = ?";
		try {
			c = ds.getConnection();
			p = c.prepareStatement(query);
			p.setInt(1, id_utente);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				pb = new ProductBean();
				pb.setId(Integer.parseInt(rs.getString("id_info_prodotto")));
				pb.setPrezzo(Double.parseDouble(rs.getString("prezzo")));
				pb.setQuantita(Integer.parseInt(rs.getString("quantita")));
				pb.setNome(rs.getString("nome"));
				pb.setDescrizione(rs.getString("descrizione"));
				pb.setTipo(rs.getString("tipo"));
				pbs.add(pb);
			}
		} finally {
			try {
				if (p != null)
					p.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return pbs;
	}
	
	@Override
	public List<OrderBean> getAllOrders() throws SQLException {
		Connection c = null;
		PreparedStatement p = null;
		
		List<OrderBean> obs = new ArrayList<>();
		
		ProductBean pb = null;
		
		String query = 
				"SELECT quantita, prezzo, id_info_prodotto, ip.nome, ip.descrizione, ip.tipo, id_ordine, data, indirizzo, id_utente FROM ORDER_ITEM as oi"
						+ " INNER JOIN ORDINE as o ON o.id = oi.id_ordine"
						+ " INNER JOIN UTENTE as u ON o.id_utente = u.id"
						+ " INNER JOIN INFO_PRODOTTO as ip ON ip.id = oi.id_info_prodotto";
		try {
			c = ds.getConnection();
			p = c.prepareStatement(query);

			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				pb = new ProductBean();
				pb.setId(Integer.parseInt(rs.getString("id_info_prodotto")));
				pb.setPrezzo(Double.parseDouble(rs.getString("prezzo")));
				pb.setQuantita(Integer.parseInt(rs.getString("quantita")));
				pb.setNome(rs.getString("nome"));
				pb.setDescrizione(rs.getString("descrizione"));
				pb.setTipo(rs.getString("tipo"));
				OrderBean ob = new OrderBean(pb, rs.getString("data"), rs.getString("indirizzo"), rs.getInt("id_utente"));
				obs.add(ob);
			}
		} finally {
			try {
				if (p != null)
					p.close();
			} finally {
				if (c != null)
					c.close();
			}
		}
		return obs;
	}

}

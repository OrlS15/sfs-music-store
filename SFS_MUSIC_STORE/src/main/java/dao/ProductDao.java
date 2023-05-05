package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import beans.ProductBean;
import interfaces.IProductDao;

public class ProductDao implements IProductDao {
	private static final String TABLE_INFO_PRODOTTO = "info_prodotto";
	private static final String TABLE_PROD_IN_VENDITA = "prod_in_vendita";
	private DataSource ds = null;

	public ProductDao(DataSource ds) {
		this.ds = ds;
		System.out.println("Inizializzo ProductDao...");
	}

	@Override
	public void doSave(ProductBean pb) throws SQLException {
		Connection c = null;
		PreparedStatement p = null;

		String query1 = "INSERT INTO " + ProductDao.TABLE_INFO_PRODOTTO
				+ " (nome, descrizione, tipo, immagine) VALUES (?, ?, ?, ?)";
		String query2 = "INSERT INTO " + ProductDao.TABLE_PROD_IN_VENDITA
				+ " (id_info_prodotto, prezzo, quantita, condizione) VALUES (?, ?, ?, ?)";

		try {
			c = ds.getConnection();
			p = c.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			p.setString(1, pb.getNome());
			p.setString(2, pb.getDescrizione());
			p.setString(3, pb.getTipo());
			p.setBinaryStream(4, pb.getImmagineIS());
			p.executeUpdate();

			// prendo l'id della nuova row creata
			ResultSet rs = p.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			rs.close();
			p.close();

			// metto il prodotto in vendita
			p = c.prepareStatement(query2);
			p.setInt(1, id);
			p.setDouble(2, pb.getPrezzo());
			p.setInt(3, pb.getQuantita());
			p.setString(4, (String) pb.getCondizione());
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
	public byte[] getProductImageById(int id) throws SQLException {
		Connection c = null;
		PreparedStatement p = null;
		
		byte[] bytes = null;

		String query = "SELECT immagine FROM " + ProductDao.TABLE_INFO_PRODOTTO + " WHERE id = ?";
		try {
			c = ds.getConnection();
			p = c.prepareStatement(query);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				bytes = rs.getBytes("immagine");
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
		
		return bytes;
	}

	@SuppressWarnings("null")
	@Override
	public ProductBean getProductById(int id) throws SQLException {
		Connection c = null;
		PreparedStatement p = null;
		
		ProductBean pb = null;

		String query = 
				"SELECT * "+ ProductDao.TABLE_PROD_IN_VENDITA
				+ "INNER JOIN "+ ProductDao.TABLE_INFO_PRODOTTO + " ON id_info_prodotto = id "
				+ "WHERE id = ?";
		try {
			c = ds.getConnection();
			p = c.prepareStatement(query);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				pb = new ProductBean();
				pb.setId(Integer.parseInt(rs.getString("id")));
				pb.setPrezzo(Integer.parseInt(rs.getString("prezzo")));
				pb.setQuantita(Integer.parseInt(rs.getString("quantita")));
				pb.setCondizione(rs.getString("condizione"));
				pb.setNome(rs.getString("nome"));
				pb.setDescrizione(rs.getString("descrizione"));
				pb.setTipo(rs.getString("tipo"));
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
		return pb;
	}

	@Override
	public List<ProductBean> getAllProducts(int limit, int offset) throws SQLException {
		Connection c = null;
		PreparedStatement p = null;
		
		List<ProductBean> pbs = null;

		String query = 
				"SELECT * "+ ProductDao.TABLE_PROD_IN_VENDITA
				+ "INNER JOIN "+ ProductDao.TABLE_INFO_PRODOTTO + " ON id_info_prodotto = id "
				+ "LIMIT ? OFFSET ?";
		
		/*try {
			c = ds.getConnection();
			p = c.prepareStatement(query);
			p.setInt(1, limit);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				pb.setId(Integer.parseInt(rs.getString("id")));
				pb.setPrezzo(Integer.parseInt(rs.getString("prezzo")));
				pb.setQuantita(Integer.parseInt(rs.getString("quantita")));
				pb.setCondizione(rs.getString("condizione"));
				pb.setNome(rs.getString("nome"));
				pb.setDescrizione(rs.getString("descrizione"));
				pb.setTipo(rs.getString("tipo"));
			}
		} finally {
			try {
				if (p != null)
					p.close();
			} finally {
				if (c != null)
					c.close();
			}
		}*/
		return pbs;
		
	}
}

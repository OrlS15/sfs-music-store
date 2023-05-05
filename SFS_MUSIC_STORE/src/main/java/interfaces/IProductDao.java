package interfaces;

import java.sql.SQLException;

import beans.ProductBean;
import java.util.List;

public interface IProductDao {
	public void doSave(ProductBean pb) throws SQLException;
	
	public byte[] getProductImageById(int id) throws SQLException;

	public ProductBean getProductById(int id) throws SQLException;

	public List<ProductBean> getAllProducts(int limit, int offset) throws SQLException;

	//public boolean checkUserIsAdmin(int id) throws SQLException;
}

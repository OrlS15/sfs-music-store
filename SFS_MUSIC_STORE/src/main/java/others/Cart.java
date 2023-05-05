package others;

import java.util.ArrayList;
import java.util.List;

import beans.ProductBean;

public class Cart {

	private List<ProductBean> products;

	public Cart() {
		products = new ArrayList<ProductBean>();
	}

	public void addProduct(ProductBean product) {
		for (ProductBean prod : products) {
			if (prod.getId() == product.getId()) {
				prod.setQuantita(prod.getQuantita() + 1);
				break;
			}
		}
		products.add(product);
	}

	public void deleteProduct(ProductBean product) {
		for (ProductBean prod : products) {
			if (prod.getId() == product.getId()) {
				if (prod.getQuantita() > 1) {
					prod.setQuantita(prod.getQuantita() - 1);
				} else {
					products.remove(prod);
				}
				break;
			}
		}
	}

	public List<ProductBean> getProducts() {
		return products;
	}
}

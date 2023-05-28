package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import beans.ProductBean;
import beans.ProductWarehouseBean;
import beans.UserBean;
import dao.ProductDao;
import dao.ProductWarehouseDao;

@WebServlet("/admin/richieste-vendita")
public class RichiesteVenditaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductWarehouseDao productDao = new ProductWarehouseDao((DataSource) getServletContext().getAttribute("DataSource"));
		try {
			List<ProductWarehouseBean> pbs = productDao.getAllNewRichiesteVendita();
			request.setAttribute("products", pbs);
			request.getRequestDispatcher("/admin/richieste-vendita.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch(action) {
			case "accept": {
				acceptRequest(request,response);
				break;
			}
			case "reject":{
				rejectRequest(request,response);
				break;
			}
		}
		
		doGet(request,response);
	}
	
	private void acceptRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRichiesta_str = request.getParameter("id");

		ProductWarehouseDao productDao = new ProductWarehouseDao((DataSource) getServletContext().getAttribute("DataSource"));
		
		try {
			productDao.changeRichiestaVenditaStato(Integer.parseInt(idRichiesta_str), "ACCETTATO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void rejectRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRichiesta_str = request.getParameter("id");

		ProductWarehouseDao productDao = new ProductWarehouseDao((DataSource) getServletContext().getAttribute("DataSource"));
		
		try {
			productDao.changeRichiestaVenditaStato(Integer.parseInt(idRichiesta_str), "RIFIUTATO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

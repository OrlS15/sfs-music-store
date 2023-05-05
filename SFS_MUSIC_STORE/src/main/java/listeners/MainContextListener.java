package listeners;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.sql.*;

@WebListener
public class MainContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();

		DataSource ds = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/sfsmusicstore");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
		ctx.setAttribute("DataSource", ds);
		System.out.println("DataSource creation...." + ds.toString());
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}

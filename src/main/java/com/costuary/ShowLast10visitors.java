package com.costuary;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.costuary.bean.VisitorBean;

@SuppressWarnings("serial")
public class ShowLast10visitors extends HttpServlet {

	private static final String INET_4_ADDRESS = "Inet4Address";
	private static final String INET_6_ADDRESS = "Inet6Address";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		VisitorBean visitorBean;
		List<VisitorBean> beanList = new ArrayList<VisitorBean>();

		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}
		// store only the first two octets of a users ip address
		String userIp = req.getRemoteAddr();
		int addressTypeInt = 0;
		InetAddress address = InetAddress.getByName(userIp);
		if (address instanceof Inet6Address) {
			// nest indexOf calls to find the second occurrence of a character
			// in a string
			// an alternative is to use Apache Commons Lang:
			// StringUtils.ordinalIndexOf()
//			userIp = userIp.substring(0, userIp.indexOf(":", userIp.indexOf(":") + 1)) + ":*:*:*:*:*:*";
			addressTypeInt = 6;
		} else if (address instanceof Inet4Address) {
//			userIp = userIp.substring(0, userIp.indexOf(".", userIp.indexOf(".") + 1)) + ".*.*";
			addressTypeInt = 4;
		}

		final String createTableSql = "CREATE TABLE IF NOT EXISTS visits ( visit_id INT NOT NULL "
				+ "AUTO_INCREMENT, user_ip VARCHAR(46) NOT NULL, address_type INT(1) NOT NULL,timestamp DATETIME NOT NULL, "
				+ "PRIMARY KEY (visit_id) )";
		final String createVisitSql = "INSERT INTO visits (user_ip, address_type, timestamp) VALUES (?, ?, ?)";
		final String selectSql = "SELECT user_ip, address_type, timestamp FROM visits ORDER BY timestamp DESC " + "LIMIT 10";

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/plain");
		String url;
		if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
			// Check the System properties to determine if we are running on
			// appengine or not
			// Google App Engine sets a few system properties that will reliably
			// be present on a remote
			// instance.
			url = System.getProperty("ae-cloudsql.cloudsql-database-url");
			try {
				// Load the class that provides the new "jdbc:google:mysql://"
				// prefix.
				Class.forName("com.mysql.jdbc.GoogleDriver");// com.mysql.jdbc.GoogleDriver
			} catch (ClassNotFoundException e) {
				throw new ServletException("Error loading Google JDBC Driver", e);
			}
		} else {
			// Set the url with the local MySQL database connection url when
			// running locally
			url = System.getProperty("ae-cloudsql.local-database-url");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new ServletException("Error loading local JDBC Driver", e);
			}
		}
		log("connecting to: " + url);
		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement statementCreateVisit = conn.prepareStatement(createVisitSql)) {
			conn.createStatement().executeUpdate(createTableSql);
			statementCreateVisit.setString(1, userIp);
			statementCreateVisit.setInt(2, addressTypeInt);
			statementCreateVisit.setTimestamp(3, new Timestamp(new Date().getTime()));
			statementCreateVisit.executeUpdate();

			try (ResultSet rs = conn.prepareStatement(selectSql).executeQuery()) {
				out.print("Last 10 visits:\n");
				while (rs.next()) {
					visitorBean = new VisitorBean();
					String savedIp = rs.getString("user_ip");
					String timeStamp = rs.getString("timestamp");
					int addType = rs.getInt("address_type");
					if(addType == 4){
						visitorBean.setAddressType(INET_4_ADDRESS);
					}else if (addType == 6){
						visitorBean.setAddressType(INET_6_ADDRESS);
					}
//					out.print("Time: " + timeStamp + " Addr: " + savedIp + "\n");
					visitorBean.setVisitIP(savedIp);
					visitorBean.setTimeStamp(timeStamp);
					beanList.add(visitorBean);
				}
			}
		} catch (SQLException e) {
			throw new ServletException("SQL error", e);
		}

        req.setAttribute("visitorBean", beanList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/ShowLast10visitors.jsp");
        dispatcher .forward(req, resp);
	}
}

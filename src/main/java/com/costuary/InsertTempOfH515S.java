package com.costuary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class InsertTempOfH515S extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = req.getRequestURI();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}

		float gpu = Float.parseFloat(req.getParameter("GPU"));
		float cpu = Float.parseFloat(req.getParameter("CPU"));
		String getFan = req.getParameter("FAN");
		getFan = getFan.replaceAll("[^0-9]","");
		int fan = Integer.parseInt(getFan);

//		String userIp = req.getRemoteAddr();
//		InetAddress address = InetAddress.getByName(userIp);


		final String createTableSql = "CREATE TABLE IF NOT EXISTS lenovo_h515s_temprature ( `TEMP_ID` INT(10) NOT NULL AUTO_INCREMENT, "
		  + "`RADEON_PCI_0008` FLOAT NOT NULL,`K10TEMP_PCI_00C3` FLOAT NOT NULL, "
		  + "`CPU_FAN_RPM` INT(4) NOT NULL, "
		  + "`RECORD_TIME` TIMESTAMP NOT NULL, "
		  + "PRIMARY KEY (`TEMP_ID`), "
		  + "UNIQUE INDEX `idlenovo_h515s_UNIQUE` (`TEMP_ID` ASC))";
		final String insertTempSql = "INSERT INTO lenovo_h515s_temprature (RADEON_PCI_0008, K10TEMP_PCI_00C3,CPU_FAN_RPM,RECORD_TIME) VALUES (?, ?, ?, ?)";
		final String selectSql = "SELECT * FROM lenovo_h515s_temprature ORDER BY RECORD_TIME DESC LIMIT 1";

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
				PreparedStatement statementCreateVisit = conn.prepareStatement(insertTempSql)) {
			conn.createStatement().executeUpdate(createTableSql);
			statementCreateVisit.setFloat(1, gpu);
			statementCreateVisit.setFloat(2, cpu);
			statementCreateVisit.setInt(3,fan);
			statementCreateVisit.setTimestamp(4, new Timestamp(new Date().getTime()));
			statementCreateVisit.executeUpdate();

			try (ResultSet rs = conn.prepareStatement(selectSql).executeQuery()) {
				out.print("Lastest Temprature Record of Lenovo-H515S:\n");
				while (rs.next()) {
				    float gpuTemprature = rs.getFloat("RADEON_PCI_0008");
					float cpuTemprature = rs.getFloat("K10TEMP_PCI_00C3");
					int cpuFanSpeed = rs.getInt("CPU_FAN_RPM");
					String timeStamp = rs.getString("RECORD_TIME");
					out.print("Time: " + timeStamp
							+ " GPU Temprature: "
							+ gpuTemprature + "°C"
							+ " CPU Temprature: "
							+ cpuTemprature + "°C"
						    + " CPU Fan Speed: "
							+ cpuFanSpeed + "RPM"
							+ "\n");
				}
			}
		} catch (SQLException e) {
			throw new ServletException("SQL error", e);
		}
	}
}

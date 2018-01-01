package com.costuary;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
public class ShowLastTenRecordsOfH515S extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = req.getRequestURI();
    	TemperatureRecordBean bean = null;
    	List<TemperatureRecordBean> beanList = new ArrayList<TemperatureRecordBean>();
		if (path.startsWith("/favicon.ico")) {
			return; // ignore the request for favicon.ico
		}
		final String selectSql = "SELECT * FROM lenovo_h515s_temprature ORDER BY RECORD_TIME DESC " + "LIMIT 10";
		String url;
		if (System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
			url = System.getProperty("ae-cloudsql.cloudsql-database-url");
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");// com.mysql.jdbc.GoogleDriver
			} catch (ClassNotFoundException e) {
				throw new ServletException("Error loading Google JDBC Driver", e);
			}
		} else {
			// Set the url with the local MySQL database connection url when running locally
//			url = System.getProperty("ae-cloudsql.local-database-url");
			url = "jdbc:mysql://192.168.3.100:3306/Costuary?user=root";
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new ServletException("Error loading local JDBC Driver", e);
			}
		}
		log("connecting to: " + url);
        try  {
        	Connection conn = DriverManager.getConnection(url);
        	ResultSet rs = conn.prepareStatement(selectSql).executeQuery();

        	while (rs.next()) {
        		bean = new TemperatureRecordBean();
        		int tempId = rs.getInt("TEMP_ID");
			    float gpuTemprature = rs.getFloat("RADEON_PCI_0008");
				float cpuTemprature = rs.getFloat("K10TEMP_PCI_00C3");
				int cpuFanSpeed = rs.getInt("CPU_FAN_RPM");
				//Parse sql.timestamp.getTime() to java.util.Date and then set to "Asia/Tokyo" timezone and save as String
				java.util.Date timeDate = new java.util.Date(rs.getTimestamp("RECORD_TIME").getTime());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
				String dateStrTmp = dateFormat.format(timeDate);

				bean.setTimestampStr(dateStrTmp);
				bean.setId(tempId);
				bean.setGpu(gpuTemprature);
				bean.setCpu(cpuTemprature);
				bean.setFan(cpuFanSpeed);

				beanList.add(bean);
			}
		} catch (SQLException e) {
			throw new ServletException("SQL error", e);
		}
        req.setAttribute("tempBean", beanList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/showLastTenRecordsOfTemp.jsp");
        dispatcher .forward(req, resp);
	}
}

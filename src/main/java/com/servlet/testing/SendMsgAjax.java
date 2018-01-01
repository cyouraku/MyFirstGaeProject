package com.servlet.testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.costuary.bean.MyLoginBean;
import com.google.appengine.repackaged.org.joda.time.LocalDate;


/**
 * Servlet implementation class ShowCurrentTimeServlet
 */
public class SendMsgAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMsgAjax() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		List<String> chatStr = new ArrayList<String>();
		chatStr = (List<String>)session.getAttribute("chatStr");
		MyLoginBean userBean = (MyLoginBean)session.getAttribute("userBean");//session.setAttribute("userBean", uBean);

		// 方法一：设定编码为UTF-8
		request.setCharacterEncoding("utf-8");
		String msg = (String) request.getParameter("Message");
		//Debug
		Logger.getLogger(SendMsgAjax.class.getName()).log(Level.INFO, "debug ajax msg: " + msg);
		msg = userBean.getUserName() + ":" + msg + "<br/>" + LocalDate.now().toString() + "<br/>";
		chatStr.add(msg);
		session.setAttribute("chatStr", chatStr);
		response.setCharacterEncoding("utf-8");

		StringBuilder sb = new StringBuilder();
		for(String str:chatStr){
			sb.append(str);
		}

		response.getWriter().append(sb.toString());
	}

}

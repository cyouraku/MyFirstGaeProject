package com.servlet.testing;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.costuary.bean.MyLoginBean;
import com.costuary.util.StringUtil;

/**
 * Servlet implementation class ShowCurrentTimeServlet
 */
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String userIp = request.getRemoteAddr();
//		request.setAttribute("userIp", userIp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UserLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		//方法一：设定编码为UTF-8
		request.setCharacterEncoding("utf-8");
		//Acquire userNm/Pw and post to AppMenu.jsp

		RequestDispatcher dispatcher;
		String alertInfo = "";
//		MyLoginBean uBean = new MyLoginBean();
		MyLoginBean uBean = new MyLoginBean();
		//check user name input rules
		String userNm = (String)request.getParameter("USER_NM");
		if(StringUtil.checkInputStrLength(userNm)){
			uBean.setUserName(userNm);
			//check user password input rules
			String userPw = (String)request.getParameter("USER_PW");
			uBean.setUserPw(userPw);


			session.setAttribute("userBean", uBean);

			//Debug:
			System.out.println("debug: userNm = " + userNm);
			System.out.println("debug: userPw = " + userPw);
//			request.setAttribute("userIp", userIp);
//			request.setAttribute("userBean", uBean);

			dispatcher = request.getRequestDispatcher("/AppMenu.jsp");
			dispatcher.forward(request, response);
		}else{
			alertInfo = "用户名超过字符长度，请重新输入！";
			//Debug:
			System.out.println("debug: alertInfo = " + alertInfo);
			request.setAttribute("alertInfo", alertInfo);
			dispatcher = request.getRequestDispatcher("/UserLogin.jsp");
			dispatcher.forward(request, response);
		}
		//check user password input rules
//		String userPw = (String)request.getParameter("USER_PW");
//		uBean.setUserPw(userPw);


//		session.setAttribute("userBean", uBean);

		//Debug:
//		System.out.println("userNm input = " + userNm);
//		System.out.println("userPw input = " + userPw);
//		request.setAttribute("userIp", userIp);
//		request.setAttribute("userBean", uBean);

//		dispatcher = request.getRequestDispatcher("/AppMenu.jsp");
//		dispatcher.forward(request, response);
	}

}

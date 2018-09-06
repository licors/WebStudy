package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smps.controls.Controller;
import smps.controls.MemberAddController;
import smps.controls.MemberListController;
import spms.vo.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 호출순서 : servlet 인터페이스에 선언된 service() 호출 -> HttpServlet 클래스에 추가된 아래 service() 호풀-> 요청에 따라 doGet(), doPost() 호출
	 * service 오버라이딩 한 이유 : GET, POST 뿐만 아니라 다른 요청 방식에도 대응하기 위해서
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			HashMap<String, Object> model = new HashMap<>();
			model.put("memberDao", sc.getAttribute("memberDao"));
			
			String pageControllerPath = null;
			Controller pageController = null;
			
			if("/member/list.do".equals(servletPath)) {
				pageController = new MemberListController();
			} 
			else if("/member/add.do".equals(servletPath)) {
				pageController = new MemberAddController();
				
				if(request.getParameter("email") != null) {
					model.put("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password"))
							.setName(request.getParameter("name")));
				}
			}
			else if("/member/update.do".equals(servletPath)) {
				pageControllerPath = "/member/update";
				if(request.getParameter("email") != null) {
					request.setAttribute("member", new Member()
							.setNo(Integer.parseInt(request.getParameter("no")))
							.setEmail(request.getParameter("email"))
							.setName(request.getParameter("name"))
							);	
				}
				
			}
			else if("/member/delete.do".equals(servletPath)) {
				pageControllerPath = "/member/delete";
			}
			else if("/auth/login.do".equals(servletPath)) {
				pageControllerPath = "/auth/login";
			}
			else if("/auth/logout.do".equals(servletPath)) {
				pageControllerPath = "/auth/logout";
			}
			
//			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
//			rd.include(request, response);
			
			String viewUrl = pageController.execute(model);
			
			for (String key : model.keySet()) {
				sc.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}

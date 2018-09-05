package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			String pageControllerPath = null;
			if("/member/list.do".equals(servletPath)) {
				pageControllerPath = "/member/list";
			} 
			else if("/member/add.do".equals(servletPath)) {
				pageControllerPath = "/member/add";
				if(request.getParameter("email") != null) {
					request.setAttribute("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password"))
							.setName(request.getParameter("name"))
							);	
				}
			}
			else if("/member/update.do".equals(servletPath)) {
				pageControllerPath = "/member/update.do";
				if(request.getParameter("email") != null) {
					request.setAttribute("member", new Member()
							.setEmail(request.getParameter("email"))
							.setPassword(request.getParameter("password"))
							.setName(request.getParameter("name"))
							);	
				}
				
			}
			else if("/member/delete.do".equals(servletPath)) {
				pageControllerPath = "/member/delete.do";
			}
			else if("/auth/login.do".equals(servletPath)) {
				pageControllerPath = "/auth/login";
			}
			else if("/auth/logout.do".equals(servletPath)) {
				pageControllerPath = "/auth/logout";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			rd.include(request, response);
			
			String viewUrl = (String) request.getAttribute("viewUrl");
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			}
			else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.do");
			rd.forward(request, response);
		}
	}
}

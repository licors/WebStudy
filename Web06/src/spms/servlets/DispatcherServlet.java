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

import smps.bind.DataBinding;
import smps.bind.ServletRequestDataBinder;
import smps.controls.Controller;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	/*
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse) 호출순서 : servlet 인터페이스에 선언된 service()
	 * 호출 -> HttpServlet 클래스에 추가된 아래 service() 호풀 
	 * service 오버라이딩 한 이유 : GET, POST 뿐만 아니라 다른 요청 방식에도 대응하기 위해서
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			HashMap<String, Object> model = new HashMap<>();
			model.put("session", request.getSession());
			Controller pageController = (Controller) sc.getAttribute(servletPath);

			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);

			for (String key : model.keySet()) {
				sc.setAttribute(key, model.get(key));
			}

			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model,
			DataBinding dataBinding) throws Exception{
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		for(int i=0;i<dataBinders.length;i+=2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			dataObj = ServletRequestDataBinder.bind(request,dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
}

package ru.edolganov.servlet3;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.dolganov.ejb3_1.EJBService;
import ru.dolganov.ejb3_1.impl.SimpleEJBBean;
import ru.dolganov.ejb3_1.impl.SingletonService;
import ru.dolganov.ejb3_1.impl.StatlessEJBServiceImpl;

@WebServlet(urlPatterns="/test")
public class TestServlet extends HttpServlet {
	
	//- @Resource(name="java:global/StatlessEJBServiceImpl/local")
	//- @EJB private StatlessEJBServiceImpl ejbServiceImpl; //не работает, хотя теоретически должно
	//+ работает хот деплой @EJB - ссылка обновится
	
	@EJB(mappedName="StatlessEJBServiceImpl/local") 
	private EJBService ejbService;
	
	@EJB(mappedName="SimpleEJBBean/no-interface")
	private SimpleEJBBean simpleEJBBean;
	
	@EJB(mappedName="SingletonService/no-interface")
	private SingletonService singletonService;
	
	
	private RequestDispatcher testPage;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletContext = getServletConfig().getServletContext();
		testPage = servletContext.getRequestDispatcher("/test.jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String info = ejbService.getInfo();
		String info2 = simpleEJBBean.getInfo();
		String info3 = singletonService.getInfo();
		req.setAttribute("info", info+"<br> "+info2+ " <br> "+info3);
		testPage.forward(req, resp);
	}

}

package fil.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/home/*", "/index/*"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = -594667926099562461L;
	
	private String getAction(String url) {
    	String[] act = url.split("/");
    	if(act.length == 0)
    		return null;
    	return act[1];
    }
    
    private void setData(String action, HttpServletRequest req) {
    	switch (action) {
    	case "home":
    		req.setAttribute("title", "HOME");
    		break;
    		
    	default:
    		req.setAttribute("body", "Action Inconnue !");
    		break;
    	}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = this.getAction(request.getPathInfo());
		if(action == null)
			action = "";
		this.setData(action, request);
		
		String target = "/JSP/pages/Main.jsp";
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(target);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

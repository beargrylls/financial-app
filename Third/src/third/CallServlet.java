package third;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.math3.distribution.NormalDistribution;

@SuppressWarnings("serial")
public class CallServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		req.getRequestDispatcher("/jsp/call.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		resp.setContentType("text/html;charset=UTF-8");
		double s = Double.parseDouble(req.getParameter("spot"));
		double r = Double.parseDouble(req.getParameter("interest"));
		double sigma = Double.parseDouble(req.getParameter("vol"));
		double k = Double.parseDouble(req.getParameter("strike"));
		double T = Double.parseDouble(req.getParameter("horizon"));
		
		double d1 = 1/(sigma*Math.sqrt(T))*(Math.log(s/k) + (r + sigma*sigma/2)*T);
		double d2 = d1 - sigma*Math.sqrt(T);
		NormalDistribution Z = new NormalDistribution();
		double callValue = Z.cumulativeProbability(d1)*s - Z.cumulativeProbability(d2)*k*Math.pow(Math.E, -r*T);

        req.setAttribute("call", "Call value: " + callValue);        
        req.getRequestDispatcher("/jsp/call.jsp").forward(req, resp);
	}
}

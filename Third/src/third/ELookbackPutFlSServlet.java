package third;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.math3.distribution.NormalDistribution;

@SuppressWarnings("serial")
public class ELookbackPutFlSServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		req.getRequestDispatcher("/jsp/elookbackputfls.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		double s,r,sigma,M,T;
		double q = 0; //q will be the dividend yield for future extensions of the software
		resp.setContentType("text/html;charset=UTF-8");
		try{
			s = Double.parseDouble(req.getParameter("spot"));
			M = Double.parseDouble(req.getParameter("max"));
			r = Double.parseDouble(req.getParameter("interest"));
			sigma = Double.parseDouble(req.getParameter("vol"));			
			T = Double.parseDouble(req.getParameter("horizon"))/365;
		}
		catch(NumberFormatException e){
			req.setAttribute("error", " Invalid input");
			req.getRequestDispatcher("/jsp/elookbackputfls.jsp").forward(req, resp);
			return;
		}
		
		if(s <= 0 || sigma <= 0 || M <= 0 || T <= 0){
			req.setAttribute("error", " Invalid input");
			req.getRequestDispatcher("/jsp/elookbackputfls.jsp").forward(req, resp);
			return;
		}			
		
		double d1 = 1/(sigma*Math.sqrt(T))*(Math.log(s/M) + (r - q + sigma*sigma/2)*T);
		double d2 = d1 - sigma*Math.sqrt(T);
		NormalDistribution Z = new NormalDistribution();
		double elookbackputflsValue = M*Math.pow(Math.E, -r*T)*Z.cumulativeProbability(-d2) 
				- s*Math.pow(Math.E, -q*T)*Z.cumulativeProbability(-d1) 
				+ s*Math.pow(Math.E, -r*T)*sigma*sigma/(2*(r-q))
				*(Math.pow(Math.E, (r-q)*T)*Z.cumulativeProbability(d1) 
				- Math.pow(s/M, -2*(r-q)/(sigma*sigma))*Z.cumulativeProbability(d1 - 2*(r-q)*Math.sqrt(T)/sigma));
		//round off to the nearest hundredth
		elookbackputflsValue = (new BigDecimal(elookbackputflsValue)).setScale(2, RoundingMode.HALF_UP).doubleValue();

        req.setAttribute("lookbackputfls", "Lookback put with floating strike value: " + elookbackputflsValue);        
        req.getRequestDispatcher("/jsp/elookbackputfls.jsp").forward(req, resp);
	}
}

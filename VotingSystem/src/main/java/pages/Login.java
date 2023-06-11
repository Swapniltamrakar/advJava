package pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VotersDaoImpl;
import pojo.Voter;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VotersDaoImpl voterDaoImpl;
	
	public void init() throws ServletException {
		try {
			voterDaoImpl = new VotersDaoImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException("Error : "+getClass(),e);
		}
	}


	public void destroy() {
		try {
			voterDaoImpl.cleanUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession hs = request.getSession();
		try(PrintWriter pw = response.getWriter()){
			String email=request.getParameter("email");
			String password = request.getParameter("pass");
			Voter v = voterDaoImpl.validateVoter(email, password);
			if(v==null) {
				pw.print("User not found"+"<a href='login.html'>retry</a>");
			}else if(v.getRole().equals("admin")){
				response.sendRedirect("admin");
				hs.setAttribute("user_det",v);
				hs.setAttribute("voter_dao",voterDaoImpl);
			}else {
				if(v.isStatus()) {
					response.sendRedirect("logout");
					hs.setAttribute("user_det",v);
					hs.setAttribute("voter_dao",voterDaoImpl);
				}else {
					response.sendRedirect("candidate_page");
					hs.setAttribute("user_det",v);
					hs.setAttribute("voter_dao",voterDaoImpl);
				}
			}
		}catch(Exception e) {
			throw new ServletException("Errorrrr",e);
		}
		
		
	}

}

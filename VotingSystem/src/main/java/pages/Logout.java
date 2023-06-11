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
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VotersDaoImpl voterDaoImpl;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession hs = request.getSession();
		Voter v = (Voter) hs.getAttribute("user_det");
		voterDaoImpl = (VotersDaoImpl) hs.getAttribute("voter_dao");
		try (PrintWriter pw = response.getWriter()) {
			if (request.getParameter("rdo") != null) {
				
				int idc = Integer.valueOf(request.getParameter("rdo"));
				voterDaoImpl.count(idc);
				pw.print("<head><style>div{"
						+ "position: absolute;"
						+ "margin-left: 40%;"
						+ "margin-top: 12%;"
						+ "}</style></head>");
				pw.print("<div>");
				pw.print("<h1> ID: "+v.getVoterId()+" "+": "+v.getName()+" "+v.getLastName()+"</h1>");
				pw.print("<h3>" + voterDaoImpl.vote(v.getVoterId()) + "</h3>");
				pw.print("<br /><a href='login.html'>LOGOUT</a>");
				pw.print("</div>");
				hs.invalidate();
				
			} else {
				pw.print("<head><style>div{"
						+ "position: absolute;"
						+ "margin-left: 40%;"
						+ "margin-top: 12%;"
						+ "}</style></head>");
				pw.print("<div>");
				pw.print("<h1> ID: "+v.getVoterId()+" "+": "+v.getName()+" "+v.getLastName()+"</h1>");
				pw.print("Alrady Voted Thank You!!!");
				pw.print("<br /><a href='login.html'>LOGOUT</a>");
				pw.print("</div>");
				hs.invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

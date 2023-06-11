package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VotersDaoImpl;
import pojo.Candidate;
import pojo.Voter;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession hs = request.getSession();
		Voter v = (Voter) hs.getAttribute("user_det");
		VotersDaoImpl voterDaoImpl = (VotersDaoImpl) hs.getAttribute("voter_dao");
		try (PrintWriter pw = response.getWriter()) {
			List<Voter> vlist = voterDaoImpl.getVoters();
			List<Candidate> clist = voterDaoImpl.getCandLeading();
			List<Candidate> plist = voterDaoImpl.getLeadingParty();
			pw.write("<head>" + "<style>" + "table{" + "border-style: solid;" + "</style>" + "</head>");
			pw.print("<h1>ADMIN ACCESS : </h1>");
			pw.print("<h3>Welcome " + v.getName() + " " + v.getLastName() + "<h3>");
			
			pw.print("<h4>VOTER DETAILS</h4>");
			
			pw.print(
					"<table rules='all'><thead><tr><th>id</th><th>Name</th><th>LastName</th><th>Email</th><th>DOB</th></tr></thead>");
			pw.print("<tbody>");
			// id | name | lastName | email | password | status | role | dob
			for (Voter v1 : vlist) {
				pw.print("<tr><td>" + v1.getVoterId() + "</td>" + "<td>" + v1.getName() + "</td>" + "<td>"
						+ v1.getLastName() + "</td>" + "<td>" + v1.getEmail() + "</td>" + "<td>" + v1.getDob()
						+ "</td></tr>");
			}
			pw.print("</tbody>");
			pw.print("</table>");
			
			pw.print("<br/>");
			pw.print("<h4>CANDIDATE DETAILS DETAILS</h4>");
			pw.print(
					"<table rules='all'><thead><tr><th>id</th><th>Name</th><th>LastName</th><th>Party</th><th>Votes</th></tr></thead>");
			pw.print("<tbody>");
			// | id | name | lastName | party | votes |
			for (Candidate c1 : clist) {
				pw.print("<tr><td>" + c1.getId() + "</td>" + "<td>" + c1.getName() + "</td>" + "<td>" + c1.getLastName()
						+ "</td>" + "<td>" + c1.getParty() + "</td>" + "<td>" + c1.getVoteCount() + "</td></tr>");
			}
			pw.print("</tbody>");
			pw.print("</table>");
			
			pw.print("<br/>");
			pw.print("<h4>PARTY DETAILS</h4>");
			pw.print("<table rules='all'><thead><tr><th>Party</th><th>Votes</th></tr></thead>");
			pw.print("<tbody>");
			// | id | name | lastName | party | votes |
			for (Candidate c2 : plist) {
				pw.print("<tr>" + "<td>" + c2.getParty() + "</td>" + "<td>" + c2.getVoteCount() + "</td></tr>");
			}
			pw.print("</tbody>");
			pw.print("</table>");
			hs.invalidate();
			pw.print("<br /><a href='login.html'>LOGOUT</a>");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

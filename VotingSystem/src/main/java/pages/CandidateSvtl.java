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
 * Servlet implementation class CandidateSvtl
 */
@WebServlet("/candidate_page")
public class CandidateSvtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VotersDaoImpl voterDaoImpl;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession hs = request.getSession();
		Voter v = (Voter) hs.getAttribute("user_det");
		VotersDaoImpl voterDaoImpl = (VotersDaoImpl) hs.getAttribute("voter_dao");
		try (PrintWriter pw = response.getWriter()) {
			List<Candidate> c = voterDaoImpl.getCandidate();
			if (v != null) {
				printIt(pw, v, c);
			} else {
				pw.print("<a href='login.html'>reset</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printIt(PrintWriter pw, Voter v, List<Candidate> c) {
		pw.print("<h1>Welcome " + v.getName() + " " + v.getLastName() + "</h1>");
		pw.print("<br />");
		pw.print("<h3>" + "Please select a candidate by clicking on the given candidate list" + "</h3>");
		pw.write("<head>" + "<style>" + "table{" + "border-style: solid;" + "</style>" + "</head>");
		pw.print("<form action='logout'>");
		pw.print("<table rules='all'>");
		pw.print("<thead><tr><th></th><th>Name</th><th>Party</th></tr></thead>");
		pw.print("<tbody>");
		for (Candidate cand : c) {
			pw.print("<tr>");
			pw.print("<td><input type='radio' name='rdo' id='rdo' value=" + cand.getId() + "></td>");
			pw.print("<td>" + cand.getName() + "</td>");
			pw.print("<td>" + cand.getParty() + "</td>");
			pw.print("</tr>");
		}
		pw.print("<tr><td></td><td><button type='submit' name='btn1' id='btn1'>VOTE</button></td></tr>");
		pw.print("</tbody></table></form>");

	}

}

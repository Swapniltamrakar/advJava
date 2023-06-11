package dao;

import static utils.DbUtils.closeConnection;
import static utils.DbUtils.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pojo.Candidate;
import pojo.Voter;

public class VotersDaoImpl implements VotersDao {

	Connection cn;
	PreparedStatement pst1;
	PreparedStatement pst2;
	PreparedStatement pst3;
	PreparedStatement pst4;
	PreparedStatement pst5;
	PreparedStatement pst6;
	PreparedStatement pst7;

	public VotersDaoImpl() throws SQLException {
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from votersdata where email=? and password=?");
		pst2 = cn.prepareStatement("select * from candidateData");
		pst3 = cn.prepareStatement("update votersdata set status=1 where id=?");
		pst4 = cn.prepareStatement("update candidateData set votes=votes+1 where id=?");
		pst5 = cn.prepareStatement("select * from votersdata where status=1");
		pst6 = cn.prepareStatement("select * from candidateData order by votes desc");
		pst7 = cn.prepareStatement("select party,sum(votes) from candidateData group by party order by sum(votes) desc");
	}

	@Override
	public Voter validateVoter(String email, String password) throws SQLException {

		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next()) {
				// id | name | lastName | email | password | status | role | dob |
				return new Voter(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, rst.getBoolean(6),
						rst.getString(7), rst.getDate(8));
			} else {
				return null;
			}
		}
	}

	public List<Candidate> getCandidate() throws SQLException {
		List<Candidate> list = new ArrayList<>();
		try (ResultSet rst = pst2.executeQuery()) {

			while (rst.next()) {
				// //| id | name | lastName | party | votes |
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getInt(5)));

			}
			return list;
		}

	}

	public String vote(int id) throws SQLException {
		pst3.setInt(1, id);
		if (pst3.executeUpdate() == 1) {
			return "voted SuccessFully ThankYou!!!!";
		} else {

			return "Some Error Occured";
		}

	}

	public List<Voter> getVoters() throws SQLException {
		List<Voter> vlist = new ArrayList<>();
		try(ResultSet rst = pst5.executeQuery()){
			while(rst.next()) {
				// id | name   | lastName | email             | password  | status | role  | dob        |
				vlist.add(new Voter(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), 
						rst.getString(5), rst.getBoolean(6), rst.getString(7), rst.getDate(8)));
			}
			
				return vlist;
			
		}
	}
	
	public List<Candidate> getCandLeading() throws SQLException{
		List<Candidate> list = new LinkedList<>();
		try(ResultSet rst = pst6.executeQuery()){
			//| id | name   | lastName | party    | votes |
			while(rst.next()) {
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5)));
			}
			return list;
		}	
	}
	
	public List<Candidate> getLeadingParty() throws SQLException{
		List<Candidate> list = new LinkedList<>();
		try(ResultSet rst = pst7.executeQuery()){
			while(rst.next()) {
				list.add(new Candidate(rst.getString(1), rst.getInt(2)));
			}
			return list;
		}		
	}

	public void count(int id) throws SQLException {
		pst4.setInt(1, id);
		if (pst4.executeUpdate() == 1) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
			closeConnection();
		}
	}

}

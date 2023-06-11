package dao;

import java.sql.SQLException;

import pojo.Voter;

public interface VotersDao {

	Voter validateVoter(String email,String password)throws SQLException;
}

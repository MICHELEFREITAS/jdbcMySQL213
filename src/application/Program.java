package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.spi.TransactionalWriter;

import db.DB;
import db.DBIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			//Não esquecer no DELETE O WHERE
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE " 
					+ "id = ? ");
			st.setInt(1, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected" + rowsAffected);
			
		}catch(SQLException e){
			//exceção personalizada
			throw new DBIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}

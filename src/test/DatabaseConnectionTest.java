package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dal.DatabaseConnection;

class DatabaseConnectionTest {

	@Test
	void test() {
		
		//Arrange
		Connection con = null;
		
		//Act
		try {
			con = DatabaseConnection.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertNotNull(con);
		
	}
}

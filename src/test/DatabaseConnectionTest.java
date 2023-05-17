package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.DatabaseConnection;

class DatabaseConnectionTest {

	@Test
	void test() throws SQLException {
		
		//Arrange
		Connection con = null;
		
		//Act
		con = DatabaseConnection.setTestingEnvironment().getConnection();
		
		//Assert
		assertNotNull(con);
		
	}
}

package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DbHelper {
  private final ApplicationManager app;

  public  DbHelper(ApplicationManager app) throws SQLException {
    this.app = app;
  }

  public Users userInfoFromDb() {
    Connection conn = null;


    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT id, username, email from mantis_user_table ");
      Users users = new Users();
      while (rs.next()){
        users.add(new UserData().withId(rs.getInt("id"))
                .withName(rs.getString("username")).withEmail(rs.getString("email")));
      }


      rs.close();
      st.close();
      conn.close();
      //System.out.println(users);
      return users ;

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return null;
  }
}
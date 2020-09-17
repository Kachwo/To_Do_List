/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcwong373.to_do_list;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wkchun
 */
@WebServlet(name="RESTServlet", urlPatterns={"/tasks", "/tasks/*"})
public class RESTServlet extends HttpServlet {
    
 private static final Logger LOG = Logger.getLogger(RESTServlet.class.getName());

    Connection con = null;
     Statement st = null;
      ResultSet rs = null;
      String url = "jdbc:derby://localhost:1527/todolist";
      String user = "ee4216";
      String password = "ee4216";
     String resultString="[";
        
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM tasks");
            while(rs.next()){
                resultString=resultString+
                     "{\"id\":" +"\"" +rs.getString("id")+"\""+"," 
                     +"\"title\":"+"\"" +rs.getString("title")+"\""+","
                     +"\"done\":"+"\"" +rs.getBoolean("done")+"\""
                     + "},";
            }
            resultString = resultString+"]";
        } catch (SQLException ex) {
            Logger.getLogger(RESTServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       response.setContentType("application/json");
       HttpSession session = request.getSession();

}
}
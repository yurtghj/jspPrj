package com.newlecture.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.jspprj.dao.AnswerisDao;
import com.newlecture.jspprj.entity.Answeris;
import com.newlecture.jspprj.entity.AnswerisView;

public class JdbcAnswerisDao implements AnswerisDao {

   @Override
   public int insert(Answeris answeris) {
      
      String sql = "INSERT INTO answeris (" + 
                  "    id," + 
                  "    title," + 
                  "    language," + 
                  "    platform," + 
                  "    runtime," + 
                  "    error_code," + 
                  "    error_message," + 
                  "    situation," + 
                  "    tried_to_fix," + 
                  "    reason," + 
                  "    how_to_fix," + 
                  "    writer_id," + 
                  "    attached_file" + 
                  ") VALUES ("
                  + "(SELECT NVL(MAX(TO_NUMBER(ID)), 0)+1 ID FROM ANSWERIS)"
                  + ",?,?,?,?,?,?,?,?,?,?,?,?)";
      
      int result = 0;
      // 드라이버 로드
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
         PreparedStatement st = con.prepareStatement(sql);
         //st.setString(1, answeris.getId());
         st.setString(1, answeris.getTitle());
         st.setString(2, answeris.getLanguage());
         st.setString(3, answeris.getPlatform());
         st.setString(4, answeris.getRuntime());
         st.setString(5, answeris.getErrorCode());
         st.setString(6, answeris.getErrorMessage());
         st.setString(7, answeris.getSituation());
         st.setString(8, answeris.getTriedToFix());
         st.setString(9, answeris.getReason());
         st.setString(10, answeris.getHowToFix());
         st.setString(11, answeris.getWriterId());
         st.setString(12, answeris.getAttachedFile());
         
         result = st.executeUpdate();
         
         st.close();
         con.close();         
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }      
      
      return result;
   }

   @Override
   public int update(Answeris answeris) {
      String sql = "update answeris set    " + 
                  "    title=?," + 
                  "    language=?," + 
                  "    platform=?," + 
                  "    runtime=?," + 
                  "    error_code=?," + 
                  "    error_message=?," + 
                  "    situation=?," + 
                  "    tried_to_fix=?," + 
                  "    reason=?," + 
                  "    how_to_fix=?," + 
                  "    hit=?" + 
                  "where id=?";
      
      int result = 0;
      // 드라이버 로드
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, answeris.getTitle());
         st.setString(2, answeris.getLanguage());
         st.setString(3, answeris.getPlatform());
         st.setString(4, answeris.getRuntime());
         st.setString(5, answeris.getErrorCode());
         st.setString(6, answeris.getErrorMessage());
         st.setString(7, answeris.getSituation());
         st.setString(8, answeris.getTriedToFix());
         st.setString(9, answeris.getReason());
         st.setString(10, answeris.getHowToFix());
         st.setInt(11, answeris.getHit());
         st.setString(12, answeris.getId());
         
         result = st.executeUpdate();
         
         st.close();
         con.close();         
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }      
      
      return result;
   }

   @Override
   public int delete(String id) {
      String sql = "delete answeris where id=?";
      
      int result = 0;
      // 드라이버 로드
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, id);      
         
         result = st.executeUpdate();
         
         st.close();
         con.close();         
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }      
      
      return result;
   }

   @Override
   public List<AnswerisView> getList(int page)  {
      
      int start = 1+(page-1)*15; // 1,16,31,46,...
      int end = page*15; // 15,30,45,...
      
      String sql = "SELECT * FROM ANSWERIS_VIEW WHERE NUM BETWEEN ? AND ? ORDER BY REG_DATE DESC";
      
      List<AnswerisView> list = new ArrayList<>();
      
      // 드라이버 로드
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
         PreparedStatement st = con.prepareStatement(sql);
         st.setInt(1, start);
         st.setInt(2, end);
         
         ResultSet rs = st.executeQuery();
         
         AnswerisView answeris = null;
         while(rs.next()) {
            answeris = new AnswerisView(
                        rs.getString("ID"),
                        rs.getString("TITLE"),
                        rs.getString("LANGUAGE"),
                        rs.getString("PLATFORM"),
                        rs.getString("RUNTIME"),
                        rs.getString("ERROR_CODE"),
                        rs.getString("ERROR_MESSAGE"),
                        rs.getString("SITUATION"),
                        rs.getString("TRIED_TO_FIX"),
                        rs.getString("REASON"),
                        rs.getString("HOW_TO_FIX"),
                        rs.getString("WRITER_ID"),
                        rs.getDate("REG_DATE"),
                        rs.getInt("HIT"),
                        rs.getString("ATTACHED_FILE"),
                        rs.getInt("COMMENT_COUNT")
                     );
            list.add(answeris);               
         }
         
         rs.close();
         st.close();
         con.close();
         
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return list;
   }

   @Override
   public AnswerisView get(String id) {
      String sql = "SELECT * FROM ANSWERIS_VIEW WHERE ID=?";
      
      AnswerisView answeris = null;
      
      // 드라이버 로드
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, id);
         
         ResultSet rs = st.executeQuery();
         
         
         if(rs.next()) {
            answeris = new AnswerisView(
                     rs.getString("ID"),
                     rs.getString("TITLE"),
                     rs.getString("LANGUAGE"),
                     rs.getString("PLATFORM"),
                     rs.getString("RUNTIME"),
                     rs.getString("ERROR_CODE"),
                     rs.getString("ERROR_MESSAGE"),
                     rs.getString("SITUATION"),
                     rs.getString("TRIED_TO_FIX"),
                     rs.getString("REASON"),
                     rs.getString("HOW_TO_FIX"),
                     rs.getString("WRITER_ID"),
                     rs.getDate("REG_DATE"),
                     rs.getInt("HIT"),
                     rs.getString("attached_file"),
                     rs.getInt("COMMENT_COUNT")
                  );            
            //System.out.printf("id : %s, name : %s\n", id, name);      
         }
         
         rs.close();
         st.close();
         con.close();
         
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return answeris;
   }

   @Override
   public int getCount() {
      String sql = "SELECT count(id) count FROM ANSWERIS";
      
      int count = 0;
      
      // 드라이버 로드
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
         Connection con = DriverManager.getConnection(url, "c##sist", "dclass");
         Statement st = con.createStatement();
                  
         ResultSet rs = st.executeQuery(sql);         
         
         if(rs.next())
            count = rs.getInt("count");         
         
         rs.close();
         st.close();
         con.close();
                  
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return count;
   }

}
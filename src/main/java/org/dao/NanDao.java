package org.dao;

import org.excel.NanjingUni;

import java.sql.*;
import java.util.List;

public class NanDao {
    public void save(List<NanjingUni> unis){
        ResultSet rs = null;
        Statement stmt = null;
        Connection connection =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/onepunch";

            connection= DriverManager.getConnection(url, "root","123456" );

            String sql ="insert into nanuniversity(id,department,major,studyMethod,politics,foreignLanguage,pro_course1,pro_course2,totalScores,remark) values(?,?,?,?,?,?,?,?,?,?) ";
            StringBuffer sb =new StringBuffer(sql);
            for (int i = 1; i <unis.size(); i++) {
                sb.append(" , (?,?,?,?,?,?,?,?,?,?)");
            }

            PreparedStatement ps = connection.prepareStatement(sb.toString());
            for (int i = 0; i <unis.size() ; i++) {
                ps.setInt(1+10*i,0);
                ps.setString(2+10*i,unis.get(i).getDepartment());
                ps.setString(3+10*i,unis.get(i).getMajor());
                ps.setString(4+10*i,unis.get(i).getStudyMethod());
                ps.setInt(5+10*i,unis.get(i).getPolitics());
                ps.setInt(6+10*i,unis.get(i).getForeignLanguage());
                ps.setInt(7+10*i,unis.get(i).getPro_course1());
                ps.setInt(8+10*i,unis.get(i).getPro_course2());
                ps.setInt(9+10*i,unis.get(i).getTotalScores());
                ps.setString(10+10*i,unis.get(i).getRemark());
            }

            ps.executeUpdate();

        } catch (ClassNotFoundException e) {

        }catch (SQLException e) {

        }finally {
            try {

//                stmt.close();
                connection.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}

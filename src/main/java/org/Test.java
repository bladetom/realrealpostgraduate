package org;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.excel.NanjingUni;
import org.listener.NanjingListener;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Test {
    public void test1query1insert(){
        String fileName = "C:\\test\\"   + "nan.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        long startTime = System.currentTimeMillis();
        EasyExcel.read(fileName, NanjingUni.class, new NanjingListener()).sheet().headRowNumber(2).doRead();
        long endTime = System.currentTimeMillis();
        System.out.println("1读取1插入所耗时间为"+(endTime-startTime)+"毫秒");
    }
    public static void main(String[] args) throws SQLException {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "C:\\test\\"   + "nan.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        long startTime = System.currentTimeMillis();
        EasyExcel.read(fileName, NanjingUni.class, new NanjingListener()).sheet().headRowNumber(2).doRead();
        long endTime = System.currentTimeMillis();
        log.info("使用时间为{}毫秒",endTime-startTime);
        System.out.println(endTime-startTime);
    }

    public static void jdbcTest(List<NanjingUni> unis) throws SQLException {
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
                ps.setInt(1+10*i,unis.get(i).getId());
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

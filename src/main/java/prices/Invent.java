/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Invent extends countNumbers{
    public int getCount(){
        int i=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select count(IID) from inventory";
            ResultSet rs=stmt.executeQuery(smt);
            while(rs.next()){
                i = rs.getInt(1);
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
        return i;
    }
}

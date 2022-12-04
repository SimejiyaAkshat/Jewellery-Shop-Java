/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class getPrices {
    private int arr[] = new int[3];
     
    public getPrices(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select * from prices";
            ResultSet rs=stmt.executeQuery(smt);
            int i = 0;
            while(rs.next()){
                arr[i] = rs.getInt("Price");
                i++;
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
    }
    
    public int goldPrice(){
        return arr[0];
    }
    public int silverPrice(){
        return arr[1];
    }
    public int platinumPrice(){
        return arr[2];
    }
}
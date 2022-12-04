/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventoryfun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class inventoylist {
    public String[] getItems(){
        int i = 1, n=1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select IID from inventory";
            ResultSet rs=stmt.executeQuery(smt);
            while(rs.next()){
                i++;
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
        String[] list = new String[i];
        while(n<=i){
            list[n-1] = Integer.toString(n);
        }
        return list;
    }
    
    public int addItem(String name, String Category, String Weight, String Type){
        int rs = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "insert into inventory(Name, Category, Weight, Type) values('"+name+"','"+Category+"',"+Integer.parseInt(Weight)+",'"+Type+"');";
            System.out.println(smt);
            rs=stmt.executeUpdate(smt);
            con.close();
        }catch (Exception e){System.out.println(e);}
        return rs;
    }
    
    public int updateItem(int Id, String name, String Category, String Weight, String Type){
        int rs = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "update inventory set IID="+Id+", Name='"+name+"',Category='"+Category+"', Weight="+Integer.parseInt(Weight)+", Type = '"+Type+"' where IID="+Id+";" ;
            System.out.println(smt);
            rs=stmt.executeUpdate(smt);
            con.close();
        }catch (Exception e){System.out.println(e);}
        return rs;
    }
    
    public int deleteItem(int id){
        int rs = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");
            Statement stmt = con.createStatement();
            String smt = "delete from inventory where IID="+id+";";
            rs = stmt.executeUpdate(smt);
            con.close();
        }catch (Exception e){System.out.println(e);}
        return rs;
    }
}

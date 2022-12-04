/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventoryfun;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import prices.getPrices;

public class inventoryTable {
    public void setTable (JTable inventoryTbl){
        DefaultTableModel tb1Mdl = (DefaultTableModel)inventoryTbl.getModel();
        tb1Mdl.setRowCount(0);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select * from inventory";
            ResultSet rs=stmt.executeQuery(smt);
            while(rs.next()){
                String id = String.valueOf(rs.getInt("IID"));
                String name = rs.getString("Name");
                String category = rs.getString("Category");
                String weight = String.valueOf(rs.getInt("Weight"));
                String type = rs.getString("Type");
                
                String tbData[] = {id,name,category,weight,type};
                DefaultTableModel tb1Model = (DefaultTableModel)inventoryTbl.getModel();
                tb1Model.addRow(tbData);
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
    }
    
    public void setTablePrice (JTable inventoryTbl){
        DefaultTableModel tb1Mdl = (DefaultTableModel)inventoryTbl.getModel();
        tb1Mdl.setRowCount(0);
        getPrices prc = new getPrices();
        int goldPrice = prc.goldPrice();
        int silverPrice = prc.silverPrice();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select * from inventory";
            ResultSet rs=stmt.executeQuery(smt);
            while(rs.next()){
                String id = String.valueOf(rs.getInt("IID"));
                String name = rs.getString("Name");
                String category = rs.getString("Category");
                String weight = String.valueOf(rs.getInt("Weight"));
                String type = rs.getString("Type");
                double price = 0;
                int weightInTola = 0;
                int weightInKilo = 0;
                if(type.equals("Gold")){
                    double pricePerGram = goldPrice / 10;
                    price = (pricePerGram*Integer.parseInt(weight)) + (Integer.parseInt(weight) * 600); 
                }
                else {
                    double pricePerGram = silverPrice / 10;
                    price = (pricePerGram*Integer.parseInt(weight)) + (Integer.parseInt(weight) * 200); 
                }
                String tbData[] = {id,name,category,weight,type, String.valueOf(price)};
                DefaultTableModel tb1Model = (DefaultTableModel)inventoryTbl.getModel();
                tb1Model.addRow(tbData);
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
    }
    
}
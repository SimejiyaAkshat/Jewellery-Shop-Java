/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orders;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Order {
    public Integer insertBooking(String query) {
        Integer numero=0;
        Integer risultato=-1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");
            Statement stmt = con.createStatement();
            numero = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                risultato=rs.getInt(1);
            }
            rs.close();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      return risultato;
    }
    public int addItem(int iid, int bid, double price){
        int rs = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "insert into list(IID,BID,Quantity, Price) values("+iid+","+bid+",1,"+(int)price+");";
            System.out.println(smt);
            rs=stmt.executeUpdate(smt);
            con.close();
        }catch (Exception e){System.out.println(e);}
        return rs;
    }
    
    public void setTable (JTable inventoryTbl){
        DefaultTableModel tb1Mdl = (DefaultTableModel)inventoryTbl.getModel();
        tb1Mdl.setRowCount(0);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select BID, b.Date, Status, Firstname, mail from bookings b join person p where b.PID = p.PID order by BID;";
            ResultSet rs=stmt.executeQuery(smt);
            while(rs.next()){
                String bid = String.valueOf(rs.getInt("BID"));
                String date = String.valueOf(rs.getDate("Date"));
                String status = rs.getString("Status");
                String name = String.valueOf(rs.getString("Firstname"));
                String mail = rs.getString("mail");
                
                String tbData[] = {bid,date,status,name,mail};
                DefaultTableModel tb1Model = (DefaultTableModel)inventoryTbl.getModel();
                tb1Model.addRow(tbData);
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
    }
}


class InOrder extends Order{
    public void indvBooking(int BID, String Date,String status,String name,String mail){
        IndOrder inord = new IndOrder(BID);
        inord.setInitial(BID, Date, status, name, mail);
        inord.setVisible(true);
    }
//    select LID, l.IID, count(Quantity), Price, Name, Category, Weight, Type from list l join inventory i where l.IID = i.IID group by l.IID;
    public double setOrderTable (JTable inventoryTbl, int Bid){
        DefaultTableModel tb1Mdl = (DefaultTableModel)inventoryTbl.getModel();
        tb1Mdl.setRowCount(0);
        double total = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jewelleryshop","root","123456");  
            Statement stmt=con.createStatement();  
            String smt = "select LID, l.IID, count(Quantity), Price, Name, Category, Weight, Type from list l join inventory i where l.IID = i.IID AND BID="+Bid+" group by l.IID;";
            System.out.println(smt);
            ResultSet rs=stmt.executeQuery(smt);
            while(rs.next()){
                String BID = String.valueOf(rs.getInt("LID"));
                String IID = String.valueOf(rs.getInt("l.IID"));
                String Quantity = String.valueOf(rs.getInt("count(Quantity)"));
                String Price = String.valueOf(rs.getInt("Price"));
                String name = rs.getString("Name");
                String category = rs.getString("Category");
                total += (double) (rs.getInt("count(Quantity)")*rs.getInt("Price"));
                String weight = String.valueOf(rs.getString("Weight"));
                String type = rs.getString("Type");
                String tbData[] = {BID,IID, Quantity, Price, name, category, weight, type};
                DefaultTableModel tb1Model = (DefaultTableModel)inventoryTbl.getModel();
                tb1Model.addRow(tbData);
            }
            con.close();
        }catch (Exception e){System.out.println(e);}
        return total;
    }
}
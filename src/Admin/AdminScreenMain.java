
package Admin;

import com.model.Account;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.proteanit.sql.DbUtils;


public class AdminScreenMain extends JFrame{
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
     private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public AdminScreenMain() {
        setVisible(true);
        setSize(800, 600);
        addComponents();
    }

    private void addComponents() {
       jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
         jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);


        jMenu1.setText("File");

        jMenuItem1.setText("Book Patient Appointment");
        jMenu1.add(jMenuItem1);
        
        jMenuItem1.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
              BookAppMain ba=new BookAppMain();
             ba=null;
           }
               
        });

        jMenuItem2.setText("Cancel Appointment");
        jMenu1.add(jMenuItem2);
        
         jMenuItem2.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
            CancelAppMain ca=new CancelAppMain();
            ca=null;
           }
               
        });

        jMenuItem3.setText("Search Patient info");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reports");

        jMenuItem4.setText("Today's Appontment");
        jMenu2.add(jMenuItem4);
        jMenuItem4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   DateFormat df=DateFormat.getDateInstance();
        String d=df.format(new Date());
                   fetchpatient(d);
                   
               } catch (SQLException ex) {
                   Logger.getLogger(AdminScreenMain.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(AdminScreenMain.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       });
        
        jMenuItem5.setText("Tommorow's Appointment");
        jMenu2.add(jMenuItem5);
  
          jMenuItem5.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   DateFormat df=DateFormat.getDateInstance();
 
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Date d1=c.getTime();//tomorrow's date
        String d2=df.format(d1);
                   fetchpatient(d2);
                   
               } catch (SQLException ex) {
                   Logger.getLogger(AdminScreenMain.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(AdminScreenMain.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       });
        
        
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
  
    }
    private void fetchpatient(String d) throws SQLException, ClassNotFoundException{
        Account a=new Account();
        ResultSet rst=a.fetchPatientInfo(d);
        jTable1.setModel(DbUtils.resultSetToTableModel(rst));
    }
}

package Presentation;

import DAO.AccountDAO;
import DAO.UserDAO;
import model.Account;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class AccountController {
    private AccountView accountViewV;

public AccountController(AccountView a){
    this.accountViewV=a;

    accountViewV.addAccountListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(accountViewV.getIdTxt().getText());
            String type= String.valueOf(accountViewV.getTypeTxt().getText());
           ;
            Date d=new Date(System.currentTimeMillis());
            float suma= Float.parseFloat(accountViewV.getSumaTxt().getText());
            String cnp=String.valueOf(accountViewV.getCnpTxt().getText());
            Account account= AccountDAO.findById(id);
            if(account==null){
                Account us=new Account(id, cnp, type, suma,d );
                AccountDAO.insert(us);

                Object[] row = new Object[5];
                row[0]=us.getId();
                row[1]=us.getType();
                row[2]=us.getDateCreation();
                row[3]=us.getMoney();
                row[4]=us.getCnp();
                accountViewV.model.addRow(row);
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Acest nume nu poate fi adaugat deoarece este deja in baza de date",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }




            accountViewV.table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int j = accountViewV.table.getSelectedRow();
                    accountViewV.getIdTxt().setText(accountViewV.model.getValueAt(j, 0).toString());
                    accountViewV.getTypeTxt().setText(accountViewV.model.getValueAt(j, 1).toString());
                    accountViewV.getDataTxt().setText(accountViewV.model.getValueAt(j, 2).toString());
                    accountViewV.getSumaTxt().setText(accountViewV.model.getValueAt(j, 3).toString());
                    accountViewV.getCnpTxt().setText(accountViewV.model.getValueAt(j, 4).toString());


                }
            });
        }
    });

    accountViewV.updateAccountListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(accountViewV.getIdTxt().getText());
            String type= String.valueOf(accountViewV.getTypeTxt().getText());

            Date d=new Date(System.currentTimeMillis());
            float suma= Float.parseFloat(accountViewV.getSumaTxt().getText());
            String cnp=String.valueOf(accountViewV.getCnpTxt().getText());
            Account account= AccountDAO.findById(id);
            int j = accountViewV.table.getSelectedRow();
            String selectedName = accountViewV.model.getValueAt(j, 0).toString();

            if(account!=null){
                Account us=new Account(id, cnp, type, suma,d );
                AccountDAO.update(us);
                Object[] row = new Object[5];
                ArrayList<Account> accounts = AccountDAO.getAccounts();
                accountViewV.model.setRowCount(0);
                for (Account u : accounts) {
                row[0]=u.getId();
                row[4]=u.getType();
                row[2]=u.getDateCreation();
                row[3]=u.getMoney();
                row[1]=u.getCnp();
                accountViewV.model.addRow(row);

                }
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Acest nume nu poate fi adaugat deoarece este deja in baza de date",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    });

    accountViewV.deleteAccountListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id=Integer.parseInt(accountViewV.getIdTxt().getText());
            String type= String.valueOf(accountViewV.getTypeTxt().getText());
            Date d=new Date(System.currentTimeMillis());
            float suma= Float.parseFloat(accountViewV.getSumaTxt().getText());
            String cnp=String.valueOf(accountViewV.getCnpTxt().getText());
            Account account= AccountDAO.findById(id);
            if(account!=null){
                Account us=new Account(id, cnp, type, suma,d );
                AccountDAO.delete(us);
                int j = accountViewV.table.getSelectedRow();
                int selectedId = Integer.parseInt(accountViewV.model.getValueAt(j, 0).toString());

                Object[] row = new Object[5];
                ArrayList<Account> accounts = AccountDAO.getAccounts();
                AccountController.this.getAccounts();
                accountViewV.model.setRowCount(0);
                for (Account u : accounts) {
                    row[0]=u.getId();
                    row[1]=u.getType();
                    row[2]=u.getDateCreation();
                    row[3]=u.getMoney();
                    row[4]=u.getCnp();
                    accountViewV.model.addRow(row);

                }
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Acest nume nu poate fi adaugat deoarece este deja in baza de date",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }



        }
    });

}

    public ArrayList<Account> getAccounts() {
        try {
            return AccountDAO.getAccounts();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "mesaj",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

}

package Presentation;

import DAO.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdministratorController {
    private AdministratorView aV;
    private ArrayList<User> user1 = new ArrayList<User>();

    public AdministratorController(AdministratorView a) {
        this.aV = a;

        aV.addUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=aV.getNameTxt().getText();
                String password= String.valueOf(aV.getPasswordTxt().getText());
                int type= Integer.parseInt(aV.getTypeTxt().getText());
                User user=UserDAO.findByName(name);
                if(user==null){
                    User us=new User(name, type, password);
                    UserDAO.insert(us);
                    User u=UserDAO.findByName(us.getName());
                    Object[] row = new Object[3];
                    row[0]=u.getName();
                    row[1]=u.getType();
                    row[2]=u.getPassword();
                    aV.model.addRow(row);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Acest nume nu poate fi adaugat deoarece este deja in baza de date",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        aV.updateUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=aV.getNameTxt().getText();
                String password= String.valueOf(aV.getPasswordTxt().getText());
                int type= Integer.parseInt(aV.getTypeTxt().getText());
                User user=UserDAO.findByName(name);
                int j = aV.table.getSelectedRow();
                String selectedName = aV.model.getValueAt(j, 0).toString();
                String selectedType = aV.model.getValueAt(j, 1).toString();
                String selectedPassword = aV.model.getValueAt(j, 2).toString();
                if(user!=null){
                    User us=new User(selectedName, type, password);
                    UserDAO.update(us);
                    Object[] row = new Object[3];
                    ArrayList<User> users = UserDAO.getUsers();
                    aV.model.setRowCount(0);
                    for (User u : users) {

                        row[0] = u.getName();
                        row[1] = u.getType();
                        row[2] = u.getPassword();

                        aV.model.addRow(row);
                    }



                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "nu se poate updata contul",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);



                }

            }
        });

        aV.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int j = aV.table.getSelectedRow();
                aV.getNameTxt().setText(aV.model.getValueAt(j, 0).toString());
                aV.getTypeTxt().setText(aV.model.getValueAt(j, 1).toString());
                aV.getPasswordTxt().setText(aV.model.getValueAt(j, 2).toString());


            }
        });
        aV.deleteUserListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=aV.getNameTxt().getText();
                String password= String.valueOf(aV.getPasswordTxt().getText());
                int type= Integer.parseInt(aV.getTypeTxt().getText());
                User user=UserDAO.findByName(name);
                if(user!=null){
                    //User us=new User(name, type, password);
                    UserDAO.delete(user);
                    //User u=UserDAO.findByName(user.getName());
                    ArrayList<User> users = UserDAO.getUsers();

                    AdministratorController.this.getUsers();
                    aV.model.setRowCount(0);
                    Object[] row = new Object[3];

                    for (User u : users) {

                        row[0] = u.getName();
                        row[1] = u.getType();
                        row[2] = u.getPassword();

                        aV.model.addRow(row);
                    }


                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Acest nume nu poate fi sters deoarece nu mai este in baza de date",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                }

            }
        });


    }


    public ArrayList<User> getUsers() {
        try {
            return UserDAO.getUsers();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "mesaj",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
}
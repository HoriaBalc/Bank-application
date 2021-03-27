package Presentation;

import DAO.ClientDAO;
import DAO.UserDAO;
import model.Account;
import model.Client;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserController {
    private UserView uV;


    public UserController(UserView a) {
        this.uV = a;

        uV.addClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = uV.getNumeTxt().getText();
                String oras = uV.getOrasTxt().getText();
                String adresa = uV.getAdresaTxt().getText();
                String nrId = uV.getNrIdTxt().getText();
                String cnp = uV.getCnpTxt().getText();
                System.out.println(cnp + "");
                Client client = ClientDAO.findByCNP(cnp);
                if (client == null) {
                    Client c = new Client(nume, cnp, adresa, oras, nrId);
                    ClientDAO.insert(c);

                    Object[] row = new Object[5];
                    row[0] = c.getName();
                    row[1] = c.getOras();
                    row[2] = c.getAddress();
                    row[3] = c.getPersonalNumericCode();
                    row[4] = c.getIdentificationNumber();
                    uV.model.addRow(row);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Acest nume nu poate fi adaugat deoarece este deja in baza de date",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);


                }

            }
        });

        uV.updateClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = uV.getNumeTxt().getText();
                String oras = uV.getOrasTxt().getText();
                String adresa = uV.getAdresaTxt().getText();
                String nrId = uV.getNrIdTxt().getText();
                String cnp = uV.getCnpTxt().getText();
                Client client = ClientDAO.findByCNP(cnp);
                int j = uV.table.getSelectedRow();

                String selectedCnp = uV.model.getValueAt(j, 3).toString();

                if (client != null) {
                    Client cl = new Client(nume, selectedCnp, adresa, oras, nrId);
                    ClientDAO.update(cl);
                    Object[] row = new Object[5];
                    ArrayList<Client> clients = ClientDAO.getClients();
                    uV.model.setRowCount(0);
                    for (Client u : clients) {

                        row[0] = u.getName();
                        row[3] = u.getOras();
                        row[2] = u.getAddress();
                        row[1] = u.getPersonalNumericCode();
                        row[4] = u.getIdentificationNumber();

                        uV.model.addRow(row);
                    }


                } else {
                    JOptionPane.showMessageDialog(null,
                            "nu se poate updata contul",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);


                }

            }
        });

        uV.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int j = uV.table.getSelectedRow();
                uV.getNumeTxt().setText(uV.model.getValueAt(j, 0).toString());
                uV.getOrasTxt().setText(uV.model.getValueAt(j, 1).toString());
                uV.getAdresaTxt().setText(uV.model.getValueAt(j, 2).toString());
                uV.getCnpTxt().setText(uV.model.getValueAt(j, 3).toString());
                uV.getNrIdTxt().setText(uV.model.getValueAt(j, 4).toString());
            }
        });
        uV.deleteClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cnp = uV.getCnpTxt().getText();
                Client client = ClientDAO.findByCNP(cnp);


                if (client != null) {
                    ClientDAO.delete(client);
                    ArrayList<Client> clients = ClientDAO.getClients();

                    UserController.this.getClients();
                    uV.model.setRowCount(0);
                    Object[] row = new Object[5];

                    for (Client u : clients) {

                        row[0] = u.getName();
                        row[3] = u.getOras();
                        row[2] = u.getAddress();
                        row[1] = u.getPersonalNumericCode();
                        row[4] = u.getIdentificationNumber();

                        uV.model.addRow(row);
                    }


                } else {
                    JOptionPane.showMessageDialog(null,
                            "Acest nume nu poate fi sters deoarece nu mai este in baza de date",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        uV.AccountListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountView aV = new AccountView();
            }
        });
        uV.TransferListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferView aV = new TransferView();
            }
        });

    }


    public ArrayList<Client> getClients() {
        try {
            return ClientDAO.getClients();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "mesaj",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }


}

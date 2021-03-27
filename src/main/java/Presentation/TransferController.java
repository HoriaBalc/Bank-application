package Presentation;

import DAO.AccountDAO;
import DAO.TransferDAO;
import model.Account;
import model.Transfer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class TransferController {
    private TransferView tV;

    public TransferController(TransferView transferView) {
        tV=transferView;
        tV.addTransferListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //if(s==null){
                try {
                    Random rand = new Random();


                    int id1 = Integer.parseInt(tV.getAccount1().getText());
                    int id2 = Integer.parseInt(tV.getAccount2().getText());
                    float suma = Float.parseFloat(tV.getSuma().getText());
                    Account a1 = AccountDAO.findById(id1);
                    Account a2 = AccountDAO.findById(id2);
                    if (suma > 0) {
                        if (a2 != null && a1 != null) {
                            if (a2.getMoney() > suma) {
                                a1.setMoney(a1.getMoney() + suma);
                                a2.setMoney(a2.getMoney() - suma);
                                AccountDAO.update(a1);
                                AccountDAO.update(a2);
                                TransferDAO.insert(new Transfer(Math.abs(rand.nextInt()), id2 + "", id1 + "", suma));
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "suma insuficienta in contul sursa",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);


                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "suma introdusa este negativa",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);


                    }
                }


                catch (NumberFormatException n){
                    System.out.println("NumberFormatException: " +n.getMessage());

                }




                }


              /*  else{
                    int id1=Integer.parseInt(tV.getAccount1().getText());
                    float suma=Float.parseFloat(tV.getSuma().getText());
                    Account a2=AccountDAO.findById(id1);
                    if(a2!=null){
                        if(a2.getMoney()>suma){
                            a2.setMoney(a2.getMoney()-suma);
                            AccountDAO.update(a2);
                            TransferDAO.insert(new Transfer(++cont,s,id1+"",suma));


                        }



                    }



                }
            }*/



        });
    }
}

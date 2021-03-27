package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TransferView {
    private TransferController transferController;
    public JFrame frame;
    private JTextField account1;
    private JTextField account2;
    private JTextField suma;
    public JButton btnAdd = new JButton("Add");
    public JLabel lblId1 = new JLabel("Id account 1");
    public JLabel lblId2 = new JLabel("Id account 2");
    public JLabel lblSuma = new JLabel("Suma");

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public TransferView(){
        frame = new JFrame("Bank - Transfer");
        transferController=new TransferController(this);
        initialize();
        frame.setVisible(true);



    }
    private void initialize(){

        account1=new JTextField(10);
        account2=new JTextField(10);
        suma=new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(4,1));
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());

        JPanel panel4 = new JPanel(new GridLayout());
        panel2.add(lblId1);
        panel3.add(lblId2);
        panel4.add(lblSuma);
        panel3.add(account2);
        panel2.add(account1);
        panel4.add(suma);
        panel1.add(btnAdd);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        frame.add(panel);


        frame.setSize(350,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }

    public JTextField getAccount1() {
        return account1;
    }

    public JTextField getAccount2() {
        return account2;
    }

    public JTextField getSuma() {
        return suma;
    }



    public void addTransferListener(ActionListener transfer) {
        btnAdd.addActionListener(transfer);
    }


}

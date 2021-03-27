package Presentation;

import model.Account;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountView {
    public AccountController accountController;
    public JFrame frame;
    private JTextField idTxt;
    private JTextField typeTxt;
    private JTextField sumaTxt;
    private JTextField cnpTxt;
    private JTextField dateTxt;

    public JButton btnAddAccount = new JButton("Add account");
    public JButton btnUpdateAccount = new JButton("Update account");
    public JButton btnDeleteAccount = new JButton("Delete account");


    public Object[] row = new Object[5];
    JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private CardLayout cardLayout = new CardLayout();

    public AccountView() {
        accountController=new AccountController(this);
        frame = new JFrame("Bank - Accounts");
        initialize();
        frame.setVisible(true);
    }

    private void initialize(){
        JLabel lblId = new JLabel("Id");
        JLabel lblType = new JLabel("Type");
        JLabel lblData = new JLabel("Data");
        JLabel lblSuma = new JLabel("Suma");
        JLabel lblCnp = new JLabel("CNP");

        Object[] columns = {"Name", "Type", "Data", "Suma", "CNP"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1000, 200);

        if(accountController.getAccounts()!=null) {
            ArrayList<Account> accounts = accountController.getAccounts();
            for (Account a : accounts) {
                row[0] = a.getId();
                row[4] = a.getType();
                row[2] = a.getDateCreation();
                row[3]= a.getMoney();
                row[1]= a.getCnp();
                model.addRow(row);
            }
        }

        idTxt = new JTextField(10);
        typeTxt = new JTextField(10);
        sumaTxt =new JTextField(10);
        cnpTxt =new JTextField(10);

        //JLabel lblNewLabel = new JLabel("Information about account");
        JPanel panou = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelButoane = new JPanel(new GridBagLayout());
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel11 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panel6 = new JPanel(new FlowLayout());
        JPanel panel7 = new JPanel(new FlowLayout());
        JPanel panel8 = new JPanel(new FlowLayout());
        JPanel panel9 = new JPanel(new FlowLayout());
        JPanel panel10 = new JPanel(new FlowLayout());
        JPanel cardPanel = new JPanel(cardLayout);
        JPanel panelAdd = new JPanel(new GridLayout(5, 1));
        JPanel panelUpdate = new JPanel(new GridLayout(8, 1));
        JPanel panelConfirmAdd = new JPanel(new FlowLayout());
        JPanel panelConfirmUpdate = new JPanel(new FlowLayout());

        panel2.add(lblId);
        panel2.add(idTxt);
        panel3.add(lblType);
        panel3.add(typeTxt);
        panel4.add(lblSuma);
        panel4.add(sumaTxt);
        panel4.add(lblCnp);
        panel4.add(cnpTxt);

        panelAdd.add(panel11);
        panelAdd.add(panel2);
        panelAdd.add(panel3);
        panelAdd.add(panel4);
        panelAdd.add(panelConfirmAdd);

        panelUpdate.add(panel1);
        panelUpdate.add(panel5);
        panelUpdate.add(panel6);
        panelUpdate.add(panel7);
        panelUpdate.add(panel8);
        panelUpdate.add(panel9);
        panelUpdate.add(panel10);
        panelUpdate.add(panelConfirmUpdate);

        panelButoane.add(btnAddAccount);
        panelButoane.add(btnUpdateAccount);
        panelButoane.add(btnDeleteAccount);


        cardPanel.add(panelAdd, "addpanel");
        //cardPanel.add(panelUpdate, "updatepanel");
        panel.add(panelButoane, BorderLayout.NORTH);
        panel.add(cardPanel, BorderLayout.SOUTH);
        panou.add(pane, BorderLayout.CENTER);
        panou.add(panel, BorderLayout.EAST);

        frame.add(panou);

        cardPanel.setVisible(true);

        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);



    }

    public JTextField getIdTxt() {
        return idTxt;
    }

    public JTextField getTypeTxt() {
        return typeTxt;
    }

    public JTextField getSumaTxt() {
        return sumaTxt;
    }

    public JTextField getCnpTxt() {
        return cnpTxt;
    }

    public JTextField getDataTxt() {
        return dateTxt;
    }



        public void addAccountListener(ActionListener aL) {


        btnAddAccount.addActionListener(aL);
    }

    public void updateAccountListener(ActionListener aL) {

        btnUpdateAccount.addActionListener(aL);
    }

    public void deleteAccountListener(ActionListener aL) {

        btnDeleteAccount.addActionListener(aL);
    }
}

package Presentation;


import DAO.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class AdministratorView {
    public AdministratorController adminController;
    public JFrame frame;
    private JTextField nameTxt;
    private JTextField typeTxt;
    private JTextField passwordTxt;
    private JTextField updateNameTxt;
    private JTextField updateTypeTxt;
    private JTextField updatePasswordTxt;

    private String selectedName;
    private String selectedType;
    private String selectedPassword;

    public JButton btnAddUser = new JButton("Add user");
    public JButton btnUpdateUser = new JButton("Update user");
    public JButton btnDeleteUser = new JButton("Delete user");
    public JButton btnGenerateReport = new JButton("Generate report");

    public Object[] row = new Object[3];
    JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private CardLayout cardLayout = new CardLayout();

    public AdministratorView() {
        adminController=new AdministratorController(this);
        frame = new JFrame("Bank - Admin");
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {

        JLabel lblName = new JLabel("Name");
        JLabel lblType = new JLabel("Type");
        JLabel lblPassword = new JLabel("Password");

        JLabel lblUpdateName = new JLabel("Name");
        JLabel lblUpdateType = new JLabel("Type");
        JLabel lblUpdatePassword = new JLabel("Password");

        Object[] columns = {"Name", "Type", "Password"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1000, 200);

        if(adminController.getUsers()!=null) {
            ArrayList<User> users = adminController.getUsers();
            for (User u : users) {
                row[0] = u.getName();
                row[1] = u.getType();
                row[2] = u.getPassword();

                model.addRow(row);
            }
        }

        nameTxt = new JTextField(10);
        typeTxt = new JTextField(10);
        passwordTxt = new JTextField(10);
        updateNameTxt = new JTextField(10);
        updateTypeTxt = new JTextField(10);
        updatePasswordTxt = new JTextField(10);


        JLabel lblNewLabel = new JLabel("Information about user");

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
        panel1.add(lblNewLabel);
        panel2.add(lblName);
        panel2.add(nameTxt);
        panel3.add(lblType);
        panel3.add(typeTxt);
        panel4.add(lblPassword);
        panel4.add(passwordTxt);
        panel5.add(lblUpdateName);
        panel5.add(updateNameTxt);
        panel6.add(lblUpdateType);
        panel6.add(updateTypeTxt);
        panel7.add(lblUpdatePassword);
        panel7.add(updatePasswordTxt);



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

        panelButoane.add(btnAddUser);
        panelButoane.add(btnUpdateUser);
        panelButoane.add(btnDeleteUser);
        panelButoane.add(btnGenerateReport);
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

    public JTextField getNameTxt() {
        return nameTxt;
    }

    public JTextField getTypeTxt() {
        return typeTxt;
    }

    public JTextField getPasswordTxt() {
        return passwordTxt;
    }

    public void addUserListener(ActionListener aL) {


        btnAddUser.addActionListener(aL);
    }

    public void updateUserListener(ActionListener aL) {

        btnUpdateUser.addActionListener(aL);
    }

    public void deleteUserListener(ActionListener aL) {

        btnDeleteUser.addActionListener(aL);
    }



}

package Presentation;


import DAO.UserDAO;
import model.Client;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class UserView {
    public UserController userController;
    public JFrame frame;
    private JTextField numeTxt;
    private JTextField orasTxt;
    private JTextField adresaTxt;
    private JTextField cnpTxt;
    private JTextField nrIdTxt;
    private JTextField updateNumeTxt;
    private JTextField updateAdresaTxt;
    private JTextField updateOrasTxt;
    private JTextField updateNrIdTxt;
    private JTextField updateCnpTxt;

    private String selectedNume;
    private String selectedAdresa;
    private String selectedOras;
    private String selectedNrId;
    private String selectedCnp;

    public JButton btnAddUser = new JButton("Add client");
    public JButton btnUpdateUser = new JButton("Update client");
    public JButton btnDeleteUser = new JButton("Delete client");
    public JButton btnAccount = new JButton("Account");
    public JButton btnTransfer = new JButton("Transfer");

    public Object[] row = new Object[5];
    JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private CardLayout cardLayout = new CardLayout();

    public UserView() {
        userController=new UserController(this);
        frame = new JFrame("Bank - User");
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {

        JLabel lblNume = new JLabel("Name");
        JLabel lblOras = new JLabel("City");
        JLabel lblAdresa = new JLabel("Address");
        JLabel lblCnp = new JLabel("CNP");
        JLabel lblNrId = new JLabel("Number Id");

        JLabel lblUpdateNume = new JLabel("Name");
        JLabel lblUpdateOras = new JLabel("City");
        JLabel lblUpdateAdresa = new JLabel("Address");
        JLabel lblUpdateCnp = new JLabel("CNP");
        JLabel lblUpdateNrId = new JLabel("Number Id");




        Object[] columns = {"Name", "City", "Address", "CNP", "Number Id"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1000, 200);

        if(userController.getClients()!=null) {
            ArrayList<Client> clients = userController.getClients();
            for (Client u : clients) {
                row[0] = u.getName();
                row[3] = u.getOras();
                row[2] = u.getAddress();
                row[1] = u.getPersonalNumericCode();
                row[4] = u.getIdentificationNumber();
                model.addRow(row);
            }
        }

        numeTxt = new JTextField(10);
        orasTxt = new JTextField(10);
        adresaTxt = new JTextField(10);
        cnpTxt = new JTextField(10);
        nrIdTxt= new JTextField(10);

        updateNumeTxt = new JTextField(10);
        updateOrasTxt = new JTextField(10);
        updateAdresaTxt = new JTextField(10);
        updateCnpTxt = new JTextField(10);
        updateNrIdTxt= new JTextField(10);

        JLabel lblNewLabel = new JLabel("Information about client:");

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
        panel2.add(lblNume);
        panel2.add(numeTxt);
        panel3.add(lblOras);
        panel3.add(orasTxt);
        panel4.add(lblAdresa);
        panel4.add(adresaTxt);
        panel3.add(lblNrId);
        panel3.add(nrIdTxt);
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

        panelButoane.add(btnAddUser);
        panelButoane.add(btnUpdateUser);
        panelButoane.add(btnDeleteUser);
        panelButoane.add(btnAccount);
        panelButoane.add(btnTransfer);
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

    public UserController getUserController() {
        return userController;
    }

    public JTextField getNumeTxt() {
        return numeTxt;
    }

    public JTextField getOrasTxt() {
        return orasTxt;
    }

    public JTextField getAdresaTxt() {
        return adresaTxt;
    }

    public JTextField getCnpTxt() {
        return cnpTxt;
    }

    public JTextField getNrIdTxt() {
        return nrIdTxt;
    }

    public void addClientListener(ActionListener aL) {

        btnAddUser.addActionListener(aL);
    }

    public void updateClientListener(ActionListener aL) {

        btnUpdateUser.addActionListener(aL);
    }

    public void deleteClientListener(ActionListener aL) {

        btnDeleteUser.addActionListener(aL);
    }

    public void AccountListener(ActionListener aL) {

        btnAccount.addActionListener(aL);
    }
    public void TransferListener(ActionListener aL) {

        btnTransfer.addActionListener(aL);
    }

}

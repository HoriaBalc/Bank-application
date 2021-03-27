package Presentation;




        import DAO.UserDAO;
        import model.User;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

public class LoginController {
    private LoginView view;
    private User user;
    public LoginController(LoginView v) {
        view=v;
        view.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name= v.getUsername().getText();
                String password= String.valueOf(v.getPassword().getPassword());
                User user= UserDAO.findByName(name);
                if(user==null){
                    JOptionPane.showMessageDialog(null,
                            "Nu este acest nume in baza de date",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if(user.getPassword().equals(password)) {
                        if (user.getType() == 1) {
                             new UserView();

                        } else {
                             new AdministratorView();


                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "Parola introdusa gresit",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }


                }
            }
        });


    }











}

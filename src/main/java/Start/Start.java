package Start;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.AccountDAO;
import DAO.ClientDAO;
import DAO.OperationsDAO;
import DAO.UserDAO;
import Presentation.LoginView;
import model.*;

//import bll.StudentBLL;


/**
 * Clasa principala unde se afla metoda main
 */
public class Start {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {
       // Client c=new Client("Dad", "103469", "adresa", "oras", "1232993136864");
       // ClientDAO.insert(c);
        //Operations o=new Operations(2, "adauga client", c.getPersonalNumericCode()+"");
       // OperationsDAO.insert(o);
        //Calendar calendar = Calendar.getInstance();

//////////////////////////////Data de rezolvat!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        LoginView view=new LoginView();

    }

}
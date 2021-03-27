

package DAO;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import connection.ConnectionFactory;
        import model.Account;
        import model.Client;
        import model.User;

/**
 * Clasa face ,cu ajutorul pachetului connection, operatiile de insert delete si update din tabelul Client din baza de date
 */
public class AccountDAO {

    protected static final Logger LOGGER = Logger.getLogger(AccountDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO account (id, type, data, suma, cnp )"
            + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM account where id = ?";
    private static final String deleteStatementString = "DELETE FROM account WHERE id=?";
    private static final String updateStatementString = "UPDATE account a set a.type=?, a.data=?, a.suma=?, a.cnp=? WHERE a.id=?";
    private final static String findAllAccountsStatement = "SELECT * FROM account";

    /**
     *
     * Implementarea operatiei de findByName care are rolul de a cauta daca exista numele dat ca parametru in tabelul Client din baza de date
     */

    public static int insert(Account account) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, account.getId());
            insertStatement.setString(2, account.getType());
            insertStatement.setDate(3,  account.getDateCreation());
            insertStatement.setFloat(4, account.getMoney());
            insertStatement.setString(5, account.getCnp());



            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int update(Account account) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, account.getType());
            updateStatement.setDate(2, (Date) account.getDateCreation());
            updateStatement.setFloat(3, account.getMoney());
            updateStatement.setString(4, account.getCnp());
            updateStatement.setInt(5, account.getId());


            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }

    public static Account findById(int id) {

        Account toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            rs.next();

            String type = rs.getString("type");
            Date data=rs.getDate("data");
            float suma=rs.getFloat("suma");
            String cnp = rs.getString("cnp");


            toReturn = new Account(id, cnp, type, suma, data );
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"AccountDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }



    public static int delete(Account account) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int deleteId =-1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);

            deleteStatement.setInt(1, account.getId());
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AccountDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }

    public static ArrayList<Account> getAccounts() {

        ArrayList<Account> toReturn = new ArrayList<Account>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findAllAccountsStatement);

            rs = findStatement.executeQuery();
            while(rs.next()){

                int id= rs.getInt("id");
                String type = rs.getString("type");

                Date data = rs.getDate("data");
                Float suma = rs.getFloat("suma");
                String cnp = rs.getString("cnp");

                toReturn.add(new Account(id, type, cnp, suma, data));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"UserDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }



}

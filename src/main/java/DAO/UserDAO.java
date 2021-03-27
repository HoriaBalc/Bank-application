
package DAO;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import connection.ConnectionFactory;
        import model.Client;
        import model.User;

/**
 * Clasa face ,cu ajutorul pachetului connection, operatiile de insert delete si update din tabelul Client din baza de date
 */
public class UserDAO {
    public ArrayList<User> a;
    protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO user (name,password,type)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM user where name = ?";
    private final static String findAllUsersStatement = "SELECT * FROM user";
    private static final String deleteStatementString = "DELETE FROM user WHERE name=?";
    private static final String updateStatementString = "UPDATE user u set u.password=?, u.type=? WHERE u.name=?";
    /**
     * Implementarea operatiei de findByName care are rolul de a cauta daca exista numele dat ca parametru in tabelul Client din baza de date
     */

    public static int insert(User user) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setInt(3, user.getType());



            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int update(User user) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, user.getPassword());
            updateStatement.setInt(2, user.getType());
            updateStatement.setString(3, user.getName());



            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }


    public static User findByName(String name) {

        User toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, name);
            rs = findStatement.executeQuery();
            rs.next();


            String password = rs.getString("password");
            int type = rs.getInt("type");

            toReturn = new User(name, type, password );
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"UserDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static ArrayList<User> getUsers() {

        ArrayList<User> toReturn = new ArrayList<User>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findAllUsersStatement);

            rs = findStatement.executeQuery();
            while(rs.next()){

                String name= rs.getString("name");
                String password = rs.getString("password");
                int type = rs.getInt("type");

                toReturn.add(new User(name, type, password));
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



    public static String delete(User user) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        String deleteId ="";
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);

            deleteStatement.setString(1, user.getName());
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }

    public ArrayList<User> getA() {
        return a;
    }

    public void setA(User u) {
         a.add(u);
    }



}


package DAO;

        import java.sql.*;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import connection.ConnectionFactory;
        import model.Account;
        import model.Client;
        import model.Operations;
        import model.User;

    /**
     * Clasa face ,cu ajutorul pachetului connection, operatiile de insert delete si update din tabelul Client din baza de date
     */
    public class OperationsDAO {

        protected static final Logger LOGGER = Logger.getLogger(DAO.AccountDAO.class.getName());
        private static final String insertStatementString = "INSERT INTO operations (id, name, account, client, user )"
                + " VALUES (?,?,?,?,?)";
        private final static String findStatementString = "SELECT * FROM operations where id = ?";
        private final static String findStatementString1 = "SELECT * FROM operation where name = ?";
        private static final String deleteStatementString = "DELETE FROM operations WHERE id=?";

        /**
         *
         * Implementarea operatiei de findByName care are rolul de a cauta daca exista numele dat ca parametru in tabelul Client din baza de date
         */

        public static int insert(Operations operations) {
            Connection dbConnection = ConnectionFactory.getConnection();

            PreparedStatement insertStatement = null;
            int insertedId = -1;
            try {
                insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setInt(1, operations.getId());
                insertStatement.setString(2, operations.getName());
                if(operations.getName().contains("account")){
                insertStatement.setString(3,  operations.getAccount());
                }else {insertStatement.setString(3,  "nu");}

                if(operations.getName().contains("user")){
                    insertStatement.setString(5, operations.getUser());
                }else {insertStatement.setString(5,  "nu");}

                if(operations.getName().contains("client")){
                    insertStatement.setString(4, operations.getClient());
                }else {insertStatement.setString(4,  "nu");}



                insertStatement.executeUpdate();

                ResultSet rs = insertStatement.getGeneratedKeys();
                if (rs.next()) {
                    insertedId = rs.getInt(1);
                }
            } catch (SQLException |NullPointerException e ) {
                LOGGER.log(Level.WARNING, "OperationsDAO:insert " + e.getMessage());
            } finally {
                ConnectionFactory.close(insertStatement);
                ConnectionFactory.close(dbConnection);
            }
            return insertedId;
        }

        public static Operations findById(int id) {

            Operations toReturn = null;

            Connection dbConnection = ConnectionFactory.getConnection();
            PreparedStatement findStatement = null;
            ResultSet rs = null;
            try {
                findStatement = dbConnection.prepareStatement(findStatementString);
                findStatement.setInt(1, id);
                rs = findStatement.executeQuery();
                rs.next();

                String name = rs.getString("name");
                //int a=rs.getInt("account");
                //String c=rs.getString("client");
                //String u = rs.getString("user");
                if(name.contains("client")){
                    String c=rs.getString("client");
                    toReturn = new Operations(id, name, c);
                }

                if(name.contains("account")){
                    int a=rs.getInt("account");
                    toReturn = new Operations(id, name, a+"");
                }

                if(name.contains("user")) {
                    String u = rs.getString("user");
                    toReturn = new Operations(id, name, u);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING,"OperationsDAO:findById " + e.getMessage());
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

    }





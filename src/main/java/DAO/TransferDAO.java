
package DAO;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import connection.ConnectionFactory;
        import model.Account;
        import model.Client;
        import model.Transfer;
        import model.User;

/**
 * Clasa face ,cu ajutorul pachetului connection, operatiile de insert delete si update din tabelul Client din baza de date
 */
public class TransferDAO {

    protected static final Logger LOGGER = Logger.getLogger(TransferDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO transfer (id, contDestinatie, contSursa, suma)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM transfer where id = ?";
    private static final String deleteStatementString = "DELETE FROM transfer WHERE id=?";
    private final static String findAllTransfersStatement = "SELECT * FROM transfer";


    /**
     *
     * Implementarea operatiei de findByName care are rolul de a cauta daca exista numele dat ca parametru in tabelul Client din baza de date
     */

    public static ArrayList<Transfer> getTransfers() {

        ArrayList<Transfer> toReturn = new ArrayList<Transfer>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findAllTransfersStatement);

            rs = findStatement.executeQuery();
            while(rs.next()){

                int id= rs.getInt("id");
                String id1 = rs.getString("contDestinatie");

                String id2 = rs.getString("contSursa");
                Float suma = rs.getFloat("suma");


                toReturn.add(new Transfer(id, id1, id2, suma));
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


    public static int insert(Transfer transfer) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, transfer.getId());
            insertStatement.setString(2, transfer.getIdDestinationAccount());
            insertStatement.setString(3,  transfer.getIdSourceAccount());
            insertStatement.setFloat(4, transfer.getMoney());




            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TransefDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static Transfer findById(int id) {

        Transfer toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            rs.next();
            String contDestinatie = rs.getString("contDestinatie");
            String contSursa=rs.getString("contSursa");
            float suma=rs.getFloat("suma");
            toReturn = new Transfer(id,  contDestinatie, contSursa, suma);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"TransferDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }



    public static int delete(Transfer transfer) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int deleteId =-1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);

            deleteStatement.setInt(1, transfer.getId());
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TransferDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }




}

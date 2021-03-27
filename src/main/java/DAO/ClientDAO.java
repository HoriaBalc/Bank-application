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
public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (nume,oras,adresa,CNP, numarId)"
            + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM client where CNP = ?";
    private static final String deleteStatementString = "DELETE FROM client WHERE CNP=?";
    private static final String updateStatementString = "UPDATE client c set c.nume=?, c.oras=?, c.adresa=?, c.numarId=? WHERE c.CNP=?";
    private final static String findAllClientsStatement = "SELECT * FROM client";

    /**
     * Implementarea operatiei de findByName care are rolul de a cauta daca exista numele dat ca parametru in tabelul Client din baza de date
     */

    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getOras());
            insertStatement.setString(3, client.getAddress());
            insertStatement.setString(4, client.getPersonalNumericCode());
            insertStatement.setString(5, client.getIdentificationNumber());


            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int update(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, client.getName());
            updateStatement.setString(2, client.getOras());
            updateStatement.setString(3, client.getAddress());
            updateStatement.setString(4, client.getIdentificationNumber());
            updateStatement.setString(5, client.getPersonalNumericCode());



            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }

    public static Client findByCNP(String CNP) {

        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, CNP);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("nume");
            String oras = rs.getString("oras");
            String personalNumericCode = rs.getString("CNP");
            String adress = rs.getString("adresa");
            String idNr=rs.getString("numarId");
            toReturn = new Client(name,personalNumericCode,adress,oras,idNr);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static ArrayList<Client> getClients() {

        ArrayList<Client> toReturn = new ArrayList<Client>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findAllClientsStatement);

            rs = findStatement.executeQuery();
            while(rs.next()){

                String name= rs.getString("nume");
                String oras = rs.getString("oras");

                String adresa = rs.getString("adresa");
                String cnp = rs.getString("CNP");
                String nrId = rs.getString("numarId");

                toReturn.add(new Client(name, oras, adresa, cnp, nrId));
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


    public static String delete(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        String deleteId ="";
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);

            deleteStatement.setString(1, client.getPersonalNumericCode());
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }




}

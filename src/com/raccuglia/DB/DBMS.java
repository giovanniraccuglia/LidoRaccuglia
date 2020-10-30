package com.raccuglia.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.raccuglia.model.Postazione;
import com.raccuglia.model.Prenotazione;
import com.raccuglia.model.Prodotto;
import com.raccuglia.model.Utente;

public class DBMS {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/Raccuglia?serverTimezone=Europe/Rome";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "24121996";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
	
	public static Utente login(String email, String password) {
    	Utente utente = null;
    	String query = "select * from Raccuglia.Utente where email = ? and password = MD5(?)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, email);
    		statement.setString(2, password);
    		ResultSet rs = statement.executeQuery();
    		if(rs.next()) {
    			utente = new Utente(rs.getInt("idUtente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cellulare"), rs.getString("email"), rs.getString("password"), rs.getString("ruolo"));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
		return utente;
    }
	
	public static void registrazioneCliente(String nome, String cognome, String cellulare, String email, String password) {
    	String query = "insert into Raccuglia.Utente (nome, cognome, cellulare, email, password, ruolo) values (?, ?, ?, ?, MD5(?), ?)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, cellulare);
			statement.setString(4, email);
			statement.setString(5, password);
			statement.setString(6, "Cliente");
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
	
	public static boolean verificaUtente(String email, String cellulare) {
    	boolean result = false;
    	String query = "select * from Raccuglia.Utente where email = ? or cellulare = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, email);
    		statement.setString(2, cellulare);
    		ResultSet rs = statement.executeQuery();
    		result = rs.next();
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
		return result;
    }
	
	public static void resetPassword(String email, String password) {
    	String query = "update Raccuglia.Utente set password = MD5(?) where email = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, password);
			statement.setString(2, email);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
	
	public static List<Prodotto> getListaProdotti() {
    	List<Prodotto> prodotti = new ArrayList<>();
    	String query = "select * from Raccuglia.Prodotto order by categoria";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			prodotti.add(new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("prezzo"), rs.getString("categoria")));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return prodotti;
    }
	
	public static void updateUtente(int idUtente, String nome, String cognome, String cellulare, String email) {
    	String query = "update Raccuglia.Utente set nome = ?, cognome = ?, cellulare = ?, email = ? where idUtente = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, cellulare);
			statement.setString(4, email);
			statement.setInt(5, idUtente);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
	
	public static void updatePassword(int idUtente, String password) {
    	String query = "update Raccuglia.Utente set password = MD5(?) where idUtente = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, password);
			statement.setInt(2, idUtente);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static boolean verificaPassword(int idUtente, String password) {
    	boolean result = false;
    	String query = "select * from Raccuglia.Utente where idUtente = ? and password = MD5(?)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idUtente);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			result = rs.next();
			rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return result;
    }
    
    public static List<Utente> getDipendenti() {
    	List<Utente> dipendenti = new ArrayList<>();
    	String query = "select * from Raccuglia.Utente where ruolo != 'Admin' and ruolo != 'Cliente' order by ruolo";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			dipendenti.add(new Utente(rs.getInt("idUtente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cellulare"), rs.getString("email"), rs.getString("password"), rs.getString("ruolo")));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return dipendenti;
    }
    
    public static void registrazioneDipendente(String nome, String cognome, String cellulare, String email, String password, String ruolo) {
    	String query = "insert into Raccuglia.Utente (nome, cognome, cellulare, email, password, ruolo) values (?, ?, ?, ?, MD5(?), ?)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, cellulare);
			statement.setString(4, email);
			statement.setString(5, password);
			statement.setString(6, ruolo);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static void deleteDipendente(int idUtente) {
    	String query = "delete from Raccuglia.Utente where idUtente = ? and ruolo != 'Admin' and ruolo != 'Cliente'";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idUtente);
    		statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static Utente getUtente(String email) {
    	Utente utente = null;
    	String query = "select * from Raccuglia.Utente where email = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				utente = new Utente(rs.getInt("idUtente"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cellulare"), rs.getString("email"), rs.getString("password"), rs.getString("ruolo"));
    		}
			rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return utente;
    }
    
    public static void deleteProdotto(int idProdotto) {
    	String query = "delete from Raccuglia.Prodotto where idProdotto = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idProdotto);
    		statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static boolean verificaProdotto(String nome) {
    	boolean result = false;
    	String query = "select * from Raccuglia.Prodotto where nome = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, nome);
    		ResultSet rs = statement.executeQuery();
    		result = rs.next();
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
		return result;
    }
    
    public static void aggiungiProdotto(String nome, String descrizione, Double prezzo, String categoria) {
    	String query = "insert into Raccuglia.Prodotto (nome, descrizione, prezzo, categoria) values (?, ?, ?, ?)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, nome);
			statement.setString(2, descrizione);
			statement.setDouble(3, prezzo);
			statement.setString(4, categoria);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static Prodotto getProdotto(String nome) {
    	Prodotto prodotto = null;
    	String query = "select * from Raccuglia.Prodotto where nome = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, nome);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				prodotto = new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("prezzo"), rs.getString("categoria"));
    		}
			rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return prodotto;
    }
    
    public static List<Prenotazione> getPrenotazioni(int idUtente) {
    	List<Prenotazione> prenotazioni = new ArrayList<>();
    	String query1 = "select Prenotazione.* from Raccuglia.Utente, Raccuglia.Prenotazione where idUtente = ? and idUtente = Utente_idUtente order by data desc";
    	try(Connection connection1  = getConnection(); PreparedStatement statement1 = connection1.prepareStatement(query1)) {
    		statement1.setInt(1, idUtente);
    		ResultSet rs1 = statement1.executeQuery();
    		while(rs1.next()) {
    			List<Postazione> postazioni = new ArrayList<>();
    			String query2 = "select Postazione.* from Raccuglia.Postazione where idPostazione in (select Postazione_idPostazione from Raccuglia.Prenotazione, Raccuglia.Prenotazione_has_Postazione where idPrenotazione = ? and idPrenotazione = Prenotazione_idPrenotazione)";
    			try(Connection connection2 = getConnection(); PreparedStatement statement2 = connection2.prepareStatement(query2)) {
    				statement2.setInt(1, rs1.getInt("idPrenotazione"));
    				ResultSet rs2 = statement2.executeQuery();
    				while(rs2.next()) {
    					postazioni.add(new Postazione(rs2.getInt("idPostazione"), rs2.getDouble("prezzo"), rs2.getBoolean("abilitata")));
    				}
    				rs2.close();
    			}catch(SQLException e) {
    	    		printSQLException(e);
    	    	}
    			prenotazioni.add(new Prenotazione(rs1.getInt("idPrenotazione"), rs1.getTimestamp("data"), rs1.getDate("dataPrenotazione"), rs1.getDouble("totale"), rs1.getBoolean("pagato"), rs1.getBoolean("rimborsato"), postazioni));
    		}
    		rs1.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return prenotazioni;
    }
    
    public static Prenotazione getPrenotazione(int idUtente, int idPrenotazione) {
    	Prenotazione prenotazione = null;
    	String query1 = "select Prenotazione.* from Raccuglia.Utente, Raccuglia.Prenotazione where idUtente = ? and idUtente = Utente_idUtente and idPrenotazione = ?;";
    	try(Connection connection1  = getConnection(); PreparedStatement statement1 = connection1.prepareStatement(query1)) {
    		statement1.setInt(1, idUtente);
    		statement1.setInt(2, idPrenotazione);
    		ResultSet rs1 = statement1.executeQuery();
    		if(rs1.next()) {
    			List<Postazione> postazioni = new ArrayList<>();
    			String query2 = "select Postazione.* from Raccuglia.Postazione where idPostazione in (select Postazione_idPostazione from Raccuglia.Prenotazione, Raccuglia.Prenotazione_has_Postazione where idPrenotazione = ? and idPrenotazione = Prenotazione_idPrenotazione)";
    			try(Connection connection2 = getConnection(); PreparedStatement statement2 = connection2.prepareStatement(query2)) {
    				statement2.setInt(1, idPrenotazione);
    				ResultSet rs2 = statement2.executeQuery();
    				while(rs2.next()) {
    					postazioni.add(new Postazione(rs2.getInt("idPostazione"), rs2.getDouble("prezzo"), rs2.getBoolean("abilitata")));
    				}
    				rs2.close();
    			}catch(SQLException e) {
    	    		printSQLException(e);
    	    	}
    			prenotazione = new Prenotazione(rs1.getInt("idPrenotazione"), rs1.getTimestamp("data"), rs1.getDate("dataPrenotazione"), rs1.getDouble("totale"), rs1.getBoolean("pagato"), rs1.getBoolean("rimborsato"), postazioni);
    		}
    		rs1.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return prenotazione;
    }
    
    public static void deletePrenotazione(int idUtente, int idPrenotazione) {
    	String query = "update Raccuglia.Utente, Raccuglia.Prenotazione set rimborsato = b'1' where (idPrenotazione = ? and idUtente = ? and idUtente = Utente_idUtente)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idPrenotazione);
			statement.setInt(2, idUtente);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static List<Postazione> getPostazioniDisabilitate() {
    	List<Postazione> postazioni = new ArrayList<>();
    	String query = "select * from Raccuglia.Postazione where abilitata = b'0'";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			postazioni.add(new Postazione(rs.getInt("idPostazione"), rs.getDouble("prezzo"), rs.getBoolean("abilitata")));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return postazioni;
    }
    
    public static List<Postazione> getPostazioniPrenotate(Date dataPrenotazione) {
    	List<Postazione> postazioni = new ArrayList<>();
    	String query = "select Postazione.* from Raccuglia.Postazione where idPostazione in (select Postazione_idPostazione from Raccuglia.Prenotazione, Raccuglia.Prenotazione_has_Postazione where idPrenotazione in (select idPrenotazione from Raccuglia.Prenotazione where dataPrenotazione = ? and rimborsato = b'0') and idPrenotazione = Prenotazione_idPrenotazione)";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setDate(1, dataPrenotazione);
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			postazioni.add(new Postazione(rs.getInt("idPostazione"), rs.getDouble("prezzo"), rs.getBoolean("abilitata")));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return postazioni;
    }
    
    public static Postazione getPostazioneToId(int idPostazione) {
    	Postazione postazione = null;
    	String query = "select * from Raccuglia.Postazione where idPostazione = ?";
    	try(Connection connection  = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idPostazione);
    		ResultSet rs = statement.executeQuery();
    		if(rs.next()) {
    			postazione = new Postazione(rs.getInt("idPostazione"), rs.getDouble("prezzo"), rs.getBoolean("abilitata"));
    		}
    		rs.close();
    	}catch(SQLException e) {
        	printSQLException(e);
        }
    	return postazione;
    }
    
    public static void inserisciPrenotazione(Date dataPrenotazione, double totale, int idUtente, List<Postazione> postazioni) {
    	int idPrenotazione = 0;
    	String query1 = "insert into Raccuglia.Prenotazione (dataPrenotazione, totale, pagato, rimborsato, Utente_idUtente) VALUES (?, ?, ?, ?, ?)";
    	try(Connection connection1  = getConnection(); PreparedStatement statement1 = connection1.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
    		statement1.setDate(1, dataPrenotazione);
    		statement1.setDouble(2, totale);
    		statement1.setBoolean(3, true);
    		statement1.setBoolean(4, false);
    		statement1.setInt(5, idUtente);
    		statement1.executeUpdate();
    		ResultSet rs1 = statement1.getGeneratedKeys();
    		if(rs1.next()) {
    			idPrenotazione = rs1.getInt(1);
    		}
    		rs1.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	for(int i = 0; i < postazioni.size(); i++) {
    		String query2 = "insert into Raccuglia.Prenotazione_has_Postazione (Prenotazione_idPrenotazione, Postazione_idPostazione) VALUES (?, ?)";
    		try(Connection connection2  = getConnection(); PreparedStatement statement2 = connection2.prepareStatement(query2)) {
    			statement2.setInt(1, idPrenotazione);
    			statement2.setInt(2, postazioni.get(i).getIdPostazione());
    			statement2.executeUpdate();
    		}catch(SQLException e) {
        		printSQLException(e);
        	}
    	}
    }
    
}

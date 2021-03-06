package com.raccuglia.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.raccuglia.model.Ordine;
import com.raccuglia.model.Postazione;
import com.raccuglia.model.Prenotazione;
import com.raccuglia.model.Prodotto;
import com.raccuglia.model.Utente;

public class DBMS {
	private static Context context = null;
	private static DataSource dataSource = null;
	
	static {
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Raccuglia");
			}
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		}catch(SQLException e) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, password);
			statement.setString(2, email);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
	
	public static List<Prodotto> getProdotti() {
    	List<Prodotto> prodotti = new ArrayList<>();
    	String query = "select * from Raccuglia.Prodotto order by categoria";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	String query = "select * from Raccuglia.Utente where ruolo != ? and ruolo != ? order by ruolo";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, "Admin");
    		statement.setString(2, "Cliente");
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	String query = "delete from Raccuglia.Utente where idUtente = ? and ruolo != ? and ruolo != ?";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idUtente);
    		statement.setString(2, "Admin");
    		statement.setString(3, "Cliente");
    		statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static void deleteProdotto(int idProdotto) {
    	String query = "delete from Raccuglia.Prodotto where idProdotto = ?";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idProdotto);
    		statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static boolean verificaProdotto(String nome) {
    	boolean result = false;
    	String query = "select * from Raccuglia.Prodotto where nome = ?";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setString(1, nome);
			statement.setString(2, descrizione);
			statement.setDouble(3, prezzo);
			statement.setString(4, categoria);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static List<Prenotazione> getPrenotazioni(int idUtente) {
    	List<Prenotazione> prenotazioni = new ArrayList<>();
    	String query1 = "select Prenotazione.* from Raccuglia.Utente, Raccuglia.Prenotazione where idUtente = ? and idUtente = Utente_idUtente order by data desc";
    	try(Connection connection = getConnection(); PreparedStatement statement1 = connection.prepareStatement(query1)) {
    		statement1.setInt(1, idUtente);
    		ResultSet rs1 = statement1.executeQuery();
    		while(rs1.next()) {
    			List<Postazione> postazioni = new ArrayList<>();
    			String query2 = "select Postazione.* from Raccuglia.Postazione where idPostazione in (select Postazione_idPostazione from Raccuglia.Prenotazione, Raccuglia.Prenotazione_has_Postazione where idPrenotazione = ? and idPrenotazione = Prenotazione_idPrenotazione)";
    			try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
    				statement2.setInt(1, rs1.getInt("idPrenotazione"));
    				ResultSet rs2 = statement2.executeQuery();
    				while(rs2.next()) {
    					postazioni.add(new Postazione(rs2.getInt("idPostazione"), rs2.getDouble("prezzo")));
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
    	String query1 = "select Prenotazione.* from Raccuglia.Utente, Raccuglia.Prenotazione where idUtente = ? and idUtente = Utente_idUtente and idPrenotazione = ?";
    	try(Connection connection = getConnection(); PreparedStatement statement1 = connection.prepareStatement(query1)) {
    		statement1.setInt(1, idUtente);
    		statement1.setInt(2, idPrenotazione);
    		ResultSet rs1 = statement1.executeQuery();
    		if(rs1.next()) {
    			List<Postazione> postazioni = new ArrayList<>();
    			String query2 = "select Postazione.* from Raccuglia.Postazione where idPostazione in (select Postazione_idPostazione from Raccuglia.Prenotazione, Raccuglia.Prenotazione_has_Postazione where idPrenotazione = ? and idPrenotazione = Prenotazione_idPrenotazione)";
    			try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
    				statement2.setInt(1, idPrenotazione);
    				ResultSet rs2 = statement2.executeQuery();
    				while(rs2.next()) {
    					postazioni.add(new Postazione(rs2.getInt("idPostazione"), rs2.getDouble("prezzo")));
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
    	String query = "update Raccuglia.Utente, Raccuglia.Prenotazione set rimborsato = ? where (idPrenotazione = ? and idUtente = ? and idUtente = Utente_idUtente)";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setBoolean(1, true);
    		statement.setInt(2, idPrenotazione);
			statement.setInt(3, idUtente);
			statement.executeUpdate();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static List<Postazione> getPostazioniPrenotate(Date dataPrenotazione) {
    	List<Postazione> postazioni = new ArrayList<>();
    	String query = "select Postazione.* from Raccuglia.Postazione where idPostazione in (select Postazione_idPostazione from Raccuglia.Prenotazione, Raccuglia.Prenotazione_has_Postazione where idPrenotazione in (select idPrenotazione from Raccuglia.Prenotazione where dataPrenotazione = ? and rimborsato = ?) and idPrenotazione = Prenotazione_idPrenotazione)";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setDate(1, dataPrenotazione);
    		statement.setBoolean(2, false);
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			postazioni.add(new Postazione(rs.getInt("idPostazione"), rs.getDouble("prezzo")));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return postazioni;
    }
    
    public static List<Postazione> getPostazioni() {
    	List<Postazione> postazioni = new ArrayList<>();
    	String query = "select * from Raccuglia.Postazione";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			postazioni.add(new Postazione(rs.getInt("idPostazione"), rs.getDouble("prezzo")));
    		}
    		rs.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return postazioni;
    }
    
    public static Postazione getPostazioneFromId(int idPostazione) {
    	Postazione postazione = null;
    	String query = "select * from Raccuglia.Postazione where idPostazione = ?";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idPostazione);
    		ResultSet rs = statement.executeQuery();
    		if(rs.next()) {
    			postazione = new Postazione(rs.getInt("idPostazione"), rs.getDouble("prezzo"));
    		}
    		rs.close();
    	}catch(SQLException e) {
        	printSQLException(e);
        }
    	return postazione;
    }
    
    public static void inserisciPrenotazione(Date dataPrenotazione, double totale, int idUtente, List<Postazione> postazioni) {
    	int idPrenotazione = 0;
    	String query1 = "insert into Raccuglia.Prenotazione (dataPrenotazione, totale, pagato, rimborsato, Utente_idUtente) values (?, ?, ?, ?, ?)";
    	try(Connection connection = getConnection(); PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
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
    		for(int i = 0; i < postazioni.size(); i++) {
        		String query2 = "insert into Raccuglia.Prenotazione_has_Postazione (Prenotazione_idPrenotazione, Postazione_idPostazione) values (?, ?)";
        		try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
        			statement2.setInt(1, idPrenotazione);
        			statement2.setInt(2, postazioni.get(i).getIdPostazione());
        			statement2.executeUpdate();
        		}catch(SQLException e) {
            		printSQLException(e);
            	}
        	}
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
   
    public static Utente getUtenteFromPostazione(Date dataPrenotazione, int idPostazione) {
    	Utente utente = null;
    	String query = "select Utente.* from Raccuglia.Utente where idUtente in (select Prenotazione.Utente_idUtente from Raccuglia.Prenotazione where idPrenotazione in (select Prenotazione_has_Postazione.Prenotazione_idPrenotazione from Raccuglia.Prenotazione_has_Postazione, Raccuglia.Postazione where Prenotazione_idPrenotazione in (select Prenotazione.idPrenotazione from Raccuglia.Prenotazione where dataPrenotazione = ? and rimborsato = ?) and idPostazione = ? and idPostazione = Postazione_idPostazione)) and ruolo = ?";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setDate(1, dataPrenotazione);
    		statement.setBoolean(2, false);
    		statement.setInt(3, idPostazione);
    		statement.setString(4, "Cliente");
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
    
    public static List<Ordine> getOrdini() {
    	List<Ordine> ordini = new ArrayList<>();
    	String query1 = "select * from Raccuglia.Ordine where ritirato != ? and pagato = ? order by data desc";
    	try(Connection connection = getConnection(); PreparedStatement statement1 = connection.prepareStatement(query1)) {
    		statement1.setBoolean(1, true);
    		statement1.setBoolean(2, true);
    		ResultSet rs1 = statement1.executeQuery();
    		while(rs1.next()) {
    			List<Prodotto> prodotti = new ArrayList<>();
    			List<Integer> quantita = new ArrayList<>();
    			String query2 = "select Prodotto.* from Raccuglia.Prodotto where idProdotto in (select Prodotto_idProdotto from Raccuglia.Ordine, Raccuglia.Ordine_has_Prodotto where idOrdine = ? and idOrdine = Ordine_idOrdine)";
    			try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
    				statement2.setInt(1, rs1.getInt("idOrdine"));
    				ResultSet rs2 = statement2.executeQuery();
    				while(rs2.next()) {
    					Prodotto prodotto = new Prodotto(rs2.getInt("idProdotto"), rs2.getString("nome"), rs2.getString("descrizione"), rs2.getDouble("prezzo"), rs2.getString("categoria"));
    					String query3 = "select Ordine_has_Prodotto.quantità from Raccuglia.Ordine, Raccuglia.Ordine_has_Prodotto, Raccuglia.Prodotto where idOrdine = ? and idOrdine = Ordine_idOrdine and idProdotto = ? and idProdotto = Prodotto_idProdotto";
    					try(PreparedStatement statement3 = connection.prepareStatement(query3)) {
    						statement3.setInt(1, rs1.getInt("idOrdine"));
    						statement3.setInt(2, prodotto.getIdProdotto());
    						ResultSet rs3 = statement3.executeQuery();
    						if(rs3.next()) {
    							quantita.add(rs3.getInt("quantità"));
    						}
    						rs3.close();
    					}catch(SQLException e) {
    	    	    		printSQLException(e);
    	    	    	}
    					prodotti.add(prodotto);
    				}
    				rs2.close();
    			}catch(SQLException e) {
    	    		printSQLException(e);
    	    	}
    			ordini.add(new Ordine(rs1.getInt("idOrdine"), rs1.getTimestamp("data"), rs1.getDouble("totale"), rs1.getBoolean("preparato"), rs1.getBoolean("ritirato"), rs1.getBoolean("pagato"), prodotti, quantita));
    		}
    		rs1.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return ordini;
    }
    
    public static List<Ordine> getOrdiniFromId(int idUtente) {
    	List<Ordine> ordini = new ArrayList<>();
    	String query1 = "select Ordine.* from Raccuglia.Utente, Raccuglia.Ordine where idUtente = ? and idUtente = Utente_idUtente order by data desc";
    	try(Connection connection = getConnection(); PreparedStatement statement1 = connection.prepareStatement(query1)) {
    		statement1.setInt(1, idUtente);
    		ResultSet rs1 = statement1.executeQuery();
    		while(rs1.next()) {
    			List<Prodotto> prodotti = new ArrayList<>();
    			List<Integer> quantita = new ArrayList<>();
    			String query2 = "select Prodotto.* from Raccuglia.Prodotto where idProdotto in (select Prodotto_idProdotto from Raccuglia.Ordine, Raccuglia.Ordine_has_Prodotto where idOrdine = ? and idOrdine = Ordine_idOrdine)";
    			try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
    				statement2.setInt(1, rs1.getInt("idOrdine"));
    				ResultSet rs2 = statement2.executeQuery();
    				while(rs2.next()) {
    					Prodotto prodotto = new Prodotto(rs2.getInt("idProdotto"), rs2.getString("nome"), rs2.getString("descrizione"), rs2.getDouble("prezzo"), rs2.getString("categoria"));
    					String query3 = "select Ordine_has_Prodotto.quantità from Raccuglia.Ordine, Raccuglia.Ordine_has_Prodotto, Raccuglia.Prodotto where idOrdine = ? and idOrdine = Ordine_idOrdine and idProdotto = ? and idProdotto = Prodotto_idProdotto";
    					try(PreparedStatement statement3 = connection.prepareStatement(query3)) {
    						statement3.setInt(1, rs1.getInt("idOrdine"));
    						statement3.setInt(2, prodotto.getIdProdotto());
    						ResultSet rs3 = statement3.executeQuery();
    						if(rs3.next()) {
    							quantita.add(rs3.getInt("quantità"));
    						}
    						rs3.close();
    					}catch(SQLException e) {
    	    	    		printSQLException(e);
    	    	    	}
    					prodotti.add(prodotto);
    				}
    				rs2.close();
    			}catch(SQLException e) {
    	    		printSQLException(e);
    	    	}
    			ordini.add(new Ordine(rs1.getInt("idOrdine"), rs1.getTimestamp("data"), rs1.getDouble("totale"), rs1.getBoolean("preparato"), rs1.getBoolean("ritirato"), rs1.getBoolean("pagato"), prodotti, quantita));
    		}
    		rs1.close();
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    	return ordini;
    }
    
    public static Prodotto getProdottoFromId(int idProdotto) {
    	Prodotto prodotto = null;
    	String query = "select * from Raccuglia.Prodotto where idProdotto = ?";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setInt(1, idProdotto);
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
    
    public static void inserisciOrdine(double totale, int idUtente, List<Prodotto> prodotti, List<Integer> quantita) {
    	int idOrdine = 0;
    	String query1 = "insert into Raccuglia.Ordine (totale, preparato, ritirato, pagato, Utente_idUtente) values (?, ?, ?, ?, ?)";
    	try(Connection connection = getConnection(); PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
    		statement1.setDouble(1, totale);
    		statement1.setBoolean(2, false);
    		statement1.setBoolean(3, false);
    		statement1.setBoolean(4, true);
    		statement1.setInt(5, idUtente);
    		statement1.executeUpdate();
    		ResultSet rs1 = statement1.getGeneratedKeys();
    		if(rs1.next()) {
    			idOrdine = rs1.getInt(1);
    		}
    		rs1.close();
    		for(int i = 0; i < prodotti.size(); i++) {
        		String query2 = "insert into Raccuglia.Ordine_has_Prodotto (Ordine_idOrdine, Prodotto_idProdotto, quantità) values (?, ?, ?)";
        		try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
        			statement2.setInt(1, idOrdine);
        			statement2.setInt(2, prodotti.get(i).getIdProdotto());
        			statement2.setInt(3, quantita.get(i));
        			statement2.executeUpdate();
        		}catch(SQLException e) {
            		printSQLException(e);
            	}
        	}
    	}catch(SQLException e) {
    		printSQLException(e);
    	}
    }
    
    public static void updateOrdine(int idOrdine, boolean pronto, boolean ritirato) {
    	String query = "update Raccuglia.Ordine set preparato = ?, ritirato = ? WHERE (idOrdine = ?)";
    	try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
    		statement.setBoolean(1, pronto);
    		statement.setBoolean(2, ritirato);
    		statement.setInt(3, idOrdine);
    		statement.executeUpdate();
    	}catch(SQLException e) {
        	printSQLException(e);
        }
    }
}
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gui.serverPortController;
import logic.Visitor;
import ocsf.server.*;
import sun.security.jca.GetInstance;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************
	private Connection con;
	private serverPortController sPC;

	/**
	 * The default port to listen on.
	 */
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */
	public EchoServer(int port, serverPortController sPC) {
		super(port);
		this.sPC = sPC;
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Visitor sv = new Visitor();
		String message = (String) msg;
		System.out.println("Message received: " + msg + " from " + client);
		if (message.equals("close")) {
			sPC.disconectClient();
			this.sendToAllClients("");
		} else if (msg instanceof ArrayList<?>) {
			if (updateEmailInDB(msg))
				this.sendToAllClients("succsess");
		} else {
			sv = searchInDB(msg);
			if (sv.getId() != null)
				this.sendToAllClients(sv.toString());
			else
				this.sendToAllClients("Error");
		}

	}

	public Visitor searchInDB(Object msg) {
		ResultSet res;
		Visitor sv = new Visitor();
		try {
			String s = (String) msg;
			PreparedStatement ps = con.prepareStatement("select * from visitors where id = ?");
			ps.setString(1, s);

			res = ps.executeQuery();

			while (res.next()) {
				sv.setId(res.getString(1));
				sv.setPhone(res.getString(2));
				sv.setLastname(res.getString(3));
				sv.setName(res.getString(4));
				sv.setEmail(res.getString(5));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sv;
	}

	public boolean updateEmailInDB(Object msg) {

		ArrayList tempV = (ArrayList<String>) msg;
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE gonaturedb.visitors SET email = ? WHERE (id = ?);");
			ps.setString(2, (String) tempV.get(0));
			ps.setString(1, (String) tempV.get(1));
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
		}

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gonaturedb?serverTimezone=IST",
					"root", "root");
			con = conn;
			System.out.println("SQL connection succeed");

		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// METHODS DESIGNED TO BE OVERRIDDEN BY CONCRETE SUBCLASSES ---------

	/**
	 * Hook method called each time a new client connection is accepted. The default
	 * implementation does nothing.
	 * 
	 * @param client the connection connected to the client.
	 */
	protected void clientConnected(ConnectionToClient client) {
		sPC.setInfoClient(client.getInetAddress().toString(), client.getInetAddress().getHostAddress().toString());
	}
}
//End of EchoServer class

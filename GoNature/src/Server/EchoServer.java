// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.util.ArrayList;
import gui.serverPortController;
import logic.Visitor;
import ocsf.server.*;
import sqlConnection.SqlConnector;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * 
 * @Editor Dan Gutchin
 * @Editor Yaniv Sokolov
 * @Editor Rafael elkoby
 * @version December 3 2020
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	private serverPortController sPC;
	private SqlConnector co;
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
		String message = null;
		System.out.println("Message received : " + msg + " from " + client);

		if (msg instanceof String) {
			message = (String) msg;
			if (message.equals("close")) {
				// FIXME not a proper client close
				sPC.setDisconectClientFields();
				this.sendToAllClients("");
				System.out.println("Drasd");
			} else {
				sv = co.searchInDB(msg);
				if (sv.getId() != null)
					this.sendToAllClients(sv.toString());
				else
					this.sendToAllClients("Error");
			}
		}
		if (msg instanceof ArrayList<?>) {
			if (co.updateEmailInDB(msg))
				this.sendToAllClients("succsess");
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		co = SqlConnector.getInstance();
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

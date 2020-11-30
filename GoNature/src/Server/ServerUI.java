package Server;

import gui.serverPortController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application {
	final public static int DEFAULT_PORT = 5555;
	 static serverPortController sPC;

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	@Override
	public void start(Stage primaryStage) throws Exception {
		serverPortController aFrame = new serverPortController(); // create server GUI
		aFrame.start(primaryStage);
	}

	public static void runServer(String p,serverPortController aFrame) {
		int port = 0; // Port to listen on
		sPC = aFrame;
		try {
			port = Integer.parseInt(p); // Set port to 5555

		} catch (Throwable t) {
			System.out.println("ERROR - Could not connect!");
		}

		EchoServer sv = new EchoServer(port, sPC);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}

}
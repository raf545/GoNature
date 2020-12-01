package gui;

import java.io.IOException;
import client.ChatClient;
import client.ClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SerchController {

	@FXML
	private TextField idText;

	@FXML
	private Button serchBtn;

	@FXML
	private Button exitBtn;

	@FXML
	void ExitWin(ActionEvent event) throws IOException {
		Platform.exit();
		ClientUI.chat.accept("close");

	}

	private String getID() {
		return idText.getText();
	}

	@FXML
	// change to serchidindb
	void SerchIdInDB(ActionEvent event) throws IOException {
		String id;
		FXMLLoader loader = new FXMLLoader();

		id = getID();
		if (id.trim().isEmpty()) {

			System.out.println("You must enter an id number");
		} else {
			ClientUI.chat.accept(id);

			if (ChatClient.v1.getId().equals("Error")) {
				System.out.println("ID Not Found");

			} else {
				System.out.println("ID Found");
				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				Stage primaryStage = new Stage();
				Pane root = loader.load(getClass().getResource("/gui/VisitorForm.fxml").openStream());
				VisitorFormController VisitorFormController = loader.getController();
				VisitorFormController.loadVisitor(ChatClient.v1);

				// how to write

				Scene scene = new Scene(root);
				primaryStage.setTitle("Visitor Managment Tool");

				primaryStage.setScene(scene);
				primaryStage.show();
			}
		}

	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/SerchGui.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("Serch");
		primaryStage.setScene(scene);

		primaryStage.show();
	}

}

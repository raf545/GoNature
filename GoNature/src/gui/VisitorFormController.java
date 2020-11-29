package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Visitor;

public class VisitorFormController implements Initializable {

	Visitor sendV;
	List<String> ListD;

	Visitor v;
	@FXML
	private TextField nameText;

	@FXML
	private TextField lastNameText;

	@FXML
	private TextField idText;

	@FXML
	private TextField phoneText;

	@FXML
	private TextField emailText;

	@FXML
	private Button emailUpadteBtn;

	@FXML
	void updateEmailInDB(ActionEvent event) {
		ListD = new ArrayList<String>();
		ListD.add(idText.getText());
		ListD.add(emailText.getText());

		ClientUI.chat.accept(ListD);

	}

	/*
	 * loadStudent loads the visitor info in to the gui
	 */
	public void loadVisitor(Visitor v1) {
		this.v = v1;
		this.idText.setText(v.getId());// RAfel
		this.nameText.setText(v.getName());
		this.lastNameText.setText(v.getLastname());
		this.phoneText.setText(v.getPhone());
		this.emailText.setText(v.getEmail());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	void backBtn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/gui/serchGui.fxml").openStream());

		// how to write

		Scene scene = new Scene(root);
		primaryStage.setTitle("Visitor Managment Tool");

		primaryStage.setScene(scene);
		primaryStage.show();

	}

}

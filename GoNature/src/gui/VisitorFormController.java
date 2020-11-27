package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.Visitor;
public class VisitorFormController implements Initializable {

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
    private Button returnBtn;
    
    @FXML
    void prevWin(ActionEvent event) {
    	
    }
    
    @FXML
    void updateEmailInDB(ActionEvent event) {

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

}

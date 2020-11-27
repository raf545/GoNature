package oldProjectClasses;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentFormController implements Initializable {
	private Student s;

	@FXML
	private Label lblName;
	@FXML
	private Label lblSurname;
	@FXML
	private Label lblFaculty;

	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtID;

	@FXML
	private Button btnclose = null;

	@FXML
	private ComboBox cmbFaculty;

	ObservableList<String> list;

	public void loadStudent(Student s1) {
		this.s = s1;
		this.txtID.setText(s.getId());// RAfel
		this.txtName.setText(s.getPName());
		this.txtSurname.setText(s.getLName());
		this.cmbFaculty.setValue(s.getFc().getFName());
	}

	// creating list of Faculties
	private void setFacultyComboBox() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("ME");
		al.add("IE");
		al.add("SE");

		list = FXCollections.observableArrayList(al);
		cmbFaculty.setItems(list);
	}
	@FXML
	private void closeToPrevWin(ActionEvent event) throws Exception {
		
		  Stage est = (Stage) btnclose.getScene().getWindow();
		  AcademicFrameController na = new AcademicFrameController(); 
		  na.start(est); 
			 
		 
	}
	@FXML
	private void SaveToServer(ActionEvent event) {
		/*
		 * Stage est = (Stage) btnclose.getScene().getWindow(); AcademicFrameController
		 * na = new AcademicFrameController(); try { na.start(est); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setFacultyComboBox();
	}

}

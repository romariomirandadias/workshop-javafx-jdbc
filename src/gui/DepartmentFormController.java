package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentServices;

public class DepartmentFormController implements Initializable {

	private DepartmentServices service;
	private Department entiti;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;

	@FXML
	private Label errorLabelName;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	public void setDepartmentService(DepartmentServices service) {
		this.service = service;
	}

	public void setDepartment(Department entiti) {
		this.entiti = entiti;
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entiti == null) {
			throw new IllegalStateException("Entiti was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entiti = getFormData();
			service.saveOrUpdate(entiti);
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}

	}

	private Department getFormData() {
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void updateFormData() {
		if (entiti == null) {
			throw new IllegalStateException("Entiti was null");
		}
		txtId.setText(String.valueOf(entiti.getId()));
		txtName.setText(entiti.getName());
	}
}

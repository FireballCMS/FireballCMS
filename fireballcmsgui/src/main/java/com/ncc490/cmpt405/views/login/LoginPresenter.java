package com.ncc490.cmpt405.views.login;

import com.ncc490.cmpt405.models.admin.LoginService;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.HyperlinkLabel;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javax.inject.Inject;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class LoginPresenter implements Initializable {

    @FXML
    private VBox mLoginFields;

    @FXML
    private TextField mEmail;

    @FXML
    private PasswordField mPassword, mPasswordRetype;

    @FXML
    private Button mLoginButton;

    @FXML
    private HyperlinkLabel mCreateAccountButton;

    @Inject
    private LoginService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupPage(resources);
        ValidationSupport validationSupport = new ValidationSupport();
        mPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && mLoginFields.getChildren().size() > 3) {
                if (mPassword.getText().isEmpty() || !mPasswordRetype.getText().equals(mPassword.getText())) {
                    validationSupport.registerValidator(mPasswordRetype, Validator.createEmptyValidator("Field is required!"));
                    return;
                }
            }
            validationSupport.registerValidator(mPasswordRetype, false, Validator.createEmptyValidator(null));
        });

        mPasswordRetype.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && !mPassword.getText().equals(mPasswordRetype.getText())) {
                validationSupport.registerValidator(mPassword, Validator.createEmptyValidator("The passwords do not match"));
            }
            else if (mPassword.getText().equals(mPasswordRetype.getText())) {
                validationSupport.registerValidator(mPassword, false, Validator.createEmptyValidator(null));
            }

            validationSupport.getRegisteredControls().forEach(System.out::println);
        });

        mPasswordRetype.textProperty().addListener((observable, oldValue, newValue) -> {
            if (mPassword.getText().equals(newValue)) {
                validationSupport.registerValidator(mPassword, false, Validator.createEmptyValidator(null));
            } else {
                validationSupport.registerValidator(mPassword, Validator.createEmptyValidator("The passwords do not match"));
            }
        });
    }

    private void setupPage(ResourceBundle resources) {
        mCreateAccountButton.setText(String.format("[%s]", mCreateAccountButton.getText()));
        mCreateAccountButton.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\[.+]")) {
                mCreateAccountButton.setText("[" + newValue + "]");
            }
        });

        mCreateAccountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (mLoginFields.getChildren().filtered(node -> node.equals(mPasswordRetype)).isEmpty()) {
                mLoginFields.getChildren().add(2, mPasswordRetype);
                mCreateAccountButton.setText(resources.getString("login.login"));
                mLoginButton.setText(resources.getString("login.signup"));
            } else {
                mLoginFields.getChildren().remove(mPasswordRetype);
                mCreateAccountButton.setText(resources.getString("login.signup"));
                mLoginButton.setText(resources.getString("login.login"));
            }
        });

        mLoginButton.pressedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && mLoginButton.isFocused()) {
                System.out.println(service.createAccount(mEmail.getText(), mPassword.getText()));
            }
        });
    }
}

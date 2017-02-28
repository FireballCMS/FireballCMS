package com.ncc490.cmpt405.controllers;

import com.ncc490.cmpt405.views.login.LoginView;
import com.ncc490.cmpt405.views.node.NodeView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new NodeView().getView());
        primaryStage.setScene(scene);
        primaryStage.show();

//        ScenicView.show(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

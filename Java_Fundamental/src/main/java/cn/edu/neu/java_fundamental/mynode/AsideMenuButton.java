package cn.edu.neu.java_fundamental.mynode;

import cn.edu.neu.java_fundamental.mynode.desc.AsideMenuButtonInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AsideMenuButton extends Button {
    public AsideMenuButton(AsideMenuButtonInfo info) {
        super(info.getTitle());
        Image image = new Image(getClass().getResourceAsStream("/images/" + info.getIcon()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        imageView.setPreserveRatio(true);
        setPrefWidth(999);
        setPrefHeight(48);
        setGraphic(imageView);
        setGraphicTextGap(10);

        setOnAction(info.getEventHandler());
    }
}

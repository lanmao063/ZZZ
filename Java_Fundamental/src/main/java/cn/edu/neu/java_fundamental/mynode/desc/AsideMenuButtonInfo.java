package cn.edu.neu.java_fundamental.mynode.desc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AsideMenuButtonInfo {

    private String title;
    private String icon;
    private EventHandler<ActionEvent> eventHandler;
}

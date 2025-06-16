package cn.edu.neu.java_fundamental.mynode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;

public class ClickableTextCell <S> extends TableCell<S,String> {

    private EventHandler<ActionEvent> eventHandler;

    public ClickableTextCell() {
        this(null);
    }

    public ClickableTextCell(EventHandler<ActionEvent> eventHandler) {
        this.eventHandler = eventHandler;
        setOnMouseClicked(event -> {
            if (eventHandler != null && !isEmpty()) {
                S rowData = getTableRow().getItem();
                // 修复点：使用 EventTarget 类型作为第二个参数
                ActionEvent actionEvent = new ActionEvent(rowData, event.getTarget());
                eventHandler.handle(actionEvent);
            }
        });
    }


    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            Text text = new Text(item);
            text.setOnMouseClicked(event -> {
                eventHandler.handle(new ActionEvent(text, event.getTarget()));
            });
            setGraphic(text);

        }
    }
}

package cn.edu.neu.java_fundamental.mynode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class ClickableTextCell <S> extends TableCell<S,String> {

    private EventHandler<ActionEvent> eventHandler;

    public ClickableTextCell() {
        addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            setTextFill(javafx.scene.paint.Color.BLUE);
        });

        addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            setTextFill(javafx.scene.paint.Color.BLACK);
        });

        addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            setTextFill(javafx.scene.paint.Color.RED);
        });

        addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            setTextFill(javafx.scene.paint.Color.BLUE);
            if (getItem() != null) {
                System.out.println("Clicked: " + getItem());
            }
        });
    }

    public ClickableTextCell(EventHandler<ActionEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }





    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            Text text = new Text(item);
            text.setUnderline(true);
            text.setFill(Paint.valueOf("#0000FF"));
            text.setOnMouseClicked(event -> { if (eventHandler != null) {
                S rowData =  getTableRow().getItem();
                ActionEvent actionEvent = new ActionEvent(rowData, event.getTarget());
                eventHandler.handle(actionEvent);
            }});
            setGraphic(text);

        }
    }
}

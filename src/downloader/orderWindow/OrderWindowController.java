package downloader.orderWindow;

import downloader.tool.Tool;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class OrderWindowController {
    @FXML
    public TextField startText;
    @FXML
    public TextField endText;
    @FXML
    public TextField exampleText;
    @FXML
    public Button outputButton;

    public void startOutput() {
        int start = Integer.parseInt(startText.getText());
        int end = Integer.parseInt(endText.getText());

        List<String> output;

        output = Tool.getOrderURLs(exampleText.getText(), start, end);

        for (String str : output) {
            System.out.println(str);
        }
    }
}

package downloader.home;

import downloader.model.URLModel;
import downloader.order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController {
    @FXML
    public TextField textField;
    @FXML
    public Button chooseButton;
    @FXML
    public TextArea textArea;
    @FXML
    public Button getButton;
    @FXML
    public Button downloadButton;

    private List<URLModel> urls = new ArrayList<>();    // URL列表
    private URLModel urlModel;

    public void startGetOrder() throws Exception {
        Order order = new Order();

        order.start(new Stage());
    }
}

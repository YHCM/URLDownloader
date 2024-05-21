package downloader.home;

import downloader.model.URLModel;
import downloader.orderWindow.OrderWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

    private String saveDirectory;     // 保存目录
    private List<URLModel> urls = new ArrayList<>();    // URL列表
    private URLModel urlModel;

    public void chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择保存目录");

        saveDirectory = String.valueOf(directoryChooser.showDialog(new Stage()));
        textField.setText(saveDirectory);
        System.out.println("保存目录：" + saveDirectory);
    }

    public void download() {

    }

    public void startOrderWindow() throws Exception {
        OrderWindow orderWindow = new OrderWindow();

        orderWindow.start(new Stage());
    }
}

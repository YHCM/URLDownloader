package downloader.home;

import downloader.model.URLModel;
import downloader.orderWindow.OrderWindow;
import downloader.tool.Tool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
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
    private List<URLModel> urlModels = new ArrayList<>();    // URL列表
    private URLModel urlModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            String[] urls = newValue.split("\\n");      // 以回车分割
            urlModels.clear();
            for (String url : urls) {
                try {
                    urlModels.add(new URLModel(url));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择保存目录");

        saveDirectory = String.valueOf(directoryChooser.showDialog(new Stage()));
        textField.setText(saveDirectory);
        System.out.println("保存目录：" + saveDirectory);
    }

    public void download() {
        System.out.println("开始下载");

        for (URLModel aUrlModel : urlModels) {
            String urlStr = aUrlModel.getUrlStr();
            String fileName = Tool.getFileName(urlStr);

            Tool.download(urlStr, saveDirectory, fileName);
        }

        System.out.println("\n下载完毕");
    }

    public void startOrderWindow() throws Exception {
        OrderWindow orderWindow = new OrderWindow(textArea);

        orderWindow.start(new Stage());
    }
}

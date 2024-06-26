package downloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("URL下载器");

        // 窗口大小不可改变
        double width = 985;
        double height = 535;
        stage.setResizable(false);

        // 设置 fxml 文件
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/downloader/home/home.fxml")));
        Scene scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.show();
        System.out.println("正在启动...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

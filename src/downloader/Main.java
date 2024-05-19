package downloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("URL下载器");

        // 根据屏幕自适应大小
        double width = 800;
        double height = 500;

        try {
            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();

            width = bounds.getWidth() / 1.6;
            height = bounds.getHeight() / 1.6;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/downloader/home.fxml")));
        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("正在启动...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

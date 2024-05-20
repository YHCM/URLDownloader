package downloader.order;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Order extends Application {
    public Order() {

    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("有序URL序列产生器");

        // 窗口大小不可改变
        double width = 525;
        double height = 150;
        stage.setResizable(false);

        // 设置 fxml 文件
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/downloader/order/order.fxml")));
        Scene scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.show();
        System.out.println("产生有序URL窗口启动...");
    }
}

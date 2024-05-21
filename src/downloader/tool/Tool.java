package downloader.tool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目中要用的方法
 */
public class Tool {
    /**
     * 获取有序的URLS序列
     * @param exampleURL URL例子
     * @param start 开始数字
     * @param end 结束数字
     * @return 一个有序的URL序列
     */
    public static List<String> getOrderURLs(String exampleURL, int start, int end) {
        List<String> orderURLs = new ArrayList<>();    // 结果URL序列

        int lastSlashIndex = exampleURL.lastIndexOf('/');       // 获取最后一个 / 的位置
        int lastDotIndex = exampleURL.lastIndexOf('.');         // 获取最后一个 . 的位置

        // 获取要替换的数字部分
        String numberString = exampleURL.substring(lastSlashIndex + 1, lastDotIndex);

        // 开始产生有序URL序列
        for (int i = start; i <= end; i++) {
            String newURL = exampleURL.replace(numberString, String.valueOf(i));

            orderURLs.add(newURL);
        }

        return orderURLs;
    }

    /**
     * 下载URL文件
     * @param urlStr URL
     * @param savePath 保存目录
     * @param fileName 文件名
     */
    public static void download(String urlStr, String savePath, String fileName) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置超时为3秒
            connection.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // 获取输入流
            InputStream inputStream = connection.getInputStream();
            // 获取字节数组
            byte[] getData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }

            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            System.out.println(url + " 下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[2048];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}

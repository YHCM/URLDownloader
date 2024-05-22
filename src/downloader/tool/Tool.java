package downloader.tool;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
//        String numberString = exampleURL.substring(lastSlashIndex + 1, lastDotIndex);

        // 开始产生有序URL序列
        for (int i = start; i <= end; i++) {
//            String newURL = exampleURL.replace(numberString, String.valueOf(i));
            String newURL = exampleURL.substring(0, lastSlashIndex + 1) + String.valueOf(i) + exampleURL.substring(lastDotIndex);

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
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, new SecureRandom());

            URL url = new URL(urlStr);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // 忽略证书相关操作
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            connection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            connection.connect();

            // 得到输入流
            InputStream inputStream = connection.getInputStream();
            // 获取字节数组
            byte[] getData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(getData);

            connection.disconnect();
            fileOutputStream.close();
            inputStream.close();

            System.out.println("URL： " + url + " 下载完成");
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException exception) {
            exception.printStackTrace();
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

    public static String getFileName(String urlStr) {
        int lastSlashIndex = urlStr.lastIndexOf('/');       // 获取最后一个 / 的位置

        String fileName = urlStr.substring(lastSlashIndex + 1);

        return fileName;
    }
}

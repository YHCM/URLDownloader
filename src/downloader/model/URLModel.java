package downloader.model;

import java.net.URL;

// URL相关类，获取相应信息
public class URLModel {
    private String urlString;      // 链接的字符串形式
    private URL url;        // 链接


    public URLModel() {

    }

    public URLModel(String urlString) {
        this.urlString = urlString;

        try {
            url = new URL(urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}

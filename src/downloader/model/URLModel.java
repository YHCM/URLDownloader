package downloader.model;

import java.net.URL;

// URL相关类，获取相应信息
public class URLModel {
    private String urlStr;      // 链接的字符串形式
    private URL url;        // 链接


    public URLModel() {

    }

    public URLModel(String urlStr) throws Exception{
        this.urlStr = urlStr;

        url = new URL(urlStr);
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}

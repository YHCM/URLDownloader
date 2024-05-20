package downloader.tool;

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
}

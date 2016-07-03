package com.bq2015.bqhttp.bqcallback;

import com.bq2015.bqhttp.MD5Utils;
import com.bq2015.oknet.model.HttpParams;
import com.bq2015.oknet.request.BaseRequest;

import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 该类主要用于对所有的网络请求进行加密，防止拦截数据包进行篡改
 */
public abstract class EncryptCallback<T> extends CommonCallback<T> {

    private static final Random RANDOM = new Random();
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyz";

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //以下是示例加密代码，根据自己的业务需求和服务器的配合，算法自行决定
        /*sign(request.getParams());*/
    }

    /**
     * 针对URL进行签名，关于这几个参数的作用，详细请看
     * http://www.cnblogs.com/bestzrz/archive/2011/09/03/2164620.html
     */
    private void sign(HttpParams params) {
        params.put("nonce", getRndStr(6 + RANDOM.nextInt(8)));
        params.put("timestamp", "" + (System.currentTimeMillis() / 1000L));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : getSortedMapByKey(params.urlParamsMap).entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.delete(sb.length() - 1, sb.length());
        String sign = MD5Utils.encode(sb.toString());
        params.put("sign", sign);
    }

    /**
     * 获取随机数
     */
    private String getRndStr(int length) {
        StringBuilder sb = new StringBuilder();
        char ch;
        for (int i = 0; i < length; i++) {
            ch = CHARS.charAt(RANDOM.nextInt(CHARS.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 按照key的自然顺序进行排序，并返回
     */
    private Map<String, String> getSortedMapByKey(ConcurrentHashMap<String, String> map) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        };
        TreeMap<String, String> treeMap = new TreeMap<>(comparator);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }
        return treeMap;
    }
}

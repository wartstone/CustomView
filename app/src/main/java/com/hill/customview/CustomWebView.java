package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.LinearLayout;

/**
 * Created by hill on 16/11/7.
 */

public class CustomWebView extends LinearLayout {
    private String TAG = "WebView";
    private Context mContext;

    private WebView mWebView;

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        Log.e(TAG, "initView");

        LayoutInflater.from(mContext).inflate(R.layout.layout_webview, this);

        mWebView = (WebView) findViewById(R.id.webview);
        //mWebView.loadUrl("http://liangruijun.blog.51cto.com/3061169/647456/");
        String content = "<pre><code><span style=\"color:#6a8759;\">以1962年的练习鞋为原型, 并于1966年研发出的第一双鞋面Onitsuka Tiger Stripes设计结合。 十字强补, 后套的柔软后跟及后跟设计都是其特色。</span></code></pre><p><img alt=\"BbsImg.png\" src=\"http://static02.aube-tv.com/upload/images/show/201611/s_62d12424_1579e199397__7fd7.png\" width=\"440\" height=\"296.26666666666665\"><br></p><p><br></p><p><br></p><p><br></p><pre><code><span style=\"color: rgb(106, 135, 89);\">以1962年的练习鞋为原型, 并于1966年研发出的第一双鞋面Onitsuka Tiger Stripes设计结合。 十字强补, 后套的柔软后跟及后跟设计都是其特色。</span></code></pre>";
        String prefix = "<html>\n" +
                "<meta name=\"viewport\" content=\"minimum-scale=1, maximum-scale=1,  initial-scale=1, user-scalable=yes, width=device-width\" />\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "<style>\n" +
                "img{\n" +
                "max-width: 100% !important;\n" +
                "height: auto !important;\n" +
                "}\n" +
                "* {\n" +
                "word-wrap: break-word;\n" +
                "word-break: break-all;\n" +
                "}\n" +
                "</style>\n" +
                "<body>\n" +
                "<font color=black>\n";
        String suffix = "\n</font>\n" +
                "</body>\n" +
                "</html>\n";

        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.loadData(prefix + content + suffix, "text/html; charset=UTF-8", null);
    }
}

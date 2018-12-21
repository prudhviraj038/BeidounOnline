package com.yellowsoft.newproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by info on 29-04-2018.
 */

public class PaymentPage extends Activity {
    private WebView wv1;
    ProgressBar progress;
    String type,order_id,page;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_page);
        wv1=(WebView) findViewById(R.id.webView);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.addJavascriptInterface(new WebAppInterface(this),"app");

        wv1.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                Log.d("WebView", "your current url when webpage loading.." + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "your current url when webpage loading.. finish" + url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onLoadResource(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("when you click on any interlink on webview that time you got url :-" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        wv1.setWebChromeClient(new MyWebViewClient());
        if (getIntent()!=null && getIntent().hasExtra("order_id")){
            type =  getIntent().getStringExtra("type");
            order_id = getIntent().getStringExtra("order_id");
            //page = getIntent().getStringExtra("page");
            Log.e("type",type);
        }
        wv1.loadUrl(Session.BASE_URL+Session.LIVE_PAYMENT_URL  +"order_id=" + order_id+ "&type=" + type  + "&currency_code=" + Session.getCurrencyCode(this));//+ "&device=" + "0" +"&page=" + page
        // wv1.loadUrl(Settings.PAY_URL + "amount=" + amount);
        //Log.e("pay_url",Settings.PAY_URL + "amount=" +amount);
       // Log.e("payment_url",Session.PAYMENT_URL + "member_id=" + Session.GetUserId(this) + "&amount=" + amount);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        progress.setProgress(0);

    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void send_message(String toast,Boolean success) {
            Log.e("toast",toast);


            if(toast.equals("success")){
                Intent intent = new Intent();
                intent.putExtra("message",toast);
                Log.e("message",toast);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }

            else {
                Log.e("tost", toast);
                if (toast.equals("failure")){
                    Intent intent = new Intent();
                    intent.putExtra("message",toast);
                    Log.e("message",toast);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        }
    }


    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            PaymentPage.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);

        }
    }




    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }
}



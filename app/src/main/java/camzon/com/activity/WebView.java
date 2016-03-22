package camzon.com.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import camzon.com.R;

public class WebView extends AppCompatActivity {
    String url = "";
    ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final android.webkit.WebView webView = (android.webkit.WebView) findViewById(R.id.webView);
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        mProgress = ProgressDialog.show(this, "Loading",
                "Please wait for a moment...");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
                if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }
            }
        });
    }
}

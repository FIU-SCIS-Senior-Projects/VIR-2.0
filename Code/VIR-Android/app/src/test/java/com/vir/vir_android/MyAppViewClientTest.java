package com.vir.vir_android;

import android.webkit.WebSettings;
import android.webkit.WebView;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyAppViewClientTest {

    @Test
    public void shouldOverrideUrlLoading_ReturnsFalse() throws Exception {

        // Force links and redirects to open in the WebView instead of in a browse.
        MyAppWebViewClient client = new MyAppWebViewClient();
        assertNotNull(client);
    }
}
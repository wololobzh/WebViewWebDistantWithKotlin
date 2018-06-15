package fr.acos.webviewwebdistantwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Classe de l'activité pour tester les WebViews.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Chargement d'une page web distante.
        ma_webview.loadUrl("https://www.wikipedia.org")

        //Activation du javaScript.
        ma_webview.settings.javaScriptEnabled = true

        //Pour rester sur l'application lors d'un clic sur un lien.
        ma_webview.webViewClient = object : WebViewClient()
        {
            //Lors d'un clic sur un lien.
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean
            {
                //Le lien est chargé au sein de la WebView
                view!!.loadUrl(request!!.url.toString())
                return true;
            }
        }
    }

    /**
     * Modification du comportement du bouton BACK.
     * pour ne pas quitter l'application mais
     * pour retourner sur la page web precedente.
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean
    {
        //Si l'utilisateur clique sur le bouton bach et
        //qu'il est possible de retourner sur une page web precedente
        //alors on retourne sur la page web precedente
        if(keyCode == KeyEvent.KEYCODE_BACK && ma_webview.canGoBack())
        {
            ma_webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

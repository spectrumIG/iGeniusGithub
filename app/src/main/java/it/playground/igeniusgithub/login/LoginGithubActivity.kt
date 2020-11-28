package it.playground.igeniusgithub.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import it.playground.igeniusgithub.GithubApplication
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.ActivityLoginGithubBinding
import it.playground.igeniusgithub.di.viewModels
import javax.inject.Inject
import javax.inject.Provider

class LoginGithubActivity : AppCompatActivity(R.layout.activity_login_github) {
    @Inject
    lateinit var viewModelProvider: Provider<LoginViewModel>

    private val loginViewModel by viewModels { viewModelProvider }

    @SuppressLint("SetJavaScriptEnabled") // <- I know, I know...
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as GithubApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        val bind = ActivityLoginGithubBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val loginWebview = bind.loginWebview
        loginWebview.settings.javaScriptEnabled = true
        loginWebview.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request!!.url.toString()
                try {
                    if(url.contains("?code=")) {
                        val code = url.substring(url.lastIndexOf("?code=") + 1).split("=").toTypedArray()[1]
                        loginViewModel.retrieveAndSaveOAuthTokenForApi(code)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return false
            }
        }

        loginViewModel.url.observe(this, { result ->
            loginWebview.loadUrl(result)
        })
        loginViewModel.authCodeSuccessful.observe(this, { success ->
            if(success) {

            }else{

            }
        })

    }
}
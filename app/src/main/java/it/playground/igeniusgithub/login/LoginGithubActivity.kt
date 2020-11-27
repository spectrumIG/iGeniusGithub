package it.playground.igeniusgithub.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.playground.igeniusgithub.R
import it.playground.igeniusgithub.databinding.ActivityLoginGithubBinding

class LoginGithubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityLoginGithubBinding.inflate(layoutInflater)
        setContentView(bind.root)

    }
}
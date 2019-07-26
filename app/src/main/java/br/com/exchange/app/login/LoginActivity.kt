package br.com.exchange.app.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.exchange.R
import br.com.exchange.app.base.BaseActivity
import br.com.exchange.app.exchange.ExchangeActivity
import br.com.exchange.databinding.ActivityLoginBinding
import org.jetbrains.anko.intentFor

class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login) as ActivityLoginBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        binding.viewModel = viewModel
        binding.btnLogin.setOnClickListener {
            startActivity(intentFor<ExchangeActivity>())
        }

    }

}
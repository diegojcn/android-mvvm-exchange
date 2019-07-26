package br.com.exchange.app.base

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.exchange.app.hideKeyboard
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import org.jetbrains.anko.findOptional
import org.koin.core.KoinComponent

abstract class BaseActivity : AppCompatActivity(), KoinComponent {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        hideKeyboard()
        return super.onTouchEvent(event)
    }
}
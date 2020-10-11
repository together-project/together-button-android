package com.github.togetherproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.togetherproject.button.module.setup.TogetherButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val togetherButton = TogetherButton(this)

        btnHelpMe.setOnClickListener {
            togetherButton.show()
        }
    }
}
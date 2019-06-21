package com.denisa.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denisa.bookapp.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.fragments.isEmpty())
            supportFragmentManager
                .beginTransaction()
                .add(R.id.mainContent, LoginFragment())
                .commit()
    }

}

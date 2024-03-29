package com.denisa.bookapp.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.denisa.bookapp.MainViewModel
import com.denisa.bookapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class DashboardFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    val TAG = this::class.java.name


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    private fun showToast(message: String) {
        super.onResume()

    }

}
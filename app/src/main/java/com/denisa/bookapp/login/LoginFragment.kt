package com.denisa.bookapp.login

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
import com.denisa.bookapp.dashboard.DashboardFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    private lateinit var auth: FirebaseAuth

    val TAG = this::class.java.name


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        Log.i(TAG,"Current user ${currentUser?.displayName}")

        if (currentUser == null) {
            btLogin.text = getString(R.string.register)
            btLogin.setOnClickListener { registerUser() }
        } else {
            btLogin.text = getString(R.string.sing_button)
            btLogin.setOnClickListener { loginUser() }
        }

        goToDashboard()
    }

    private fun loginUser() {
        if (inputFieldsValidated()) return

        auth.signInWithEmailAndPassword(editUsername.text.toString().trim(), editPassword.text.toString().trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG,"Signed in user ${auth.currentUser!!.uid} ${auth.currentUser!!.email}")
                    goToDashboard()
                } else {
                    Log.i(TAG,"sign in failed")
                    showToast(String.format(getString(R.string.sign_in_error_msg), task.exception?.message))
                }
            }
    }

    private fun goToDashboard() {
        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.mainContent, DashboardFragment())
            ?.commit()
    }

    private fun registerUser() {
        if (inputFieldsValidated()) return

        auth.createUserWithEmailAndPassword(editUsername.text.toString().trim(), editPassword.text.toString().trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG,"Current user ${auth.currentUser!!.uid}")
                    goToDashboard()
                } else {
                    Log.i(TAG,"create user failed")
                    showToast(String.format(getString(R.string.create_fail_msg),task.exception?.message))
                }
            }
    }

    private fun inputFieldsValidated(): Boolean {
        if (editUsername.text.isNullOrBlank()) {
            showToast(getString(R.string.empty_username_msg))
            return true
        }
        if (editPassword.text.isNullOrBlank()) {
            showToast(getString(R.string.empty_password_msg))
            return true
        }
        return false
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}
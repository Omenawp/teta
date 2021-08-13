package com.oelrun.teta.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.oelrun.teta.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var loginActions: LoginActions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(loginActions?.checkAuth() == true) {
            this.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToProfileFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            loginActions?.login()
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginActions){
            loginActions = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        loginActions = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface LoginActions {
    fun checkAuth(): Boolean
    fun login()
}
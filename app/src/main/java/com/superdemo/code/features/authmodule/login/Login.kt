package com.superdemo.code.features.authmodule.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quadrish.flexline.R
import com.quadrish.flexline.databinding.FragmentLoginBinding
import com.superdemo.code.core.utils.setOnSingleClickListener
import com.superdemo.code.core.utils.startDestination
import com.superdemo.code.features.mainmodule.MainParent

class Login : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        binding.btnSignin.setOnSingleClickListener {
            activity!!.startDestination(MainParent())
        }

        binding.tvRegister.setOnSingleClickListener {
            findNavController().navigate(R.id.register)
        }

        return binding.root
    }

}
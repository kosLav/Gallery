package com.example.gallery.main.presentetion

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gallery.R
import com.example.gallery.main.domain.model.TokenStorage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tokenStorage = TokenStorage(context!!)

        Handler(Looper.getMainLooper()).postDelayed({
            if (tokenStorage.fetchAuthToken() != null){
                findNavController().navigate(R.id.action_splashFragment2_to_mainActivity)
            } else {
                findNavController().navigate(R.id.action_splashFragment2_to_authFragment)
            }
            },DELAY_SPLASH_SCREEN)
    }

    companion object {
        private const val DELAY_SPLASH_SCREEN = 800L
    }
}
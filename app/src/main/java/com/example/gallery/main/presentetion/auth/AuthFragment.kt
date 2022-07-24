package com.example.gallery.main.presentetion.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gallery.R
import com.example.gallery.databinding.FragmentAuthBinding
import com.example.gallery.main.utils.LoadState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoginError()
        observePasswordError()
        observeLoadState()

        binding.authBtn.setOnClickListener {
            viewModel.setLogin(binding.loginEditText.unMasked)
            viewModel.auth()
        }

        binding.passwordEditText.doOnTextChanged { password, _, _, _ ->
            viewModel.setPassword(password.toString())
        }
    }

    private fun observeLoginError() {
        viewModel.mutableLoginError.observe(viewLifecycleOwner) { loginError ->
            when (loginError) {
                LoginError.EMPTY -> {
                    binding.tiLogin.error = getString(R.string.login_empty_error)
                }
                LoginError.NOT_VALID -> {
                    binding.tiLogin.error = getString(R.string.login_not_valid_error)
                }
                LoginError.VALID -> {
                    binding.tiLogin.error = null
                }
                else -> {

                }
            }

        }
    }

    private fun observePasswordError() {
        viewModel.mutablePasswordError.observe(viewLifecycleOwner) { passwordError ->
            when (passwordError) {
                PasswordError.EMPTY -> {
                    binding.tiPassword.error = getString(R.string.password_empty_error)
                }
                PasswordError.NOT_VALID -> {
                    binding.tiPassword.error = getString(R.string.password_not_valid_error)
                }
                PasswordError.VALID -> {
                    binding.tiPassword.error = null
                }
                else -> {

                }
            }

        }
    }

    private fun observeLoadState() {
        viewModel.loadState.observe(viewLifecycleOwner) { loadState ->
            when (loadState) {
                LoadState.LOADING -> {
                    binding.authBtn.isLoading = true
                    binding.blockContentContainer.isVisible = true
                }
                LoadState.ERROR -> {
                    binding.authBtn.isLoading = false
                    binding.blockContentContainer.isVisible = false
                    Snackbar.make(
                        binding.root,
                        getString(R.string.auth_error_text),
                        Snackbar.LENGTH_LONG
                    ).setAnchorView(binding.authBtn).show()
                }
                LoadState.SUCCESS -> {
                    binding.authBtn.isLoading = false
                    binding.blockContentContainer.isVisible = false
                    findNavController().navigate(R.id.action_authFragment_to_mainFragment)
                }
                else -> {

                }
            }
        }
    }
}
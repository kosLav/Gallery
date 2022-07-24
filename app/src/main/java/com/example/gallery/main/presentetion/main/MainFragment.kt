package com.example.gallery.main.presentetion.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.gallery.R
import com.example.gallery.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(HomeFragment())
        binding.bottomBarNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_navBar_home -> loadFragment(HomeFragment())
                R.id.bottom_navBar_favorites -> loadFragment(FavoritFragment())
                R.id.bottom_navBar_profile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private  fun loadFragment(fragment: Fragment){
        if (fragment != null) {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container,fragment)
            transaction?.commit()
        }
    }
}
package com.example.intrestingfactsaboutnumber_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.intrestingfactsaboutnumber_kotlin.R
import com.example.intrestingfactsaboutnumber_kotlin.databinding.FragmentStoreFactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFactFragment : Fragment() {
    private lateinit var binding: FragmentStoreFactBinding
    private val args: StoreFactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreFactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply{

            numberFromFactView.text = args.number.toString()
            factStoreView.text = args.fact

            toMaim.setOnClickListener{
                val direction = StoreFactFragmentDirections
                    .actionStoreFactFragmentToMainFragment()
                findNavController().navigate(direction)
            }
        }
    }
}
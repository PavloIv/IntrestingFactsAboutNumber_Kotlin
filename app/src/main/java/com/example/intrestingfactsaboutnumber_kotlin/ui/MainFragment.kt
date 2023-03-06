package com.example.intrestingfactsaboutnumber_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intrestingfactsaboutnumber_kotlin.R
import com.example.intrestingfactsaboutnumber_kotlin.adapter.FactsAdapter
import com.example.intrestingfactsaboutnumber_kotlin.databinding.FragmentMainBinding
import com.example.intrestingfactsaboutnumber_kotlin.viewmodel.DatabaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {


    lateinit var binding: FragmentMainBinding

    private val viewModel: DatabaseViewModel by viewModels()

    @Inject
    lateinit var factsAdapter: FactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getRandomNumberFact.setOnClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToGetRandomFactFragment()
            findNavController().navigate(direction)
        }

        binding.getFact.setOnClickListener {
            val number = binding.userNumber.text.toString()
            if (number.isNotEmpty()) {
                val direction = MainFragmentDirections
                    .actionMainFragmentToGetFactFragment(Integer.parseInt(number))
                findNavController().navigate(direction)
            }else{
                Snackbar.make(it,"Please, write the number about which you want to get a fact"
                    , Snackbar.LENGTH_LONG).show()
            }
        }


        binding.apply {

            viewModel.getAllFacts()

            viewModel.factsList.observe(viewLifecycleOwner) {

                factsAdapter.differ.submitList(it)

                rvFactsList.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = factsAdapter
                }

                factsAdapter.setOnItemClickListener {
                    val direction = MainFragmentDirections
                        .actionMainFragmentToStoreFactFragment(it.number,it.fact)
                    findNavController().navigate(direction)
                }
            }
        }
    }
}
package com.example.intrestingfactsaboutnumber_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.intrestingfactsaboutnumber_kotlin.R
import com.example.intrestingfactsaboutnumber_kotlin.databinding.FragmentGetFactBinding
import com.example.intrestingfactsaboutnumber_kotlin.db.FactsEntity
import com.example.intrestingfactsaboutnumber_kotlin.viewmodel.DatabaseViewModel
import com.example.intrestingfactsaboutnumber_kotlin.viewmodel.FactViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GetFactFragment : Fragment() {
    private lateinit var binding: FragmentGetFactBinding
    private val viewModel: FactViewModel by viewModels()
    private val args: GetFactFragmentArgs by navArgs()

    private val dbViewModel: DatabaseViewModel by viewModels()

    @Inject
    lateinit var entity: FactsEntity

    private var factId = 0
    private var number = 0
    private var fact = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetFactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadNumberFact(args.number)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply{
            viewModel.getNumberFact.observe(viewLifecycleOwner){
                    response ->
                number = response.number
                fact = response.text
                numberFromFactView.text = number.toString()
                factStoreView.text = fact

                if (fact.isNotEmpty()){
                    entity.id = factId
                    entity.number = number
                    entity.fact = fact

                    dbViewModel.saveFact(entity)}
            }

            toMaim.setOnClickListener{
                val direction = GetFactFragmentDirections
                    .actionGetFactFragmentToMainFragment()
                findNavController().navigate(direction)
            }
        }
    }
}
package com.example.myapplication

import MyViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: MyViewModel by viewModels()
    private val tab_calendrier: MutableList<Pair<String, String>> = mutableListOf()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.savedStateHandle.getLiveData<String>("heure_key")
            .observe(viewLifecycleOwner, Observer { heure ->
                heure?.let {
                    tab_calendrier.add(it to (viewModel.savedStateHandle.get<String>("nom_key") ?: ""))
                    Log.v("heure", it)
                    updateTable()
                }
            })

        viewModel.savedStateHandle.getLiveData<String>("nom_key")
            .observe(viewLifecycleOwner, Observer { nom ->
                nom?.let {
                    tab_calendrier.add((viewModel.savedStateHandle.get<String>("heure_key") ?: "") to it)
                    Log.v("nom", it)
                    updateTable()
                }
            })
    }

    private fun updateTable() {
        Log.v("tableau", tab_calendrier.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val tab_calendrier: MutableList<Triple<String, String, String>> = mutableListOf()

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
        val jourArg=arguments?.getString("jour")
        val heureArg = arguments?.getString("heure")
        val nomArg = arguments?.getString("nom")
        if (jourArg != null && heureArg != null && nomArg != null &&jourArg != "" && heureArg != "" && nomArg != "") {
            tab_calendrier.add(Triple(jourArg, heureArg, nomArg))
            addNewTextView(jourArg,heureArg,nomArg)
            Log.v("test",tab_calendrier.toString())
        }
        setupViews()
    }

    private fun setupViews() {
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
    private fun addNewTextView(jour : String, heure : String, nom : String) {
        val newTextView = TextView(context)
        "évènement : $nom $jour à $heure".also { newTextView.text = it }
        newTextView.id = View.generateViewId()

        // Récupérer le ConstraintLayout
        val constraintLayout = view?.findViewById<ConstraintLayout>(R.id.constraintLayout)

        constraintLayout?.addView(newTextView)
        val params = newTextView.layoutParams as ConstraintLayout.LayoutParams
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        params.topToBottom = binding.buttonFirst.id
        newTextView.layoutParams = params

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


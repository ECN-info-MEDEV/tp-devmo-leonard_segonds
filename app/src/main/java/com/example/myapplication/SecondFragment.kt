package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            // Récupérer les données des champs de saisie
            val spinner1 = binding.spinnerJour
            val jour =spinner1.selectedItem.toString()
            val spinner2 = binding.spinnerHeure
            val heure =spinner2.selectedItem.toString()
            val nom = binding.editTextNom.text.toString()

            // Créer une instance de l'action de navigation avec les arguments
            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment(jour,heure, nom)

            // Naviguer vers FirstFragment en passant l'action avec les arguments
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

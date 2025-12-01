package com.example.appmovil.ui.theme.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appmovil.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUserData()

        binding.btnSave.setOnClickListener {
            val updatedUser = User(
                name = binding.etName.text.toString(),
                email = binding.etEmail.text.toString(),
                address = binding.etAddress.text.toString(),
                phone = binding.etPhone.text.toString()
            )
            UserPrefs.saveUser(requireContext(), updatedUser)
            Toast.makeText(requireContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show()
        }

        // -------------- BOTÓN DE REGRESO ------------------
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        // --------------------------------------------------
    }

    private fun loadUserData() {
        val user = UserPrefs.getUser(requireContext())
        binding.etName.setText(user.name)
        binding.etEmail.setText(user.email)
        binding.etAddress.setText(user.address)
        binding.etPhone.setText(user.phone)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

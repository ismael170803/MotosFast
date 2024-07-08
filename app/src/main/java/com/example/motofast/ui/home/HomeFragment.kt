package com.example.motofast.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motofast.Products
import com.example.motofast.R
import com.example.motofast.TuAdapter
import com.example.motofast.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    private val tuCollection = db.collection("Products")
    private lateinit var adapter: TuAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        binding.rDatos.layoutManager = LinearLayoutManager(requireContext())
        adapter = TuAdapter()
        binding.rDatos.adapter = adapter

        // Configurar botón de consulta
        binding.btnConsultar.setOnClickListener {
            consultarColeccion()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun consultarColeccion() {
        tuCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val listaTuModelo = mutableListOf<Products>()
                for (document in querySnapshot) {
                    val nombre = document.getString("Nombre")
                    val precio = document.getLong("Precio")?.toInt()
                    val img = document.getString("url").toString()
                    val ID = document.id
                    if (nombre != null && precio != null) {
                        val tuModelo = Products(ID, nombre, precio, img)
                        listaTuModelo.add(tuModelo)
                    }
                }
                adapter.setDatos(listaTuModelo)
            }
    }
}
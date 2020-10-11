package com.github.togetherproject.button.module.help_services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.togetherproject.button.databinding.FragmentHelpServicesBinding
import com.github.togetherproject.button.model.Services

class HelpServicesFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHelpServicesBinding
    private lateinit var servicesRecycler: RecyclerView
    private var servicesAdapter = HelpServicesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpServicesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        servicesRecycler = binding.recyclerServices
        servicesRecycler.layoutManager = LinearLayoutManager(this@HelpServicesFragment.context)
        servicesRecycler.adapter = servicesAdapter

        val serv1 = Services(
            "Casa da mulher brasileira",
            "Av. Paraná, 870, Cabral, Curitiba",
            "(41) 3221-2710"
        )

        val serv2 = Services(
            "Delegacia da Mulher e do Adolescente",
            "Rua Julio Meneguetti, 195 - Jardim Novo Horizonte, Maringa",
            "(44) 3220-2500"
        )

        val serv3 = Services(
            "Departamento de Defesa da Mulher",
            "Rua da fonte, Matinhos",
            "(41) 3971-6270"
        )

        val serv4 = Services(
            "Centro Referência em Atendimento à Mulher em Situação de Violência",
            "Rua Padre Bernardo Plate, 1250, Jardim Polo Centro, Foz do Iguaçu",
            "0800 643 8111"
        )

        val serv5 = Services(
            "Secretaria da Mulher e Assuntos da Família",
            "Rua Castro Alves, 335, Jardim Ponta Grossa, Apucarana",
            "(43) 3422-4479"
        )

        val serv6 = Services(
            "Delegacia da Mulher e do Adolescente de São José dos Pinhais",
            "Av. Sen. Souza Naves, 484, Centro, São José dos Pinhais",
            "(41) 3753-2050"
        )

        val serv7 = Services(
            "Delegacia da Mulher",
            "Rua da Abolição, 538, Zona 1, Cianorte",
            "(44) 3631-2169"
        )

        val serv8 = Services(
            "Delegacia da Mulher",
            "Rua XV de Novembro, 909, Centro, Ponta Grossa",
            "(42) 3309-1300"
        )

        val services = arrayListOf(serv1, serv2, serv3, serv4, serv5, serv6, serv7, serv8)
        servicesAdapter.setList(services)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}
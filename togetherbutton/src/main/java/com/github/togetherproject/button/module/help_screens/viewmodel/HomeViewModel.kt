package com.github.togetherproject.button.module.help_screens.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.togetherproject.button.model.Services
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel: ViewModel() {

    val viewLiveData: MutableLiveData<ViewState> = MutableLiveData()

    sealed class ViewState {
        object LoadError: ViewState()
        class LoadSuccess(val services: List<Services>): ViewState()
    }

    fun getServicesList() = viewModelScope.launch {
        try {
            val services = getList()
            viewLiveData.postValue(ViewState.LoadSuccess(services))
        } catch (e: Exception) {
            e.message?.let { Log.e("ERROR", it) }
            viewLiveData.postValue(ViewState.LoadError)
        }
    }

    private fun getList(): List<Services> {
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

        return listOf(serv1, serv2, serv3, serv4, serv5, serv6, serv7, serv8)
    }

    fun call(num: String, context: Context) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:${num}")
        context.startActivity(intent)
    }

}
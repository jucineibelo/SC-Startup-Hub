package com.scstartup.core.gateway

import com.scstartup.core.domain.Empreendimento

interface EmpreendimentoGateway {
    fun salvarEmpreendimento(empreendimento: Empreendimento): Empreendimento
    fun buscarEmpreendimentoPorId(id: Long): Empreendimento?
    fun atualizarEmpreendimento(empreendimento: Empreendimento): Empreendimento
    fun deletarEmpreendimento(id: Long)
    fun listarEmpreendimentos(): List<Empreendimento>
}

//crud
package com.scstartup.core.usecase

import com.scstartup.core.domain.Empreendimento

interface EmpreendimentoUseCase {
    fun salvarEmpreendimento(empreendimento: Empreendimento): Empreendimento
    fun buscarEmpreendimentoPorId(id: Long): Empreendimento?
    fun atualizarEmpreendimento(empreendimento: Empreendimento): Empreendimento
    fun deletarEmpreendimento(id: Long)
    fun listarEmpreendimentos(): List<Empreendimento>
}

//lógica de negócios, regras de negócio, validações, etc.
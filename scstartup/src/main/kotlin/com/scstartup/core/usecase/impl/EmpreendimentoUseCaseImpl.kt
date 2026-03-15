package com.scstartup.core.usecase.impl

import com.scstartup.core.domain.Empreendimento
import com.scstartup.core.gateway.EmpreendimentoGateway
import com.scstartup.core.usecase.EmpreendimentoUseCase

class EmpreendimentoUseCaseImpl(private val empreendimentoGateway: EmpreendimentoGateway) : EmpreendimentoUseCase {

    override fun salvarEmpreendimento(empreendimento: Empreendimento): Empreendimento {
        validarCampos(empreendimento)
        return empreendimentoGateway.salvarEmpreendimento(empreendimento)
    }

    override fun buscarEmpreendimentoPorId(id: Long): Empreendimento? {
        validarId(id)
        val empreendimento = empreendimentoGateway.buscarEmpreendimentoPorId(id)

        if (empreendimento == null) {
            throw RuntimeException("Empreendimento não encontrado")
        }

        return empreendimento
    }

    override fun atualizarEmpreendimento(empreendimento: Empreendimento): Empreendimento {

        if (empreendimento.id == null) {
            throw IllegalArgumentException("ID é obrigatório para atualização")
        }

        val existente = empreendimentoGateway.buscarEmpreendimentoPorId(empreendimento.id)
            ?: throw RuntimeException("Empreendimento não encontrado")


        if (empreendimento.nome.isNotBlank()) {
            existente.nome = empreendimento.nome
        }

        if (empreendimento.empreendedor.isNotBlank()) {
            existente.empreendedor = empreendimento.empreendedor
        }

        if (empreendimento.municipio.isNotBlank()) {
            existente.municipio = empreendimento.municipio
        }

        if (empreendimento.contato.isNotBlank()) {
            existente.contato = empreendimento.contato
        }

        return empreendimentoGateway.atualizarEmpreendimento(existente)
    }

    override fun deletarEmpreendimento(id: Long) {

        validarId(id)
        val existente = empreendimentoGateway.buscarEmpreendimentoPorId(id)

        if (existente == null) {
            throw RuntimeException("Empreendimento não encontrado")
        }

        empreendimentoGateway.deletarEmpreendimento(id)
    }

    override fun listarEmpreendimentos(): List<Empreendimento> {
        return empreendimentoGateway.listarEmpreendimentos()
    }


    private fun validarCampos(empreendimento: Empreendimento) {

        if (empreendimento.nome.isBlank()) {
            throw IllegalArgumentException("Nome do empreendimento é obrigatório")
        }

        if (empreendimento.empreendedor.isBlank()) {
            throw IllegalArgumentException("Nome do empreendedor é obrigatório")
        }

        if (empreendimento.municipio.isBlank()) {
            throw IllegalArgumentException("Município é obrigatório")
        }

        if (empreendimento.contato.isBlank()) {
            throw IllegalArgumentException("Contato é obrigatório")
        }
    }

    private fun validarId(id: Long) {
        if (id <= 0) {
            throw IllegalArgumentException("ID inválido")
        }
    }

}

//implementei a interface EmpreendimentoUseCase, mas ainda não implementei os métodos. O próximo passo seria implementar a lógica de negócios para cada um desses métodos, utilizando o empreendimentoGateway para acessar os dados.

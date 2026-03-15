package com.scstartup.infrastructure.adapter

import com.scstartup.core.domain.Empreendimento
import com.scstartup.core.gateway.EmpreendimentoGateway
import com.scstartup.infrastructure.database.entity.EmpreendimentoH2Entity
import com.scstartup.infrastructure.database.repository.EmpreendimentoRepository
import com.scstartup.infrastructure.mapper.EmpreendimentoEntityMapper
import org.springframework.stereotype.Component

@Component
class EmpreendimentoPersistenceAdapter(
    private val empreendimentoRepository: EmpreendimentoRepository,
    private val mapper: EmpreendimentoEntityMapper
) : EmpreendimentoGateway {

    override fun salvarEmpreendimento(empreendimento: Empreendimento): Empreendimento {
        val entity: EmpreendimentoH2Entity = mapper.toEntity(empreendimento)
        val saved = empreendimentoRepository.save(entity)
        return mapper.toDomain(saved)
    }

    override fun buscarEmpreendimentoPorId(id: Long): Empreendimento? {
        val entityOpt = empreendimentoRepository.findById(id)
        return entityOpt.map { mapper.toDomain(it) }.orElse(null)
    }

    override fun atualizarEmpreendimento(empreendimento: Empreendimento): Empreendimento {
        val existente = empreendimentoRepository.findById(empreendimento.id!!)
            .orElseThrow { RuntimeException("Empreendimento não encontrado") }

        if (empreendimento.nome.isNotBlank()) existente.nome = empreendimento.nome
        if (empreendimento.empreendedor.isNotBlank()) existente.empreendedor = empreendimento.empreendedor
        if (empreendimento.municipio.isNotBlank()) existente.municipio = empreendimento.municipio
        if (empreendimento.contato.isNotBlank()) existente.contato = empreendimento.contato
        existente.segmento = empreendimento.segmento.name
        existente.status = empreendimento.status.name

        val updated = empreendimentoRepository.save(existente)
        return mapper.toDomain(updated)
    }

    override fun deletarEmpreendimento(id: Long) {
        empreendimentoRepository.deleteById(id)
    }

    override fun listarEmpreendimentos(): List<Empreendimento> {
        return empreendimentoRepository.findAll().map { mapper.toDomain(it) }
    }
}
package com.scstartup.infrastructure.mapper

import com.scstartup.core.domain.Empreendimento
import com.scstartup.infrastructure.database.entity.EmpreendimentoH2Entity
import org.springframework.stereotype.Component

@Component
class EmpreendimentoEntityMapper {

    fun toDomain(entity: EmpreendimentoH2Entity): Empreendimento {
        return Empreendimento(
            id = entity.id,
            nome = entity.nome,
            empreendedor = entity.empreendedor,
            municipio = entity.municipio,
            segmento = entity.segmento,
            contato = entity.contato,
            status = entity.status
        )
    }

    fun toEntity(domain: Empreendimento): EmpreendimentoH2Entity {
        return EmpreendimentoH2Entity(
            id = domain.id,
            nome = domain.nome,
            empreendedor = domain.empreendedor,
            municipio = domain.municipio,
            segmento = domain.segmento ?: throw IllegalStateException("Segmento não pode ser nulo ao salvar"),
            contato = domain.contato,
            status = domain.status ?: throw IllegalStateException("Status não pode ser nulo ao salvar")
        )
    }
}
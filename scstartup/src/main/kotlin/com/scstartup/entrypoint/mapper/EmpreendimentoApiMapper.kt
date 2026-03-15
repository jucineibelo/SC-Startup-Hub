package com.scstartup.entrypoint.mapper

import com.scstartup.core.domain.Empreendimento
import com.scstartup.entrypoint.dto.EmpreendimentoCreateRequest
import com.scstartup.entrypoint.dto.EmpreendimentoResponse
import com.scstartup.entrypoint.dto.EmpreendimentoUpdateRequest
import org.springframework.stereotype.Component

@Component
class EmpreendimentoApiMapper {

    fun toDomain(request: EmpreendimentoCreateRequest): Empreendimento =
        Empreendimento(
            id = null,
            nome = request.nome,
            empreendedor = request.empreendedor,
            municipio = request.municipio,
            segmento = request.segmento,
            contato = request.contato,
            status = request.status
        )


    fun toDomain(request: EmpreendimentoUpdateRequest, id: Long): Empreendimento =
        Empreendimento(
            id = id,
            nome = request.nome ?: "",
            empreendedor = request.empreendedor ?: "",
            municipio = request.municipio ?: "",
            segmento = request.segmento, // pode ser null
            contato = request.contato ?: "",
            status = request.status // pode ser null
        )


    fun toResponse(empreendimento: Empreendimento): EmpreendimentoResponse = EmpreendimentoResponse(
        id = empreendimento.id!!,
        nome = empreendimento.nome,
        empreendedor = empreendimento.empreendedor,
        municipio = empreendimento.municipio,
        segmento = requireNotNull(empreendimento.segmento) { "Segmento não pode ser nulo na resposta" },
        contato = empreendimento.contato,
        status = requireNotNull(empreendimento.status) { "Status não pode ser nulo na resposta" }
    )
}
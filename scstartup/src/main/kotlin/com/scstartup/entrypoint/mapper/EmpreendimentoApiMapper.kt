package com.scstartup.entrypoint.mapper

import com.scstartup.core.domain.Empreendimento
import com.scstartup.core.enums.Segmento
import com.scstartup.core.enums.Status
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
            segmento = Segmento.valueOf(request.segmento),
            contato = request.contato,
            status = if (request.status) Status.ATIVO else Status.INATIVO
        )


    fun toDomain(request: EmpreendimentoUpdateRequest, id: Long): Empreendimento =
        Empreendimento(
            id = id,
            nome = request.nome ?: "",
            empreendedor = request.empreendedor ?: "",
            municipio = request.municipio ?: "",
            segmento = if (request.segmento != null) Segmento.valueOf(request.segmento) else Segmento.TECNOLOGIA, // valor default
            contato = request.contato ?: "",
            status = if (request.status != null) {
                if (request.status) Status.ATIVO else Status.INATIVO
            } else Status.ATIVO
        )


    fun toResponse(empreendimento: Empreendimento): EmpreendimentoResponse =
        EmpreendimentoResponse(
            id = empreendimento.id!!,
            nome = empreendimento.nome,
            empreendedor = empreendimento.empreendedor,
            municipio = empreendimento.municipio,
            segmento = empreendimento.segmento.name,
            contato = empreendimento.contato,
            status = empreendimento.status == Status.ATIVO
        )
}
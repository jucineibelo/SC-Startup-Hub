package com.scstartup.entrypoint.controller

import com.scstartup.core.usecase.EmpreendimentoUseCase
import com.scstartup.entrypoint.dto.EmpreendimentoCreateRequest
import com.scstartup.entrypoint.dto.EmpreendimentoResponse
import com.scstartup.entrypoint.dto.EmpreendimentoUpdateRequest
import com.scstartup.entrypoint.mapper.EmpreendimentoApiMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/empreendimentos")
class EmpreendimentoController(
    private val useCase: EmpreendimentoUseCase,
    private val mapper: EmpreendimentoApiMapper
) {

    @PostMapping
    fun criar(@RequestBody request: EmpreendimentoCreateRequest): ResponseEntity<EmpreendimentoResponse> {
        val salvo = useCase.salvarEmpreendimento(mapper.toDomain(request))
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(salvo))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<EmpreendimentoResponse> {
        val dominio = useCase.buscarEmpreendimentoPorId(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(mapper.toResponse(dominio))
    }

    @GetMapping
    fun listar(): ResponseEntity<List<EmpreendimentoResponse>> {
        val lista = useCase.listarEmpreendimentos().map { mapper.toResponse(it) }
        return ResponseEntity.ok(lista)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: Long,
        @RequestBody request: EmpreendimentoUpdateRequest
    ): ResponseEntity<EmpreendimentoResponse> {
        val atualizado = useCase.atualizarEmpreendimento(mapper.toDomain(request, id))
        return ResponseEntity.ok(mapper.toResponse(atualizado))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Void> {
        useCase.deletarEmpreendimento(id)
        return ResponseEntity.noContent().build()
    }
}
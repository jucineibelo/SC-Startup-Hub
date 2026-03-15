package com.scstartup.config

import com.scstartup.core.gateway.EmpreendimentoGateway
import com.scstartup.core.usecase.EmpreendimentoUseCase
import com.scstartup.core.usecase.impl.EmpreendimentoUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {

    @Bean
    fun empreendimentoUseCase(empreendimentoGateway: EmpreendimentoGateway): EmpreendimentoUseCase {
        return EmpreendimentoUseCaseImpl(empreendimentoGateway)
    }
}
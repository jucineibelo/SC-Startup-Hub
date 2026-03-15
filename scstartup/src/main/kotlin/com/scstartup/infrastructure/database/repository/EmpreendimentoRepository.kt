package com.scstartup.infrastructure.database.repository

import com.scstartup.infrastructure.database.entity.EmpreendimentoH2Entity
import org.springframework.data.jpa.repository.JpaRepository

interface EmpreendimentoRepository : JpaRepository<EmpreendimentoH2Entity, Long>
# Desafio Prático - Curso de IA para Devs

## Descrição

Este projeto é um backend desenvolvido como parte do desafio prático do curso de IA para Devs. O objetivo é gerenciar empreendimentos, permitindo operações de cadastro, consulta, atualização e remoção, utilizando arquitetura Clean, Kotlin, Spring Boot e banco de dados H2.

## Tecnologias Utilizadas

- **Linguagem:** Kotlin
- **Framework:** Spring Boot
- **Banco de Dados:** H2 (em memória)
- **Arquitetura:** Clean Architecture

## Como Executar o Projeto

1. **Pré-requisitos:**
   - Java 21+
   - Gradle (ou utilize o wrapper `./gradlew`)
2. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd scstartup
   ```
3. **Execute o projeto:**
   ```bash
   ./gradlew bootRun
   ```
   Ou, no Windows:
   ```cmd
   gradlew.bat bootRun
   ```
4. **Acesse a aplicação:**
   - API: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Usuário: `sa`
     - Senha: (em branco)

## Endpoints Principais

### Criar Empreendimento

`POST /empreendimentos`

```json
{
  "nome": "Startup X",
  "empreendedor": "João Silva",
  "municipio": "São Paulo",
  "segmento": "TECNOLOGIA",
  "contato": "joao@email.com",
  "status": true
}
```

### Buscar por ID

`GET /empreendimentos/{id}`

### Listar todos

`GET /empreendimentos`

### Atualizar Empreendimento

`PATCH /empreendimentos/{id}`

```json
{
  "nome": "Startup X Atualizada",
  "segmento": "SERVICOS",
  "status": false
}
```

### Deletar Empreendimento

`DELETE /empreendimentos/{id}`

## Estrutura do Projeto

- `src/main/kotlin/com/scstartup/core`: Domínio e casos de uso
- `src/main/kotlin/com/scstartup/entrypoint`: Controllers, DTOs e mappers de API
- `src/main/kotlin/com/scstartup/infrastructure`: Persistência, entidades e mappers de banco
- `src/main/resources`: Configurações e scripts

## Enumerações

- **Segmento:** TECNOLOGIA, COMERCIO, INDUSTRIA, SERVICOS, AGRONEGOCIO
- **Status:** ATIVO (true), INATIVO (false)

## Contato

Jucinei Luiz Belo - jucineibelo@gmail.com

Video da explicação do projeto e teste: https://www.youtube.com/watch?v=JtjRXvPjCrM

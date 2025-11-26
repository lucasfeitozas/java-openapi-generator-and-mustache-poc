# OpenAPI Generator POC (Java 21) 🚀

[![Java](https://img.shields.io/badge/Java-21-orange)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen)](https://spring.io/projects/spring-boot)
[![OpenAPI Generator](https://img.shields.io/badge/OpenAPI%20Generator-7.5.0-blue)](https://openapi-generator.tech/)
[![Build](https://img.shields.io/badge/Maven-clean%20verify-lightgrey)](pom.xml)

POC para demonstrar geração de SDKs e stubs de servidor Java 21 usando [OpenAPI Generator](https://openapi-generator.tech/) integrado ao Maven, expondo as operações via Spring Boot 3.3 e adicionando código com templates Mustache.

### 📌 Índice
- [Visão geral](#visão-geral)
- [Estrutura](#estrutura)
- [Pré-requisitos](#pré-requisitos)
- [Fluxo de geração](#fluxo-de-geração)
- [Rodando e testando a API](#rodando-e-testando-a-api)
- [SDK Java 21 (`openapi-sdk`)](#sdk-java-21-openapi-sdk)
- [Mustache / código adicional](#mustache--código-adicional)
- [Dicas/Próximos passos](#dicaspróximos-passos)

## Visão geral

- 💡 OpenAPI 3 → gera interfaces Spring e SDK RestTemplate.
- ⚙️ Spring Boot 3.3, Java 21, Maven multi-módulo.
- 🧩 Mustache para inserir código utilitário (ex.: logging).
- ✅ Build único: `mvn clean verify` orquestra tudo.

## Estrutura

```
open-api-gen-poc
├── openapi/                  # api-docs.json (especificação OpenAPI 3)
├── mustache-tool/            # utilitário CLI que renderiza templates Mustache
├── openapi-server/           # aplicação Spring Boot que publica os endpoints gerados
├── openapi-sdk/              # SDK cliente Java 21
├── templates/mustache/       # templates + dados de exemplo para Mustache
└── pom.xml                   # parent (Spring Boot 3.3, Java 21)
```

## Mapa visual

```mermaid
flowchart TD
    Spec[openapi/api-docs.json] --> Gen[OpenAPI Generator (Maven)]
    Gen --> Server[openapi-server\nInterfaces Spring + Mustache]
    Gen --> SDK[openapi-sdk\nRestTemplate Client]
    Server --> Boot[Spring Boot 3.3\n:8080]
    Boot --> Curl[curl GET/POST]
```

## Pré-requisitos

- JDK 21 (JAVA_HOME configurado)
- Maven 3.9+

## Fluxo de geração

1. Coloque/atualize o arquivo `openapi/api-docs.json` com o seu contrato (o repositório inclui um exemplo de Greeting API).
2. Execute `mvn clean verify` na raiz. O build executa automaticamente:
   - `openapi-generator-maven-plugin` para `openapi-server` (gera interfaces Spring em `target/generated-sources/openapi`).
   - `openapi-generator-maven-plugin` para `openapi-sdk` (gera cliente RestTemplate em `target/generated-sources/openapi`).
   - `mustache-tool` + `exec-maven-plugin` para renderizar código adicional (ver seção Mustache).
3. Substitua o arquivo `openapi/api-docs.json` e reexecute o comando sempre que a especificação mudar.

### Servidor Spring Boot (`openapi-server`)

- Código principal: `openapi-server/src/main/java/com/example/openapi/OpenApiServerApplication.java`
- Controllers gerados: apenas a interface (`interfaceOnly=true`). O controle de requisições fica em `com.example.openapi.greetings.GreetingsRestController`, que implementa a interface gerada (`GreetingsApi`) e publica as rotas via Spring MVC.
- Dependências relevantes: `spring-boot-starter-web`, `spring-boot-starter-validation`, `swagger-annotations`, `jackson-databind-nullable`.
- Execute localmente: `mvn -pl openapi-server spring-boot:run`.

## Rodando e testando a API

Com o `mvn clean verify` já executado, os artefatos estão em `openapi-server/target/`.

- ▶️ Subir a aplicação:
  - Dev (quente): `mvn -pl openapi-server spring-boot:run`
  - Jar pronto: `java -jar openapi-server/target/openapi-server-0.0.1-SNAPSHOT.jar`
  - Endereço: `http://localhost:8080` (porta em `openapi-server/src/main/resources/application.yml`).

- 🔎 Testar os endpoints de Greeting:
  - GET calculado: `curl -i "http://localhost:8080/greetings/Lucas?title=Dr."`
  - POST criando saudação:
    ```bash
    curl -i -X POST "http://localhost:8080/greetings" \
      -H "Content-Type: application/json" \
      -d '{"message":"Oi Codex","language":"pt-BR"}'
    ```
  - Esperado: 200 no GET e 201 no POST, corpo JSON com `message`, `language`, `createdAt`.

### SDK Java 21 (`openapi-sdk`)

- É um artefato Maven simples (`jar`).
- O código gerado fica em `openapi-sdk/target/generated-sources/openapi`. Você pode consumir via `mvn -pl openapi-sdk install` e depois adicionar a dependência `com.example:openapi-sdk:0.0.1-SNAPSHOT` em outro projeto.
- Inclui Spring `RestTemplate`, suporte a `jakarta.validation` e `swagger-annotations` para manter compatibilidade com o servidor.

## Mustache / código adicional

A pasta `mustache-tool` expõe uma pequena CLI (`com.example.openapi.mustache.LoggingTemplateRenderer`) que lê um template Mustache e um arquivo JSON com dados. O plugin `exec-maven-plugin` em `openapi-server/pom.xml` roda essa CLI durante a fase `generate-sources` e grava o resultado em `openapi-server/target/generated-sources/mustache/...`.

- Template: `templates/mustache/logging-config.mustache`
- Dados padrão: `templates/mustache/logging-config.json`
- Saída: classe `RequestLoggingConfiguration` (bean Spring Boot que registra `CommonsRequestLoggingFilter`).

### Personalizando

1. Ajuste `templates/mustache/logging-config.json` (p. ex. `maxPayloadLength`, pacote, nome da classe).
2. Opcionalmente crie novos templates Mustache e adicione outra execução do `exec-maven-plugin` apontando para eles.
3. Para desabilitar a renderização basta passar `-Dmustache.renderer.skip=true` no Maven.

Também é possível apontar o `openapi-generator-maven-plugin` para templates customizados (`templateDir`). Basta criar os arquivos Mustache desejados e informar `-Dopenapi.generator.maven.plugin.additional-properties=templateDir=...` ou adicionar um bloco `<templateDirectory>` na configuração do plugin caso queira substituir algum dos templates padrão.

## Dicas/Próximos passos

- Ajuste `groupId/artifactId/version` conforme o seu domínio.
- Conecte o servidor a uma fonte de dados real dentro das implementações concretas de cada interface gerada.
- Versões específicas do gerador ficam centralizadas em `pom.xml` (propriedades `openapi.generator.version`, `swagger.annotations.version`, etc.).
- Caso precise publicar o SDK em um registry, utilize `mvn -pl openapi-sdk deploy` apontando para o seu repositório Maven.

# Hospital Saúde - Sistema de Beneficiários

Este projeto é uma aplicação Spring Boot que gerencia beneficiários de um hospital, oferecendo operações CRUD através de uma API RESTful. A documentação da API é gerada automaticamente com Swagger usando Springdoc OpenAPI.

## Pré-requisitos

- Java 17
- Maven 3.x
- IDE (Eclipse, IntelliJ IDEA, etc.)

## Configuração e Execução

1. **Clonar o repositório:**

   ```bash
   git clone https://github.com/wagnerk78/hospitalSaude
   cd hospitalSaude

2. **Compilar e executar com Maven:**

   ```bash
    mvn spring-boot:run


3. **Acessar a aplicação:**
   
  http://localhost:8080

4. **Documentação da API:**

http://localhost:8080/swagger-ui.html

A seguir estão os principais endpoints disponíveis na API:

<b>Listar Beneficiários:</b> GET /beneficiario <br>
<b>Buscar Beneficiário por ID:</b> GET /beneficiario/{beneficiarioId} \n
Adicionar Beneficiário: POST /beneficiario
Atualizar Beneficiário: PUT /beneficiario/{beneficiarioId}
Atualizar Documentos do Beneficiário: PUT /beneficiario/{beneficiarioId}/documentos
Excluir Beneficiário: DELETE /beneficiario/{beneficiarioId}
Para detalhes adicionais sobre parâmetros, respostas e exemplos, consulte a documentação Swagger.

5. **Estrutura do Projeto:**

src/main/java/com/medicina/saude/: Contém os arquivos Java do projeto.
controller/: Controladores da API REST.
dto/: Objetos de transferência de dados.
exceptions/: Exceções personalizadas.
model/: Entidades do banco de dados.
repository/: Repositórios JPA.
service/: Camada de serviço da aplicação.
src/main/resources/: Configurações e arquivos de recursos.
application.properties: Propriedades de configuração da aplicação.
pom.xml: Arquivo de configuração do Maven.






  

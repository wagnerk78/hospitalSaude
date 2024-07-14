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
<b>Buscar Beneficiário por ID:</b> GET /beneficiario/{beneficiarioId}  <br>
<b>Adicionar Beneficiário:</b> POST /beneficiario  <br>
<b>Atualizar Beneficiário:</b> PUT /beneficiario/{beneficiarioId}  <br>
<b>Atualizar Documentos do Beneficiário:</b> PUT /beneficiario/{beneficiarioId}/documentos  <br>
<b>Excluir Beneficiário:</b> DELETE /beneficiario/{beneficiarioId}  <br>
Para detalhes adicionais sobre parâmetros, respostas e exemplos, consulte a documentação Swagger.  <br>

5. **Estrutura do Projeto:**

src/main/java/com/medicina/saude/: Contém os arquivos Java do projeto.<br>
controller/: Controladores da API REST.<br>
dto/: Objetos de transferência de dados.<br>
exceptions/: Exceções personalizadas.<br>
model/: Entidades do banco de dados.<br>
repository/: Repositórios JPA.<br>
service/: Camada de serviço da aplicação.<br>
src/main/resources/: Configurações e arquivos de recursos.<br>
application.properties: Propriedades de configuração da aplicação.<br>
pom.xml: Arquivo de configuração do Maven.<br>






  

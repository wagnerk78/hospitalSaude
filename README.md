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
   
      ```bash
      http://localhost:8080

4. **Endpoints Principais:**

   ```bash
   http://localhost:8080/swagger-ui.html


A seguir estão os principais endpoints disponíveis na API:

- <b>Listar Beneficiários:</b> GET /beneficiario <br>
- <b>Buscar Beneficiário por ID:</b> GET /beneficiario/{beneficiarioId}  <br>
- <b>Adicionar Beneficiário:</b> POST /beneficiario  <br>
- <b>Atualizar Beneficiário:</b> PUT /beneficiario/{beneficiarioId}  <br>
- <b>Atualizar Documentos do Beneficiário:</b> PUT /beneficiario/{beneficiarioId}/documentos  <br>
- <b>Excluir Beneficiário:</b> DELETE /beneficiario/{beneficiarioId}  <br>
Para detalhes adicionais sobre parâmetros, respostas e exemplos, consulte a documentação Swagger.  <br>


5. <b>**Estrutura do Projeto:**</b>

- src/main/java/com/medicina/saude/: Contém os arquivos Java do projeto.<br>
- controller/: Controladores da API REST.<br>
- dto/: Objetos de transferência de dados.<br>
- exceptions/: Exceções personalizadas.<br>
- model/: Entidades do banco de dados.<br>
- repository/: Repositórios JPA.<br>
- service/: Camada de serviço da aplicação.<br>
- src/main/resources/: Configurações e arquivos de recursos.<br>
- application.properties: Propriedades de configuração da aplicação.<br>
- pom.xml: Arquivo de configuração do Maven.<br>


6. <b>**Desenvolvimento da aplicação:**</b>

<p style="text-align: justify;">
A aplicação foi desenvolvida em duas classes principais: Beneficiário e Documento. Foram criadas as classes model e repository. Na classe controller, optei por não fazer as consultas de documentos em uma única classe que realizasse todas as tarefas pedidas no teste. Para mudar o retorno do JSON, utilizei as classes principais seguidas de DTO. Posteriormente, a documentação foi feita utilizando o Swagger.
</p> <br>

<p>Para facilitar os testes da API estou deixando abaixo os raws preenchidos:</p>

1. **Adicionar Beneficiário:**

   ```bash
   {
    "nome": "João da Silva",
    "telefone": "123456789",
    "dtNascimento": "1990-01-01",
    "documentos": [
        {
            "documento": "123.456.789-00",
            "descricao": "CPF"
        },
        {
            "documento": "MG-12.345.678",
            "descricao": "RG"
        }
    ]
   }

2. **Atualizar Beneficiário:**

   ```bash
   {
    "nome": "Maria das Graças"
   }

3. **Atualizar Documentos do Beneficiário:**

   ```bash
   [
    {
        "documento": "A12356",
        "descricao": "PASSAPORTE"
    }
   ]



  

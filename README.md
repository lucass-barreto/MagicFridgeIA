# MagicFridgeIA - Geladeira Inteligente com Geração de Receitas via IA

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-green?logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-green?logo=thymeleaf&logoColor=white)
[![OpenAI API](https://img.shields.io/badge/OpenAI-API-10a37f?logo=openai&logoColor=white)](https://openai.com/api/)

Aplicação web full-stack que simula o gerenciamento de uma geladeira, permitindo ao usuário cadastrar ingredientes e, com um único clique, solicitar à API da OpenAI que gere uma receita criativa utilizando os itens disponíveis. O projeto foi desenvolvido como um estudo aprofundado no ecossistema Java, com foco na integração de serviços de IA, na construção de interfaces web reativas com Thymeleaf e nas melhores práticas de desenvolvimento com Spring Boot.

---

### 📸 Visão Geral da Interface

A aplicação conta com um dashboard moderno e funcional, que centraliza todas as interações do usuário.

#### **Dashboard Principal:** 
*O painel centraliza o formulário de adição de ingredientes e a opção da geração de receita com IA.*

![Dashboard da Aplicação](.github/assets/dashboard.png)

*Logo abaixo há uma lista com todos os itens adicionados e as opções de **deletar** e **editar** um item.*
![Lista de itens](.github/assets/tabela-de-itens.png)

#### **Página de Edição Dedicada:**
*Uma interface limpa para a alteração de um ingrediente específico, garantindo uma experiência de usuário fluida.*

![Página de Alteração de Item](.github/assets/alterar-item.png)

#### **Geração de Receitas com IA:**
*Após adicionar os ingredientes, o usuário pode solicitar uma receita, que é exibida dinamicamente na mesma tela.*

![Exemplo de Receita Gerada](.github/assets/gerador-de-receitas.png)

---

### 📜 Descrição Completa

**MagicFridgeIA** é uma solução criativa para um problema comum: o que cozinhar com o que se tem em casa? A aplicação permite o gerenciamento completo do inventário de uma geladeira, desde o cadastro de itens com suas respectivas categorias e validades até a exclusão e alteração dos mesmos.

O grande diferencial do projeto é a sua integração com a **API da OpenAI**. O back-end consulta todos os ingredientes disponíveis no banco de dados, formata essa lista em um prompt otimizado e o envia para um modelo GPT. A resposta da IA, uma receita completa, é então exibida na interface.

O projeto foi construído com uma clara separação de responsabilidades, utilizando controllers `@RestController` para uma potencial API JSON e um `@Controller` dedicado para a interface de usuário, renderizada no lado do servidor com **Thymeleaf**.

---

### 🚀 Funcionalidades

- ✅ **CRUD Completo de Ingredientes:** Endpoints e interface para Criar, Ler, Atualizar e Deletar itens na geladeira.
- 🖥️ **Dashboard Interativo:** Interface web única construída com Thymeleaf para todas as operações.
- 🤖 **Integração com IA:** Comunicação reativa com a API da OpenAI usando `WebClient` para gerar receitas.
-  dynamic **Prompt Dinâmico:** A aplicação busca os dados do banco em tempo real para construir o prompt que é enviado à IA.
- 🗃️ **Versionamento de Banco de Dados:** Uso do **Flyway** para criar e gerenciar o schema do banco de dados de forma automatizada.
- ✨ **Design Moderno:** Interface com CSS puro, focada em uma experiência de usuário limpa e agradável.

---

### 💪 Desafios Superados

Durante o desenvolvimento, vários desafios foram superados, servindo como grandes pontos de aprendizado:

- **Integração com API Externa (`401 Unauthorized`):** Um longo processo de depuração para resolver falhas de autenticação com a API da OpenAI. A solução envolveu a correção de detalhes minuciosos, como o espaço no header "Bearer ", o nome exato do modelo (`gpt-4o-mini`) e a garantia de que a chave de API correta estava sendo carregada pelo ambiente Spring.
- **Programação Reativa (`WebClient`):** Compreensão na prática de como lidar com respostas assíncronas (`Mono`). O desafio foi aprender a extrair dados de um JSON complexo sem usar DTOs, navegando pela estrutura com `Map` e `List` e utilizando o operador `.map()` para transformar a resposta dentro do fluxo reativo.
- **Spring MVC & Thymeleaf:** Aprofundamento na diferença crucial entre `@Controller` (para renderizar views) e `@RestController` (para retornar dados). Resolução de erros `404 (Not Found)` e `405 (Method Not Allowed)` através da correção de rotas (`@RequestMapping`) e do uso do `HiddenHttpMethodFilter` do Spring para permitir o uso dos verbos `PUT` e `DELETE` em formulários HTML.
- **Configuração de Build (Maven):** Resolução de um erro raro (`HalfWidthInputException`) causado por um caractere invisível no arquivo `application.properties`, o que exigiu a recriação do arquivo para limpar problemas de codificação.
- **Injeção de Dependência (`NullPointerException`):** Diagnóstico de erros de `NullPointerException` que levaram à compreensão da importância da injeção de dependências via construtor e da necessidade de `getters/setters` em classes DTO (resolvido com o `@Data` do Lombok).

---

### 🛠️ Tecnologias Utilizadas

- **Backend:**
    - Java 17
    - Spring Boot 3
    - Spring Web (com WebClient para chamadas reativas)
    - Spring Data JPA / Hibernate
- **Frontend:**
    - Thymeleaf
    - HTML5
    - CSS3
- **Banco de Dados:**
    - H2 Database (In-Memory)
    - Flyway (para versionamento de schema)
- **Build & Dependências:**
    - Apache Maven
    - Lombok
    - MapStruct

---

### ⚙️ Instalação e Uso

Para executar este projeto localmente, siga os passos abaixo:

**Pré-requisitos:**

- Java (JDK) 17 ou superior instalado.
- Apache Maven instalado.
- Uma chave de API da OpenAI.

**1. Clone o repositório:**
```bash
git clone [https://github.com/lucass-barreto/MagicFridgeIA.git](https://github.com/lucass-barreto/MagicFridgeIA.git)
cd MagicFridgeIA
```

**2. Configure a Chave da API:**

Crie um arquivo chamado `.env` na raiz do projeto e adicione a sua chave da OpenAI: 
```bash
API_KEY=sk-proj-sua-chave-secreta-aqui
```

*Lembre-se de adicionar o arquivo `.env` ao seu `.gitignore`!*

**3. Build do Projeto:**

Execute o comando Maven para construir o projeto.
```bash
./mvnw clean install
```

4.  **Execute a Aplicação:**
    Inicie a aplicação através da sua IDE, executando a classe principal, ou via terminal:
    ```bash
    ./mvnw spring-boot:run
    ```
5.  **Acesse a Aplicação:**
    * **Interface Web (Dashboard):** [http://localhost:8080/ui/dashboard](http://localhost:8080/ui/dashboard)
    * **API REST (Exemplos):**
        * `GET http://localhost:8080/food/`
        * `GET http://localhost:8080/recipe/generate`
    * **Console do Banco H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (Use as credenciais do `application.properties` se necessário).

## 📫 Contato

**Lucas Barreto Oliveira**

* **GitHub:** [@lucass-barreto](https://github.com/lucass-barreto)
* **LinkedIn:** [@lucass-barreto](https://www.linkedin.com/in/lucass-barreto)
* **Email:** lucasbo.dev@gmail.com

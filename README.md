# 💇‍♂️ API REST - Sistema de Agendamento de Serviços (Barbearia/Salão)

Esta é uma API REST corporativa desenvolvida para o gerenciamento de barbearias e salões de beleza. O sistema permite cadastrar clientes, funcionários, serviços e gerenciar uma agenda de marcações inteligente com regras rígidas de validação de negócios.

## 🛠️ Tecnologias e Ferramentas Utilizadas
* **Java 17**
* **Spring Boot 3.x**
* **Spring Data JPA**
* **Validation (Hibernate Validator)** 
* **MySQL**
* **Maven**

## 🧠 Regras de Negócio e Validadores Implementados
* **Validação de Horário:** Impede agendamentos fora do horário de funcionamento do estabelecimento.
* **Validação de Conflito:** Garante que um mesmo profissional não receba dois agendamentos no mesmo horário.

## CHALLENGE-WALLET

**Escopo**

Foi desenvolvida uma API capaz de gerenciar usuários (CRUD), 
bem como prover a autenticação desses usuários e permitir operações
básicas financeiras, sendo elas depósito, saque, transferência entre usuários e pagamento de contas.

**Tecnologia**

As tecnologias utilizadas foram:
- Java
- Spring boot
- MySQL
- Kafka
- Docker / docker-compose

**Arquitetura**

O sistema está desenvolvido em uma arquitetura de microsserviços, sendo eles:

- **user**: Serviço responsável por gerenciar o cadastro de usuários (CRUD) e realizar a autenticação. 
Além disso, quando um usuário é cadastrado é gerado um evento de domínio (utilizando kafka) informando os dados do usuário criado;
- **wallet**: Serviço responsável por cadastrar uma conta para usuários (consumindo via kafka o evento de domínio da criação de usuário) 
e gerenciar operações financeiras criando transações e movimentações financeiras de CRÉDITO e DÉBITO.
- **gateway**: Serviço responsável por unificar a api em uma URL e despachar as requisições para os serviços 
de negócio, também possui um filtro de autorização.
- **eureka**: Serviço responsável por possuir os endereços e portas de cada serviço de negócio presente na arquitetura.

**Como rodar**

Para rodar a aplicação é necessário possuir previamente instalados o docker e docker-compose na máquina, 
para rodar, basta utilizar o comando na raiz do projeto:

> docker-compose up -d

O sistema irá subir todos os serviços, junto com o kafka e os bancos de dados utilizados.

Foi adiciona uma [collection](./challenge-wallet.postman_collection.json) do postman com os recursos disponíveis da api para facilitar a utilização

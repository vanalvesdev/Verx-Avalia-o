# Verx Avaliação
API usada como avaliação para vaga na UOL.

## Uso
A API pode ser utilizada acessando os enpoints /clientes (para recuperação de todos os clientes já salvos) e /clientes/{id} (para as demais ações de criar, atualizar e remover um cliente). Também é possivel acessar o endpoint /swagger-ui.html e obter mais informações e exemplos.



## Execução
Essa é a uma aplicação Maven com o plugin do spring-boot então, para executar a aplicação basta executar o comando maven **spring-boot:run**.
A aplicação também provê o maven wrapper, desta forma não é necessario instalar o maven para utiliza-lo, para executar os comandos maven com o maven wrapper basta ir à raiz do projeto e executar ./mvnw (linux) ou mvnw.cmd(windows) e o comando especifico que queira executar.

Ex: ./mvnw spring-boot:run

### Empacotar
Caso o objetivo seja enpacota-lo para uma execução posterior, pode ser feito utilizando o comando **clean package**, assim será criado uma pasta target que conterá um jar chamado **avaliacao-verx-0.0.1-SNAPSHOT.jar**

Ex: ./mvnw clean package

### Execução do jar
O jar criado com o comando maven pode ser executado com o comando padrão **java -jar avaliacao-verx-0.0.1-SNAPSHOT.jar**

## Teste
Ao executar a aplicação e acessar o enpoint /swagger-ui.html você encontrará uma interface simples com as descrições dos endpoints e exemplos do json de entrada e saida de cada um deles.
Também poderá consumir todos os endpoints sem a necessidade da criação de um client.

## Produção
A aplicação utiliza o banco de dados H2 que executa em memoria, o que significa que ao parar a aplicação os dados serão perdidos.
Para o ambiente de produção já está previsto e preparado a integração com um banco de dados MySQL, e para utiliza-lo serão necessarios alguns passos.
1. Configurar as variaveis de ambiente DATASOURCE_URL, DATASOURCE_USERNAME e DATASOURCE_PASSWORD com as informações referentes ao banco MySQL que queira se conectar
1. Informar a aplicação que deve executar preparada para o ambiente de produção, para isso é necessario passar o parametro JVM informando o perfil de prod.
Ex: **java -jar -Dspring.profiles.active=prod avaliacao-verx-0.0.1-SNAPSHOT.jar**

##Ferramentas utilizadas
* H2 - Banco em memória, utilizado no ambiente de desenvolvimento pela simplicidade de execução e não exigir instalação de ferramentas ou programas
* Lombok - Biblioteca para redução de boilerplate no java, utilizando apenas algumas anotações você evita a verbosidade e foca apenas nos codigos do seu negócio. 
* Swagger2 - Uma ferramenta para documentação da aplicação, que também auxilia nos testes. Com poucas configurações o swagger permite a geração automatica de uma documentação para a API em uma interface simples de usar.

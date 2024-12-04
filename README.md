[Documentação Técnica RFC do projeto](https://docs.google.com/document/d/11Tobzwm5-JkiURC2DJRTKl8LARjj46NG/edit?usp=sharing&ouid=113156827946997430842&rtpof=true&sd=true)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=bugs)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=WesleySardi_projeto-conclusao-curso-backend&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=WesleySardi_projeto-conclusao-curso-backend)

# ZloTrackband - Back-End

## Descrição do Projeto

Este é o back-end da aplicação **ZloTrackband**, responsável por gerenciar as funcionalidades relacionadas às pulseiras de auxílio para pessoas dependentes. Ele provê os serviços necessários para autenticação, gerenciamento de dependentes, notificações, histórico de localizações e integração com dispositivos externos.

### Principais Funcionalidades:

- **Autenticação JWT**: Proteção das rotas com base em papéis (Roles) definidos.
- **Gerenciamento de Dependentes**: CRUD completo para dependentes e responsáveis.
- **Notificações**: Envio e recebimento de notificações via e-mail e SMS.
- **Histórico de Localizações**: Registro e consulta das localizações dos dependentes.
- **Interação com Dispositivos**: Integração com NFC e dispositivos externos para interação com as pulseiras.

## Visão Geral

### Introdução
O back-end do **ZloTrackband** foi desenvolvido utilizando **Spring Boot**. Ele organiza a aplicação em camadas claras e bem definidas: Controller, Handler, Service e Repository, garantindo fácil manutenção e escalabilidade.

### Tecnologias Utilizadas
- **Spring Boot**: Framework para desenvolvimento rápido e eficiente em Java.
- **JPA/Hibernate**: Para mapeamento e manipulação de dados no banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado na aplicação.
- **JWT**: Para autenticação segura.
- **Maven**: Para gerenciamento de dependências e build.

### Estrutura do Projeto

```bash
biomed-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/biomed/
│   │   │       ├── controllers/       # Controladores (endpoints)
│   │   │       ├── handlers/          # Tratamento de exceções
│   │   │       ├── services/          # Lógica de negócios
│   │   │       ├── repositories/      # Interação com o banco de dados
│   │   │       ├── dtos/              # Objetos de transferência de dados
│   │   │       ├── util/              # Utilitários (Roles, Paths)
│   │   └── resources/
│   │       └── application.properties # Configurações da aplicação
├── pom.xml                             # Arquivo de configuração do Maven
└── README.md                           # Documentação do projeto
```

### Descrição das Camadas
- **Controller**: Define os endpoints e gerencia as requisições HTTP.
- **Handler**: Trata exceções e fornece respostas padronizadas.
- **Service**: Contém a lógica de negócios e manipulação de dados.
- **Repository**: Responsável pela interação direta com o banco de dados, utilizando JPA.

### Endpoints e DTOs
Os endpoints são organizados em pastas conforme as entidades: **Dependente**, **Responsável**, **DeviceStorage**, **Email**, **Sms**, **Notification**, **ScanHistory**. Todos seguem os seguintes padrões:
- **POST, PUT, DELETE**: Recebem **Commands** e retornam **Results**.
- **GET**: Recebem **Querys** e retornam **ViewModels**.

Além disso, todos os endpoints são protegidos com **JWT Tokens**, exigindo autenticação.

#### DTO Geral para Respostas
Todas as respostas são encapsuladas pelo DTO `StatusResponseViewModel`, facilitando a tratativa no front-end:
```java
public class StatusResponseViewModel<T> {
    private T responseContent;
    private String infoMessage;
    private String statusMessage;
    private int status;
    private Boolean isOk;
    // Getters e Setters
}
```

### Gerenciamento de Segurança

O acesso às rotas é gerenciado com base nos papéis definidos no arquivo Roles e nos caminhos especificados em Paths. Exemplo de configuração no Spring Security:

```java
.requestMatchers(
    Paths.DEP_ADMIN_PATH.getPath() + "/**",
    Paths.RES_ADMIN_PATH.getPath() + "/**"
).hasRole(Roles.ADMIN.getRole())
.requestMatchers(
    Paths.DEP_MANAGER_PATH.getPath() + "/**",
    Paths.RES_MANAGER_PATH.getPath() + "/**"
).hasAnyRole(Roles.CUIDADOR.getRole(), Roles.ADMIN.getRole())
```

#### Arquivo Paths

```java
public enum Paths {
    DEP_ADMIN_PATH("/api/dependent/admin"),
    DEP_MANAGER_PATH("/api/dependent/manager"),
    DEP_COMMONUSER_PATH("/api/dependent/commonuser"),
    RES_ADMIN_PATH("/api/responsible/admin"),
    RES_MANAGER_PATH("/api/responsible/manager"),
    RES_COMMONUSER_PATH("/api/responsible/commonuser");

    private final String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
```

#### Arquivo Roles

```java
public enum Roles {
    ADMIN("ADMIN"),
    CUIDADOR("CUIDADOR"),
    RESPONSAVEL("RESPONSAVEL");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
```

### Conclusão

Este back-end foi projetado para oferecer segurança, escalabilidade e organização. Ele segue boas práticas de desenvolvimento em Java, utilizando Spring Boot e uma arquitetura em camadas, garantindo facilidade de manutenção e expansão do sistema.

### Desenvolvedores

- Wesley Erik Sardi

### Contribuições são bem-vindas! Para contribuir, abra uma issue ou envie um pull request.

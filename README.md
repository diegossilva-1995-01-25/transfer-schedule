
# Transfer Schedule

Protótipo de Aplicação Demo para Agendamento de Transferência Bancária, feito com o propósito avaliatório para vaga de emprego.

## Composição:
- Aplicação RestfulAPI back-end fabricada com SpringBoot, Banco Relaciona H2, estrutura: controller, service, entity, repository;
- Aplicação front-end fabricada com Angular;

## Funcionamento (fluxo principal):
    1. Acessar aplicação front-end, se deparando com tela de login;
    2. Ir à tela de registrar-se e fazer o registro do usuário;
    3. Realizar o login;
    4. Se o usuário é novo, ele vai ser redirecionado a criar uma conta;
    5. Ele verifica a sua conta;
    6. Retorna ao menu principal;
    7. Se dirige à tela de agendamento de transferência;
    8. Usuário preenche os dados necessários ainda não pré-preenchidos;
    9. Envia os dados.

## O que foi utilizado e versões:

### Back-end:
- SpringBoot versão 4.0.5;
- JJWT-API versão 0.13.0;
- JJWT-Implementation versão 0.13.0;
- JJWT-Jackson versão 0.13.0;
- H2 versão latest (2.4.240);
- Project Lombok versão latest (1.18.44);

#### Usados do SpringBoot V4.0.5:
- spring-boot-h2console;
- spring-boot-starter-data-jpa;
- spring-boot-starter-security;
- spring-boot-devtools;
- spring-boot-starter-data-jpa-test;
- spring-boot-starter-security-test;
- spring-boot-starter-web;

### Front-end:
- Package manager NPM, versão 11.11.0;
- Angular Common versão 21.2.0;
- Angular Compiler versão 21.2.0;
- Angular Core versão 21.2.7;
- Angular Forms versão 21.2.0;
- Angular Platform-Browser versão 21.2.0;
- Angular Router versão 21.2.0;
- MDI/font versão 7.4.47;
- rxjs versão 7.8.0;
- tslib versão 2.3.0;
- Angular Build versão 21.2.5;
- Angular CLI versão 21.2.5;
- Angular Compiler-CLI versão 21.2.0;
- jsdom versão 28.0.0;
- prettier versão 3.8.1;
- typescript": "~5.9.2;
- vitest versão 4.0.8".


# SysPriority

SysPriority é um sistema de priorização inteligente de atendimentos
hospitalares.

O objetivo do sistema é organizar a fila de consultas de forma dinâmica
e justa, garantindo que pacientes com maior necessidade sejam atendidos
primeiro, sem depender apenas da ordem de chegada.

------------------------------------------------------------------------

## Como Funciona

O sistema gerencia uma fila de atendimentos baseada em critérios de
prioridade.\
Cada consulta recebe um score calculado automaticamente no momento da
criação e atualizado conforme o tempo de espera aumenta.

Quando o médico estiver disponível, o sistema direciona automaticamente
o próximo paciente com maior prioridade para atendimento.

------------------------------------------------------------------------

## Critérios de Ordenação da Fila

A prioridade é definida com base em:

-   Gravidade do atendimento (escala definida no sistema)
-   Idade do paciente
-   Tempo de espera

### Regras gerais:

-   Casos mais graves recebem maior prioridade.
-   Pacientes idosos e crianças podem receber prioridade adicional.
-   Quanto maior o tempo de espera, maior será o aumento progressivo na
    prioridade.

A fila é reordenada dinamicamente sempre que necessário.

------------------------------------------------------------------------

## Cadastro e Verificação de Paciente

Sempre que o recepcionista cria uma nova consulta:

1.  O CPF do paciente é informado.
2.  O sistema verifica automaticamente se o paciente já existe no banco
    de dados.
3.  Caso exista, os dados são reutilizados.
4.  Caso não exista, o cadastro é realizado antes da criação da
    consulta.

Esse processo evita duplicidade de registros e mantém a base de dados
consistente.

------------------------------------------------------------------------

## Objetivo do Sistema

O SysPriority foi desenvolvido para oferecer:

-   Organização eficiente da fila de atendimento
-   Priorização baseada em critérios objetivos
-   Redução de riscos associados a espera prolongada
-   Maior controle operacional para unidades de saúde

O sistema é direcionado para ambientes hospitalares, clínicas e unidades
de atendimento que necessitam de organização inteligente de filas.

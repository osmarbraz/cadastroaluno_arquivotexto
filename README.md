# Exemplo de Cadastro de Aluno utilizando arquivo texto.
 
## Descrição

Este projeto foi desenvolvido em Java com o objetivo de implementar operações de manipulação de dados em um arquivo texto (`dados.txt`), simulando um pequeno sistema de cadastro de alunos.

Os registros são armazenados em arquivo texto, sendo cada aluno gravado em uma única linha e seus atributos separados pelo caractere ponto e vírgula (`;`).

O sistema utiliza exclusão lógica, ou seja, os registros não são removidos fisicamente do arquivo. Quando um aluno é excluído, sua matrícula é substituída pelo valor `-1`, indicando que o registro foi marcado como excluído.

## Funcionalidades

O programa apresenta um menu com as seguintes opções:

1. Incluir
2. Excluir
3. Alterar
4. Pesquisar
5. Listar Lógico
6. Listar Tudo
7. Sair

### Incluir

Permite cadastrar um novo aluno no arquivo `dados.txt`.

### Excluir

Realiza a exclusão lógica de um aluno através da matrícula informada.

### Alterar

Permite modificar os dados de um aluno já cadastrado.

### Pesquisar

Localiza um aluno pela matrícula e exibe seus dados caso seja encontrado.

### Listar Lógico

Exibe apenas os registros ativos, desconsiderando aqueles marcados como excluídos.

### Listar Tudo

Exibe todos os registros armazenados no arquivo, inclusive os excluídos logicamente.


## Estrutura dos Dados

Cada registro possui os seguintes atributos:

* Matrícula
* Nome
* Endereço
* Curso
* Idade
* Sexo

Exemplo de registro:

1001;João Silva;Rua A;ADS;20;M

## Exclusão Lógica

Quando um registro é excluído, a matrícula é substituída por `-1`.

Exemplo:

-1;Maria Souza;Rua B;SI;22;F

Dessa forma, o registro permanece armazenado no arquivo para fins de auditoria ou recuperação futura.
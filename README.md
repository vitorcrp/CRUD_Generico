# CRUD Genérico

O CRUD é uma classe desenvolvida em Java para realizar operações em arquivos, contém métodos como o Create, Read, Update e Delete. Para testá-lo, foi utilizada a classe Carro.

## O que foi implementado no Projeto

* O método **CREATE**, serve para criar um novo registro no arquivo, de forma que ele recebe o objeto passado por parâmetro e retorna o id do respectivo objeto criado.
* O método **READ**, serve para recuperar um registro já inserido no arquivo, de forma que ele recebe o id do objeto a ser procurado. Caso ache o registro retorna seus respectivos atributos, caso contrário retorna null.
* O método **UPDATE**, serve para atualizar um registro já inserido no arquivo, de forma que ele recebe o objeto passado por parâmetro e retorna um valor booleano. Caso consiga atualizar, o método retorna true, caso contrário retorna false e printa uma mensagem de erro ao usuário.
* O método **DELETE**, serve para deletar um registro já inserido no arquivo, de forma que ele recebe o id do objeto a ser deletado e retorna um valor booleano. Caso consiga deletar, o método retorna true, caso contrário retorna false e printa uma mensagem de erro ao usuário.
* A Classe `Carro` que contém os atributos dos registros para realizar os testes do **CRUD**.
* A **interface Registro** que contém as assinaturas dos métodos:
  1. `getID()` que retorna o ID do respectivo objeto.
  2. `setID()` que recebe um inteiro e seta como ID do objeto.
  3. `toByteArray()` que cria um vetor de bytes para o respectivo registro e escreve seus atributos no arquivo.
  4. `fromByteArray()` que seta os atributos do objeto com o registro lido em bytes.
* A `Classe Main` que serve para testar a **Classe CRUD**.

## O que não foi implementado na Classe

* O método **READ** que recebe um conjunto de entidades, ou seja, um valor especifíco para se realizar a pesquisa.

## Execução:

- Para utilizar o CRUD, deve-se executar na classe `Main`, o método:
```java
public static void main(String[] args)
```

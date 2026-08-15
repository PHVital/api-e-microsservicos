# Escritório de Investimentos VcRiquinho - Protótipo

## 1. Diagrama de Classes UML

```mermaid
classDiagram
    class Conta {
        <<interface>>
        +depositar(valor: double): void
        +sacar(valor: double): void
        +getSaldo(): double
        +realizarSimulacao(dias: int, taxaCDIMensal: double, cliente: Cliente): void
    }

    class ContaBancaria {
        <<abstract>>
        #saldo: double
        #numeroConta: String
        +depositar(valor: double): void
        +sacar(valor: double): void
        +getSaldo(): double
        #validarPeriodoSimulacao(dias: int): void
    }
    Conta <|.. ContaBancaria

    class ContaCorrente {
        +realizarSimulacao(dias: int, taxaCDIMensal: double, cliente: Cliente)
    }
    class ContaCDI {
        +realizarSimulacao(dias: int, taxaCDIMensal: double, cliente: Cliente)
    }
    class ContaInvestimento {
        -produtos: List~ProdutoInvestimento~
        +adicionarProduto(p: ProdutoInvestimento)
        +removerProduto(p: ProdutoInvestimento)
        +realizarSimulacao(dias: int, taxaCDIMensal: double, cliente: Cliente)
    }
    ContaBancaria <|-- ContaCorrente
    ContaBancaria <|-- ContaCDI
    ContaBancaria <|-- ContaInvestimento

    class ProdutoInvestimento {
        <<abstract>>
        #nome: String
        #descricao: String
        +calcularRendimento(valor: double, dias: int): double
        +isElegivelTaxa(dias: int): boolean
    }
    class RendaFixa {
        -rendimentoMensalFixo: double
        -carenciaDias: int
        +calcularRendimento(valor: double, dias: int): double
        +isElegivelTaxa(dias: int): boolean
    }
    class RendaVariavel {
        -rendimentoMensalEsperado: double
        +calcularRendimento(valor: double, dias: int): double
        +isElegivelTaxa(dias: int): boolean
    }
    ProdutoInvestimento <|-- RendaFixa
    ProdutoInvestimento <|-- RendaVariavel

    class Cliente {
        <<abstract>>
        #nome: String
        #email: String
        #contas: List~Conta~
        +Cliente(nome: String, email: String, contaInicial: Conta)
        +adicionarConta(c: Conta)
        +getTaxaInvestimentoAutomatico(): double
    }
    class PessoaFisica {
        -cpf: String
        +getTaxaInvestimentoAutomatico(): double
    }
    class PessoaJuridica {
        -cnpj: String
        +getTaxaInvestimentoAutomatico(): double
    }
    Cliente <|-- PessoaFisica
    Cliente <|-- PessoaJuridica

    Cliente "1" --> "1..*" Conta : possui
    ContaInvestimento "*" --> "*" ProdutoInvestimento : aloca
```

## 2. Escolhas de Design e Justificativas

A solução foi estruturada aplicando fortemente os conceitos de Programação Orientada a Objetos (POO):

### Encapsulamento e Restrições de Domínio
- **Garantia de Conta Inicial Obrigatória**: Atendendo à regra de negócio descrita no enunciado, o construtor da classe genérica `Cliente` e de suas subclasses (`PessoaFisica` e `PessoaJuridica`) obrigatoriamente exige uma `Conta` válida. Caso seja fornecido `null`, o sistema interrompe o fluxo lançando um `IllegalArgumentException`. Isso blinda a aplicação no nível de domínio para que não existam clientes sem contas alocados na memória.
- **Validação de Períodos Permitidos**: A classe `ContaBancaria` agora expõe o método `validarPeriodoSimulacao(int dias)` que é ativamente acionado pelas contas filhas antes de realizar qualquer cálculo nas simulações. Ele garante de maneira limpa e unificada que apenas os períodos estritos de 30, 60, 90 ou 180 dias sejam computados.
- Os atributos das classes permanecem com modificadores `private` ou `protected`, forçando manipulação através de comportamentos (métodos) e não acesso direto ao estado.

### Classes Abstratas, Interfaces e Herança
- A **Interface `Conta`** dita o contrato bancário global, e a **Classe Abstrata `ContaBancaria`** efetiva as lógicas concretas (como manipular saldo e barrar períodos não autorizados), enxugando totalmente as classes filhas de lógicas repetitivas (DRY).

### Polimorfismo
- O motor de simulação contido em `ContaInvestimento.realizarSimulacao` processa uma lista heterogênea baseada na abstração `ProdutoInvestimento`. Ele consulta polimorficamente o método `isElegivelTaxa()` que em Renda Fixa avalia o período de carência (bloqueando ou liberando a taxa de serviço), enquanto em Renda Variável aprova integralmente. Essa manobra enxuga blocos complexos de `if/else` e instâncias (type checking).
- O mesmo vale na simulação bancária: a lista iterada em `Cliente` aponta para referências genéricas de `Conta`, disparando a mecânica correta para Conta Corrente, CDI ou Investimento dependendo unicamente da natureza do objeto sob a variável.

### Protótipo em Memória e CRUD
Para atestar os requisitos solicitados de forma funcional, o arquivo principal de terminal `Main` executa de maneira estrita o **CRUD** (Create, Read, Update, Delete) em listas nativas que simulam o banco de dados. Lá é demonstrada a inclusão, formatação (Read), a alteração do estado de objetos (Update via Setters) e por fim a remoção (Delete). Na remoção de produtos, ilustra-se consistentemente que retirar um ativo da instituição também desvincula esse ativo das contas de clientes investidores.

// Sou professora de inglês, então o sistema que pensei foi um que controlasse as informações básicas dos meus alunos, como nome, matrícula e status do pagamento (se foi pago ou não pago).

class Aluno {

    private String nome;
    private String matricula;
    private double mensalidade;
    private boolean pagamentoPendente;

    public Aluno(String nome, String matricula, double mensalidade) {
        this.nome = nome;
        this.matricula = matricula;
        this.mensalidade = mensalidade;
        this.pagamentoPendente = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public boolean isPagamentoPendente() {
        return pagamentoPendente;
    }

    public void setPagamentoPendente(boolean pagamentoPendente) {
        this.pagamentoPendente = pagamentoPendente;
    }

    // Método para realizar o pagamento
    public void pagarMensalidade() {
        if (this.pagamentoPendente) {
            this.pagamentoPendente = false;
            System.out.println("Pagamento realizado com sucesso: " + nome);
        } else {
            System.out.println(nome + " já pagou a mensalidade.");
        }
    }

    // Método para mostrar o status do pagamento
    public void mostrarStatusPagamento() {
        if (this.pagamentoPendente) {
            System.out.println("Pagamento pendente: " + nome);
        } else {
            System.out.println("Pagamento realizado: " + nome);
        }
    }
}

class AlunoComDesconto extends Aluno {
    private double percentualDesconto;

    public AlunoComDesconto(String nome, String matricula, double mensalidade, double percentualDesconto) {
        super(nome, matricula, mensalidade); // Chama o construtor da classe base
        this.percentualDesconto = percentualDesconto;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    @Override
    public void pagarMensalidade() {
        double valorComDesconto = getMensalidade() * (1 - percentualDesconto / 100);
        System.out.println("Pagamento com desconto realizado. Valor a pagar: " + valorComDesconto);
        super.setPagamentoPendente(false); // Marca o pagamento como feito
    }

    public void mostrarDesconto() {
        System.out.println("Desconto de " + percentualDesconto + "% para " + getNome());
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando um aluno normal
        Aluno aluno1 = new Aluno("Maria", "A123", 300.0);
        aluno1.mostrarStatusPagamento();
        aluno1.pagarMensalidade();
        aluno1.mostrarStatusPagamento();

        // Criando um aluno com desconto
        AlunoComDesconto aluno2 = new AlunoComDesconto("João", "B456", 300.0, 10.0);
        aluno2.mostrarStatusPagamento();
        aluno2.pagarMensalidade();
        aluno2.mostrarStatusPagamento();
        aluno2.mostrarDesconto();
    }
}

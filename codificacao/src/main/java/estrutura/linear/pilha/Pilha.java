package estrutura.linear.pilha;

import estrutura.linear.Processo;

public class Pilha {

    private Processo processoAtual;
    private Pilha proximaPilha;

    public Pilha() {
        this.processoAtual = null;
        this.proximaPilha = null;
    }

    public void inserirPilha(Processo processoNovo) {
        if (this.processoAtual == null) {
            this.processoAtual = processoNovo;
            this.proximaPilha = new Pilha();
        } else {
            Pilha novoNo = new Pilha();
            novoNo.processoAtual = this.processoAtual;
            novoNo.proximaPilha = this.proximaPilha;
            this.processoAtual = processoNovo;
            this.proximaPilha = novoNo;
        }
    }

    public Processo removerPilha() {
        if (this.processoAtual == null) {
            System.out.println("Pilha vazia! Não é possível remover.");
            return null;
        }
        Processo removido = this.processoAtual;
        this.processoAtual = this.proximaPilha.processoAtual;
        this.proximaPilha = this.proximaPilha.proximaPilha;
        return removido;
    }

    public void imprimirEstado() {
        if (this.processoAtual == null) {
            System.out.println("Pilha vazia!");
        } else {
            imprimirEstadoAux();
        }
    }

    private void imprimirEstadoAux() {
        if (this.processoAtual != null) {
            System.out.println("- " + this.processoAtual.getIdentificador());
            if (this.proximaPilha != null) { 
                this.proximaPilha.imprimirEstadoAux();
            }
        }
    }

    public static void main(String[] args) {
        Pilha pilha = new Pilha();
        System.out.println("=== DEMONSTRAÇÃO DE PILHA ===\n");

        System.out.println("[1] Inserindo 3 processos...");
        pilha.inserirPilha(new Processo("Processo-1", 1));
        pilha.inserirPilha(new Processo("Processo-2", 2));
        pilha.inserirPilha(new Processo("Processo-3", 3));

        System.out.println("Estado da Pilha (topo -> base):");
        pilha.imprimirEstado();

        System.out.println("\n[2] Removendo 1 processo...");
        Processo removido = pilha.removerPilha();
        if (removido != null) {
            System.out.println("Removido: " + removido.getIdentificador());
        }
        System.out.println("Estado da Pilha (topo -> base):");
        pilha.imprimirEstado();

        System.out.println("\n[3] Removendo mais 2 processos...");
        pilha.removerPilha();
        pilha.removerPilha();
        System.out.println("Estado da Pilha (topo -> base):");
        pilha.imprimirEstado();

        System.out.println("\n[4] Tentando remover de uma pilha vazia...");
        pilha.removerPilha();
    }
}

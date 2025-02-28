package estrutura.linear.lista;

import estrutura.linear.Processo;

public class ListaSimplesmenteEncadeada {

    private class No {
        Processo processo;
        No proximo;

        public No(Processo processo) {
            this.processo = processo;
            this.proximo = null;
        }
    }

    private No cabeca;

    public ListaSimplesmenteEncadeada() {
        this.cabeca = null;
    }

    public void inserir(Processo processoNovo) {
        No novoNo = new No(processoNovo);
        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            No noAtual = cabeca;
            while (noAtual.proximo != null) {
                noAtual = noAtual.proximo;
            }
            noAtual.proximo = novoNo;
        }
    }

    public void remover(String identificador) {
        if (cabeca == null) {
            return;
        }

        if (cabeca.processo.getIdentificador().equals(identificador)) {
            cabeca = cabeca.proximo;
            return;
        }
        No noAtual = cabeca;
        while (noAtual.proximo != null) {
            if (noAtual.proximo.processo.getIdentificador().equals(identificador)) {
                noAtual.proximo = noAtual.proximo.proximo;
                return;
            }
            noAtual = noAtual.proximo;
        }
    }

    public void imprimirEstado() {
        if (cabeca == null) {
            System.out.println("Lista vazia!");
            return;
        }
        No noAtual = cabeca;
        while (noAtual != null) {
            System.out.println("- " + noAtual.processo.getIdentificador());
            noAtual = noAtual.proximo;
        }
    }

    public static void main(String[] args) {
        ListaSimplesmenteEncadeada lista = new ListaSimplesmenteEncadeada();
        System.out.println("=== DEMONSTRAÇÃO DE LISTA SIMPLESMENTE ENCADEADA ===\n");

        System.out.println("[1] Inserindo 3 processos...");
        lista.inserir(new Processo("Processo-A", 1));
        lista.inserir(new Processo("Processo-B", 2));
        lista.inserir(new Processo("Processo-C", 3));

        System.out.println("Estado da Lista:");
        lista.imprimirEstado();

        System.out.println("\n[2] Removendo Processo-B...");
        lista.remover("Processo-B");
        System.out.println("Estado da Lista:");
        lista.imprimirEstado();

        System.out.println("\n[3] Removendo Processo-A e Processo-C...");
        lista.remover("Processo-A");
        lista.remover("Processo-C");
        System.out.println("Estado da Lista:");
        lista.imprimirEstado();

        System.out.println("\n[4] Tentando remover em lista vazia...");
        lista.remover("Processo-X");
        System.out.println("Estado da Lista:");
        lista.imprimirEstado();
    }
}

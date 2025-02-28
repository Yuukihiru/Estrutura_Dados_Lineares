package estrutura.linear.lista;

import estrutura.linear.Processo;

public class ListaDuplamenteEncadeada {

    private class No {
        private Processo processo;
        private No anterior;
        private No proximo;

        public No(Processo processo, No anterior, No proximo) {
            this.processo = processo;
            this.anterior = anterior;
            this.proximo = proximo;
        }

        public Processo getProcesso() {
            return processo;
        }

        public No getAnterior() {
            return anterior;
        }

        public void setAnterior(No anterior) {
            this.anterior = anterior;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }

    private No cabeca = null;
    private No cauda = null;

    public void inserir(Processo processo) {
        No novoNo = new No(processo, cauda, null);
        if (cabeca == null) {
            cabeca = novoNo;
        } else {
            cauda.setProximo(novoNo);
        }
        cauda = novoNo;
    }

    public void remover(String identificador) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.getProcesso().getIdentificador().equals(identificador)) {
                if (atual == cabeca) {
                    cabeca = atual.getProximo();
                    if (cabeca != null) {
                        cabeca.setAnterior(null);
                    }
                } else {
                    atual.getAnterior().setProximo(atual.getProximo());
                }

                if (atual == cauda) {
                    cauda = atual.getAnterior();
                } else if (atual.getProximo() != null) {
                    atual.getProximo().setAnterior(atual.getAnterior());
                }
                return;
            }
            atual = atual.getProximo();
        }
    }

    public void imprimirEstado() {
        No atual = cabeca;
        if (atual == null) {
            System.out.println("Lista vazia!");
            return;
        }
        while (atual != null) {
            System.out.println("- " + atual.getProcesso().getIdentificador());
            atual = atual.getProximo();
        }
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        System.out.println("=== DEMONSTRAÇÃO DE LISTA DUPLAMENTE ENCADEADA ===\n");

        System.out.println("[1] Inserindo 3 processos...");
        lista.inserir(new Processo("Processo-1", 1));
        lista.inserir(new Processo("Processo-2", 2));
        lista.inserir(new Processo("Processo-3", 3));

        System.out.println("Estado da Lista:");
        lista.imprimirEstado();

        System.out.println("\n[2] Removendo Processo-2...");
        lista.remover("Processo-2");
        System.out.println("Estado da Lista:");
        lista.imprimirEstado();

        System.out.println("\n[3] Removendo Processo-1 e Processo-3...");
        lista.remover("Processo-1");
        lista.remover("Processo-3");
        System.out.println("Estado da Lista:");
        lista.imprimirEstado();

        System.out.println("\n[4] Tentando remover em lista vazia...");
        lista.remover("Processo-X");
        System.out.println("Estado da Lista:");
        lista.imprimirEstado();
    }
}

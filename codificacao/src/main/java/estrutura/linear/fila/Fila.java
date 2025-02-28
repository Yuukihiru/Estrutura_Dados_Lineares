package estrutura.linear.fila;

import estrutura.linear.Processo;
import java.util.Iterator;

public class Fila implements Iterable<Processo> {

    private Processo processoAtual;
    private Fila proximaFila;

    public Fila() {
        this.processoAtual = null;
        this.proximaFila = null;
    }

    public void inserirFila(Processo processoNovo) {
        if (this.processoAtual == null) {
            this.processoAtual = processoNovo;
            this.proximaFila = new Fila();
        } else {
            Fila temp = this;
            while (temp.proximaFila != null && temp.proximaFila.processoAtual != null) {
                temp = temp.proximaFila;
            }
            temp.proximaFila.processoAtual = processoNovo;
            temp.proximaFila.proximaFila = new Fila();
        }
    }

    public Processo removerFila() {
        if (this.processoAtual == null) {
            System.out.println("Fila vazia! Não é possível remover.");
            return null;
        }
        Processo removido = this.processoAtual;
        if (this.proximaFila == null || this.proximaFila.processoAtual == null) {
            this.processoAtual = null;
            this.proximaFila = null;
        } else {
            this.processoAtual = this.proximaFila.processoAtual;
            this.proximaFila = this.proximaFila.proximaFila;
        }
        return removido;
    }

    public boolean filaVazia() {
        return (this.processoAtual == null);
    }

    public void imprimirEstado() {
        if (filaVazia()) {
            System.out.println("Fila vazia!");
        } else {
            imprimirEstadoAux();
        }
    }

    private void imprimirEstadoAux() {
        System.out.println("- " + this.processoAtual.getIdentificador());
        if (this.proximaFila != null && this.proximaFila.processoAtual != null) {
            this.proximaFila.imprimirEstadoAux();
        }
    }
    
    // Implementação do Iterator para a Fila
    @Override
    public Iterator<Processo> iterator() {
        return new FilaIterator(this);
    }
    
    private class FilaIterator implements Iterator<Processo> {
        private Fila current;
        
        public FilaIterator(Fila inicio) {
            this.current = inicio;
            if (current != null && current.processoAtual == null) {
                current = null;
            }
        }
        
        @Override
        public boolean hasNext() {
            return current != null && current.processoAtual != null;
        }
        
        @Override
        public Processo next() {
            Processo p = current.processoAtual;
            current = current.proximaFila;
            return p;
        }
    }

    public static void main(String[] args) {
        Fila fila = new Fila();
        
        System.out.println("=== DEMONSTRAÇÃO DE FILA ===\n");

        System.out.println("[1] Inserindo 3 processos...");
        fila.inserirFila(new Processo("Processo 1", 1));
        fila.inserirFila(new Processo("Processo 2", 2));
        fila.inserirFila(new Processo("Processo 3", 3));

        System.out.println("Estado da Fila:");
        fila.imprimirEstado();

        System.out.println("\nIterando pela fila com for-each:");
        for(Processo p : fila) {
            System.out.println("-> " + p.getIdentificador());
        }
        
        System.out.println("\n[2] Removendo 1 processo...");
        Processo removido = fila.removerFila();
        if (removido != null) {
            System.out.println("Removido: " + removido.getIdentificador());
        }

        System.out.println("Estado da Fila:");
        fila.imprimirEstado();

        System.out.println("\n[3] Removendo mais 2 processos...");
        fila.removerFila();
        fila.removerFila();

        System.out.println("Estado da Fila:");
        fila.imprimirEstado();

        System.out.println("\n[4] Tentando remover de uma fila vazia...");
        fila.removerFila();
    }
}

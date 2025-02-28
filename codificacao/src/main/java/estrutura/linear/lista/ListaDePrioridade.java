package estrutura.linear.lista;

import estrutura.linear.NoComUnicaLigacao;
import estrutura.linear.Processo;
import java.util.Iterator;

public class ListaDePrioridade implements Iterable<Processo> {
    private NoComUnicaLigacao cabeca;
    
    public ListaDePrioridade() {
        this.cabeca = null;
    }
    
    /**
     * Insere um novo processo na lista de forma ordenada pela prioridade.
     */
    public void inserir(Processo processo) {
        NoComUnicaLigacao novoNo = new NoComUnicaLigacao(processo);
        if (cabeca == null || processo.getPrioridade() < cabeca.processo.getPrioridade()) {
            novoNo.proximo = cabeca;
            cabeca = novoNo;
        } else {
            NoComUnicaLigacao atual = cabeca;
            while (atual.proximo != null && atual.proximo.processo.getPrioridade() <= processo.getPrioridade()) {
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo;
            atual.proximo = novoNo;
        }
    }
    
    /**
     * Remove o processo de maior prioridade (cabeça da lista).
     */
    public void remover() {
        if (cabeca != null) {
            cabeca = cabeca.proximo;
        }
    }
    
    /**
     * Imprime todos os processos com suas prioridades.
     */
    public void imprimir() {
        NoComUnicaLigacao atual = cabeca;
        while (atual != null) {
            System.out.println(atual.processo.getIdentificador() + " (Prioridade: " + atual.processo.getPrioridade() + ")");
            atual = atual.proximo;
        }
    }
    
    @Override
    public Iterator<Processo> iterator() {
        return new ListaDePrioridadeIterator(cabeca);
    }
    
    // Iterator interno para percorrer a lista.
    private class ListaDePrioridadeIterator implements Iterator<Processo> {
        private NoComUnicaLigacao atual;
        
        public ListaDePrioridadeIterator(NoComUnicaLigacao inicio) {
            this.atual = inicio;
        }
        
        @Override
        public boolean hasNext() {
            return atual != null;
        }
        
        @Override
        public Processo next() {
            Processo processo = atual.processo;
            atual = atual.proximo;
            return processo;
        }
    }
    
    public static void main(String[] args) {
        ListaDePrioridade lista = new ListaDePrioridade();
        
        lista.inserir(new Processo("Processo 1", 1));
        lista.inserir(new Processo("Processo 2", 2));
        lista.inserir(new Processo("Processo 3", 3));
        
        System.out.println("Lista de prioridade após inserções:");
        lista.imprimir();
        
        lista.remover();
        System.out.println("Lista de prioridade após remoção do mais prioritário:");
        lista.imprimir();
        
        // Exemplo de iteração usando for-each
        System.out.println("Iterando com for-each:");
        for (Processo p : lista) {
            System.out.println("-> " + p.getIdentificador() + " (Prioridade: " + p.getPrioridade() + ")");
        }
    }
}

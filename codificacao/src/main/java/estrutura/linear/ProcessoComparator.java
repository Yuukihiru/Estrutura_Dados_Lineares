package estrutura.linear;

import java.util.Comparator;

public class ProcessoComparator implements Comparator<Processo> {
    @Override
    public int compare(Processo p1, Processo p2) {
        // Menor valor indica maior prioridade.
        return Integer.compare(p1.getPrioridade(), p2.getPrioridade());
    }
}

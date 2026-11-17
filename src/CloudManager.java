import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;




public class CloudManager {
    
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public CloudManager(){
    }

    public void registrarCliente(Cliente c){
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    public void provisionarServidor(Cliente c, Servidor s){
        bancoDeDados.computeIfAbsent(c, chave -> new HashSet<>()).add(s);
    }

    public double calcularFaturaAtiva(Cliente c){
        return bancoDeDados.getOrDefault(c, Collections.emptySet())
            .stream()
            .filter(Servidor::isStatusLigado)
            .mapToDouble(Servidor::getPrecoPorHora)
            .sum();
    }
}

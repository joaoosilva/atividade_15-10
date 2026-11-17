import java.util.ArrayList;

public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private ArrayList<Container> containers = new ArrayList<>();

    public Servidor(String ip, double precoPorHora, double ramTotal){
        this.ip = ip;
        this.precoPorHora = precoPorHora;
        this.ramTotal = ramTotal;
        this.statusLigado = false;
    }

    public void ligar(){
        statusLigado = true;
    }

    public void desligar(){
        statusLigado = false;
    }

    /*===================================================== */

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public void setPrecoPorHora(double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public double getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(double ramTotal) {
        this.ramTotal = ramTotal;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    public void setStatusLigado(boolean statusLigado) {
        this.statusLigado = statusLigado;
    }

    public void alocarContainer(Container c){
        if (!statusLigado){
            throw new ServidorOfflineException(
                "Erro: não é possível alocar containers em um servidor desligado.");
        }
        
        if(calcularRamEmUso() + c.getRamNecessaria() > ramTotal){
            throw new CapacidadeExcedidaException(  
                "Falha crítica: Memória RAM insuficiente no servidor [" + ip + "].");

        }
        containers.add(c);
    }

    public double calcularRamEmUso(){
        return containers.stream()
        .mapToDouble(Container::getRamNecessaria)
        .sum();
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }

        if(!(obj instanceof Servidor)){
            return false;
        }

        Servidor outro = (Servidor) obj;

        return ip.equals(outro.ip);
    }


    @Override
    public int hashCode(){
        return ip.hashCode();
    }
}

public class MainCloud {
    public static void main(String[] args) {
        
        CloudManager manager = new CloudManager();

        Cliente cliente = new Cliente("12345678900", "Wayne Enterprises");

        manager.registrarCliente(cliente);

        Servidor servidor = new Servidor("192.168.0.1", 5.50, 16.0);

        manager.provisionarServidor(cliente, servidor);

        Container c1 = new Container("C-01", "Aplicação", 10.0);
        Container c2 = new Container("C-02", "Aplicação",8.0);
 
        servidor.ligar();

        try{
            servidor.alocarContainer(c1);
        } catch(ServidorOfflineException | CapacidadeExcedidaException e){
            System.out.println(e.getMessage());
        }
 
        try {
            servidor.alocarContainer(c2);
        } catch (ServidorOfflineException | CapacidadeExcedidaException e){
            System.out.println(e.getMessage()); 
        }

        servidor.desligar();

        try {
            servidor.alocarContainer(c1);
        } catch (ServidorOfflineException | CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        servidor.ligar();

        double fatura = manager.calcularFaturaAtiva(cliente);
        System.out.println("Fatura: R$:" + fatura);
    }
}

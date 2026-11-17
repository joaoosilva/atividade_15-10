
    public class Cliente {
        private String cpf;
        private String nome;

        public Cliente(String cpf, String nome){
            this.cpf = cpf;
            this.nome = nome;
        }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
       
        if(!(obj instanceof Cliente)){
            return false;
        }

        Cliente outro = (Cliente) obj;

        return cpf.equals(outro.cpf);
    }

    @Override
    public int hashCode(){
        return cpf.hashCode();
    }
    }
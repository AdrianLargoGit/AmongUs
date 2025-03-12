public class Clave {
    private String claveLetras;
    private String claveDigitos;

    public Clave(int longitud) {
        claveLetras = "";
        claveDigitos = "";
        for (int i = 1; i <= longitud; i++) {
            int valor = (int) (Math.random() * 26);
            claveLetras = claveLetras + (char)('A' + valor);
        }
        for (int i = 1; i <= longitud; i++) {
            int valor = (int)(Math.random() * 10);
            claveDigitos = claveDigitos + (char)('0' + valor);
        }
    }

    public Clave(String claveLetras, String claveDigitos) {
        this.claveLetras = claveLetras;
        this.claveDigitos = claveDigitos;
    }

    public boolean equals(Clave clave) {
        return this.claveLetras.equals(clave.claveLetras.toUpperCase()) &&
                this.claveDigitos.equals(clave.claveDigitos);
    }

    public Clave getDistorsionada() {
        String letras = "";
        for (int i = 0; i < claveLetras.length(); i++) {
            int cambio = (int)(Math.random() * 3) - 1;
            char letra = (char)(claveLetras.charAt(i) + cambio);
            switch(letra){
                case '@':
                    letra = 'Z';
                    break;
                case '[':
                    letra = 'A';
                    break;
            }
            letras = letras + letra;
        }
        String digitos = "";
        for (int i = 0; i < claveDigitos.length(); i++) {
            int cambio = (int)(Math.random() * 3) - 1;
            char digito = (char)(claveDigitos.charAt(i) + cambio);
            switch(digito){
                case '/':
                    digito = '9';
                    break;
                case ':':
                    digito = '0';
                    break;
            }
            digitos = digitos + digito;
        }
        return new Clave(letras, digitos);
    }

    public String toString(){
        return "<" + claveLetras + "," + claveDigitos + ">";
    }
}

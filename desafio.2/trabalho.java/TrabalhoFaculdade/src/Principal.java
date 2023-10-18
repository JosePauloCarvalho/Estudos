import java.time.LocalDate;
import java.time.Year;
import javax.swing.JOptionPane;

class Exame {
    protected String nomePaciente;
    private String tipoSanguineo;
    private Year anoNascimento;
    private double resultado;

    public Exame(String nomePaciente, String tipoSanguineo, Year anoNascimento, double resultado) {
        this.nomePaciente = nomePaciente;
        this.tipoSanguineo = tipoSanguineo;
        this.anoNascimento = anoNascimento;
        this.resultado = resultado;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public int getIdade() {
        int currentYear = LocalDate.now().getYear();
        return currentYear - anoNascimento.getValue();
    }

    public double getResultado() {
        return resultado;
    }

    public String classificarResultado() {
        return "";
    }

    public void mostrarResultado() {
        String mensagem = "Resultado do exame: " + resultado + "\nClassificação: " + classificarResultado();
        JOptionPane.showMessageDialog(null, mensagem);
    }
}

class ExameGlicemia extends Exame {
    private double glicose;

    public ExameGlicemia(String nomePaciente, String tipoSanguineo, Year anoNascimento, double resultado, double glicose) {
        super(nomePaciente, tipoSanguineo, anoNascimento, resultado);
        this.glicose = glicose;
    }

    @Override
    public String classificarResultado() {
        if (glicose < 100) {
            return "Normoglicemia";
        } else if (glicose < 126) {
            return "Pré-diabetes";
        } else {
            return "Diabetes estabelecido";
        }
    }

    public static ExameGlicemia criarExameGlicemia() {
        String nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente:");
        String tipoSanguineo = JOptionPane.showInputDialog("Digite o tipo sanguíneo:");
        Year anoNascimento = Year.of(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de nascimento do paciente:")));
        double glicose = Double.parseDouble(JOptionPane.showInputDialog("Digite o nível de glicose:"));

        return new ExameGlicemia(nomePaciente, tipoSanguineo, anoNascimento, glicose, glicose);
    }
}

class ExameColesterol extends Exame {
    private double ldl;
    private double hdl;
    private char risco;

    public ExameColesterol(String nomePaciente, String tipoSanguineo, Year anoNascimento, double resultado, double ldl, double hdl, char risco) {
        super(nomePaciente, tipoSanguineo, anoNascimento, resultado);
        this.ldl = ldl;
        this.hdl = hdl;
        this.risco = risco;
    }

    @Override
    public String classificarResultado() {
        if (risco == 'B') {
            if (ldl < 100) {
            }
        } else if (risco == 'M') {
            if (ldl < 70) {
            }
        } else if (risco == 'A') {
            if (ldl < 50) {
            }
        } 
           String hdlClassification = "";
            if (risco == 'B') {
                if (hdl < 100) {
                 hdlClassification = "Risco Baixo (HDL abaixo de 100 mg/dL)";
                }
          } else if (risco == 'M') {
              if (hdl < 70) {
                hdlClassification = "Risco Médio (HDL abaixo de 70 mg/dL)";
            }
        } else if (risco == 'A') {
            if (hdl < 50) {
                hdlClassification = "Risco Alto (HDL abaixo de 50 mg/dL)";
            }
        }
        return hdlClassification;
    }

    public static ExameColesterol criarExameColesterol() {
        String nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente:");
        String tipoSanguineo = JOptionPane.showInputDialog("Digite o tipo sanguíneo:");
        Year anoNascimento = Year.of(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de nascimento do paciente:")));
        double ldl = Double.parseDouble(JOptionPane.showInputDialog("Digite o nível de LDL:"));
        double hdl = Double.parseDouble(JOptionPane.showInputDialog("Digite o nível de HDL:"));
        char risco = JOptionPane.showInputDialog("Digite o risco (B, M ou A):").charAt(0);

        return new ExameColesterol(nomePaciente, tipoSanguineo, anoNascimento, ldl, hdl, risco, risco);
    }
}

    class ExameTriglicerideos extends Exame {
    private double triglicerideos;

    public ExameTriglicerideos(String nomePaciente, String tipoSanguineo, Year anoNascimento, double resultado, double triglicerideos) {
        super(nomePaciente, tipoSanguineo, anoNascimento, resultado);
        this.triglicerideos = triglicerideos;
    }

    @Override
    public String classificarResultado() {
        int idade = getIdade();
        if (idade <= 9) {
            if (triglicerideos < 75) {
                return "Com jejum: inferior a 75 mg/dL";
            }
        } else if (idade <= 19) {
            if (triglicerideos < 90) {
                return "Com jejum: inferior a 90 mg/dL";
            }
        } else {
            if (triglicerideos < 150) {
                return "Com Jejum: inferior a 150 mg/dL";
            }
        }
        return nomePaciente;
    }
    
    /**
     * @return
     */
    public static ExameTriglicerideos criarExameTriglicerideos(){
        String nomePaciente = JOptionPane.showInputDialog("Digite o nome do paciente:");
        String tipoSanguineo = JOptionPane.showInputDialog("Digite o tipo sanguíneo:");
        Year anoNascimento = Year.of(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de nascimento do paciente:")));
        double triglicerideos = Double.parseDouble(JOptionPane.showInputDialog("Digite o nível de triglicerídeos:"));

        ExameTriglicerideos exameTriglicerideos = new ExameTriglicerideos(nomePaciente, tipoSanguineo, anoNascimento, triglicerideos, triglicerideos);
        
        exameTriglicerideos.mostrarResultado();

        return exameTriglicerideos;
    }

    public static void main(String[] args) {
        criarExameTriglicerideos();
    }
}

public class Principal {
    public static void main(String[] args) {
        ExameGlicemia exameGlicemia = ExameGlicemia.criarExameGlicemia();
        exameGlicemia.mostrarResultado();

        ExameColesterol exameColesterol = ExameColesterol.criarExameColesterol();
        exameColesterol.mostrarResultado();
        
        ExameTriglicerideos exameTriglicerideos = ExameTriglicerideos.criarExameTriglicerideos();
        exameTriglicerideos.mostrarResultado();
    }
}




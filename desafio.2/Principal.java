import java.time.LocalDate;
import java.time.Year;

// Classe base para os exames
class Exame {
    private String nomePaciente;
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

    // Método para ser sobrescrito nas subclasses
    public String classificarResultado() {
        return "";
    }

    public void mostrarResultado() {
        System.out.println("Resultado do exame: " + resultado);
        System.out.println("Classificação: " + classificarResultado());
    }
}

// Subclasse para o exame de Glicemia
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
}

// Subclasse para o exame de Colesterol
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
        String ldlClassification = "";
        if (risco == 'B') {
            if (ldl < 100) {
                ldlClassification = "Risco Baixo (LDL abaixo de 100 mg/dL)";
            }
        } else if (risco == 'M') {
            if (ldl < 70) {
                ldlClassification = "Risco Médio (LDL abaixo de 70 mg/dL)";
            }
        } else if (risco == 'A') {
            if (ldl < 50) {
                ldlClassification = "Risco Alto (LDL abaixo de 50 mg/dL)";
            }
        }

        String hdlClassification = "";
        int idade = getIdade();
        if (idade <= 19) {
            if (hdl > 45) {
                hdlClassification = "HDL - BOM (acima de 45 mg/dL)";
            }
        } else {
            if (hdl > 40) {
                hdlClassification = "HDL - BOM (acima de 40 mg/dL)";
            }
        }

        return ldlClassification + " | " + hdlClassification;
    }
}

// Subclasse para o exame de Triglicerídeos
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

        return "";
    }
}

public class Principal {
    public static void main(String[] args) {
        Exame exameGlicemia = new ExameGlicemia("João", "A+", Year.of(1985), 120, 130);
        exameGlicemia.mostrarResultado();

        Exame exameColesterol = new ExameColesterol("Maria", "B-", Year.of(2000), 220, 80, 50, 'M');
        exameColesterol.mostrarResultado();

        Exame exameTriglicerideos = new ExameTriglicerideos("Pedro", "O+", Year.of(2015), 180, 80);
        exameTriglicerideos.mostrarResultado();
    }
}

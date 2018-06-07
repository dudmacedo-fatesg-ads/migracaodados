package br.eti.eduardomacedo.ads5.migracaodados;

import br.eti.eduardomacedo.ads5.migracaodados.model.Lote;
import br.eti.eduardomacedo.ads5.migracaodados.negocio.NLote;
import br.eti.eduardomacedo.ads5.migracaodados.persistence.LoteDAO;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Argumentos {

    @Option(name = "-i", usage = "Indica o caminho do arquivo/pasta a importar")
    private String caminho;

    public void run() {
        File input = new File(caminho);

        if (input.isFile()) {
            importarDoArquivo(input);
        } else {
            List<File> arquivos = new LinkedList<>();
            try {
                Files.walk(Paths.get(caminho))
                        .filter((f) -> {return f.toFile().isFile();})
                        .forEach((f) -> {
                            arquivos.add(new File(f.toString()));
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (File arq : arquivos) {
                importarDoArquivo(arq);
            }
        }
    }

    private void importarDoArquivo(File arq) {
        System.out.print("Importando " + arq.getName() + "... ");
        Lote l = NLote.getLote(arq);

        if (l != null) {
            LoteDAO lotdao = new LoteDAO();

            lotdao.insert(l);
        }
        System.out.println("OK");
    }
}

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProcesaParrafo extends HTMLEditorKit.ParserCallback {

    private static final String KEYWORD = "Cabras";
    private boolean inParagraph;
    private BufferedWriter logWriter;
    private String htmlFileName;

    public ProcesaParrafo(String htmlFileName) {
        this.inParagraph = false;
        this.htmlFileName = htmlFileName;
        try {
            // Crea el archivo de log
this.logWriter = new BufferedWriter(new FileWriter("file-" + KEYWORD + ".log"));
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

@Override
public void handleText(char[] data, int pos) {
    if (inParagraph) {
        String texto = new String(data).toLowerCase(); // Convertir a minúsculas para comparación
        String keywordLower = KEYWORD.toLowerCase();   // Convertir la palabra clave a minúsculas

        int index = 0;
        while ((index = texto.indexOf(keywordLower, index)) != -1) {
            System.out.println("Se encontró '" + KEYWORD + "' en la posición: " + pos);

            // Escribir en el archivo de log
            try {
                logWriter.write("Archivo: " + htmlFileName + " - Posición de '" + KEYWORD + "': " + pos + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            index += keywordLower.length(); // Avanzar para encontrar más palabras
        }
    }
}

@Override
public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
    if (t == HTML.Tag.P) {
        inParagraph = true;
    }
}

@Override
public void handleEndTag(HTML.Tag t, int pos) {
    if (t == HTML.Tag.P) {
        inParagraph = false;
    }
}

// Método para cerrar el archivo log cuando termine el procesamiento
public void closeLogFile() {
    try {
        if (logWriter != null) {
            logWriter.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
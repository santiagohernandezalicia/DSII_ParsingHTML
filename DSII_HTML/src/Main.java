import javax.swing.text.html.HTMLEditorKit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        if (args.length != 1) {
            LOG.severe("Falta incluir el documento HTML.");
            System.exit(1);
        }

        String archivoHTML = args[0];

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(archivoHTML);
        } catch (FileNotFoundException e) {
            LOG.severe("No se puede abrir el documento HTML.");
            System.exit(2); }


        // Crear una instancia de HTMLParser
        HTMLParser htmlParser = new HTMLParser();
        ProcesaParrafo procesadorParrafo = new ProcesaParrafo();

        // Obtener el parser desde HTMLParser
        HTMLEditorKit.Parser parser = htmlParser.getParser();

        try {
            parser.parse(fileReader, procesadorParrafo, true);
        } catch (IOException e) {
            LOG.severe("No se puede leer el documento HTML.");
            System.exit(2);
        }
    }
}
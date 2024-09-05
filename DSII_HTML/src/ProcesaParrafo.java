import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class ProcesaParrafo extends HTMLEditorKit.ParserCallback {

    // Palabra clave
    private static final String KEYWORD = "Cabras";
    private boolean inParagraph;

    public ProcesaParrafo() {
        inParagraph = false;
    }

    @Override
    public void handleText(char[] data, int pos) {
        if (inParagraph) {
            String texto = new String(data).toLowerCase(); // Convertir a minúsculas para comparación
            String keywordLower = KEYWORD.toLowerCase();   // Convertir la palabra clave a minúsculas

            int index = 0;
            while ((index = texto.indexOf(keywordLower, index)) != -1) {
                // La posición en el documento será pos + index
                System.out.println("Se encontro '"  + KEYWORD + "' en la posición: " + (pos));
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
}


package it.unibs.fp.rovineperdute;

public class Costante {

    public static final int C0 = 0;
    public static final int C1 = 1;

    // usati nella classe XML

    public static final String ERRORE_LETTURA = "Errore nell'inizializzazione del reader:";
    public static final String ERRORE_SCRITTURA = "Errore nella scrittura";

    public static final String ID = "id";
    public static final String NOME = "name";
    public static final String COORDINATAX = "x";
    public static final String COORDINATAY = "y";
    public static final String COORDINATAH = "h";
    public static final String LINK = "link";

    public static final String ROUTES = "routes";
    public static final String ROUTE = "route";
    public static final String TEAM = "team";
    public static final String TEAM_NOME1 = "Tonathiu";
    public static final String TEAM_NOME2 = "Metztli";

    public static final String COSTO = "cost";
    public static final String NUMERO_CITTA = "cities";
    public static final String CITTA = "city";

    public static final String ENCODING = "utf-8";
    public static final String VERSION = "1.0";

    public static final String METODO_FORMATTAZIONE = "xml";
    public static final String INDENT_FORMATTAZIONE = "yes";
    public static final String HTTPS_FORMATTAZIONE = "{https://xml.apache.org/xslt}indent-amount";
    public static final String LIVELLO_INDENTAZIONE = "2";
    public static final String DICHIARAZIONE_FORMATTAZIONE = "yes";

    // usati nel Main
    public static final int[] FILE_INPUT_INDICE = {5, 12, 50, 200, 2000, 10000};
    public static final String FILE_INPUT = "./percorsi/file_input/PgAr_Map_%d.xml";
    public static final String FILE_OUTPUT = "./percorsi/file_output/Routes_%d.xml";
    public static final String FILE_ROUTES = "./percorsi/file_output/Routes.xml";
}
package it.unibs.fp.rovineperdute;

import it.unibs.fp.mylib.MyMenu;

public class Utente {


    private static MyMenu menu_file = new MyMenu(Costante.titolo_menu_file, Costante.voci_menu_file);

    public static String scegliFile(){
        int scelta = menu_file.scegli();
        String nome_file = "";

        switch(scelta){

            nome

            case 1:{
                nome_file = String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - 1]);
                break;
            }
            case 2:{
                nome_file = String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - 1]);
                break;
            }
            case 3:{
                nome_file = String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - 1]);
                break;
            }
            case 4:{
                nome_file = String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - 1]);
                break;
            }
            case 5:{
                nome_file = String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - 1]);
                break;
            }
            case 6:{
                nome_file = String.format(Costante.FILE_INPUT, Costante.FILE_INPUT_INDICE[scelta - 1]);
                break;
            }

        }

        return nome_file;
    }
}

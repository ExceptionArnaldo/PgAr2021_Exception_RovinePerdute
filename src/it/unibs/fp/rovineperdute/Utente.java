package it.unibs.fp.rovineperdute;

import it.unibs.fp.mylib.MyMenu;

public class Utente {


    private static final MyMenu menu_file = new MyMenu(Costante.titolo_menu_file, Costante.voci_menu_file);

    public static int scegliFile(){

        return menu_file.scegli();
    }
}

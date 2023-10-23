package projet;

/*
* Une énumération des différents états de correction possible: Vrai, faux, non corrigé
*/
public enum Reponse {
    VRAI, FAUX, NR;
    /*
    * Une méthode qui retourne la chaine de caractère explicitant l'état de correction
    * @return String, l'état de correction
    */
    public String result(){
        switch(this){
            case VRAI : return "correcte";
            case FAUX : return "faux";
            case NR : return "non rempli";
            default: return "non corrigé";
        }
    }
}

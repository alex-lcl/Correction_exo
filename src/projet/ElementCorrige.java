package projet;

/*
* Une classe représentant la correction d'un morceau de la phrase
*/
public class ElementCorrige{
    
    
    private Reponse rep; // l'état de la correction, si la réponse est vrai, fausse...
    private int n; // la position du morceau corrigé dans la phrase
    private String z; // la chaine de caractère du morceau
    private int note;
    /*
    * le constructeur
    * @param int x, la position du morceau corrigé dans la phrase
    * @param String s, la chaine de caractère du morceau
    * @param Morceau i, le Morceau à corriger
    * @param Morceau y, le Morceau de réference pour la correction
    */
    public ElementCorrige(int x, String s, Morceau morceau, Morceau morceau2, Notation notation){ 
        n = x;
        z= s;
        //rep = setCorrection(morceau, morceau2); // ALC
        this.setCorrection(morceau, morceau2); // CJ
        this.setNote(rep, notation);
    }
/*
* Une méthode permettant de définir l'état de correction du morceau
* @param i, le Morceau à corriger
* @param y, le Morceau de réference pour la correction
* @return rep, l'état de la correction
*/
    protected void setCorrection(Morceau i, Morceau y){
        if (i.reponseAttendue().equals(y.reponseAttendue()))
        {
            rep = Reponse.VRAI;
        }
        else if  (i.reponseAttendue().equals("...")){
            rep = Reponse.NR;
        }
        else {
            rep = Reponse.FAUX;
        }
        
    }
    /*
     * une méthode qui suivant l'état de la correction, y associe une note
     * @param rep, Reponse, l'état de la correction
     * @param notation, Notation, le type de notation
     */
    protected void setNote(Reponse rep, Notation notation) {
    	notation.note(rep);
    	note = notation.getNote();
    	
    }
    
    /*
    * une méthode qui renvoie la position du morceau corrigé dans la pharse
    * @return n, int, la position du morceau corrigé dans la pharse
    */
    public int getN(){
        return n;
    }
    /*
    * une méthode qui renvoie le morceau entrée de l'élève dans la pharse
    * @return z, String, le morceau entrée de l'élève dans la pharse
    */
    public String getZ(){
        return z;
    }
    /*
    * une méthode qui renvoie l'état de la correction
    * @return rep, Reponse, l'état de la correction
    */
    public Reponse getRep(){
        return rep;
    }
    /*
     * une méthode qui renvoie la note associé à l'élément corrigé
     * @return note, int, la valeur de la note de l'élélment corrigé.
     */
    public int getNote(){
        return note;
    }
    
}

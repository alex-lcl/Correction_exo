package projet;
import java.util.ArrayList;
import java.util.List;
/*
* Une classe qui représente la correction d'un exercice
*/
public class Correction {
    private List<ElementCorrige> correction; // la liste des éléments corrigés de l'exercice
    private int note; //la note de l'exercice
    private Resultat resultat; // le résultat de l'exercice: Succès, Echec, etc.
/*
* le constructeur ne prend pas d'argument
*/
    public Correction(){
        correction = new ArrayList<>();
        note = 0;
        resultat = Resultat.NONFAIT;
    }
    
    /*
     * une méthode qui retourne la liste correction
     * @return correction, une liste d'élément corrigés
     */
    public List<ElementCorrige> getCorrection() {
    	return correction;
    }
    /*
     * une méthode qui retourne ne résultat d'un exercice
     * @return resltat, Resultat, le résultat de l'exercice: succès, echec, etc.
     */
    public Resultat getResultat() {
    	return resultat;
    }
    /*
     * une méthode qui attribut à résultat sa valeur
     * @param res, Resultat, un type de résultat possible: echec, succès, etc.
     */
    public void setResultat(Resultat res) {
    	resultat=res;
    }

    /*
     * une méthode qui pour une correction d'exercice, indique le type de résultat
     * pour réussir: 
     * au niveau  débutant : 50 % de la note maximale suffit, 
	 * au niveau intermédiaire : 60 % de la note maximale,  
	 * au niveau expert : 80 % de la note maximale et il ne doit pas y avoir d’exercice ayant – de 50 % de la note maximale (gameover).
     * @param etu, Etudiant, l'élève qui a éralisé l'exercice
     * @param level, NiveauLangue, le niveau de la langue de l'élève
     * @param notation, Notation, le type de notation de la correction
     */
    public void setResultat(Etudiant etu, NiveauLangue level, Notation notation) {
    	double noteMax = (double)notation.noteMax(this);
    	//si l'étudiant a un niveau débutant
    	if (etu.getNiveau(level).getNiveau().equals(Niveau.DEBUTANT)) {
    		//l'exercice est réussi si la note est au moins 50% de la note maximal possible
			double notePass = (noteMax/100)*50;
			System.out.println("Note maximum possible: "+notation.noteMax(this));
			System.out.println("Note minimum pour réussir l'exercice: "+ notePass);
			if(this.getNote() >= notePass) {
				this.setResultat(Resultat.REUSSI);
				System.out.println("L'exercice est réussi.");
			}
			else {
				this.setResultat(Resultat.ECHEC);
				System.out.println("L'exercice est un echec.");
			}
		}
    	//si l'étudiant a un niveau intermédiaire
    	else if (etu.getNiveau(level).getNiveau().equals(Niveau.INTERMEDIAIRE)) {
    		//l'exercice est réussi si la note est au moins 60% de la note maximal possible
			double notePass = (noteMax/100)*60;
			if(this.getNote() >= notePass) {
				this.setResultat(Resultat.REUSSI);
				System.out.println("L'exercice est réussi.");
			}
			else {
				this.setResultat(Resultat.ECHEC);
			}
		}
    	//si l'étudiant a un niveau avancé
    	//(etu.getNiveau(level).getNiveau().equals(Niveau.AVANCE))
    	else  {
    		//l'exercice est réussi si la note est au moins 80% de la note maximal possible
    		//et s'il n'y a pas d'exercice dont la note est en dessous de 50% de la note maximal possible
			double notePass = (noteMax/100)*80;
			double noteMin = (noteMax/100)*50;
			if(this.getNote() < noteMin) {
				this.setResultat(Resultat.GAMEOVER);
				System.out.println("L'exercice est un echec.");
			}
			else if(this.getNote() >= notePass) {
				this.setResultat(Resultat.REUSSI);
				System.out.println("L'exercice est réussi.");
			}
			
			else if(this.getNote() < notePass) {
				this.setResultat(Resultat.ECHEC);
				System.out.println("L'exercice est un echec.");
			}
		}
}
/*
* une méthode qui corrige une phrase
* @param p2, la Phrase à corriger
* @param p1, la Phrase de référence
* @return correction, une liste de ElementCorrige, les morecaux corrigés
*/
    public void corrige (Phrase p2, Phrase p1, Notation n){
        int i=0;
        while (i<p2.size()){ //pour chaque élement dans p2
            //corriger l'élément
            if (p1.get(i) instanceof MorceauVariable){
                ElementCorrige c = new ElementCorrige(i, p2.reponseAttendue(i), p2.get(i), p1.get(i), n);
                //l'ajouter à la liste de correction
                this.correction.add(c);
                this.note(c);
                System.out.println("Le morceau: '" + c.getZ() + "' à la position " + c.getN() + " est " + c.getRep().result());
            }
            i++;
        }
    }
    /*
     * une méthode qui ajoute la note de l'élément corrigé à la note de l'exercice
     * @param c, EelementCorrige
     */
    public void note(ElementCorrige c) {
    	note = note + c.getNote();
    }
    /*
     * une méthode qui retourne la note de l'exercice
     * @return note, int, la note de l'exercice
     */
    public int getNote() {
    	return note;
    }
    /*
    * une méthode qui affiche la correction
    */
    public void affiche(){
        for (ElementCorrige c : correction){
            System.out.println("Le morceau: '" + c.getZ() + "' à la position " + c.getN() + " est " + c.getRep().result());
        }
    }
    /*
     * une méthode qui affiche la note d'un exercice
     */
    public void afficheNote() {
    	System.out.println("La note est de: "+this.getNote());
    }
}

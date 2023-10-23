package projet;
/*
 * une notation de type difficile
 */
public class NotationDifficile implements Notation {
	int n;
	/*
	 * constructeur vide
	 */
	public NotationDifficile() {
	}
	/*
	 * méthode qui pour une réponse vrai/fausse, donne les points correspondant
	 * @param rep, Reponse, si l'élément corrigé est vrai/faux/non fait/...
	 */
	@Override
	public void note(Reponse rep) {
		switch(rep) {
		case VRAI:
			n=1;
			break;
		case NR:
			n=0;
			break;
		case FAUX:
			n=-1;
			break;
		default:
			n=0;
			break;
		}

	}
	/*
	 * une méthode retournant la valeur de la note
	 * @return n, la valeur d'un élément corrigé
	 */
	@Override
	public int getNote() {
		return n;
	}
	/*
	 * méthode retournant la note maximum d'un exercice
	 * @param exo, Correction, la correction d'un exercice
	 * @param n, Notation, le type de notation
	 * @return nmax, int, la note maximal de l'exercice
	 */
	@Override
	public int noteMax(Correction exo) {
		int nmax=0; //initialisation de la note maximal possible
		this.note(Reponse.VRAI); //l'élément corrigé est noté vrai
		//pour chaque élément corrigé dans l'exercice corrigé
		for (@SuppressWarnings("unused") ElementCorrige element: exo.getCorrection()) {
			nmax = nmax + this.getNote(); //on ajoute cette valeur à la note maximal possible
		}
		return nmax;
	}

}

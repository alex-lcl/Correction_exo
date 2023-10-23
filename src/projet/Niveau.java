package projet;
/*
 * enmération des différent niveau par langue possible:
 * débutant, interméidaire, anancé, nd (non défini), expert
 */
public enum Niveau {
	DEBUTANT, INTERMEDIAIRE, AVANCE, ND, EXPERT;
	/*
	 * une méthode qui pour un élément de l'énumération, retourne une chaine de caractère corespondant
	 * @return String, la chaine de caractère correspondant au niveau
	 */
	public String affiche(){
        switch(this){
            case DEBUTANT : return "débutant";
            case INTERMEDIAIRE : return "intermédaire";
            case AVANCE : return "avancé";
            case EXPERT: return "expert";
            case ND: return "non défini";
            default: return "non défini";
        }
    }
	/*
	 * une méthode pour obtenir le niveau gierarchique
	 * @retun int, l'entier associé au niveau: plus le niveau est élevé, plus l'entier est élevé
	 */
	public int getNumNiveau() {
		switch(this){
        case DEBUTANT : return 1;
        case INTERMEDIAIRE : return 2;
        case AVANCE : return 3;
        case EXPERT: return 4;
        case ND: return 0;
        default: return 0;
    }
	}
}

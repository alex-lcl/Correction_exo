package projet;
/*
* une classe Morceau représantant les morceaux des phrases
*
*/
        
public abstract class Morceau {

    public String morceau;
    public int position;
/* 
* Déclaration du constructeur
* @param token, une chaine de caractère
* @param valP, un integer marquant la positin du token du morceau dans la phrase
*/
	public Morceau (String token, int valP){
		morceau = token;
		position = valP;
	}

	/*
	* Une methode pour reccupérer le morceau formaté pour l'élève
	* @return la chaine de caractère constituant le morceau
	*/
	public String pourEleve() { return morceau;}
	/*
	* Une methode pour reccupérer le morceau formaté par le Prof
	* @return la chaine de caractère constituant le morceau
	*/
    public String pourProf() { return morceau;}
	/*
	* Une methode pour reccupérer le morceau attendu par l'élève
	* @return la chaine de caractère constituant le morceau
	*/
    public String reponseAttendue() { return morceau;}
	/*
	* Une methode pour reccupérer la position du morceau
	* @return un integer de la position du morceau
	*/
	public int getPosition() {return position;}


}

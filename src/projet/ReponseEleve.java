package projet;

import java.util.ArrayList;
import java.util.List;
/*
 * Une classe qui repérsente la réponse de l'élève à un exercice
 */
public class ReponseEleve {
	private List<String> exercice = new ArrayList<>(); //exercice de l'élève non parsé
	private List<Phrase> exercice_parse = new ArrayList<>(); //exercice de l'élève parsé suivant l'exercice du prof
	private List<Phrase> exercice_prof = new ArrayList<>(); //exercice du prof parsé
    
	/*
	 * le constructeur
	 * @param eleve, Exo, l'exercice de l'élève
	 * @param prof, Exo, l'exercice du professeur
	 */
	public ReponseEleve(Exo eleve, Exo prof){
		exercice=eleve.getExo();
		this.exerciceParse(prof);
	}
	
	/*
	 * une méthode qui retourne une ligne de l'exercice de l'élève suivant son index
	 * @param i, int, la position de la ligne à retourner dans la liste de string exercice
	 * @return l'élément de la liste corespondant à l'index i.
	 */
	public String getReponse(int i){
        return exercice.get(i);
    }
	/*
	 * une méthode qui retourne une ligne parsé de l'exercice de l'élève suivant son index
	 * @param i, int, la position de la ligne à retourner dans la liste de string exercice
	 * @return l'élément parsé de la liste corespondant à l'index i.
	 */
	public Phrase getParseEleve(int i){
        return exercice_parse.get(i);
    }
	/*
	 * une métjode qui retourne une ligne de l'exercice du prof suivant son index
	 * @param i, int, la position de la ligne à retourner dans la liste de string exercice
	 * @return l'élément de la liste corespondant à l'index i.
	 */
	public Phrase getParseProf(int i){
        return exercice_prof.get(i);
    }
	/*
	 * une méthode qui parse l'exercice du prof et de l'élève
	 * @param prof, Exo, l'exercice du prof de référence
	 */
	protected void exerciceParse(Exo prof) {
		int i=0;
		Parser p = new Parser(); //création du parseur
        for (String s: prof.getExo()){
            Phrase s2= new Phrase(p.parse(s)); //on parse la phrase du prof
            exercice_parse.add(s2.annalyseReponseEleve(this.getReponse(i))); //on ajoute la phrase parsé de l'élève à exercice_parse
            exercice_prof.add(s2); //on ajoute la phrase parsé du prof à exercice_prof
            i++;
        }
	}

}

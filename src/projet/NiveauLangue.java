package projet;

import java.util.ArrayList;
import java.util.List;
/*
 * une classe représentant le niveau d'une langue
 */
public class NiveauLangue {
	private Niveau niveau; //le niveau
	private Langue langue; //la langue
	private List<Etudiant> etudiants; //la liste des étudiants de ce nievau et de cette langue
	private List<Exo> listExo; //la liste des exercices associés
	/*
	 * le constructeur 
	 * @param level, Niveau, le niveau dans la langue donnée
	 * @param lang, Langue, la langue étudiée
	 */
	public NiveauLangue(Niveau level, Langue lang) {
		niveau = level;
		langue = lang;
		etudiants = new ArrayList<>();
		listExo = new ArrayList<>();
	}
	/*
	 * méthode qui retourne le niveau
	 * @return niveau, Niveau, le niveau dans la langue
	 */
	public Niveau getNiveau() {
		return niveau;
	}
	/*
	 * méthode qui retourne la langue
	 * @return langue, Langue, la langue étudiée
	 */
	public Langue getLangue() {
		return langue;
	}
	/*
	 * méthode qui retourne la liste d'étudiants
	 * @return etudiants, List<Etudiant>, la liste des étudiants de ce niveau et de cette langue
	 */
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}
	/*
	 * méthode qui ajoute un étudiant aux étudiants
	 * @param e, Etudiant, l'élève a ajouté
	 */
	public void ajoutEleve(Etudiant e) {
		etudiants.add(e);
	}
	/*
	 * une méthode qui retourne la liste des exercices de ce niveau et de cette langue
	 * @return listExo, List<Exo>, liste d'exercices de ce niveau et de cette langue
	 */
	public List<Exo> getlistExo() {
		return listExo;
	}
	/*
	 * une méthode qui ajoute un exercice à la liste d'exercice
	 * @param e, Exo, l'exercice à ajouter
	 */
	public void ajoutExo(Exo e) {
		listExo.add(e);
	}
	/*
	 * une méthode qui ajoute une liste d'exercice
	 * @param exos, List<Exo>, liste d'exercice à ajouter à cette langue et ce niveau
	 */
	public void setListExo(List<Exo> exos) {
		listExo.addAll(exos);
	}
}

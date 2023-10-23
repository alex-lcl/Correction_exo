package projet;

import java.util.ArrayList;
import java.util.List;
/*
 * une classe qui représente un élève
 */
public class Etudiant {
	String numero; //le numéro étudiant
	String nom; //le nom de l'étudiant
	String prénom; //le prénom de l'étudiant
	List<NiveauLangue> niveau; //la liste de Niveau de langue de l'élève
	
	/*
	 * le constructeur
	 * @param num, string, le numéro de l'étudiant
	 * @param name, string, le prénom de l'élève
	 * @param familyName, string, le nom de famille de l'élève
	 */
	public Etudiant(String num, String name, String familyName){
		numero = num;
		nom = familyName;
		prénom = name;
		niveau = new ArrayList<>();
	}
	/*
	 * méthode qui retourne le numéro de l'élève
	 * @return numero, string, le numéro de l'élève
	 */
	public String getNumero() {
		return numero;
	}
	/*
	 * méthode qui pour un NiveauLangue donnée, retourne le niveau de l'élève
	 * @param level, NiveauLangue, la langue et un niveau associé
	 * @return niv, Niveau, le niveau de la langue
	 * ou
	 * @return null, s'il n'y a pas de langue et de niveau dans la liste de NievauLangue 
	 */
	public NiveauLangue getNiveau(NiveauLangue level) {
		
		for (NiveauLangue niv: niveau) {
			if (niv.getLangue() == level.getLangue() && niv.getNiveau() == level.getNiveau()) {
				return niv;
			}
		}
		return null;
		
	}
	/*
	 * méthode qui pour une langue donnée, retourne le niveau de l'élève
	 * @param lang, Langue, la langue cherchée
	 * @return niv, Niveau, le niveau de la langue
	 * ou
	 * @return null, s'il n'y a pas de langue dans la liste de NievauLangue 
	 */
	public Niveau getNiveau(Langue lang) {
		
		for (NiveauLangue niv: niveau) {
			if (niv.getLangue() == lang) {
				return niv.getNiveau();
			}
		}
		return null;
		
	}
	/*
	 * une méthode qui pour modifie le niveau dans une langue donnée
	 * @param level, NiveauLangue, la paire de niveau/langue avec le niveau mis à jour
	 */
	public void setNiveau(NiveauLangue level) {
		for(NiveauLangue niv: this.niveau) {
			if (niv.getLangue() == level.getLangue()) {
				this.niveau.remove(niv);
				niv.getEtudiants().remove(this);
				break;
			}
		}
		this.niveau.add(level);
		level.ajoutEleve(this);
	}
}

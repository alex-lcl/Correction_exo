package projet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/*
 * représentation de la banque d'exercices
 */
public class BanqueExo {
	private List<Exo> banque; //liste d'exercice
	private String repertoire; //le nom du répertoire
	/*
	 * le constructeur
	 * @param directory, File, le répertoire contennant des fichiers exercices
	 */
	public BanqueExo(File directory) {
		repertoire = directory.toString();
		banque = new ArrayList<>();
		this.setBanque(directory);
	}
	/*
	 * une méthode qui retourne le nom de répertoire
	 * @return repertoire, String, le nom du repertoire
	 */
	public String getRepertoire() {
		return repertoire;
	}
	/*
	 * une méthode qui enregistre dans la liste d'exercice banque, les exercices
	 * @param directory, File, le répertoire où se trouve les exercices
	 */
	protected void setBanque(File directory) {
		//pour chaque fichier dans la liste de fichier du repertoire
		for (File file : directory.listFiles()) {
			//si le fichier n'est pas un répertoire
			if (!file.isDirectory()) {
				//le fichier est un exercice
				Exo Exo = new Exo(directory.toString()+"/"+file.getName());
				//on ajoute l'exercice à la banque
				banque.add(Exo);
			}
		}
	}
	/*
	 * une méthode qui retourne la liste d'exercice de la banque
	 * @return banque, List<Exo>, la liste d'exercice
	 */
	public List<Exo> getExo() {
		return banque;
	}
	/*
	 * une méthode qui génère dans un répertoire tous les exercices de la banque d'exercice
	 * @param directoryOut, String, le répertoire où générer les exercices
	 */
	public void generation(String directoryOut) {
		//pour chaque exercice de la banque
		for (Exo exo: banque) {
			//génération de l'exercice
			exo.generationExo(directoryOut+"/"+exo.getName());
		}
	}
	/*
	 * une méthode qui génère dans un répertoire une liste d'exercice
	 * @param directoryOut, String, le répertoire où générer les exercices
	 * @param exos, List<Exo>, la liste d'exercice à générer
	 */
	public void generation(String directoryOut, List<Exo> exos) {
		//pour chaque exercice
		for (Exo ex: exos) {
			//génération de l'exercice
			ex.generationExo(directoryOut+"/"+ex.getName());
		}
	}
	/*
	 * méthode qui permet de sélectionner certains exercices de la banque
	 * @param level, Niveau, le niveau des exercices sélectionner
	 * @param lang, Langue, la langue des exercice à selectionner
	 * @return exos, List<Exo>, les exercices sélectionner 
	 */
	public List<Exo> selectionne(Niveau level, Langue lang) {
		List<Exo> exos = new ArrayList<>();
		//pour chaque exercice dans la banque
		for (Exo ex: banque) {
			//si la langue de l'exercice correspond à la langue entrée en argument
			//si le niveau de l'exercice correspond au niveau entré en argument
			if ((ex.getLangue().getNom().equals(lang.getNom())) && (ex.getNiveau().equals(level))) {
				exos.add(ex);
			}
		}
		return exos;
	}
	/*
	 * méthode qui permet de sélectionner certains exercices de la banque
	 * @param level, Niveau, le niveau des exercices sélectionner
	 * @param lang, Langue, la langue des exercice à selectionner
	 * @param catégorie, String, la catégorie de l'exercice
	 * @return exos, List<Exo>, les exercices sélectionner 
	 */
	public List<Exo> selectionne(Niveau level, Langue lang, String catégorie) {
		List<Exo> exos = new ArrayList<>();
		//pour chaque exercice dans la banque
		for (Exo ex: banque) {
			//si la catégorie de l'exercice entrée en paramètre correspond à la catégorie de l'exercice
			//si la langue de l'exercice correspond à la langue entrée en argument
			//si le niveau de l'exercice correspond au niveau entré en argument
			if ((ex.getLangue().getNom().equals(lang.getNom())) && (ex.getNiveau().equals(level)) && (ex.getCategorie().equals(catégorie)) ) {
				exos.add(ex);
			}
		}
		return exos;
	}
	/*
	 * méthode qui permet de sélectionner certains exercices de la banque
	 * @param catégorie, String, la catégorie de l'exercice
	 * @return exos, List<Exo>, les exercices sélectionner 
	 */
	public List<Exo> selectionne(String catégorie) {
		List<Exo> exos = new ArrayList<>();
		//pour chaque exercice dans la banque
		for (Exo ex: banque) {
			//si la catégorie de l'exercice entrée en paramètre correspond à la catégorie de l'exercice
			if ((ex.getCategorie().equals(catégorie)) ) {
				exos.add(ex);
			}
		}
		return exos;
	}
	/*
	 * méthode qui ajoute les exercices au Niveau de langue
	 * @param niv, NiveauLangue, le niveau de la langue 
	 */
	public void ajoutExos(NiveauLangue niv) {
		List<Exo> exos = this.selectionne(niv.getNiveau(), niv.getLangue());
		niv.setListExo(exos);
	}
	/*
	 * une méthode qui retourne la liste des catégories des exercices de la banque
	 * @return cats, la liste des catégorie des exercices de la banque
	 */
	public List<String> getCatégories(){
		List<String> cats = new ArrayList<>();
		//pour chaque exercice de la banque
		for (Exo ex: banque) {
			//si la catégorie de l'exercice n'est pas dans la liste
			if (!cats.contains(ex.getCategorie())){
				//on l'ajoute dans la liste
				cats.add(ex.getCategorie());
			}
		}
		return cats;
	}

}

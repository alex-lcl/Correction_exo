package projet;

import java.util.ArrayList;
import java.util.List;
/*
 * représentation du professeur : correction automatique
 */
public class RobotProf {
	List<Correction> corrections; //la liste des exercices corrigés d'une catégorie
	Notation notation; //le type de notation
	List<Boolean>  listeReussiteCats; //par catégorie, liste indiquant que la quantité d'exercice réussite est suffisante
	
	/*
	 * constructeur sans argument, avec une notation d'office standard
	 */
	public RobotProf() {
		corrections = new ArrayList<>();
		listeReussiteCats = new ArrayList<>();
		notation = new NotationStandard();
	}
	/*
	 * constructeur avec un argument, un type de notation à rentrer en argument
	 * @param n, String, le type de notation
	 */
	public RobotProf(String n) {
		corrections = new ArrayList<>();
		listeReussiteCats = new ArrayList<>();
		this.setNotation(n);
	}
	/*
	 * une méthode permetant de définir le type de notation
	 * @param n, String, le type de notation: indulgente, standard, difficile, peau de vache
	 */
	protected void setNotation (String n) {
		switch(n){
		case "indulgente":
			notation = new NotationIndulgente();
		case "standard":
			notation = new NotationStandard();
		case "difficile":
			notation = new NotationDifficile();
		case "peau de vache":
			notation = new NotationPeauDeVache();
		default:
			notation = new NotationStandard();
		}
	}
	/*
	 * méthode pour la correction d'un exercice
	 * @param prof, Exo, l'exercice de référence du professeur
	 * @param eleve, Exo, l'exercice de l'élève
	 * @param etu, Etudiant, l'élève ayant réalsié l'exercice
	 * @param level, NiveauLangue, le nievau de langue de l'élève
	 */
	public void exoCorrection(Exo Prof, Exo Eleve, Etudiant etu, NiveauLangue level){
        int i=0;
        ReponseEleve rep= new ReponseEleve(Eleve, Prof); //la réponse de l'élève à l'exercice
        Correction cor = new Correction();
        //pour chaque ligne de l'exercice
        for (@SuppressWarnings("unused") String s: Prof.getExo()){ 
        	//affichage de la ligne produite par l'élève
            System.out.println(rep.getReponse(i));
            //correction de cette ligne par rapport à la ligne du prof
            cor.corrige(rep.getParseEleve(i), rep.getParseProf(i), this.notation);
            i++;
        }
        //affichage de la note de l'exercice
        cor.afficheNote();
        //ajout de la correction à la liste
        corrections.add(cor);
        //définition du résultat
        cor.setResultat(etu, level, this.notation);
    }
	/*
	 * Méthode pour corriger la banque d'exercice par catégorie d'exercice
	 * @param prof, BanqueExo, la banque d'exercice du prof
	 * @param eleve, BanqueExo, la banque d'exercice de l'élève
	 * @param etu, Etudiant, l'élève qui a réalisées les exercices
	 * @param level, NiveauLangue, le niveau dans une langue de l'élève
	 * @param levelSup, NiveauLangue, le niveau de langue supérieur à level
	 */
	public void correctionBanque(BanqueExo prof, BanqueExo eleve, Etudiant etu,  NiveauLangue level, NiveauLangue levelSup) {
		//on récupère chaque catégorie d'exercices dans une liste
		List<String> cats = eleve.getCatégories();
		//pour chaque catégorie dans la liste
		for (String cat: cats) {
			this.corrections.clear(); //la liste des corrections d'une catégorie est vidée
			List<Exo> eleve_cat = eleve.selectionne(cat); //on sélectionne dans la banque de l'élève les exercices correspondant à la catégorie
			List<Exo> prof_cat = prof.selectionne(cat); //on sélectionne dans la banque du prof les exercices correspondant à la catégorie
			this.correction(prof_cat, eleve_cat, etu, level); //on corrige ces listes d'exercices
			this.setReussite(etu, level); //on indique si ces exercices sont réussi ou pas
		}
		this.faitPasserNiveau(etu, level, levelSup);
	}
	/*
	 * méthode pour corriger une liste d'exercice
	 * @param prof, List<Exo>, les exercices du prof
	 * @param eleve, List<Exo>, les exercices de l'élève
	 * @param etu, Etudiant, l'élève qui a réalisées les exercices
	 * @param level, NiveauLangue, le niveau dans une langue de l'élève
	 * @param levelSup, NiveauLangue, le niveau de langue supérieur à level
	 */
	public void correction(List<Exo> prof, List<Exo> eleve, Etudiant etu, NiveauLangue level) {
		//pour chaque exercice de l'élève
		for (Exo exoEleve: eleve) {
			//pour chaque exercice du prof
			for (Exo exoProf: prof) {
				//si le nom de l'exercice de l'élève équivaut à celui du prof
				if (exoEleve.getName().equals(exoProf.getName())) {
					//si le niveau de l'élève est supérieur à l'exercice
					if(etu.getNiveau(level).getNiveau().getNumNiveau() > exoEleve.getNiveau().getNumNiveau()) {
						//la notation est difficile et on corrige l'exercice
						this.setNotation("difficile");
						this.exoCorrection(exoProf, exoEleve, etu, level);
					}
					//si le niveau de l'élève n'est pas supérieur à l'exercice
					else {
						//on corrige l'exercice avec la notation originel entrée dans le constructeur
						this.exoCorrection(exoProf, exoEleve, etu, level);
					}
					
				}
			}
		}
	}
	/*
	 * Méthode indiquant la réussite d'une catégorie des exercices corrigés
	 * 50% des exercices d'une catégorie doit être réussi
	 * @param etu, Etudiant, l'élève qui a réalisées les exercices
	 * @param level, NiveauLangue, le niveau dans une langue de l'élève
	 */
	public void setReussite(Etudiant etu, NiveauLangue level) {
	    Boolean value=null; //la valeur booléenne indiquant si une catégorie est réussite (true) ou pas (false)
	    int countCorrection=0; //compteur des exercices corrigés d'une catégorie
	    int countReussi=0; //compteur des exercices réussis
	    //si l'élève est de niveau avancé
	    if (etu.getNiveau(level).getNiveau().equals(Niveau.AVANCE)) {
	    	//pour chaque correction d'une catégorie
	    	for (Correction cor: corrections) {
	    		countCorrection++;//on compte le nombre de correction
	    		if (cor.getResultat().equals(Resultat.REUSSI)){
	    			countReussi++;//on compte le nombre d'exercice réussi
	    		}
	    		else if(cor.getResultat().equals(Resultat.GAMEOVER)){
	    			value = false;//si le résultat d'un exercice est gameover, alors la catégorie n'est pas réussi
	    		}
	    	}
	    	if (!value.equals(false)) { //si la valeur n'a pas déjà été déclaré comme fausse
	    		if (countReussi >= (((double)countCorrection/100)*50)) {//la catégorie est vrai si le nombre d'exercice réussi est au moins de 50% du total des exercices
	    			value = true;
	    		}
	    		else {//sinon, c'est faux
	    			value = false;
	    		}
	    	}
	    	else {
	    		if (countReussi >= (((double)countCorrection/100)*50)) {//la catégorie est vrai si le nombre d'exercice réussi est au moins de 50% du total des exercices
	    			value = true;
	    		}
	    		else {//sinon, c'est faux
	    			value = false;
	    		}
	    	}
	    }
	    //si le niveau de l'élève est débutant ou interméidaire
	    else if (etu.getNiveau(level).getNiveau().equals(Niveau.DEBUTANT) || etu.getNiveau(level).getNiveau().equals(Niveau.INTERMEDIAIRE)) {
	    	//pour chaque correction d'une catégorie
	    	for (Correction cor: corrections) {
	    		countCorrection++; //on compte le nombre de correction
	    		if (cor.getResultat().equals(Resultat.REUSSI)){//on compte aussi le nombre d'exercice corrigé réussi
	    			countReussi++;
	    		}
	    	}
	    	//si le nombre d'exercice réussi est supérieur ou égale à 50% des exercices corrigés
	    	if (countReussi >= (((double)countCorrection/100)*50)) {
	    		value = true; //la catégorie est réussite
	    	}
	    	else {//sinon, la catégorie n'est pas réussite
	    		value = false;
	    	}
    	}
	    this.listeReussiteCats.add(value);//on ajoute cette valeur à la liste
	}
	/*
	 * Méthode faisant passer le niveau: chaque catégorie doit être réussite
	 * @param etu, Etudiant, l'élève qui a réalisées les exercices
	 * @param level, NiveauLangue, le niveau dans une langue de l'élève
	 * @param levelSup, NiveauLangue, le niveau de langue supérieur à level
	 */
	public void faitPasserNiveau(Etudiant etu, NiveauLangue level, NiveauLangue levelSup) {
		int count=0;//compteur des catégories
		int countTrue=0; //compteur des catégories réussites
		//pour chaque catégorie
		for(Boolean bool: listeReussiteCats) {
			count++;
			//on compte le nombre de catégorie réussi
			if(bool.equals(true)) {
				countTrue++;
			}
		}
		//si le nombre de catégorie réussi est égale au nombre de catégorie totale
		if (countTrue == count) { //l'élève passe un niveau
			etu.setNiveau(levelSup);
			System.out.println("L'élève "+etu.getNumero()+" a passé un niveau !");
		}
		else {//sinon, l'élève ne passe pas de nievau
			System.out.println("L'élève "+etu.getNumero()+" ne passe pas de niveau !");
		}
	}
}

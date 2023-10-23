package projet;

import java.io.File;
import java.util.List;

/*
 * @author Alexandra LI COMBEAU LONGUET 21700177
 * Exécution type:
 * java main P Prof Eleve
 * java main E Prof 2189282
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	//une langue
    	//langue disponnible: français, anglais,...
    	Langue fr = new Langue("français");
    	//des niveaux de langue
    	NiveauLangue lvl1 = new NiveauLangue(Niveau.DEBUTANT, fr );
    	NiveauLangue lvl2 = new NiveauLangue(Niveau.INTERMEDIAIRE, fr );
    	//un élève
    	Etudiant e1 = new Etudiant("2189282", "Marie", "Chevalier");
    	e1.setNiveau(lvl1);//définition du niveau de l'élève
    	
    	if (args[0].equals("P")) {
    		//le deuxième argument est le répertoire du prof
    		//le troisième est le répertoire de l'élève
    		if (args[1] !=null && args[2] !=null) {
    			
    			File directory = new File(args[1]);
    			
    			System.out.println("Lecture des exercices...");
    			BanqueExo exos = new BanqueExo(directory);
    			
    			System.out.println("Génération des exercices pour les élèves...");
    			/*
    			 * methode 1 de génération d'exercice
    			 */
    			//exos.generation(args[2], exos.selectionne(lvl1.getNiveau(), lvl1.getLangue()));
    			/*
    			 * méthode 2 de génération d'exercice
    			 */
    			//pour ajouter les exercices correspondant de la banque d'exercice à un niveau et une langue:
    			exos.ajoutExos(lvl1);
    			//pour obtenir la liste d'exercice du niveau:
    			List<Exo> ex_lvl1 = lvl1.getlistExo();
    			//pour les générer
    			exos.generation(args[2], ex_lvl1);
    		}
    		else {
        		System.out.println("ERREUR: execution du type: java main P Prof Eleve");
        	}
    	}
    	else if (args[0].equals("E")) {
        	//le deuxième argument est le répertoire du prof
    		//le troisième est le répertoire de l'élève
        	if (args[1] !=null && args[2] !=null) {
        		File directoryProf = new File(args[1]);
        		File directoryEleve = new File(args[2]);
        	
        		System.out.println("Lecture des fichiers...");
        		BanqueExo Prof = new BanqueExo(directoryProf);
        		BanqueExo Eleve = new BanqueExo(directoryEleve);
        	
        		System.out.println("Correction...");
           
            
        		RobotProf e = new RobotProf("indulgente");
        		e.correctionBanque(Prof, Eleve, e1, lvl1, lvl2);
        	}
        	else {
        		System.out.println("ERREUR: execution du type: java main E Prof Eleve");
        	}
        }
    	else {
    		System.out.println("ERREUR: l'execution doit être du type: ");
    		System.out.println("Pour générer les exercices aux élèves: java main P Prof Eleve");
    		System.out.println("Pour corriger les exercices d'un élève: java main E Prof Eleve");
    	}
        

    }
    
}

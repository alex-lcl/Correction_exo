package projet;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* @author Alexandra LI COMBEAU LONGUET 21700177
* Une classe représentant un exercice: un fichier correspondant à un exercice
*/
public class Exo {
    private List<String> exercice = new ArrayList<>(); // les phrases de l'exercices provenant du fichier
    private Niveau niveau; // le niveau de l'exercice
    private Langue langue; // la langue de l'exercice
    private String categorie; // la catégorie de l'exercice
    private String nom; // le nom du fichier contenant l'exercice
    
    /*
     * Le constructeur
     */
    public Exo(String file){
    	String path = file ;
    	nom = path.split("/")[path.split("/").length-1];
        this.setLectureFichier(file);
    }
    /*
     * Une méthode retournant le nom du fichier de l'exercice
     * @return nom, le nom du fichier de l'exercice
     */
    public String getName() {
    	return nom;
    }
    /*
     * une méthode retournant les phrases de l'exercice
     * @return exercice, une liste de chaine de caractère
     */
    public List<String> getExo(){
        return exercice;
    }
    /*
     * une méthode retournant une phrase de l'exercice
     * @param i, un entier correspondant à l'index de la chaine de caractère dans la liste exercice
     * @return une String de la liste exercice ayant pour index i
     */
    public String getExo(int i){
        return exercice.get(i);
    }
    /*
     * une méthode retournant le niveau d'un exercice
     * @return le niveau de l'exercice sous forme d'une String
     */
    public String getNiveauString(){
        return niveau.affiche();
    }
    /*
     * une méthode retournant le niveau d'un exercice
     * @return niveau, le niveau de l'exercice 
     */
    public Niveau getNiveau(){
        return niveau;
    }
    /*
     * une méthode retournant la langue d'un exercice
     * @return langue, la langue de l'exercice 
     */
    public Langue getLangue(){
        return langue;
    }
    /*
     * une méthode retournant la catégorie d'un exercice
     * @return categorie, String, la catégorie de l'exercice 
     */
    public String getCategorie(){
        return categorie;
    }
    /*
     * une méthode qui lit un fichier et réccupère les éléments des instances de la classe Exo
     * @param file, une chaine de caractère correspondant au nom du fichier/chemin du fichier à lire
     */
    protected void setLectureFichier(String file){
        FileInputStream fis = null;
        //lecture du fichier du prof
        try {
            fis = new FileInputStream(file);
            InputStreamReader fr = new InputStreamReader(fis,"UTF-8");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            int i=0; //compteur des lignes du fichier
            //pour chaque ligne du fichier
            while ((line = reader.readLine())!= null){
            	// si la ligne est la première du fichier
                if (i==0){ 
                	//pour chaque élement séparés par un ";"
                    for (String element : line.split(";")){ 
                    	// si l'élément à gauche de ":" est "niveau"
                        if (element.split(":")[0].toLowerCase().strip().equals("niveau")){ 
                        	//alors l'élément à droite récupéré est soit débutant, intérméidaire, avancé, expert
                        	//on récupère cet élément dans la variable "niveau".
                        	switch(element.split(":")[1].strip()) { 
                        	case "débutant":
                        		this.niveau = Niveau.DEBUTANT;
                        		break;
                        	case "intermédiaire":
                        		this.niveau = Niveau.INTERMEDIAIRE;
                        		break;
                        	case "avancé":
                        		this.niveau = Niveau.AVANCE;
                        		break;
                        	case "expert":
                        		this.niveau = Niveau.EXPERT;
                        		break;
                        	default:
                        		this.niveau = Niveau.ND;
                        		break;
                        	}
                        }
                     // si l'élément à gauche de ":" est "langue"
                        else if (element.split(":")[0].toLowerCase().strip().equals("langue")){
                        	//on récupère l'élément à droite de ":" dans la variable "langue".
                            this.langue = new Langue(element.split(":")[1].strip());
                        }
                     // si l'élément à gauche de ":" est "catégorie
                        else if (element.split(":")[0].toLowerCase().strip().equals("catégorie")){
                        	//on récupère l'élément à droite de ":" dans la variable "catégorie".
                            this.categorie = element.split(":")[1].strip();
                        }
                    }
                    i++;
                }
                // si on est sur la seconde ligne, et qu'elle contient "Mots manquants:"
                else if ((i==1) && (line.contains("Mots manquants:")) ){
                	//on ne fait rien
                    continue;
                }
                //dans les autres cas, on se trouve devant une phrase de l'exercice
                else {
                    //on ajoute la ligne à liste exercice
                    exercice.add(line);
                    exercice.add("\n");
                    i++;
                }
                
            }
            fr.close();
            fis.close();
            reader.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void generationExo(String fileOut){
        //pour créer l'exercice de l'élève
        try {
            // si le fichier n'existe pas, il sera créé
            // Ne tient pas compte de l’encodage.
            FileWriter fw = new FileWriter(fileOut, StandardCharsets.UTF_8);
            Parser p = new Parser(); //création du parseur
            
            fw.write("Niveau:"+this.niveau.affiche()+"; Langue:"+this.langue.getNom()+"; catégorie:"+this.categorie+";\n");
            
            // Réccupérer la liste de tous les mots manquants
            List<String> touslesmanquants = new ArrayList<>();
            //pour chaque phrase de l'exercice
            for (String s: exercice){
            	//on parse la phrase
                Phrase s2= new Phrase(p.parse(s)); 
                //on réccupère les mots manquants dans touslesmanquants 
                for (String mot: s2.motsmanquants()){
                    touslesmanquants.add(mot);
                }
            }
            fw.write("Mots manquants:");            
            //Si l'exercice est de niveau débutant, écrire la liste dans l'ordre des réponses
            if (this.niveau.equals(Niveau.DEBUTANT)){ 
                for (String mot : touslesmanquants){
                    fw.write(mot);
                    fw.write(" ");
                }
                fw.write("\n");
            }
            //Si l'exercice est de niveau intérmédiaire, écrire la liste dans un ordre aléatoire
            else if (this.niveau.equals(Niveau.INTERMEDIAIRE)){
                Collections.shuffle(touslesmanquants); //mélange les éléments de touslesmanquants
                for (String mot: touslesmanquants){
                    fw.write(mot);
                    fw.write(" ");
                }
                fw.write("\n");
            }
            else {
                fw.write("aucun mot fourni.\n");
            }
            //écrire l'exercice
            // pour chaque ligne de l'exercice
            for (String phrase: this.exercice){
            	//on parse la ligne en morceaux
                Phrase phrase1 = new Phrase(p.parse(phrase)); 
                //on récupère la ligne en version "à trous" pour l'élève.
                String phrase2 = new String(phrase1.pourEleve());
                //on écrit dans le nouveau fichier cette ligne "pour l'élève".
                fw.write(phrase2);
            
            }
            fw.close();
        
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }
    }

}

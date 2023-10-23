package projet;
import java.util.ArrayList;
import java.util.List;
/*
* une classe représentant les différantes états de phrases possibles: 
* celle entrée par le prof, celle attendue par le prof, celle à remplir par l'élève...
*/
public class Phrase {
    List<Morceau> p;
    /*
    * le constructeur
    * @param s, une liste de morceaux
    */
    public Phrase (List<Morceau> s){
        p = s;
    }
    /*
    * Une méthode retournant la phrase à remplir par l'élève
    * @return a_remplir, la chaine de caractère contenant la pharse en question
    */
    public StringBuilder pourEleve() { 
        int i = 0; 
        StringBuilder a_remplir = new StringBuilder();
        while(i <p.size()){
            String morceau = p.get(i).pourEleve();
            a_remplir.append(morceau);
            i++;
        }
        return a_remplir;
    }
    /*
    * Une méthode retournant la phrase entrée par le professeur
    * @return prof, la chaine de caractère contenant la pharse en question
    */
    public StringBuilder pourProf() { 
        int i = 0; 
        StringBuilder prof = new StringBuilder();
        while(i <p.size()){
            String morceau = p.get(i).pourProf();
            prof.append(morceau);
            i++;
        }
        return prof;
    }
    /*
    * Une méthode retournant la phrase attendue par le professeur
    * @return attendu, la chaine de caractère contenant la pharse en question
    */
    public StringBuilder reponseAttendue() { 
        StringBuilder attendu = new StringBuilder();
        int i=0;
        while(i <p.size()){
            String morceau = p.get(i).reponseAttendue();
            attendu.append(morceau);
            i++;
        }
        return attendu;
    }
    /*
    * une méthode pour obtenir la chaine de caractère du morceau intacte de la Phrase, sans modification des morceaux variables
    * @param int i, la position du morceau dans la Phrase
    * @return morceau, la chaine de caractère correspondante
    */
    public String reponseAttendue(int i) { 
        String morceau = p.get(i).reponseAttendue();
        return morceau;
    }
/*
* une méthode pour découper en morceaux la réponse de l'élève suivant la réponse attendue
* @param s, une chaine de caractère contenant la réponse de l'élève
* @return rep2, une Phrase
*/
    public Phrase annalyseReponseEleve(String s){
        List<Morceau> rep = new ArrayList<>();
        Phrase rep2;
        int y=0; // position du premier caractère du morceau dans la phrase
        int z=1; // la position des morceaux dans une phrase
        for (Morceau m: p){ //pour chaque Morceau dans la liste de Morceau parsé
            int f=y+m.reponseAttendue().length(); // position du dernier caractère du morceau dans la phrase
            // si le morceau m de la pharse du prof est dans la phrase aux mêmes cordonnées dans celle de l'élève
            if ((f <= s.length()-1) && (m.reponseAttendue().equals(s.substring(y, f)))){
                if (m instanceof MorceauFixe){
                    MorceauFixe m2 = new MorceauFixe(s.substring(y, f), z);
                    rep.add(m2);
                }
                else {
                    MorceauVariable m2 = new MorceauVariable(s.substring(y, f), z);
                    rep.add(m2);
                }
                z++;
                y= y+m.reponseAttendue().length();
            }
            else { // si le morceau de la phrase du prof et le morceau dans la phrase de l'élève ne sont pas identiques
                if (z < p.size()){
                	if (s.substring(y, y+3).equals("...")) { //s'il y a ... dans la phrase de l'élève 
                		y=y+"...".length();
                		MorceauVariable m2 = new MorceauVariable("...", z);
                		rep.add(m2);
                		z++;
                	}
                	else {
                		String s2 = p.get(z).reponseAttendue();
                		int i = y+ s.substring(y).indexOf(s2);//index du début du prochain mot
                		MorceauVariable m2 = new MorceauVariable(s.substring(y,i), z);
                		rep.add(m2);
                		z++;
                		y=i;
                	}
                }
                else if (z==p.size()){//on traite le dernier morceau de la phrase du prof
                    if (m instanceof MorceauFixe){
                        MorceauFixe m2 = new MorceauFixe(s.substring(y, f), z);
                        rep.add(m2);
                    }
                    else {
                        MorceauVariable m2 = new MorceauVariable(s.substring(y, f), z);
                        rep.add(m2);
                    }
                    break;
                }
                
            }
        }
        rep2 = new Phrase(rep);
        return rep2;
    }
    /*
    * une méthode qui retourne la taille de la phrase
    * @return int, le nombre d'élément dans la pharse
    */
    public int size() {
        return p.size();
    }
    /*
    * une méthode qui retourne le morceau suivant sa position dans la phrase
    * @param int i, la position du morceau dans la pharse
    * @return Morceau, le Morceau de la phrase ayant la position i
    */
    public Morceau get(int i) {
        return p.get(i);
    }
    /*
    * une méthode qui affiche les mots manquants
    * @return manquants, le ou les morceaux manquants
    */
    public List<String> motsmanquants() {
        List<String> manquants = new ArrayList<>();
        for (Morceau m: p) {
            if (m instanceof MorceauVariable){
                manquants.add(m.reponseAttendue().strip());
            }
        }
        return manquants;
    }

}

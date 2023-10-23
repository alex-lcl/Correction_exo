package projet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/*
* Une classe implémentant l'interface Parseur
* qui parse une pharse en morceaux
*/
public class Parser implements Parseur {
    private char d;
    private Pattern p;

/*
* un constructeur definissant le délimiteur de morceaux
*/
    public Parser(){
        d='#';
        //Pattern p = Pattern.compile("(#([^#]*)#)");
        p = Pattern.compile("("+d+"(.*?)"+d+")");
    }
/*
* une méthode pour parser une phrase en morceaux, se basant sur les morceaux variables 
* @param s, une chaine de caractère constituant une pharse
* @return phrase, une liste de morceaux de la phrase
*/
    public List<Morceau> parse(String s) {
        
        List<Morceau> phrase = new ArrayList<>();

        Matcher m = p.matcher(s);
        
        int i =0; //un curseur parcourant la phrase du premier caractère au dernier
        int z=0; //la position du morceau dans la phrase
        while(m.find()) { //tant qu'on trouve des morceaux variables
            if (m.start()==i){ //si le morceau variable trouvé commence au début de la phrase s
                MorceauVariable p2=new MorceauVariable(m.group(2), z);
                phrase.add(p2);
                z++;
                i = m.end(); //le curseur se positionne à la fin du morceau trouvé
            }
            else { //dans les autres cas
                String s1 = s.substring(i, m.start());
                MorceauFixe p1= new MorceauFixe(s1, z);
                phrase.add(p1);
                z++;
                MorceauVariable p2=new MorceauVariable(m.group(2), z);
                phrase.add(p2);
                z++;
                i = m.end(); //le curseur se positionne à la fin du morceau trouvé
            }
        }
        if (i != s.length()){ //si le cureur n'a pas atteint le dernier caractère de la phrase s
            String s2 = s.substring(i, s.length());
            MorceauFixe p3= new MorceauFixe(s2, z);
            phrase.add(p3);
        }
        
        return phrase;
    }
    
}

package projet;

/*
* Une classe héritant de la classe Morceau, pour les morceaux de phrases qui varient (à remplir par l'élève, où le porf indique la réponse attendue) 
*/
public class MorceauVariable extends Morceau{
    
    MorceauVariable (String token, int ValP) {
        super(token, ValP);
    }

    @Override
    public String pourEleve(){
        return "...";
    }
    @Override
    public String pourProf() { 
        return "#"+morceau+"#";
    }
    @Override
    public String reponseAttendue() { 
        return morceau;
    }
}

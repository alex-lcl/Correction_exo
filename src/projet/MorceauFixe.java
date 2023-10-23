package projet;
/*
* Une classe héritant de la classe Morceau, pour les morceaux de phrases ne variant pas
*/
public class MorceauFixe extends Morceau{

    MorceauFixe(String token, int ValP){
        super(token, ValP);
    }

    @Override
    public String pourEleve(){
        return morceau;
    }
    @Override
    public String pourProf() { 
        return morceau;
    }
    @Override
    public String reponseAttendue() { 
        return morceau;
    }
}

package projet;
/*
 * Enumération des différents états possibles de la correction d'un exercice:
 * réussi: exercice est réussi
 * echec: exercice n'est pas réussi
 * gameover: dans le cas d'un éleve d'un niveau avancé, un exercice dont la note est inférieur à 50% de la note maximale
 * nonfait: par défault
 */
public enum Resultat {
	REUSSI, ECHEC, NONFAIT, GAMEOVER

}

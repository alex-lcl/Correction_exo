package projet;
/*
 * une classe qui modélise une langue
 */
public class Langue {
	String nom; //le nom de la langue
	CodeLangue code; //le code de la langue
	/*
	 * le constructeur
	 * @param name, String, le nom de la langue
	 */
	public Langue(String name) {
		nom = name;
		this.setCode(name);
	}
	/*
	 * une méthode qui à un nom de langue, y associe un code
	 * @param name, le nom de la langue
	 */
	protected void setCode(String name) {
		switch(name){
		case "français":
			this.code = CodeLangue.FR;
		case "anglais":
			this.code = CodeLangue.EN;
		case "chnois":
			this.code = CodeLangue.ZH;
		default:
			this.code = CodeLangue.FR;
			}
	}
	/*
	 * une méthode qui retourne le nom de la langue
	 * @return nom, String, le nom de la langue
	 */
	public String getNom() {
		return nom;
	}
}

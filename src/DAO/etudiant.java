package DAO;
public class etudiant {
	 private String nom;
	 private String prenom;
	 private String annee_scolaire;
	 private String filiere;
	 private static int count ;
	 private int id;
	//constructeur using fields
	public etudiant(String nom, String prenom, String annee_scolaire, String filiere) {
		this.nom = nom;
		this.prenom = prenom;
		this.annee_scolaire = annee_scolaire;
		this.filiere = filiere;
		this.id = count;
		count =count +1;
	}
	 //constructeur par default
	public etudiant() {
		this.nom = "";
		this.prenom = "";
		this.annee_scolaire = "";
		this.filiere = "";
		this.id = count;
		count =count +1;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAnnee_scolaire() {
		return annee_scolaire;
	}
	public void setAnnee_scolaire(String annee_scolaire) {
		this.annee_scolaire = annee_scolaire;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		etudiant.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//affiche
	public void affiche() {
        System.out.println(this.toString());}

	}

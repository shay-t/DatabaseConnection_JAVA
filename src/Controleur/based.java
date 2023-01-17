package Controleur;
import java.sql.*;

import DAO.etudiant;
public class based {
	//variable static pour eviter son passage en arguments
	public static Connection con;
	//methode static pour instancier directement://
	
	//1-Une méthode de connexion à la base de données
	public static void connecter() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Le Driver est OK");
			String url="jdbc:mysql://localhost:3306/ecole";
			String user="root";
			String passwd="";
			con = DriverManager.getConnection(url , user, passwd);
			System.out.println("La connexion est etablie");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//-Une methode pour la deconnexion
	public static void deconnecter() {
		try {
			con.close();
			System.out.println("La connexion est ferme");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//1-une methode pour la selection de tous les etudiants
	public static void selection_etudiants() {
		 ResultSet resultats=null;
		 String requete="SELECT * FROM etudiant";
		 try {
			Statement stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);
			while(resultats.next()) {
				System.out.println(resultats.getString("id")+"\n"+resultats.getString("nom")+"\n"+resultats.getString("prenom")+"\n"
						+resultats.getString("annee")+"\n"+resultats.getString("filiere"));
			}
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}

	//2-Une méthode d’insertion d’un étudiant
	public static void inserer(DAO.etudiant e1) {
		try {
			String requete=String.format("INSERT INTO etudiant (nom,prenom,annee,filiere) VALUES ('%s','%s','%s','%s')",e1.getNom(),e1.getPrenom(),e1.getAnnee_scolaire(),e1.getFiliere());
			Statement stmt= con.createStatement();
			int results = stmt.executeUpdate(requete);
			System.out.println("la requete est effectué");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//3-Une méthode de modification d’un étudiant n
	public static void modifier(int id,DAO.etudiant e1) {
		try {
			   String requete =String.format("UPDATE etudiant set nom='%s',prenom='%s',annee='%s',filiere='%s' WHERE id=%d ",e1.getNom(),e1.getPrenom(),e1.getAnnee_scolaire(),e1.getFiliere(),id); 
			   Statement stmt = con.createStatement();
			   int results = stmt.executeUpdate(requete);
			} catch (SQLException e) {
				e.printStackTrace();
		}
}
	//4-Une methode pour la suppression dun etudiant
	public static void supprimer(int id) {
		try {
			   String requete =String.format("DELETE FROM etudiant WHERE id='%d'",id); 
			   Statement stmt = con.createStatement();
			   int results = stmt.executeUpdate(requete);
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
//pour interface graphique
//selectioner tout pour interface graphique
public static ResultSet sel() {
	try {
	   Statement stmt =con.createStatement();
	   String requete="SELECT * FROM etudiant";
	   ResultSet rsl = stmt.executeQuery(requete);
	   return rsl;
	} catch (SQLException e) {
		return null;
	}
}
//rechercher=selectionner un etduiant
public static ResultSet sel(DAO.etudiant et) {
	try {
	   Statement stmt = con.createStatement();
	   String sql =String.format("SELECT * FROM etudiant WHERE nom='%s' and prenom='%s'",et.getNom(),et.getPrenom());
	   ResultSet resultats = stmt.executeQuery(sql);
	   return resultats;
	} catch (SQLException e) {
		return null;
	}
}
//fct d'authentification
public static boolean checklogin(String log , String mdp) {
	int rowc=0;
	try { 
		   Statement stmt = con.createStatement();
		   String sql =String.format("SELECT * FROM admin WHERE login='%s' and mdp='%s'",log,mdp);
		   ResultSet resultats = stmt.executeQuery(sql);
		   while(resultats.next()) {
			   rowc++;
		   }
		   if(rowc>0) {return true;}
		   else {return false;}
		   
		} catch (SQLException e) {
		   e.printStackTrace();
		   return false;
		}
}
//execute update != execute query
}
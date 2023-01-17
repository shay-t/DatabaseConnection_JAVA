package User;
import java.util.Scanner;
import Controleur.based;
import DAO.etudiant;
public class test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean b=true;
		while(b) {
		Scanner Scan=new Scanner(System.in);
        System.out.println("Veuilez effectuer un choix ");
        System.out.println("Taper 1: Pour la sélection de tout les etudiants ");
        System.out.println("Taper 2: Pour l'insertion dun etudiant");
        System.out.println("Taper 3: Pour la modification dun etudiant");
        System.out.println("Taper 4:Pour la supression dun etudiants");
        int choice=Scan.nextInt();
        //Pas besoin creation d'une instance based
        switch(choice) {
        case 1:
        	based.connecter();
        	based.selection_etudiants();
        	based.deconnecter();
        	break;
        case 2:
        	System.out.println("Donner le nom ");
        	String n=Scan.next();
        	System.out.println("Donner le prenom ");
        	String p=Scan.next();
        	System.out.println("Donner lannee scolaire ");
        	String as =Scan.next();
        	System.out.println("Donner la filiere ");
        	String f =Scan.next();
        	etudiant e =new etudiant(n,p,as,f);
        	based.connecter();
        	based.inserer(e);
        	based.deconnecter();
        	break;
        case 3:
        	System.out.println("Donner lid ");
        	int id=Scan.nextInt();
        	System.out.println("Donner le nom ");
        	String n2=Scan.next();
        	System.out.println("Donner le prenom ");
        	String p2=Scan.next();
        	System.out.println("Donner lannee scolaire ");
        	String as2 =Scan.next();
        	System.out.println("Donner la filiere ");
        	String f2 =Scan.next();
        	etudiant e2 =new etudiant(n2,p2,as2,f2);
        	based.connecter();
        	based.modifier(id,e2);
        	based.deconnecter();
        	break;
        case 4:
        	System.out.println("Donner lid ");
        	int id2=Scan.nextInt();
        	based.connecter();
        	based.supprimer(id2);
        	based.deconnecter();
        	break;
        default:
        	System.out.println("veuillezchoisir un choix des choix posibles");
        }}
}}

package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Controleur.based;
import DAO.etudiant;
import net.proteanit.sql.DbUtils;

import java.sql.*;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class test {

	JFrame frame;
	private JTextField nom;
	private JTextField prenom;
	private JTextField filiere;
	private JTextField annee;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_2;
	private JTable table;
	private JButton btnNewButton_3;
	//declaration dune variable globale la ligne selectionnéé
	private int l;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//rendre false-> copier autentification true
					test window = new test();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 576, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(22, 91, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prenom");
		lblNewLabel_1.setBounds(22, 133, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		nom = new JTextField();
		nom.setBounds(78, 88, 86, 20);
		frame.getContentPane().add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setBounds(78, 130, 86, 20);
		frame.getContentPane().add(prenom);
		prenom.setColumns(10);
		
		JButton btnNewButton = new JButton("Inserer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//recuperer les arguments
				String n=nom.getText();
				String p=prenom.getText();
				String f = filiere.getText(); 
				String a = annee.getText();
				etudiant e1 = new etudiant(n,p,a,f);
	        	based.connecter();
	        	based.inserer(e1);
	        	based.deconnecter();
	        	//vider le contenu
	        	nom.setText("");
	        	prenom.setText("");	
	        	filiere.setText("");
	        	annee.setText("");
	        	}
		});
		btnNewButton.setBounds(22, 253, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Filiere");
		lblNewLabel_1_1.setBounds(22, 171, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		filiere = new JTextField();
		filiere.setColumns(10);
		filiere.setBounds(78, 168, 86, 20);
		frame.getContentPane().add(filiere);
		
		annee = new JTextField();
		annee.setBounds(78, 206, 86, 20);
		frame.getContentPane().add(annee);
		annee.setColumns(10);
		
		lblNewLabel_1_2 = new JLabel("Annee");
		lblNewLabel_1_2.setBounds(22, 209, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		lblNewLabel_2 = new JLabel("Les etudiants :");
		lblNewLabel_2.setBounds(340, 66, 146, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = (int)table.getModel().getValueAt(l, 0);
				String nom=(String)table.getModel().getValueAt(l, 1);
				String prenom=(String)table.getModel().getValueAt(l, 2);
				String annee=(String)table.getModel().getValueAt(l, 3);
				String filiere=(String)table.getModel().getValueAt(l, 4);
				etudiant e2 =new etudiant(nom,prenom,annee,filiere);
				based.connecter();
				based.modifier(id,e2);
				based.deconnecter();
				//refresh function->possibilité de la mettre globale
				based.connecter();
	        	ResultSet selection = based.sel();
	        	//resultat stocker dans table par bias de r2XML.jar
	        	table.setModel(DbUtils.resultSetToTableModel(selection));
	        	based.deconnecter();
			}
		});
		btnNewButton_1.setBounds(301, 253, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// recuperer la valeur de l'id -> ligne selectionne(variable globale)
				//:1ere colonne
				int id = (int)table.getModel().getValueAt(l, 0);
				based.connecter();
				based.supprimer(id);
				based.deconnecter();
				//refresh function
				based.connecter();
	        	ResultSet selection = based.sel();
	        	//resultat stocker dans table par bias de r2XML.jar
	        	table.setModel(DbUtils.resultSetToTableModel(selection));
	        	based.deconnecter();
			}
		});
		btnNewButton_2.setBounds(417, 253, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton refresh = new JButton("Refresh");
		refresh.setActionCommand("Refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//refresh function
				based.connecter();
	        	ResultSet selection = based.sel();
	        	//resultat stocker dans table par bias de r2XML.jar
	        	table.setModel(DbUtils.resultSetToTableModel(selection));
	        	based.deconnecter();
	    
			}
		});
		refresh.setBounds(22, 28, 89, 23);
		frame.getContentPane().add(refresh);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//valeur de la variable globae de la ligne selectionné
				l=table.getSelectedRow();
			}
		});
		table.setBounds(263, 91, 260, 115);
		frame.getContentPane().add(table);
		
		btnNewButton_3 = new JButton("chercher");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//recuperer les arguments
				String n=nom.getText();
				String p=prenom.getText();
				String f = filiere.getText(); 
				String a = annee.getText();
				etudiant e1 = new etudiant(n,p,a,f);
	        	based.connecter();
	        	ResultSet S= based.sel(e1);
	        	table.setModel(DbUtils.resultSetToTableModel(S));
	        	based.deconnecter();
	        	//vider le contenu
	        	nom.setText("");
	        	prenom.setText("");	
	        	filiere.setText("");
	        	annee.setText("");
	        	
			}
		});
		btnNewButton_3.setBounds(132, 253, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
	
}

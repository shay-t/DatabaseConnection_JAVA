package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import Controleur.based;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class authentification {
	private JFrame frame;
	private JTextField mdp;
	private JTextField login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					authentification window = new authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("s'identifier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String log = login.getText();
				String mp = mdp.getText();
				based.connecter();
				boolean b =based.checklogin(log,mp);
				based.deconnecter();
				if(!b) {
					//popup
					JOptionPane.showMessageDialog(null, "Vos identifiants sont incorrecte");
				}
				else {
					//redirection: 
					
					test window2 = new test();
					window2.frame.setVisible(true);
					//pour fermer -> pas possible avec window pas encore creer on accede directement frame
					//car on est toujours dans constructeur
					frame.setVisible(false);
				}
				
			}
		});
		btnNewButton.setBounds(155, 178, 121, 20);
		frame.getContentPane().add(btnNewButton);
		
		mdp = new JTextField();
		mdp.setBounds(194, 115, 181, 20);
		frame.getContentPane().add(mdp);
		mdp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Identifiant :");
		lblNewLabel.setBounds(59, 64, 78, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe :");
		lblNewLabel_1.setBounds(59, 118, 103, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		login = new JTextField();
		login.setBounds(194, 61, 181, 20);
		frame.getContentPane().add(login);
		login.setColumns(10);
	}
}

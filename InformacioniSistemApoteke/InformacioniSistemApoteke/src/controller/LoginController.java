package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Korisnik;
import model.TipKorisnika;
import view.LoginFrame;
import view.MainFrame;

public class LoginController extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int brojac;
	private Korisnik user;

	public LoginController() {
		putValue(NAME, "Login");
		brojac = 1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) arg0.getSource();
		LoginFrame frame = (LoginFrame) btn.getTopLevelAncestor();
		user = new Korisnik();
		//user.setTipKorisnika(TipKorisnika.ADMINISTRATOR);
		//boolean logovanje = true;
		boolean logovanje = false;
	
		try {
			ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
			FileInputStream file = new FileInputStream("src/resources/users.txt");
			ObjectInputStream in;
                try {
                	in = new ObjectInputStream(file);
                    korisnici = (ArrayList<Korisnik>) in.readObject();
                    in.close();
					file.close();
                } catch (IOException ex) {

                } catch (ClassNotFoundException ex) {
                }
			for(int i=0;i<korisnici.size();i++) {
		        String usernamePokupiText = frame.getUsername().getText();
				String passwordPokupiText = new String(frame.getPassword().getPassword());
					if( usernamePokupiText.equals(korisnici.get(i).getKorisnickoIme()) && passwordPokupiText.equals(korisnici.get(i).getLozinka())) {
							logovanje = true;
							user.setIme(korisnici.get(i).getIme());
							user.setPrezime(korisnici.get(i).getPrezime());
							user.setKorisnickoIme(korisnici.get(i).getKorisnickoIme());
							user.setLozinka(korisnici.get(i).getLozinka());
							user.setTipKorisnika(korisnici.get(i).getTipKorisnika());
							
					}
		        //System.out.println(dataFile);
		      }

		   	} catch (FileNotFoundException o) {
		      System.out.println("An error occurred.");
		      o.printStackTrace();
		   	}
			
		if(logovanje){
			frame.dispose();

			MainFrame.getInstance().setVisible(true);
			MainFrame.getInstance().setUser(user);
			MainFrame.getInstance().setTitle("InformacioniSistemApoteke - " +user.getIme()+ " " + user.getPrezime());
			if(user.getTipKorisnika() == TipKorisnika.ADMINISTRATOR){
				MainFrame.getInstance().getRegistracija().setEnabled(true);
				MainFrame.getInstance().getPrikazKorisnika().setEnabled(true);
				MainFrame.getInstance().getKreirajLek().setEnabled(true);
				MainFrame.getInstance().getIzmeniLek().setEnabled(true);
				MainFrame.getInstance().getKreirajRecept().setEnabled(true);
				MainFrame.getInstance().getIzbrisiLek().setEnabled(true);
				MainFrame.getInstance().getIzbrisiRecept().setEnabled(true);
				MainFrame.getInstance().getPrikaziTransakcije().setEnabled(true);
				MainFrame.getInstance().getProdajLek().setEnabled(true);
				MainFrame.getInstance().getProdajLek().setEnabled(true);
			} else if(user.getTipKorisnika() == TipKorisnika.APOTEKAR) {
				MainFrame.getInstance().getKreirajLek().setEnabled(true);
				MainFrame.getInstance().getIzmeniLek().setEnabled(true);
				MainFrame.getInstance().getProdajLek().setEnabled(true);
				MainFrame.getInstance().getIzbrisiLek().setEnabled(true);
				MainFrame.getInstance().getPrikaziTransakcije().setEnabled(true);
			}else {
				MainFrame.getInstance().getKreirajRecept().setEnabled(true);
				MainFrame.getInstance().getIzbrisiRecept().setEnabled(true);
			}
		}
		else
		{
			if(brojac==3) {
				frame.setVisible(false);
			}
			JOptionPane.showMessageDialog(frame, "Wrong input of username or password, please enter again!", "Input error", JOptionPane.ERROR_MESSAGE);
			brojac++;
		}
	
	}

}

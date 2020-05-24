package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;

import model.Korisnik;
import model.Lek;
import model.Recept;
import view.MainFrame;
import view.RecipeFrame;

public class CreateRecipeController extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Lek> lekovi = null;
	public Recept recept;
	public Lek lek;
	public float ukupnaCena;
	public Map<Lek,Integer> pomocna;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ukupnaCena = 0;
		try {
			FileInputStream file = new FileInputStream("src/resources/medicaments.txt"); 
			ObjectInputStream in = new ObjectInputStream(file); 
			lekovi = new ArrayList<Lek>();
			// Method for deserialization of object
			Korisnik korisnik;
			try {
				lekovi = (ArrayList<Lek>) in.readObject();
			} catch (IOException ex) {
			} catch (ClassNotFoundException ex) {
			}

			in.close();
			file.close();

		} catch (IOException o) {
			System.out.println("An error occurred.");
			o.printStackTrace();	
		}
		RecipeFrame recipeFrame = new RecipeFrame(lekovi);
		recept = new Recept();
		
		pomocna = new HashMap<>();

		recipeFrame.getDodaj().addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				lek = new Lek();
				String sifra = recipeFrame.getComboBox().getSelectedItem().toString();
				for(int i=0;i<lekovi.size();i++) {
					if(sifra.equals(lekovi.get(i).getSifra())) {
						lek.setSifra(lekovi.get(i).getSifra());
						lek.setIme(lekovi.get(i).getIme());
						lek.setCena(lekovi.get(i).getCena());
						lek.setProzivodjac(lekovi.get(i).getProzivodjac());
						lek.setRecept(lekovi.get(i).isRecept());
					}
				}
				pomocna.put(lek, Integer.parseInt(recipeFrame.getTkolicina().getText()));
				ukupnaCena += lek.getCena() * Integer.parseInt(recipeFrame.getTkolicina().getText());
				recipeFrame.getTkolicina().setText("");
			}  
		});  

		recipeFrame.getKreiraj().addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				String spinner = recipeFrame.getTimeSpinner().getValue().toString();
				String[] lista = spinner.split(" ");
				String vreme = lista[3];

				String str = recipeFrame.getTdateTime().getJFormattedTextField().getText() + " " + vreme; 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
				LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

				
				recept.setDatum(dateTime);
				recept.setIdLekara(MainFrame.getInstance().getUser().getKorisnickoIme());
				recept.setJmbgPacijenta(recipeFrame.getTjmbg().getText());
				recept.setUkupnaCena(ukupnaCena);
				recept.setLekovi(pomocna);

				try{

					ArrayList<Recept> recepti = new ArrayList<Recept>();
					FileInputStream file = new FileInputStream("src/resources/recipes.txt"); 
					ObjectInputStream in;
					try {
						in = new ObjectInputStream(file);
						recepti = (ArrayList<Recept>) in.readObject();
						in.close();
						file.close();
					} catch (IOException ex) {

					} catch (ClassNotFoundException ex) {
					}
					recept.setSifra(recepti.size()+1);
					recepti.add(recept);

					FileOutputStream fout = new FileOutputStream("src/resources/recipes.txt");
					ObjectOutputStream oos;
					try {
						oos = new ObjectOutputStream(fout);
						oos.writeObject(recepti);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					recipeFrame.dispose();
				} catch (FileNotFoundException o) {
					System.out.println("An error occurred.");
					o.printStackTrace();
				}

			}  
		});  

	}

}

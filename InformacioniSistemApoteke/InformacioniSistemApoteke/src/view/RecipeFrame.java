package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import formatter.DateLabelFormatter;
import model.Lek;

public class RecipeFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container c; 
    private JLabel title; 
    private JLabel jmbg;
    private JLabel lekKolicina;
    private JTextField tjmbg; 
    private JTextField tkolicina;
    private JLabel dateTime; 
    private JDatePickerImpl tdateTime;
    private JSpinner timeSpinner;
    private JButton kreiraj;
    private JButton dodaj;
    private JComboBox comboBox;
	
	public RecipeFrame(ArrayList<Lek> lekovi) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) 1000, 500);
		setLocationRelativeTo(null);
		setTitle("Lek");
		
		setResizable(false); 
		  
        c = getContentPane(); 
        c.setLayout(null); 

        title = new JLabel("Kreiranje recepta");
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(200, 30); 
        c.add(title); 
  
        jmbg = new JLabel("JMBG pacijenta"); 
        jmbg.setFont(new Font("Arial", Font.PLAIN, 20)); 
        jmbg.setSize(200, 20); 
        jmbg.setLocation(50, 100); 
        c.add(jmbg); 
  
        tjmbg = new JTextField(); 
        tjmbg.setFont(new Font("Arial", Font.PLAIN, 16)); 
        tjmbg.setSize(200, 20); 
        tjmbg.setLocation(200, 100); 
        c.add(tjmbg); 
  
        dateTime = new JLabel("Datum i vreme"); 
        dateTime.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dateTime.setSize(200, 20); 
        dateTime.setLocation(50, 150); 
        c.add(dateTime); 
        
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        UtilDateModel model = new UtilDateModel();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        model.setSelected(true);
        //model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        tdateTime = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        tdateTime.setSize(200, 20); 
        tdateTime.setLocation(250, 150); 
        c.add(tdateTime);
        
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        timeSpinner.setSize(200, 20); 
        timeSpinner.setLocation(470, 150); 
        c.add(timeSpinner);
        
        lekKolicina = new JLabel("Sifra leka i kolicina"); 
        lekKolicina.setFont(new Font("Arial", Font.PLAIN, 20)); 
        lekKolicina.setSize(200, 20); 
        lekKolicina.setLocation(50, 200); 
        c.add(lekKolicina);
        
        String[] stringLekovi = new String[lekovi.size()];
        for(int i=0;i<lekovi.size();i++) {
        	stringLekovi[i] = lekovi.get(i).getSifra();
        }
        
        comboBox = new JComboBox(stringLekovi);
        comboBox.setSize(200, 20); 
        comboBox.setLocation(250, 200);
        c.add(comboBox);
        
        tkolicina = new JTextField(); 
        tkolicina.setFont(new Font("Arial", Font.PLAIN, 16)); 
        tkolicina.setSize(200, 20); 
        tkolicina.setLocation(470, 200); 
        c.add(tkolicina);
        
        dodaj = new JButton("Dodaj");	
        dodaj.setSize(70, 20); 
        dodaj.setLocation(700, 200); 
        c.add(dodaj);
        
        kreiraj = new JButton("Kreiraj");
        		
        kreiraj.setSize(150, 40); 
        kreiraj.setLocation(220, 350); 
        c.add(kreiraj);
  
        setVisible(true); 
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}


	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JLabel getJmbg() {
		return jmbg;
	}

	public void setJmbg(JLabel jmbg) {
		this.jmbg = jmbg;
	}

	public JTextField getTjmbg() {
		return tjmbg;
	}

	public void setTjmbg(JTextField tjmbg) {
		this.tjmbg = tjmbg;
	}

	public JLabel getDateTime() {
		return dateTime;
	}

	public void setDateTime(JLabel dateTime) {
		this.dateTime = dateTime;
	}

	public JDatePickerImpl getTdateTime() {
		return tdateTime;
	}

	public void setTdateTime(JDatePickerImpl tdateTime) {
		this.tdateTime = tdateTime;
	}

	public JSpinner getTimeSpinner() {
		return timeSpinner;
	}

	public void setTimeSpinner(JSpinner timeSpinner) {
		this.timeSpinner = timeSpinner;
	}

	public JButton getKreiraj() {
		return kreiraj;
	}

	public void setKreiraj(JButton kreiraj) {
		this.kreiraj = kreiraj;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JTextField getTkolicina() {
		return tkolicina;
	}

	public void setTkolicina(JTextField tkolicina) {
		this.tkolicina = tkolicina;
	}

	public JButton getDodaj() {
		return dodaj;
	}

	public void setDodaj(JButton dodaj) {
		this.dodaj = dodaj;
	}
	
}
package szamologep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
*
**/

public class Szamologep  implements ActionListener {
	
	JFrame keret;
	JTextField szovegMezo;
	JButton [] szamGombok= new JButton[10];
	JButton [] funkcioGombok= new JButton[9];
	
	JButton osszeadas,kivonas,szorzas,osztas;
	JButton tort,egyenlo,torles,tisztitas,negativ;
	
	JPanel tabla;
	
	Font betutipus=new Font("Ink Free",Font.BOLD,30);
	
	double szam1=0,szam2=0,eredmeny=0;
	char operator;
	
	public Szamologep() {
		keret=new JFrame("Számológép");
		keret.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		keret.setSize(420,550);
		keret.setLayout(null);
		
		szovegMezo=new JTextField();
		szovegMezo.setBounds(50, 25, 300, 50);
		szovegMezo.setFont(betutipus);
		szovegMezo.setEditable(false);
		
		osszeadas=new JButton("+");
		kivonas=new JButton("-");
		szorzas=new JButton("*");
		osztas=new JButton("/");
		tort=new JButton(".");
		egyenlo=new JButton("=");
		torles=new JButton("DEL");
		tisztitas=new JButton("AC");
		negativ=new JButton("(-)");
		
		funkcioGombok[0]=osszeadas;
		funkcioGombok[1]=kivonas;
		funkcioGombok[2]=szorzas;
		funkcioGombok[3]=osztas;
		funkcioGombok[4]=tort;
		funkcioGombok[5]=egyenlo;
		funkcioGombok[6]=torles;
		funkcioGombok[7]=tisztitas;
		funkcioGombok[8]=negativ;
		
		for(int i=0;i<9;i++) {
			funkcioGombok[i].addActionListener(this);
			funkcioGombok[i].setFont(betutipus);
			funkcioGombok[i].setFocusable(false);
		}
		
		for(int j=0;j<10;j++) {
			szamGombok[j]=new JButton(String.valueOf(j));
			szamGombok[j].addActionListener(this);
			szamGombok[j].setFont(betutipus);
			szamGombok[j].setFocusable(false);
		}
		
		negativ.setBounds(50,430,100,50);
		torles.setBounds(150, 430, 100, 50);
		tisztitas.setBounds(250, 430, 100, 50);
		
		tabla=new JPanel();
		tabla.setBounds(50, 100, 300, 300);
		tabla.setLayout(new GridLayout(4,4,10,10));
		tabla.setBackground(Color.gray);
		
		tabla.add(szamGombok[7]);
		tabla.add(szamGombok[8]);
		tabla.add(szamGombok[9]);
		tabla.add(osszeadas);
		tabla.add(szamGombok[4]);
		tabla.add(szamGombok[5]);
		tabla.add(szamGombok[6]);
		tabla.add(kivonas);
		tabla.add(szamGombok[1]);
		tabla.add(szamGombok[2]);
		tabla.add(szamGombok[3]);
		tabla.add(szorzas);
		tabla.add(osztas);
		tabla.add(tort);
		tabla.add(szamGombok[0]);
		tabla.add(egyenlo);
		tabla.add(osztas);
		
		keret.add(tabla);
		keret.add(negativ);
		keret.add(torles);
		keret.add(tisztitas);
		keret.add(szovegMezo);
		keret.setVisible(true);
		
	}

	public static void main(String[] args) {
		
		Szamologep szamolas=new Szamologep();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int k=0;k<10;k++) {
			if(e.getSource()==szamGombok[k]) {
				szovegMezo.setText(szovegMezo.getText().concat(String.valueOf(k)));
			}
		}
		if(e.getSource()==tort) {
			szovegMezo.setText(szovegMezo.getText().concat("."));
		}
		if(e.getSource()==osszeadas) {
			szam1=Double.parseDouble(szovegMezo.getText());
			operator='+';
			szovegMezo.setText("");
		}
		if(e.getSource()==kivonas) {
			szam1=Double.parseDouble(szovegMezo.getText());
			operator='-';
			szovegMezo.setText("");
		}
		if(e.getSource()==szorzas) {
			szam1=Double.parseDouble(szovegMezo.getText());
			operator='*';
			szovegMezo.setText("");
		}
		if(e.getSource()==osztas) {
			szam1=Double.parseDouble(szovegMezo.getText());
			operator='/';
			szovegMezo.setText("");
		}
		if(e.getSource()==egyenlo) {
			szam2=Double.parseDouble(szovegMezo.getText());
			
			switch(operator) {
			case '+':
				eredmeny=szam1+szam2;
				break;
			case '-':
				eredmeny=szam1-szam2;
				break;
			case '*':
				eredmeny=szam1*szam2;
				break;
			case '/':
				eredmeny=szam1/szam2;
				break;
			}
			szovegMezo.setText(String.valueOf(eredmeny));
			szam1=eredmeny;
		}
		if(e.getSource()==tisztitas) {
			szovegMezo.setText("");
		}
		if(e.getSource()==torles) {
			String string=szovegMezo.getText();
			szovegMezo.setText("");
			for(int i=0;i<string.length()-1;i++) {
				szovegMezo.setText(szovegMezo.getText()+string.charAt(i));
			}
		}
		if(e.getSource()==negativ) {
			double atmenetei=Double.parseDouble(szovegMezo.getText());
			atmenetei=atmenetei*(-1);
			szovegMezo.setText(String.valueOf(atmenetei));
		}
		
	}

}

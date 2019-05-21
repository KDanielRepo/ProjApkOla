import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GUI implements ActionListener {
    JFrame ramka = new JFrame();
    JPanel panel = new JPanel();
    JMenu menu = new JMenu();
    JTable tabela ;
    JLabel wpis_txt = new JLabel("Wpis");
    JLabel wynik_txt = new JLabel("Wynik");
    JTextField wpis = new JTextField();
    JTextArea wynik = new JTextArea();
    JButton zero= new JButton("zero");
    JButton zapis = new JButton("save");
    JButton pobieranie = new JButton("down");
    JToolBar narzedzia = new JToolBar();
    // wykres
    JPanel stopka = new JPanel();
    //navi
    JSlider suw_pion = new JSlider();
    JSlider suw_poziom = new JSlider();
    public GUI (){
        obrys();
    }

public void obrys (){
        ramka.setLayout(null);
    ramka.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));
    ramka.add(panel);
    //ramka.add(stopka);
    ramka.setVisible(true);
    ramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    panel.setLayout(null);
    panel.setBounds(0,0,1920,800);
    narzedzia.setBounds(1620,0,300,520);
    narzedzia.setBackground(Color.black);
    panel.add(narzedzia);

    Object[] nazwy = {"A","B","C","D","E"};
    Object[][] a_1 = {{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4}};
    tabela = new JTable(a_1,nazwy);
    tabela.setRowHeight(80);
    tabela.setBounds(530,320,400,400);

    panel.add(tabela);

    wpis.setBounds(1000,400,105,240);
    wpis.setSize(105,240);
    wpis.setBackground(Color.BLUE);
    panel.add(wpis);

    wpis_txt.setBounds(1010,350,105,50);
    panel.add(wpis_txt);



    wynik.setBounds(1100,400,105,240);
    wynik.setSize(105,240);
    wynik.setBackground(Color.RED);
    panel.add(wynik);

    wynik_txt.setBounds(1110,350,105,50);
    panel.add(wynik_txt);



    zero.setBounds(1000,650,70,70);
    zero.setSize(70,70);
    zero.addActionListener(this);
    panel.add(zero);

    zapis.setBounds(1070,650,70,70);
    zapis.setSize(70,70);
    zapis.addActionListener(this);
    panel.add(zapis);

    pobieranie.setBounds(1140,650,70,70);
    pobieranie.setSize(70,70);
    pobieranie.addActionListener(this);
    panel.add(pobieranie);



    stopka.setLayout(null);
    stopka.setBounds(0,960,1920,40);
    stopka.setBackground(Color.green);
    ramka.add(stopka);
    ramka.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    String pobierz = e.getActionCommand();
    switch (pobierz){
        case "zero":
            for (int i=0;i<tabela.getColumnCount();i++){
                for (int j=0;j<tabela.getRowCount();j++){
                    tabela.setValueAt(0,i,j);
                }
            }
            break;
        case "save":
            File zapisf = new File("C:/ProjApkOla/plik.txt");
            FileWriter zapisfw = new FileWriter(zapisf);
            BufferedWriter zapisbufw = new BufferedWriter(zapisfw);
            for(int z=0;z<tabela.getRowCount();z++){
                for (int y=0;y<tabela.getRowCount();y++){
                    zapisbufw.write((String)tabela.getValueAt(z,y));
                }
            }
            break;
        case "down":
            break;
    }
    }
}

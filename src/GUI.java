import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame ramka = new JFrame();
    JPanel panel = new JPanel();
    JMenu menu = new JMenu();
    JTable tabela ;
    JLabel wpis_txt = new JLabel("kek",SwingConstants.CENTER);
    JLabel wynik_txt = new JLabel("keks",SwingConstants.CENTER);
    JTextField wpis = new JTextField();
    JTextArea wynik = new JTextArea();
    JButton zerowanie = new JButton();
    JButton zapis = new JButton();
    JButton pobieranie = new JButton();
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
    ramka.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));
    ramka.add(panel);
    //ramka.add(stopka);
    ramka.setVisible(true);
    ramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    panel.setLayout(null);
    narzedzia.setBounds(1620,0,300,540);
    narzedzia.setBackground(Color.black);
    panel.add(narzedzia);

    Object[] nazwy = {"A","B","C","D","E"};
    Object[][] a_1 = {{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4}};
    tabela = new JTable(a_1,nazwy);
    tabela.setRowHeight(70);
    tabela.setBounds(480,320,350,350);
    panel.add(tabela);

    wpis_txt.setBounds(900,350,100,50);
    panel.add(wpis_txt);

    wpis.setBounds(900,440,100,210);
    wpis.setSize(100,210);
    wpis.setBackground(Color.blue);
    panel.add(wpis);

    wynik_txt.setBounds(1000,350,100,50);
    wynik_txt.setBackground(Color.red);
    panel.add(wynik_txt);

    }
}

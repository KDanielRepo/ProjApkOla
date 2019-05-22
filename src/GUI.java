import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI implements ActionListener {
    JFrame ramka = new JFrame();
    JPanel panel = new JPanel();
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
    JSlider suw_pion = new JSlider(JSlider.VERTICAL,0,4,4);
    JSlider suw_poziom = new JSlider(JSlider.HORIZONTAL,0,4,0);
    int ver = 0;
    public GUI (){
        obrys();
    }




public void obrys (){
        ramka.setLayout(null);
    ramka.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));
    ramka.add(panel);
    ramka.add(stopka);
    ramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    panel.setLayout(null);
    panel.setBounds(0,0,1920,800);
    narzedzia.setBounds(1620,0,300,520);
    narzedzia.setBackground(Color.lightGray);
    panel.add(narzedzia);

    Object[] nazwy = {"A","B","C","D","E"};
    Object[][] a_1 = {{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4}};
    tabela = new JTable(a_1,nazwy);
    tabela.setRowHeight(80);
    tabela.setBounds(530,320,400,400);
    panel.add(tabela);


    suw_poziom.setBounds(530,290,400,30);
    suw_poziom.setMinorTickSpacing(1);
    suw_poziom.setMajorTickSpacing(5);
    suw_poziom.setPaintTicks(true);
    suw_poziom.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            System.out.println("hue");
        }
    });
    panel.add(suw_poziom);


    suw_pion.setBounds(490,320,30,400);
    suw_pion.setMinorTickSpacing(1);
    suw_pion.setMajorTickSpacing(5);
    suw_pion.setPaintTicks(true);
    suw_pion.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            ver = 4 - suw_pion.getValue();
        }
    });
    panel.add(suw_pion);


    wpis.setBounds(1000,400,105,240);
    wpis.setSize(105,240);
    wpis.setBackground(Color.WHITE);
    panel.add(wpis);

    wpis_txt.setBounds(1010,350,105,50);
    panel.add(wpis_txt);



    wynik.setBounds(1105,400,105,240);
    wynik.setSize(105,240);
    wynik.setBackground(Color.WHITE);
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
    stopka.setBackground(Color.lightGray);
    ramka.add(stopka);


    JMenuBar menu = new JMenuBar();
    menu.setBounds(0,0,1920,30);
    JMenu file = new JMenu("pliki");
    JMenu edit=new JMenu("edycja");
    JMenu tools =new JMenu("narzedzia");
    JMenu about=new JMenu("o mnie");
    JMenuItem zapisz = new JMenuItem("zapis");
    JMenuItem clear = new JMenuItem("zeruj");
    JMenuItem insert = new JMenuItem("wstaw");
    JMenuItem ave = new JMenuItem("ave");
    JMenuItem sum = new JMenuItem("sum");
    JMenuItem min = new JMenuItem("min");
    JMenuItem max = new JMenuItem("max");
    JMenuItem autor = new JMenuItem("o mnie");

    menu.add(file);
    menu.add(edit);
    menu.add(tools);
    menu.add(about);

    file.add(zapisz);
    edit.add(clear);
    edit.add(insert);
    tools.add(ave);
    tools.add(sum);
    tools.add(min);
    tools.add(max);
    about.add(autor);

    ////obrazki

    JToolBar pasek_narzedzia = new JToolBar("pasekon");

    ave.addActionListener(this);
    ImageIcon aveobraz = new ImageIcon();
    ave.setIcon(aveobraz);

    sum.addActionListener(this);
    ImageIcon sumobraz = new ImageIcon();
    sum.setIcon(sumobraz);

    min.addActionListener(this);
    ImageIcon minobraz = new ImageIcon();
    min.setIcon(minobraz);

    max.addActionListener(this);
    ImageIcon maxobraz = new ImageIcon();
    max.setIcon(maxobraz);







    zapisz.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File zapisf = new File("C:/ProjApkOla/plik.txt");
                FileWriter zapisfw = new FileWriter(zapisf);
                BufferedWriter zapisbufw = new BufferedWriter(zapisfw);
                for(int z=0;z<tabela.getRowCount();z++){
                    for (int y=0;y<tabela.getRowCount();y++){
                        zapisbufw.write((String)tabela.getValueAt(z,y));
                    }
                }
            }
            catch (IOException ee){
                ee.printStackTrace();
            }
        }
    });
    clear.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i=0;i<tabela.getColumnCount();i++){
                for (int j=0;j<tabela.getRowCount();j++){
                    tabela.setValueAt(0,i,j);
                }
            }
        }
    });
    insert.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabela.setValueAt(Integer.parseInt(wpis.getText()),ver,suw_poziom.getValue());
        }
    });
    ave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            float sum = 0;
            float przez = 0;
            for (int i = 0; i < tabela.getColumnCount(); i++) {
                for (int j = 0; j < tabela.getRowCount(); j++) {
                    try {
                        sum += (Integer)tabela.getValueAt(i,j);
                        przez++;
                    } catch (NumberFormatException | ArithmeticException ee) {
                       ee.printStackTrace();
                    }
                }
            }
            sum = sum/przez;
            wynik.setText(Float.toString(sum));
        }
    });
    sum.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            float sum = 0;
            for (int i = 0; i < tabela.getColumnCount(); i++) {
                for (int j = 0; j < tabela.getRowCount(); j++) {
                    try {
                        sum += (Integer)tabela.getValueAt(i,j);
                    } catch (NumberFormatException | ArithmeticException ee) {
                        ee.printStackTrace();
                    }
                }
            }
            wynik.setText(Float.toString(sum));
        }
    });
    min.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int temp = 0;
            int tempk = 0;
            int min = 0;
            for (int i = 0; i < tabela.getRowCount(); i++) {
                for (int j = 0; j < tabela.getColumnCount(); j++) {
                    try {
                        temp = (Integer) tabela.getValueAt(i,j);
                        if ((j + 1) < tabela.getColumnCount()) {
                            tempk = (Integer) tabela.getValueAt(i,j+1);
                        }
                        if (i == 0 & j == 0) {
                            if (temp < tempk) {
                                min = temp;
                            } else {
                                min = tempk;
                            }
                        }
                        if (temp < min) {
                            min = temp;
                        } else if (tempk < min) {
                            min = tempk;
                        }
                    } catch (NumberFormatException ee) {
                        ee.printStackTrace();
                    }
                }
            }
            wynik.setText(Integer.toString(min));
        }
    });
    max.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int temp = 0;
            int tempk = 0;
            int max = 0;
            for (int i = 0; i < tabela.getColumnCount(); i++) {
                for (int j = 0; j < tabela.getRowCount(); j++) {
                    try {
                        temp = (Integer) tabela.getValueAt(i,j);
                        if ((j + 1) < tabela.getRowCount()) {
                            tempk = (Integer) tabela.getValueAt(i,j+1);
                        }
                        if (temp > max) {
                            max = temp;
                        } else if (tempk > max) {
                            max = tempk;
                        }
                    } catch (NumberFormatException ee) {
                        ee.printStackTrace();
                    }
                }
            }
            wynik.setText(Integer.toString(max));
        }
    });
    autor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showAuthor();
        }
    });






    panel.add(menu);
    ramka.repaint();
    ramka.setVisible(true);
    }

    public void showAuthor(){
        JFrame jFrame = new JFrame("About Me");
        JPanel jPanel = new JPanel(new GridLayout(5,1));
        JLabel apk = new JLabel("ApkOla");
        JLabel ver = new JLabel("Nr. wersji: 1.5.3");
        JLabel lic = new JLabel("Licencja: Freeware");
        JLabel me = new JLabel("Twórca: Sułek Aleksandra");
        JLabel con = new JLabel("Kontakt: suleczek723@gmail.pl");
        jFrame.add(jPanel);
        jPanel.add(apk);
        jPanel.add(ver);
        jPanel.add(lic);
        jPanel.add(me);
        jPanel.add(con);
        jFrame.setSize(300,300);
        jFrame.setLocation(660,240);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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
            try {
                File zapisf = new File("C:/ProjApkOla/plik.txt");
                FileWriter zapisfw = new FileWriter(zapisf);
                BufferedWriter zapisbufw = new BufferedWriter(zapisfw);
                for(int z=0;z<tabela.getRowCount();z++){
                    for (int y=0;y<tabela.getRowCount();y++){
                        zapisbufw.write((String)tabela.getValueAt(z,y));
                    }
                }
            }
            catch (IOException ee){
                ee.printStackTrace();
            }
            break;
        case "down":
        tabela.setValueAt(Integer.parseInt(wpis.getText()),ver,suw_poziom.getValue());
            break;
    }
    }
}

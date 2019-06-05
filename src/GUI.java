import com.l2fprod.common.swing.JButtonBar;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendarCombo;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    java.util.List<String> names;
    List<Double> values;
    JFXPanel chart = new JFXPanel();
    //tips
    DefaultTipModel tipModel = new DefaultTipModel();
    JTipOfTheDay tipOfTheDay = new JTipOfTheDay(tipModel);
    //calendar
    JPanel calendarPanel = new JPanel();
    JCalendarCombo calendarCombo = new JCalendarCombo();
    String[] nazwy = {"A","B","C","D","E"};
    Integer[][] a_1 = {{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4},{0,1,2,3,4}};
    TableModel tableModel = new TableModel(a_1, nazwy);
    JButtonBar jButtonBar = new JButtonBar();
    public GUI (){
        obrys();
        tipOfTheDay.showDialog(ramka);
        getValueList();
        getValues();
        chart();

    }




public void obrys (){
        ramka.setLayout(null);
    ramka.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));
    ramka.add(panel);
    ramka.add(stopka);
    ramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    panel.setLayout(null);
    panel.setBounds(0,0,1920,800);
    narzedzia.setBounds(1820,30,64,360);
    narzedzia.setOrientation(SwingConstants.VERTICAL);
    narzedzia.setBackground(Color.lightGray);
    panel.add(narzedzia);


    tabela = new JTable(tableModel);
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

    //JToolBar pasek_narzedzia = new JToolBar("pasekon");
    JButton przyciskave = new JButton();
    JButton przycisksum = new JButton();
    JButton przyciskmin = new JButton();
    JButton przyciskmax = new JButton();

    przyciskave.addActionListener(this);
    ImageIcon aveobraz = new ImageIcon(getClass().getResource("./sred.png"));
    przyciskave.setIcon(aveobraz);

    przycisksum.addActionListener(this);
    ImageIcon sumobraz = new ImageIcon(getClass().getResource("./sum.png"));
    przycisksum.setIcon(sumobraz);

    przyciskmin.addActionListener(this);
    ImageIcon minobraz = new ImageIcon(getClass().getResource("./mini.png"));
    przyciskmin.setIcon(minobraz);

    przyciskmax.addActionListener(this);
    ImageIcon maxobraz = new ImageIcon(getClass().getResource("./maxi.png"));
    przyciskmax.setIcon(maxobraz);

    narzedzia.add(przyciskave);
    narzedzia.add(przyciskmax);
    narzedzia.add(przyciskmin);
    narzedzia.add(przycisksum);

    tipModel.add(new DefaultTip("1", "Kapibary są fajne"));
    tipModel.add(new DefaultTip("2", "I lubią pływać"));
    tipModel.add(new DefaultTip("3", "Są bardzo fajne"));
    tipModel.add(new DefaultTip("4", "kapibara"));

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    calendarCombo.setDateFormat(dateFormat);
    calendarCombo.setName("data");
    calendarCombo.addDateListener(new DateListener() {
        @Override
        public void dateChanged(DateEvent dateEvent) {
            wynik.setText(calendarCombo.getDate().toString());
        }
    });
    calendarCombo.setBounds(300,0,300,40);
    stopka.add(calendarCombo);
    chart.setBounds(1400,400,500,800);
    panel.add(chart);

    jButtonBar.setOrientation(JButtonBar.HORIZONTAL);
    jButtonBar.setBounds(0,40,120,500);
    JButton jButton = new JButton("kek");
    jButton.setBackground(Color.RED);
    JButton jButton2 = new JButton("kek2");
    jButton2.setBackground(Color.RED);
    JButton jButton3 = new JButton("kek3");
    jButton3.setBackground(Color.RED);
    jButton.setSize(new Dimension(100,100));
    jButton2.setSize(new Dimension(100,100));
    jButton3.setSize(new Dimension(100,100));
    jButtonBar.setBackground(Color.LIGHT_GRAY);
    jButtonBar.add(jButton);
    jButtonBar.add(jButton2);
    jButtonBar.add(jButton3);
    panel.add(jButtonBar);
    jButtonBar.setVisible(true);




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
            tableModel.clearTableValues();
            if (tableModel.changed){
                chart();
                tableModel.changed = false;
            }
        }
    });
    insert.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.insertIntoTable(ver,suw_poziom.getValue(),Integer.parseInt(wpis.getText()));
            if (tableModel.changed){
                chart();
                tableModel.changed = false;
            }
        }
    });
    ave.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.showAverage();
        }
    });
    sum.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.showSum();
        }
    });
    min.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.showMinimum();
        }
    });
    max.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.showMaximum();
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

    public void chart() {
        getValues();
        final CategoryAxis yAxis = new CategoryAxis();
        final NumberAxis xAxis = new NumberAxis();
        final BarChart<Number,String> sbc =
                new BarChart<Number,String>(xAxis, yAxis);
        xAxis.setTickLabelRotation(90);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = new Stage();
                stage.setTitle("Pie Chart");
                Scene scene = new Scene(new Group());
                xAxis.setLabel("Jej ilość");
                yAxis.setLabel("Liczba");
                XYChart.Series series1 = new XYChart.Series();
                for (int i = 0; i < names.size(); i++) {
                    series1.getData().add(new XYChart.Data<Number,String>(values.get(i),names.get(i)));
                }
                sbc.getData().addAll(series1);
                ((Group) scene.getRoot()).getChildren().add(sbc);
                chart.setScene(scene);
            }
        });
    }

    public void getValueList() {
        names = new ArrayList<String>();
        for (int i = 0; i < tabela.getRowCount(); i++) {
            for (int j = 0; j < tabela.getColumnCount(); j++) {
                names.add(Integer.toString((Integer) tabela.getValueAt(i, j)));
                for (int k = names.size(); k >= 0; k--) {
                    if (k >= 2) {
                        if (names.get(names.size() - 1).equals(names.get(k - 2))) {
                            names.remove(names.size() - 1);
                            k = 0;
                        }
                    }
                }
            }
        }
    }

    public void getValues() {
        values = new ArrayList<Double>();
        for (int k = 0; k < names.size(); k++) {
            Double number = 0.0;
            for (int i = 0; i < tabela.getRowCount(); i++) {
                for (int j = 0; j < tabela.getColumnCount(); j++) {
                    if (names.get(k).equals(Integer.toString((Integer) tabela.getValueAt(i, j)))) {
                        number += 1;
                    }
                }
            }
            values.add(number);
        }
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
            tableModel.clearTableValues();
            if (tableModel.changed){
                chart();
                tableModel.changed = false;
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
        tableModel.insertIntoTable(ver,suw_poziom.getValue(),Integer.parseInt(wpis.getText()));
        if (tableModel.changed){
            chart();
            tableModel.changed = false;
        }
            break;
    }
    }
}

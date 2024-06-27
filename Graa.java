package kursJava.Fruit_Shooter_Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Graa extends JFrame {

    private Panell panel;
    public Graa(){
        initComponent();
    }

    public void initComponent(){
        JOptionPane.showMessageDialog(rootPane,"Zasady gry:" +"\n"+
                "1.Zbieraj czerwone jablka, aby zdobywać punkty(+100)"+"\n"+
                "2.Uważaj, aby nie zbierac zielonych jablek(-200)"+"\n"+
                "3.Unikaj owocu granatu (Game Over)"+"\n"+
                "4.Zdobądź jak najszybciej 3500 punktow"+"\n"+
                "5.Ostatnia sprawa, liczbymy na ciebie"+"\n"+
                "Jak bedziesz gotowy wcisnij : "+"OK");
        this.setTitle("Zbierz je wszystkie!!!");
        this.setSize(800,600);

        panel = new Panell();
        this.getContentPane().add(panel);
        this.setIconImage(getAppIcon());
        panel.setLayout(null);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setFocusable(true);
    }
    private Image getAppIcon() {
        try {
            URL iconUrl = getClass().getResource("jablko_czerwone.png");
            BufferedImage icon = ImageIO.read(iconUrl);

            return icon;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        new Graa().setVisible(true);
    }
}

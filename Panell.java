package kursJava.Fruit_Shooter_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Panell extends JPanel implements MouseListener {

    private int boundX = 800;
    private int boundY = 600;
    private Rectt ramkaHp;
    private Rectt pasekHp = new Rectt(90,90,200,15);
    private Toolkit zdjecieMapa;
    private Toolkit zdjecieLufa;
    private Image imgTaczka;
    private Image imgMapa;
    private int points = 0;
    private String labelPoints;
    private List<Charakterss> charakters = new ArrayList<>();
    private Thread movingAppleRed1;
    private Thread movingAppleRed2;
    private Thread movingAppleRed3;
    private Thread movingAppleRed4;
    private Thread movingAppleRed5;
    private Thread movingAppleGreen1;
    private Thread movingAppleGreen2;
    private Thread movingGranat;
    private Thread checkres;
    private int roadX1 = 2,roadY1 = 1;
    private int roadX2 = 4,roadY2 = 2;
    private int roadX3 = 1,roadY3 = 1;
    private int roadX4 = 1,roadY4 = 2;
    private int roadX5 = 3,roadY5 = 3;
    private int roadX6 = 2,roadY6 = 2;
    private int roadX7 = 2,roadY7 = 2;
    private int roadX8 = 1,roadY8 = 1;
    private Charakterss apple_red1 = new Charakterss("Fruit_Shooter_Game\\jablko_czerwone.png",150,150);
    private Charakterss apple_red2 = new Charakterss("Fruit_Shooter_Game\\jablko_czerwone.png",150,150);
    private Charakterss apple_red3 = new Charakterss("Fruit_Shooter_Game\\jablko_czerwone.png",150,150);
    private Charakterss apple_red4 = new Charakterss("Fruit_Shooter_Game\\jablko_czerwone.png",150,150);
    private Charakterss apple_red5 = new Charakterss("Fruit_Shooter_Game\\jablko_czerwone.png",150,150);
    private Charakterss apple_green1 = new Charakterss("Fruit_Shooter_Game\\jablko_zielone.png",150,150);
    private Charakterss apple_green2 = new Charakterss("Fruit_Shooter_Game\\jablko_zielone.png",150,150);
    private Charakterss granat = new Charakterss("Fruit_Shooter_Game\\granat.png",150,150);
    private Charakterss tab[] = new Charakterss[8];
    private boolean hpFlaga = false;
    public Panell(){
        this.setLayout(null);
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("Fruit_Shooter_Game/worek.png").getImage(),
                new Point(15,15),"custom cursor"));
        action();
        addMouseListener(this);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        paintMapa(g2d);
        paintLufa(g2d);
        paintPoints(g2d);
        paintHp(g2d);
        paintCharacters(g2d);
    }
    private void paintMapa(Graphics2D g2d){
        boundX = this.getWidth();
        boundY = this.getHeight();
        zdjecieMapa = Toolkit.getDefaultToolkit();
        imgMapa = zdjecieMapa.getImage("Fruit_Shooter_Game/sad_jablek.jpg");
        g2d.drawImage(imgMapa,0,0,this.getWidth(),this.getHeight(),null);
        repaint();
    }
    private void paintLufa(Graphics2D g2d){
        zdjecieLufa = Toolkit.getDefaultToolkit();
        imgTaczka = zdjecieLufa.getImage("Fruit_Shooter_Game/taczka.png");
        g2d.drawImage(imgTaczka,this.getWidth()/2-100,this.getHeight()-100,200,100,null);
        repaint();
    }
    private void paintPoints(Graphics2D g2d){
        labelPoints = "Punkty: "+points;
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Arial",Font.BOLD,24));
        g2d.drawString(labelPoints,this.getWidth()/2-(g2d.getFont().getSize()*2),g2d.getFont().getSize()+20);
        repaint();
    }
    private void paintCharacters(Graphics2D g2d){
        for (Charakterss charakter : charakters) {
            g2d.drawImage(charakter.image, charakter.getX(), charakter.getY(), this);
        }
    }
    private void paintHp(Graphics2D g2d){

        ramkaHp = new Rectt(89,89,201,16);
//        pasekHp

        ramkaHp.setColor(Color.BLACK);
        pasekHp.setColor(Color.GREEN);

        ramkaHp.setX(ramkaHp.h+25);
        ramkaHp.setY(this.getHeight()-ramkaHp.h-20);
        pasekHp.setX(ramkaHp.x+1);
        pasekHp.setY(ramkaHp.y+1);

        ramkaHp.drawBorder(g2d);
        pasekHp.drawShape(g2d);

        String hp = "Hp";
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Arial",Font.BOLD,18));
        g2d.drawString(hp,ramkaHp.getX()-20,ramkaHp.getY());
        repaint();
    }
    private void action(){
        charakters.add(apple_red1);
        charakters.add(apple_red2);
        charakters.add(apple_red3);
        charakters.add(apple_red4);
        charakters.add(apple_red5);
        charakters.add(apple_green1);
        charakters.add(apple_green2);
        charakters.add(granat);
        tab[0]=apple_red1;
        tab[1]=apple_red2;
        tab[2]=apple_red3;
        tab[3]=apple_red4;
        tab[4]=apple_red5;
        tab[5]=apple_green1;
        tab[6]=apple_green2;
        tab[7]=granat;

        Random random = new Random();
        for(Charakterss charakter:charakters){
            int x = random.nextInt(boundX-charakter.getWidth()-154);
            int y = random.nextInt(boundY-charakter.getHeight()-304);
            charakter.setLocation(x,y);
        }
        movingAppleRed1 = new Thread( () -> {
            while(true){
                moveAppleRed1();
                repaint();
                try {
                    Thread.sleep(20);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        movingAppleRed1.start();
        movingAppleRed2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveAppleRed2();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingAppleRed2.start();
        movingAppleRed3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveAppleRed3();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingAppleRed3.start();
        movingAppleRed4 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveAppleRed4();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingAppleRed4.start();
        movingAppleRed5 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveAppleRed5();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingAppleRed5.start();
        movingAppleGreen1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveAppleGreen1();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingAppleGreen1.start();
        movingAppleGreen2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveAppleGreen2();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingAppleGreen2.start();
        movingGranat = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    moveGranat();
                    repaint();
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        movingGranat.start();
        checkres = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    checkress();
                    repaint();
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        checkres.start();
    }
    private void moveAppleRed1(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_red1){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX1 = -roadX1;
                if(charakter.getY() > boundY-charakter.getHeight()-150 || charakter.getY() < 0)
                    roadY1 = -roadY1;
                charakter.setXspeed(charakter.getX(),roadX1);
                charakter.setYspeed(charakter.getY(),roadY1);
            }
        }
    }
    private void moveAppleRed2(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_red2){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX2 = -roadX2;
                if(charakter.getY() > boundY-charakter.getHeight()-150 || charakter.getY() < 0)
                    roadY2 = -roadY2;
                charakter.setXspeed(charakter.getX(),roadX2);
                charakter.setYspeed(charakter.getY(),roadY2);
            }
        }
    }
    private void moveAppleRed3(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_red3){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX3 = -roadX3;
                if(charakter.getY() > boundY-charakter.getHeight()-150|| charakter.getY() < 0)
                    roadY3 = -roadY3;
                charakter.setXspeed(charakter.getX(),roadX3);
                charakter.setYspeed(charakter.getY(),roadY3);
            }
        }
    }
    private void moveAppleRed4(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_red4){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX4 = -roadX4;
                if(charakter.getY() > boundY-charakter.getHeight()-150 || charakter.getY() < 0)
                    roadY4 = -roadY4;
                charakter.setXspeed(charakter.getX(),roadX4);
                charakter.setYspeed(charakter.getY(),roadY4);
            }
        }
    }
    private void moveAppleRed5(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_red5){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX5 = -roadX5;
                if(charakter.getY() > boundY-charakter.getHeight()-150 || charakter.getY() < 0)
                    roadY5 = -roadY5;
                charakter.setXspeed(charakter.getX(),roadX5);
                charakter.setYspeed(charakter.getY(),roadY5);
            }
        }
    }
    private void moveAppleGreen1(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_green1){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX6 = -roadX6;
                if(charakter.getY() > boundY-charakter.getHeight()-150 || charakter.getY() < 0)
                    roadY6 = -roadY6;
                charakter.setXspeed(charakter.getX(),roadX6);
                charakter.setYspeed(charakter.getY(),roadY6);
            }
        }
    }
    private void moveAppleGreen2(){
        for(Charakterss charakter:charakters){
            if(charakter == apple_green2){
                if(charakter.getX() > boundX-charakter.getWidth()-1 || charakter.getX() < 1)
                    roadX7 = -roadX7;
                if(charakter.getY() > boundY-charakter.getHeight()-150 || charakter.getY() < 0)
                    roadY7 = -roadY7;
                charakter.setXspeed(charakter.getX(),roadX7);
                charakter.setYspeed(charakter.getY(),roadY7);
            }
        }
    }
    private void moveGranat(){
        for(Charakterss charakter:charakters) {
            if (charakter == granat) {
                if (charakter.getX() > boundX - charakter.getWidth() - 1 || charakter.getX() < 1)
                    roadX8 = -roadX8;
                if (charakter.getY() > boundY - charakter.getHeight() - 150 || charakter.getY() < 0)
                    roadY8 = -roadY8;
                charakter.setXspeed(charakter.getX(), roadX8);
                charakter.setYspeed(charakter.getY(), roadY8);
            }
        }
    }
    private void checkress(){
        if(hpFlaga){
            if(pasekHp.getW()>0){
                pasekHp.shrink();
                hpFlaga = false;
            }
            else{
                pasekHp.setW(0);
                hpFlaga = false;
                handleGameOver();
            }
        }
        if(points==3500){
            handleGameWinner();
        }
    }

    private void handleGameWinner() {
        String message = "Przetrwalismy dzieki tobie. Dziekujemy! Wybierz opcję:";
        Object[] options = {"Wyłącz", "Zrestartuj"};
        int choice = JOptionPane.showOptionDialog(
                this,
                message,
                "Koniec gry",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            pasekHp.setW(200);
            points=0;
            new Panel();
        }
    }
    private void handleGameOver() {
        String message = "Przegrałeś! Wybierz opcję:";
        Object[] options = {"Wyłącz", "Zrestartuj"};
        int choice = JOptionPane.showOptionDialog(
                this,
                message,
                "Koniec gry",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            pasekHp.setW(200);
            points=0;
            new Panel();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(Charakterss charakter:charakters){
            if(e.getX()>=charakter.getX() && e.getX() <= charakter.getX()+charakter.getWidth() && e.getY() >= charakter.getY() && e.getY() <= charakter.getY() + charakter.getHeight()){
                Random random = new Random();
                int x = random.nextInt(boundX-charakter.getWidth()-150);
                int y = random.nextInt(boundY-charakter.getHeight()-300);
                if(charakter == apple_red1){
                    apple_red1.setLocation(x,y);
                    repaint();
                    points+=100;
                    break;
                }
                if(charakter == apple_red2){
                    apple_red2.setLocation(x,y);
                    repaint();
                    points+=100;
                    break;
                }
                if(charakter == apple_red3){
                    apple_red3.setLocation(x,y);
                    repaint();
                    points+=100;
                    break;
                }
                if(charakter == apple_red4){
                    apple_red4.setLocation(x,y);
                    repaint();
                    points+=100;
                    break;
                }
                if(charakter == apple_red5){
                    apple_red5.setLocation(x,y);
                    repaint();
                    points+=100;
                    break;
                }
                if(charakter == apple_green1){
                    apple_green1.setLocation(x,y);
                    repaint();
                    points-=200;
                    hpFlaga = true;
                    break;
                }
                if(charakter == apple_green2) {
                    apple_green2.setLocation(x, y);
                    repaint();
                    repaint();
                    points-=200;
                    hpFlaga = true;
                    break;
                }
                if(charakter==granat){
                    points = -999;
                    pasekHp.setW(0);
                    handleGameOver();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

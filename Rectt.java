package kursJava.Fruit_Shooter_Game;

import java.awt.*;

public class Rectt {

    protected int x, y, w=200, h;
    protected Color color;

    public Rectt(int x,int y,int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawBorder(Graphics2D g2d){
        g2d.setColor(color);
        g2d.drawRect(x,y,w,h);
    }
    public void drawShape(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillRect(x,y,w,h);
    }
    public void shrink(){
        w-=30;
    }
}

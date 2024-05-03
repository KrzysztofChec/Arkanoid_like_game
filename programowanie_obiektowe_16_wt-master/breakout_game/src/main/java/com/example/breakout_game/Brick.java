package com.example.breakout_game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicsItem {
    private static int gridRows=20;
    private static int gridCols=10;
    private Color color;
    boolean visible =true;
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

//    public static void setGridSize(int rows, int cols) {
//        gridRows = rows;
//        gridCols = cols;
//    }

    private double x;
    private double y;
    private double width;
    private double height;


//    public Brick(double x, double y,  Color color) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//        this.color = color;
//    }
public Brick(double x, double y, double width, double height, Color color) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
}

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
//    public Brick() {
//
//        y = 60;//canvasHeight * 0.5;
//        x = (canvasWidth-width) / 5;
//
//        height = canvasHeight * .01;
//        width = canvasWidth * 0.5;
//        color=Color.BLUE;
//    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        if (visible) {
            graphicsContext.setFill(color);
            graphicsContext.fillRect(x, y, width, height);
        }
    }
}

package com.example.breakout_game;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {

//    Paddle paddle = new Paddle();
    private Point2D moveVector = new Point2D(1, -1).normalize();
    private double velocity = 500;
    Paddle paddle = new Paddle();
    public Point2D getMoveVector() {
        return moveVector;
    }

    public void setMoveVector(Point2D moveVector) {
        this.moveVector = moveVector;
    }

    public Ball() {
        x = -100;
        y = -100;
        width = height = canvasHeight * .015;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void setPosition(Point2D point) {
        this.x = point.getX() - width/2;
        this.y = point.getY() - height/2;
    }

    public double getVelocity() {
        return velocity;
    }



    public void bounceFromPaddle(double hitPosition, double paddleX, double paddleWidth) {
        double relativeHitPosition = hitPosition - paddleX; // Pozycja uderzenia względem paletki

        double angle = 0; // Kąt odbicia

        if (relativeHitPosition < paddleWidth / 3) {

            angle = Math.toRadians(-35);
        } else if (relativeHitPosition > 2 * paddleWidth / 3) {

            angle = Math.toRadians(-35);
        } else {

            angle = Math.toRadians(70);
        }


        double adjustmentFactor = (relativeHitPosition - paddleWidth / 3) / (paddleWidth / 3);
        double adjustedAngle = angle * (1 + adjustmentFactor);

        double ballSpeed = moveVector.magnitude();
        double direction = Math.signum(moveVector.getX());

        double newSpeedX = ballSpeed * Math.cos(adjustedAngle) * direction;
        double newSpeedY = -ballSpeed * Math.sin(adjustedAngle);

        moveVector = new Point2D(newSpeedX, newSpeedY);
    }









    public void updatePosition(double diff) {
        x += moveVector.getX() * velocity * diff;
        y += moveVector.getY() * velocity * diff;
       // System.out.println("x"+x);
        //System.out.println("y:"+y);
    }
}

package com.example.breakout_game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;

    public void loadLevel() {
        int gridRows = 20;
        int gridCols = 10;
        double brickWidth = getWidth() / gridCols;
        double brickHeight = getHeight() / (2 * gridRows);  // Ustalanie dynamicznej wysokości cegieł

        System.out.println("brickWidth: "+brickWidth);
        System.out.println("brickHeight: "+brickHeight);
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE};

        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridCols; col++) {
                double brickX = col * brickWidth;
                double brickY = row * brickHeight;

//                System.out.println("brick x" + brickX);
//                System.out.println("brickY " + brickY);
                Color color;
                if (row >= 2 && row <= 7) {
                    color = colors[row - 2];
                } else {
                    color = Color.BLUE;
                }

                Brick brick = new Brick(brickX, brickY, brickWidth, brickHeight, color);
                bricks.add(brick);
            }
        }
    }
    private boolean gameRunning = false;

    private boolean shouldBallBounceHorizontally (){
        if (ball.getX() <= 0.2 || ball.getX() + ball.getWidth() >= getWidth()) {
            return true;
        }
        return false;
    }

    private boolean shouldBallBounceVertically(){
        if ( ball.getY()<=0.2) {
            return true;
        }
        return false;
    }
    private static final double MAX_ANGLE_LEFT = Math.toRadians(30);
    private static final double MAX_ANGLE_RIGHT = Math.toRadians(60);

    private AnimationTimer animationTimer = new AnimationTimer() {
        private long lastUpdate;
        @Override
        public void handle(long now) {
            double diff = (now - lastUpdate)/1_000_000_000.;
            lastUpdate = now;

            if(shouldBallBounceVertically()){

                ball.setMoveVector(new Point2D(ball.getMoveVector().getX(), -ball.getMoveVector().getY()));

            }

            if (shouldBallBounceHorizontally()) {

                ball.setMoveVector(new Point2D(-ball.getMoveVector().getX(), ball.getMoveVector().getY()));
            }
            ball.updatePosition(diff);



            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballWidth = ball.getWidth();
            double ballHeight = ball.getHeight();

            double paddleX = paddle.getX();
            double paddleY = paddle.getY();
            double paddleWidth = paddle.getWidth();
            double paddleHeight = paddle.getHeight();

            if (ballY + ballHeight >= paddleY && ballY <= paddleY + paddleHeight && ballX + ballWidth >= paddleX && ballX <= paddleX + paddleWidth) {
                double ballSpeedX = ball.getMoveVector().getX();
                double ballSpeedY = ball.getMoveVector().getY();

                double hitPosition = ballX + ballWidth / 2 - (paddleX + paddleWidth / 2);
                double normalizedHitPosition = hitPosition / (paddleWidth / 2);
                double bounceAngle = normalizedHitPosition * Math.PI / 3;

                double ballSpeed = Math.sqrt(ballSpeedX * ballSpeedX + ballSpeedY * ballSpeedY);
                double newBallSpeedX = ballSpeed * Math.sin(bounceAngle);
                double newBallSpeedY = -ballSpeed * Math.cos(bounceAngle);

                ball.setMoveVector(new Point2D(newBallSpeedX, newBallSpeedY)); //odbicie z kartami
            }

//            if (ballY + ballHeight >= paddleY && ballY <= paddleY + paddleHeight && ballX + ballWidth >= paddleX && ballX <= paddleX + paddleWidth) {
//                double ballSpeedX = ball.getMoveVector().getX();
//                double ballSpeedY = ball.getMoveVector().getY();
//
//                // Odwróć kierunek ruchu piłki w osi Y
//                ballSpeedY = -ballSpeedY;
//
//                // Zmień wektor ruchu piłki
//                ball.setMoveVector(new Point2D(ballSpeedX, ballSpeedY)); //normalne odbicie
//            }










            for (Brick brick : bricks) {

                if (brick.isVisible()) {
                    //System.out.println("w petli ");
                    double brickX = brick.getX();
                    double brickY = brick.getY();
                    double brickWidth = brick.getWidth();
                    double brickHeight = brick.getHeight();



                    if (ballX + ballWidth >= brickX && ballX <= brickX + brickWidth &&
                            ballY + ballHeight >= brickY && ballY <= brickY + brickHeight) {
                        // Wykryto kolizję piłki z cegłą
                        System.out.println("kolizja ");
                        // Sprawdź w którą ściankę cegły uderzyła piłka
                        boolean hitTop = ballY + ballHeight >= brickY && ballY + ballHeight <= brickY + brickHeight / 2;
                        boolean hitBottom = ballY <= brickY + brickHeight && ballY >= brickY + brickHeight / 2;
                        boolean hitLeft = ballX + ballWidth >= brickX && ballX + ballWidth <= brickX + brickWidth / 2;
                        boolean hitRight = ballX <= brickX + brickWidth && ballX >= brickX + brickWidth / 2;

                        if (hitTop || hitBottom) {

                            ball.setMoveVector(new Point2D(ball.getMoveVector().getX(), -ball.getMoveVector().getY()));
                            brick.setVisible(false);
                        } else if (hitLeft || hitRight) {

                            brick.setVisible(false);
                            ball.setMoveVector(new Point2D(-ball.getMoveVector().getX(), ball.getMoveVector().getY()));
                        }

                        if (hitTop || hitBottom || hitLeft || hitRight) {

                            brick.setVisible(false);
                            draw();
                            break;
                        }

                        //brick.setVisible(false);
                        draw();

                    }
                }
            }


            draw();
        }

        @Override
        public void start() {
            super.start();
            lastUpdate = System.nanoTime();
        }
    };

    public GameCanvas() {
        super(640, 700);

        bricks = new ArrayList<>();

        this.setOnMouseMoved(mouseEvent -> {
            paddle.setPosition(mouseEvent.getX());
            if(!gameRunning)
                ball.setPosition(new Point2D(mouseEvent.getX(), paddle.getY() - ball.getWidth() / 2));


            draw();
        });


        this.setOnMouseClicked(mouseEvent -> {
            gameRunning = true;
            animationTimer.start();
        });

    }
    //Brick b;
    public void initialize() {
        graphicsContext = this.getGraphicsContext2D();
        GraphicsItem.setCanvasSize(getWidth(), getHeight());
        paddle = new Paddle();
        ball = new Ball();
        loadLevel();
//        b=new Brick();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());

        for (Brick brick : bricks) {
            //System.out.println(brick);
            brick.draw(graphicsContext);
        }
        //b.draw(graphicsContext);
        paddle.draw(graphicsContext);
        ball.draw(graphicsContext);



    }

}
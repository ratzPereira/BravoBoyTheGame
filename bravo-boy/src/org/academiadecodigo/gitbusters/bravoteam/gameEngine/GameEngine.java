package org.academiadecodigo.gitbusters.bravoteam.gameEngine;

import org.academiadecodigo.gitbusters.bravoteam.Sound;
import org.academiadecodigo.gitbusters.bravoteam.utility.Random;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameEngine {

    private static final int CANVAS_WIDTH = 960;
    private static final int CANVAS_HEIGHT = 540;
    private static final int ROW_LEVEL = 490;
    private static final int JUMP_LEVEL = 320;
    private static final int CELLSIZE = 50;
    private static final int PADDING = 10;
    private boolean jump;
    private boolean inAir;
    private boolean isRunning = true;
    private boolean onGrounded = true;
    private Picture hero;
    private Picture background;
    private Picture ground;
    private Picture block;
    private Picture block1;
    private Picture block2;
    private Picture bird;
    private Picture bird2;
    private int increment = PADDING;
    private int random1 = Random.getRandomNum(1, 400);
    private int random2 = Random.getRandomNum(200, 400);
    private int random3 = Random.getRandomNum(400, 500);
    private Sound gamePlayMusic;
    private Sound gameOverSound;
    private Sound gameOverHit;
    private Sound menuMusic;
    private Text score;
    private Picture gameOver;
    private Picture menuImage;
    private boolean isInMenu = true;
    private boolean startGame = false;
    private boolean restartAvailable = false;
    private Integer difficultyCounter = 0;

    public GameEngine() throws InterruptedException {
        new KeyboardSetupRestart(this);
        new KeyboardSetupMenu(this);
        new KeyboardSetup(this);
        Rectangle CANVAS = new Rectangle(PADDING, PADDING, CANVAS_WIDTH, CANVAS_HEIGHT);
        CANVAS.draw();
        update();
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void stopGameOverMusic() {
        gameOverHit.stop();
        gameOverSound.stop();
    }

    public void setRestartAvailable(boolean restartAvailable) {
        this.restartAvailable = restartAvailable;
    }

    public boolean getRestartAvailable() {
        return restartAvailable;
    }


    public void startGame() {
        startGame = true;
    }

    public void initMenu() {
        menuImage = new Picture(PADDING, PADDING, "resources\\startimg.png");
        menuImage.draw();
        menuMusic = new Sound("resources\\music\\menuSong.wav");
        menuMusic.play(false);
    }

    public void update() throws InterruptedException {


        while (isRunning) {
            Thread.sleep(10);

            if (isInMenu) {
                initMenu();
                isInMenu = false;
            }


            if (startGame) {

                if (hero == null && background == null && ground == null && block == null) {
                    drawInitialGraphics();
                }

                if (jump && onGrounded) {
                    hero.translate(0, -5);
                    onGrounded = false;
                    inAir = true;
                    Sound jump = new Sound("resources\\music\\jump.wav");
                    jump.play(false);
                }

                if (inAir && hero.getY() > JUMP_LEVEL) {
                    hero.translate(0, -15);
                    if (hero.getY() == JUMP_LEVEL) {
                        inAir = false;
                    }

                } else if (hero.getY() == ROW_LEVEL) {
                    onGrounded = true;
                    jump = false;

                }

                if (hero.getY() < ROW_LEVEL) {
                    hero.translate(0, 5);
                }

                if (block1 == null && block != null) {
                    if (block.getX() == random1) {
                        block1 = new Picture(CANVAS_WIDTH - CELLSIZE, ROW_LEVEL, "resources\\block.png");
                        block1.draw();
                    }
                }

                if (block != null) {
                    if (block.getX() <= 0) {
                        block.delete();
                        block = null;
                        difficultyCounter++;
                        score.delete();
                        score = new Text(835,50,"SCORE: " + difficultyCounter.toString());
                        score.grow(70,25);
                        score.setColor(Color.WHITE);
                        score.draw();
                    }
                }


                if (block == null && block2 != null) {
                    if (block2.getX() == random2) {
                        block = new Picture(CANVAS_WIDTH - CELLSIZE, ROW_LEVEL, "resources\\block.png");
                        block.draw();
                    }
                }

                if (block2 == null && block1 != null) {
                    if (block1.getX() == random3) {
                        block2 = new Picture(CANVAS_WIDTH - CELLSIZE, ROW_LEVEL, "resources\\block.png");
                        block2.draw();
                    }
                }

                if (block1 != null) {
                    if (block1.getX() <= 0) {
                        block1.delete();
                        block1 = null;
                        difficultyCounter++;
                        score.delete();
                        score = new Text(835,50,"SCORE: " + difficultyCounter.toString());
                        score.grow(70,25);
                        score.setColor(Color.WHITE);
                        score.draw();
                    }
                }

                if (block2 != null) {
                    if (block2.getX() <= 0) {
                        block2.delete();
                        block2 = null;
                        difficultyCounter++;
                        score.delete();
                        score = new Text(835,50,"SCORE: " + difficultyCounter.toString());
                        score.grow(70,25);
                        score.setColor(Color.WHITE);
                        score.draw();
                    }
                }

                if (block1 != null) {
                    if (difficultyCounter < 8) {
                        block1.translate(-5, 0);
                    } else if (difficultyCounter >= 8 && difficultyCounter < 20) {
                        block1.translate(-7, 0);
                    } else {
                        block1.translate(-10, 0);
                    }
                }
                if (bird != null) {
                    if (difficultyCounter < 8) {
                        bird.translate(-2, 0);
                    } else if (difficultyCounter >= 8 && difficultyCounter < 20) {
                        bird.translate(-4, 0);
                    } else {
                        bird.translate(-6, 0);
                    }
                }
                if (bird != null) {
                    if (bird.getX() <= 0) {
                        bird.delete();
                        bird2 = null;
                        difficultyCounter++;
                    }
                }
                if (bird2 != null) {
                    if (difficultyCounter < 8) {
                        bird2.translate(-2, 0);
                    } else if (difficultyCounter >= 8 && difficultyCounter < 20) {
                        bird2.translate(-4, 0);
                    } else {
                        bird2.translate(-6, 0);
                    }
                }

                if (block != null) {
                    if (difficultyCounter < 8) {
                        block.translate(-5, 0);
                    } else if (difficultyCounter >= 8 && difficultyCounter < 20) {
                        block.translate(-7, 0);
                    } else {
                        block.translate(-10, 0);
                    }
                }

                if (block2 != null) {
                    if (difficultyCounter < 8) {
                        block2.translate(-5, 0);
                    } else if (difficultyCounter >= 8 && difficultyCounter < 20) {
                        block2.translate(-7, 0);
                    } else {
                        block2.translate(-10, 0);
                    }
                }

                if (block2 != null) {
                    if (hero.getX() >= block2.getX() - 30 && hero.getX() <= block2.getX() + 30 && hero.getY() <= block2.getY() && hero.getY() >= block2.getY() - 50) {
                        int x = hero.getX();
                        int y = hero.getY();
                        hero.delete();
                        hero = new Picture(x, y, "resources\\deadHero.png");
                        hero.draw();
                        gameOver = new Picture(PADDING, PADDING, "resources\\gameover2.png");
                        gameOver.draw();
                        isRunning = false;
                        if (gamePlayMusic != null)
                            gamePlayMusic.stop();
                        gameOverHit = new Sound("resources\\music\\punchHit.wav");
                        gameOverHit.play(false);
                        gameOverSound = new Sound("resources\\music\\gameOver.wav");
                        gameOverSound.play(false);
                    }
                }

                if (block1 != null) {
                    if (hero.getX() >= block1.getX() - 30 && hero.getX() <= block1.getX() + 30 && hero.getY() <= block1.getY() && hero.getY() >= block1.getY() - 50) {
                        int x = hero.getX();
                        int y = hero.getY();
                        hero.delete();
                        hero = new Picture(x, y, "resources\\deadHero.png");
                        hero.draw();
                        gameOver = new Picture(PADDING, PADDING, "resources\\gameover2.png");
                        gameOver.draw();
                        isRunning = false;
                        if (gamePlayMusic != null)
                            gamePlayMusic.stop();
                        gameOverHit = new Sound("resources\\music\\punchHit.wav");
                        gameOverHit.play(false);
                        gameOverSound = new Sound("resources\\music\\gameOver.wav");
                        gameOverSound.play(false);
                    }
                }

                if (block != null) {
                    if (hero.getX() >= block.getX() - 30 && hero.getX() <= block.getX() + 30 && hero.getY() <= block.getY() && hero.getY() >= block.getY() - 50) {
                        int x = hero.getX();
                        int y = hero.getY();
                        hero.delete();
                        hero = new Picture(x, y, "resources\\deadHero.png");
                        hero.draw();
                        gameOver = new Picture(PADDING, PADDING, "esources\\gameover2.png");
                        gameOver.draw();
                        isRunning = false;
                        gamePlayMusic.stop();
                        gameOverHit = new Sound("resources\\music\\punchHit.wav");
                        gameOverHit.play(false);
                        gameOverSound = new Sound("resources\\music\\gameOver.wav");
                        gameOverSound.play(false);

                    }
                }
            }
        }

    }

    public void jump() {

        jump = true;
    }

    public void drawInitialGraphics() {

        isInMenu = false;
        menuImage.delete();
        menuMusic.stop();


        this.gamePlayMusic = new Sound("resources\\music\\GamePlayMusic.wav");
        gamePlayMusic.setLoop(1000);
        gamePlayMusic.play(false);

        background = new Picture(PADDING, PADDING, "resources\\background.gif");
        background.draw();

        for (int i = 0; i < 19; i++) {
            ground = new Picture(increment, CANVAS_HEIGHT, "resources\\ground-tile.png");
            ground.draw();
            increment += 50;
        }

        score = new Text(835,50,"SCORE: " + difficultyCounter.toString());
        score.grow(70,25);
        score.setColor(Color.WHITE);
        score.draw();

        hero = new Picture(150, ROW_LEVEL, "resources\\hero.png");
        hero.draw();

        block = new Picture(CANVAS_WIDTH - CELLSIZE, ROW_LEVEL, "resources\\block.png");
        block.draw();

        bird = new Picture(CANVAS_WIDTH - CELLSIZE, ROW_LEVEL - 150, "resources\\angry111.png");
        bird.draw();

    }


}

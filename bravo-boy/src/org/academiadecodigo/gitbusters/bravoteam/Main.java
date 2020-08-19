package org.academiadecodigo.gitbusters.bravoteam;

import org.academiadecodigo.gitbusters.bravoteam.gameEngine.GameEngine;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        GameEngine gameEngine = new GameEngine();

        while (true) {
            if (gameEngine.getRestartAvailable()) {
                gameEngine.stopGameOverMusic();
                gameEngine = new GameEngine();
            }
        }


    }
}

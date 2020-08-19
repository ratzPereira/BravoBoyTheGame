package org.academiadecodigo.gitbusters.bravoteam.gameEngine;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardSetupRestart implements KeyboardHandler {

    private Keyboard keyboard = new Keyboard(this);
    private KeyboardEvent upKey = new KeyboardEvent();
    private GameEngine gameEngine;

    public KeyboardSetupRestart(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        setUpKey();
    }

    public void setUpKey() {
        upKey.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        upKey.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(upKey);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (!gameEngine.getIsRunning()) {
            gameEngine.setRestartAvailable(true);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}

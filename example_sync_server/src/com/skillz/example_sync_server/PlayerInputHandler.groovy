package com.skillz.example_sync_server

import com.skillz.server.Game
import com.skillz.server.MessageHandler
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import messages.PlayerInput

@Slf4j
@CompileStatic
class PlayerInputHandler extends MessageHandler<Player> {
    def validate(PlayerInput message) {
        if (player.game.isGamePaused()) {
            return false
        }

        if (message.newScore() > 5) {
            return false
        }

        int newScore = player.score + message.newScore()
        if (newScore > 100 || newScore < 0) {
            return false;
        }

        return true
    }

    def on(PlayerInput message) {
        player.score += message.newScore();
    }
}

package com.skillz.example_sync_server

import com.skillz.server.Game
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.security.SecureRandom

@Slf4j
@CompileStatic
class ExampleSkillzGame extends Game<Player> {

    public static final int TICK_RATE = 150
    public static final String VERSION = "1.0"
    public static final int VERSION_NUMBER = 1

    private static final int WIN_SCORE = 100

    @Override
    def start() {
        // Logic ran once the game has started (both players successfully joined) (ex. choose which player goes first)
    }

    @Override
    def process() {
        // Ran once per Tick and should handle
        for (player in players) {
            if (player.score >= WIN_SCORE) {
                setCompleted()
            }
        }
    }

    @Override
    def broadcast() {
        // Ran once per Tick, after Process() and should handle sending out all outgoing messages that occur on Tick.
        for (player in players) {
            if (player.isConnected()) {
                player.messageSender.sendGameState()
            }
        }
    }
}

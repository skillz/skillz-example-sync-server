package com.skillz.example_sync_server

import com.skillz.server.Game
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.security.SecureRandom

@Slf4j
@CompileStatic
class ExampleSkillzGame extends Game<Player> {

    // Required Configuration Vars
    public static final int TICK_RATE = 100
    public static final String VERSION = "1.0"
    public static final int VERSION_NUMBER = 1

    // Optional Configuration Vars
    public static final int WARNING_SECONDS = 4
    public static final int DISCONNECT_SECONDS = 15
    public static final int MAX_RECONNECTS = -1
    public static final int MAX_ALLOWED_APP_PAUSES = -1
    public static final int MAX_ALLOWED_APP_CONNECTION_WARNINGS = -1
    public static final int MIN_TIME_IN_SECONDS_FOR_NEW_PAUSE = 1
    public static final boolean USE_CUMULATIVE_PAUSE_DISCONNECT_TIMER = true
    public static final boolean ABORT_ON_DISCONNECT = true
    public static final int FORFEIT_SCORE_VALUE = 0

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

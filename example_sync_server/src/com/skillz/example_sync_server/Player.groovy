package com.skillz.example_sync_server

import com.skillz.server.Client
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class Player extends Client<ExampleSkillzGame> {

    MessageSender messageSender = new MessageSender(this)

    @Override
    def start() {
        // Logic ran once the game has started (both players successfully joined)
    }

    @Override
    def process() {
        // Ran once per Tick. Can be used to track game-time events per player, for example
    }

    @Override
    def reconnect(Client existingPlayer) {
        // Ran when a player reconnects and used to restore the state from the originally connected player
        existingPlayer = existingPlayer as Player
    }
}

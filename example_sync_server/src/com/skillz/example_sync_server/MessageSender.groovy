package com.skillz.example_sync_server

import com.skillz.server.Client
import com.skillz.server.MessageBuilder
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import messages.GameState

@Slf4j
@CompileStatic
class MessageSender {
    private Player player

    MessageSender(Player player) {
        this.player = player
    }

    MessageSender sendGameState() {
        MessageBuilder builder = new MessageBuilder()
        GameState.startGameState(builder)
        GameState.addOpcode(builder, new GameState().opcode())
        GameState.addTickCount(builder, player.game.tickCounter)
        GameState.addGameTickCount(builder, player.game.gameTickCounter)
        GameState.addPlayerScore(builder, player.score)
        GameState.addOpponentScore(builder, player.game.getOtherPlayer(player).score)
        int offset = GameState.endGameState(builder)
        builder.finish(offset)
        player.write(builder.sizedByteArray())
        this
    }

}

package com.skillz.example_sync_server

import com.skillz.server.MessageHandler
import com.skillz.server.Server

class Main {
    static void main(final String[] args) {
        Server.start(MessageHandler.Type.TICK)
    }
}

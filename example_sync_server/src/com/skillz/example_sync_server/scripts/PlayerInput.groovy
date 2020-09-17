// This is an example of using a Groovy script instead of a class to implement message handlers
// This is functionally the same as the implementation in PlayerInputHandler.groovy, but as a script
//
// Pros:
//  - Zero boilerplate
//  - Live reloadable (will be released in future iteration shortly)
// Cons:
//  - Ran as a groovy script as opposed to the statically compiled MessageHandler via @CompileStatic

//def validate(messages.PlayerInput message) {
//    if (player.game.isGamePaused()) {
//        return false
//    }
//
//    if (message.newScore() > 5) {
//        return false
//    }
//
//    int newScore = player.score + message.newScore()
//    if (newScore > 100 || newScore < 0) {
//        return false;
//    }
//
//    return true
//}
//
//def on(messages.PlayerInput message) {
//    player.score += message.newScore();
//}

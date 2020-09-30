// This is an example of using a Groovy script instead of a class to implement message handlers
// This script will pass all Chat messages directly to the other player
//
// Pros:
//  - Zero boilerplate
//  - Live reloadable (will be released in future iteration shortly)
// Cons:
//  - Ran as a groovy script as opposed to the statically compiled MessageHandler via @CompileStatic

def validate(messages.Chat message) {
    if (player.isPaused()) {
        return false
    }
    true
}
def on(messages.Chat message) {
    player.passthrough message
}

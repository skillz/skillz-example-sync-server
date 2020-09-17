package com.skillz.example_sync_server.tests

import spock.lang.Specification
import spock.lang.Unroll

class ExampleTest extends Specification {

    def setupSpec() {
        // Ran once at start of the suite
    }

    def setup() {
        // Ran once before each individual test
    }

    @Unroll
    def '+ operator returns #result for #x + #y'() {
        given:
        // Define initial variable states here for this test (that aren't defined in the where clause below)

        when:
        // Execute the function you want to test with the inputs provided
        def sum = x + y

        then:
        // Verify that the output was expected
        sum == result

        where:
        // Define values in a table to test multiple inputs
        x   |   y  || result
        1   |   0  || 1
        10  |  10  || 20
        100 |  -5  || 95

    }

}

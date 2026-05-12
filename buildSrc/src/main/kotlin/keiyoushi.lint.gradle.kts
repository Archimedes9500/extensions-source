plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        target()
        targetExclude("**/build/**/*.kt")
        ktlint()
            .editorConfigOverride(mapOf(
                "max_line_length" to 2147483647,
            ))
        trimTrailingWhitespace()
        endWithNewline()
        addStep(RandomUaCheck.create())
    }

    java {
        target()
        targetExclude("**/build/**/*.java")
        googleJavaFormat()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("gradle") {
        target("**/*.gradle")
        trimTrailingWhitespace()
        endWithNewline()
    }

    format() {
        target("**/*.xml")
        targetExclude("**/build/**/*.xml")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks {
    val spotlessTask = "spotlessCheck"
    named("preBuild") {
        dependsOn(tasks.getByName(spotlessTask))
    }
}

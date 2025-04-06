package it.fabiovokrri.model

/**
 * Status of the [Task].
 */
enum class TaskStatus(name: String) {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
}
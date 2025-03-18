pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Taskly"
include(":app")
include(":core:data")
include(":core:model")
include(":core:database")
include(":core:datastore")
include(":core:network")
include(":core:notifications")
include(":core:common")
include(":features:overview")
include(":features:profile")
include(":features:tasklist")
include(":widget")
include(":wearable")

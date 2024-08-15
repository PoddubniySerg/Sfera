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
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "zavod-teplits-ru"
include(":app")
include(":app-core")
include(":app-config")
include(":app-di")
include(":app-navigation")
include(":data-api")
include(":data-storage")
include(":feature-onboarding")
include(":feature-auth")
include(":feature-chats")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //noinspection JcenterRepositoryObselete
        jcenter()
    }
}

rootProject.name = "ProjectTAPenerjemahBahasaIsyaratIndonesia"
include(":app")

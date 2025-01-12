package com.example.composebase.di

object AppModule {


    private val coreModules = listOf(
        CoroutinesModule.module,
        LocalStorageModule.module,
        UtilsModule.module,
        WebServiceModule.module
    )

    private val databaseModule = listOf(DatabaseModule.module)

    private val viewModelsModules = listOf(ViewModelsModule.module)

    private val useCasesModules = listOf(UseCasesModule.module)

    private val firebaseModules = listOf(FirebaseModule.module)

    private val networkModules = listOf(NetworkModule.module)

    private val repositoriesModules = listOf(RepositoriesModule.module)


    val modules =
        coreModules + databaseModule + viewModelsModules + useCasesModules +
                firebaseModules + networkModules + repositoriesModules
}
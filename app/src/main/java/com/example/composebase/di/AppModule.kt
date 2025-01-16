package com.example.composebase.di

import com.ba.pokedex.utils.notifications.INotificationService
import com.example.composebase.core.utils.notifications.NotificationService
import com.example.composebase.core.utils.permissions.IPermissionService
import com.example.composebase.core.utils.permissions.PermissionService
import org.koin.dsl.module

object AppModule {


    private val coreModules = listOf(
        CoroutinesModule.module,
        LocalStorageModule.module,
        UtilsModule.module,
        WebServiceModule.module
    )

    private val myModule = module {

        single<INotificationService> { NotificationService() }

        single<IPermissionService> { PermissionService() }

    }

    private val databaseModule = listOf(DatabaseModule.module)

    private val viewModelsModules = listOf(ViewModelsModule.module)

    private val useCasesModules = listOf(UseCasesModule.module)

    private val firebaseModules = listOf(FirebaseModule.module)

    private val networkModules = listOf(NetworkModule.module)

    private val repositoriesModules = listOf(RepositoriesModule.module)


    val modules =
        coreModules + databaseModule + viewModelsModules + useCasesModules +
                firebaseModules + networkModules + repositoriesModules + myModule
}
package com.example.composebase.core.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker.Result.retry
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.ba.pokedex.utils.notifications.INotificationService
import com.example.composebase.R
import com.example.composebase.core.usecases.IWorkerPokemonUseCase
import com.example.composebase.core.utils.coroutines.ICoroutineContextProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonWorker(private val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), KoinComponent {

    companion object {
        private const val REPEAT_IN_SECONDS = 30000L
        private const val NUMBER_OF_EXECUTIONS = 5
        private const val COUNTER_START = 1
        private const val QUERY_LIMIT = 10
        const val OFFSET_KEY = "OFFSET_KEY"
        private const val DEFAULT_OFFSET = 15

    }

    private val notificationService: INotificationService by inject()

    private val coroutineCtx: ICoroutineContextProvider by inject()

    private val useCase: IWorkerPokemonUseCase by inject()


    override suspend fun doWork(): Result {
        val currentOffset = inputData.getInt(OFFSET_KEY, DEFAULT_OFFSET)

        return withContext(coroutineCtx.getIOContext()) {

            return@withContext try {
                var offset = currentOffset

                for (i in COUNTER_START..NUMBER_OF_EXECUTIONS) {
                    val numberInPokedex = useCase.execute(offset, QUERY_LIMIT)
                    offset += QUERY_LIMIT
                    notificationService.showNotification(
                        context.getString(R.string.pokemon_worker_notification_title),
                        context.getString(
                            R.string.pokemon_worker_notification_body,
                            numberInPokedex.toString()
                        )
                    )
                    delay(REPEAT_IN_SECONDS)
                }

                val outputData = workDataOf(OFFSET_KEY to offset)
                Result.success(outputData)
            } catch (e: Exception) {
                notificationService.showNotification(
                    context.getString(R.string.pokemon_worker_error_notification_title),
                    context.getString(
                        R.string.pokemon_worker_error_notification_body
                    )
                )
                retry()
            }
        }
    }
}
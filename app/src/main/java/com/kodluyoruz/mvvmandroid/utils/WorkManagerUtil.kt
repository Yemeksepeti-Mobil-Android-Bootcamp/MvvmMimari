package com.kodluyoruz.mvvmandroid.utils

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.*

class WorkManagerUtil constructor(app: Application) {

    private val workManager = WorkManager.getInstance(app)
    internal val workInfo =
        workManager.getWorkInfosByTagLiveData("KodluyoruzWork")

    internal fun apply() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val myWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(constraints)
                .build()
        workManager.enqueue(myWorkRequest)
    }

    //    internal fun apply(imageOperations: ImageOperations) {
//        imageOperations.continuation.enqueue()
//    }
    internal fun cancel() {
        workManager.cancelUniqueWork("KodluyoruzWork")
    }

}


class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        // Do the work here--in this case, upload the images.
//        uploadImages()
        Log.v("WorkManager", "Work-TimingLog")

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}
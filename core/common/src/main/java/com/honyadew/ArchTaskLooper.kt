package com.honyadew

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor

// For unit test in ViewModel
@SuppressLint("RestrictedApi")
class ArchTaskLooper {
    fun after(){
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
    fun before(){
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor(){
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

        })
    }
}
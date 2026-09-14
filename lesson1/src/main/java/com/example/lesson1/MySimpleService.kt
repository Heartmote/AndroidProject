package com.example.lesson1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MySimpleService : Service() {

    private val TAG = "MySimpleService"

    // 当 Service 第一次被创建时调用
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate: 服务已创建")
    }

    // 每次通过 startService() 启动时都会调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onStartCommand: 服务已启动")

        // 模拟一个耗时操作（注意：实际开发中耗时操作需要开子线程，不能直接写在主线程）
        // 这里仅仅为了演示，实际项目中不要在主线程做网络请求或大量计算
        val data = intent?.getStringExtra("data")
        Log.d(TAG, "接收到数据: $data")

        // 返回值说明：
        // START_STICKY：服务被系统杀死后，会自动重新创建
        // START_NOT_STICKY：被杀死后不会自动重建
        // START_REDELIVER_INTENT：重建时重新传递 Intent
        return START_STICKY
    }

    // 必须实现的方法，用于绑定服务（本案例不涉及绑定，返回 null 即可）
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    // 当 Service 被停止或销毁时调用
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service onDestroy: 服务已销毁")
    }
}

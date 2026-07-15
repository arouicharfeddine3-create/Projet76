package com.automation.workflow.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import io.ktor.server.routing.post
import io.ktor.server.routing.get
import io.ktor.response.respond
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class WebhookListenerService : Service() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startWebhookServer()
        return START_STICKY
    }

    private fun startWebhookServer() {
        val server = embeddedServer(Netty, port = 8080) {
            routing {
                post("/webhook/{id}") {
                    // Handle webhook
                    call.respond("OK")
                }
                get("/health") {
                    call.respond("OK")
                }
            }
        }
        server.start(wait = false)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}

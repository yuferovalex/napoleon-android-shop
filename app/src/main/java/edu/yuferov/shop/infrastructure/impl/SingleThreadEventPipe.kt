package edu.yuferov.shop.infrastructure.impl

import android.util.Log
import edu.yuferov.shop.infrastructure.EventHandler
import edu.yuferov.shop.infrastructure.EventPublisher
import java.lang.Exception

class SingleThreadEventPipe : EventPublisher {
    private val handlers: MutableList<EventHandler> = mutableListOf()

    fun subscribe(handler: EventHandler) {
        handlers.add(handler)
    }

    fun unsubscribe(handler: EventHandler) {
        handlers.remove(handler)
    }

    override fun publish(event: Any) {
        handlers.forEach {
            try {
                it.handleApplicationEvent(event)
            } catch (exception: Exception) {
                Log.e(
                    "infrastructure",
                    "An error occurred in application event handler $it",
                    exception
                )
            }
        }
    }
}
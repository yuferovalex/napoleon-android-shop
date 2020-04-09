package edu.yuferov.shop.infrastructure

/**
 * Interface of application event publisher
 */
interface EventPublisher {

    /**
     * Publish application event.
     *
     * @param event event to publish.
     */
    fun publish(event: Any)
}
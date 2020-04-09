package edu.yuferov.shop.infrastructure

/**
 * Handler of application events.
 */
interface EventHandler {
    /**
     * Application event handler.
     *
     * @param event event to handle.
     */
    fun handleApplicationEvent(event: Any)
}
package com.sourcey.materiallogindemo.utility;

import com.amazonaws.mobileconnectors.pinpoint.analytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.pinpoint.analytics.monetization.AmazonMonetizationEventBuilder;
import com.sourcey.materiallogindemo.MainActivity;

/**
 * Created by thenu on 20-01-2018.
 */

public class EventLog {

    public EventLog(){}

    public void logEvent(String eventName, String eventData) {
        MainActivity.pinpointManager.getSessionClient().startSession();
        final AnalyticsEvent event =
                MainActivity.pinpointManager.getAnalyticsClient().createEvent(eventName)
                        .withAttribute("DemoAttribute1", eventData)
                        .withAttribute("DemoAttribute2", eventData)
                        .withMetric("DemoMetric1", Math.random());

        MainActivity.pinpointManager.getAnalyticsClient().recordEvent(event);
        MainActivity.pinpointManager.getSessionClient().stopSession();
        MainActivity.pinpointManager.getAnalyticsClient().submitEvents();
    }

    public void logMonetizationEvent() {
        MainActivity.pinpointManager.getSessionClient().startSession();

        final AnalyticsEvent event =
                AmazonMonetizationEventBuilder.create(MainActivity.pinpointManager.getAnalyticsClient())
                        .withFormattedItemPrice("$10.00")
                        .withProductId("DEMO_PRODUCT_ID")
                        .withQuantity(1.0)
                        .withProductId("DEMO_TRANSACTION_ID").build();

        MainActivity.pinpointManager.getAnalyticsClient().recordEvent(event);
        MainActivity.pinpointManager.getSessionClient().stopSession();
        MainActivity.pinpointManager.getAnalyticsClient().submitEvents();
    }
}

package vnu.uet.boatsafe.utils.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by nvt2896 on 8/23/17.
 */

public class Event {

    public static void postEvent (EventMessage eventMessage) {

        EventBus.getDefault().post(eventMessage);
    }

    public static void postEventSticky (EventMessage eventMessage) {

        EventBus.getDefault().postSticky(eventMessage);
    }
}

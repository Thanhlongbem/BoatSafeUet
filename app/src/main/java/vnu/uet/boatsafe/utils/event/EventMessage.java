package vnu.uet.boatsafe.utils.event;

/**
 * Created by nvt2896 on 8/23/17.
 */

public class EventMessage {

    private String key;
    private Object values;

    public EventMessage(String key, Object values) {

        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }
}

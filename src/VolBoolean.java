public class VolBoolean {

    private volatile boolean value;

    VolBoolean() {
        this.value = false;
    }

    VolBoolean(boolean value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
}



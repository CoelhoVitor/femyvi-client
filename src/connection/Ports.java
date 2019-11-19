package connection;

public enum Ports {
    HEALTHCHECK_1(4000),
    HEALTHCHECK_2(4001),
    FETCH(3001),
    REMOVE(3002),
    UPLOAD(3003),
    AUTH(3004),;

    private final int value;

    Ports(int portValue) {
        value = portValue;
    }

    public int getValue() {
        return value;
    }

}

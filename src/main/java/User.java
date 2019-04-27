public class User {
    private String id;
    private int pin;

    User(String id, int pin) {
        this.id = id;
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }
}

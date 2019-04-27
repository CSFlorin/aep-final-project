// understands a user's identity
public class User {
    private int pin;

    public User(int pin) {
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }
}

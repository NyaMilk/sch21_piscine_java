public class Egg extends Thread {
    private String name;

    private int count;

    private Say say;

    public Egg(String name, int count, Say say) {
        this.name = name;
        this.count = count;
        this.say = say;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            say.sayEgg();
        }
    }
}
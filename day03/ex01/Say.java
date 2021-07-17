public class Say {

    public String who = "Egg";

    public synchronized void sayEgg() {

        while (who.equals("Hen")) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Egg");
        this.who = "Hen";
        notify();
    }

    public synchronized void sayHen() {

        while (who.equals("Egg")) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Hen");
        this.who = "Egg";
        notify();
    }
}
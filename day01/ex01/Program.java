public class Program {
    public static void main(String[] args) {
        User buyer = new User("Buyer", 1000);
        User seller = new User("Seller", 2000);

        System.out.println("First user: " + buyer.getId() + " " + buyer.getName() + ", balance: " + buyer.getBalance());
        System.out.println("Second user: " + seller.getId() + " " + seller.getName() + ", balance: " + seller.getBalance());

        User grafo = new User("Java", 4221);

        System.out.println("Third user: " + grafo.getId() + " " + grafo.getName() + ", balance: " + grafo.getBalance());
        System.out.println("First user: " + buyer.getId());
    }
}
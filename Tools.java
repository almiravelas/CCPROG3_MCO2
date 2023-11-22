public class Tools {
    public static void Clear() {
        System.out.println("--------------");

        try {
            Runtime.getRuntime().exec("cls");
        } catch (Exception e) { }
    }

    public static void Pause() {
        System.out.print("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) { }
    }
}

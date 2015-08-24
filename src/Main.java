import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int userChoice;
        Scanner scanner = new Scanner(System.in);
        /* User should make a choice */
        System.out.println("Choose web site to image loading");
        System.out.println("1. " + Constant.webSite.HD_WALLPAPERS.getHost());
        System.out.println("2. " + Constant.webSite.INTERFACELIFT.getHost());

        /* Read user choice */
        userChoice = scanner.nextInt();

        final int THREAD_COUNT = 6;
        HDWallpapers wPapers = new HDWallpapers(THREAD_COUNT);
        wPapers.prepare(3);
        wPapers.execute();

    }
}

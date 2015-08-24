import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int userChoice = 1;
        Scanner scanner = new Scanner(System.in);
        /* User should make a choice */
        System.out.println("Choose web site to image loading");
        System.out.println("1. " + Constant.webSite.HD_WALLPAPERS.getHost());
        System.out.println("2. " + Constant.webSite.INTERFACELIFT.getHost());

        /* Read user choice */
        try{
            userChoice = scanner.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Sorry, your input is incorrect. You must enter 1 or 2. Try again later.");
            System.exit(-1);
        }

        final int THREAD_COUNT = 6;

        WallpapersFactoryMethod wFactory = new WallpapersFactoryMethod();
        AbstractWallpapers wPapers = wFactory.createWallpapers(Constant.webSite.getByCode(userChoice), THREAD_COUNT);
        wPapers.prepare(3);
        wPapers.execute();
    }
}

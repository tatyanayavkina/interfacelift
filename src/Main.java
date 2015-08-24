
public class Main {
    public static void main(String[] args) {

        final int THREAD_COUNT = 6;
        Wallpapers wPapers = new Wallpapers(THREAD_COUNT);
        wPapers.prepare(3);
        wPapers.execute();

    }
}

/**
 * Created by Татьяна on 24.08.2015.
 */
public abstract class AbstractWallpapers {
    private String rootUrl;
    protected ThreadPool threadPool;

    public abstract void prepare(int crawlingPageCount);

    public void execute() {
        threadPool.start();
        threadPool.join();
        threadPool.stop(); // there is a possible error: finishing of the main thread may occur non planning interrupting pool threads
    }
}

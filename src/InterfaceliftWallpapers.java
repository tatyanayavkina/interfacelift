/**
 * Created by Татьяна on 24.08.2015.
 */
public class InterfaceliftWallpapers extends AbstractWallpapers {
    private final String rootUrl = Constant.webSite.INTERFACELIFT.getHost();
    private ThreadPool threadPool;

    public InterfaceliftWallpapers(int threadCount){
        this.threadPool = new ThreadPool(threadCount);
    }

    public void prepare(int crawlingPageCount){
        String pageUrl;

        for(int i = 1; i <= crawlingPageCount; ++i) {
            pageUrl =  rootUrl + "/wallpaper/downloads/date/wide_16:9/1920x1080/index" + i + ".html";
            threadPool.addTask(new FindImageUrlTask(rootUrl, pageUrl, threadPool,  Constant.webSite.INTERFACELIFT.getProtocol()));
        }
    }
}

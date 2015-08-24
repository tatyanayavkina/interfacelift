
public class HDWallpapers extends AbstractWallpapers {

    private final String rootUrl = Constant.webSite.HD_WALLPAPERS.getHost();
    private ThreadPool threadPool;

    public HDWallpapers(int threadCount){
        super(threadCount);
    }

    public void prepare(int crawlingPageCount) {
        String pageUrl;

        for(int i = 1; i <= crawlingPageCount; ++i) {
            pageUrl =  rootUrl + "/latest_wallpapers/page/" + i;
            threadPool.addTask(new ImageLoadPageSearchTask(rootUrl, pageUrl, threadPool, Constant.webSite.HD_WALLPAPERS.getProtocol()));
        }
    }

}

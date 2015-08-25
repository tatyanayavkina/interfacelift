
public class HDWallpapers extends AbstractWallpapers {

    public HDWallpapers(int threadCount){
        super(Constant.webSite.HD_WALLPAPERS.getHost(), threadCount);
    }

    public void prepare(int crawlingPageCount) {
        String pageUrl;

        for(int i = 1; i <= crawlingPageCount; ++i) {
            pageUrl =  rootUrl + "/latest_wallpapers/page/" + i;
            threadPool.addTask(new ImageLoadPageSearchTask(rootUrl, pageUrl, threadPool, Constant.webSite.HD_WALLPAPERS.getProtocol()));
        }
    }

}

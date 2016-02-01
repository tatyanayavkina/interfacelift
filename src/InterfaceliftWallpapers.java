
public class InterfaceliftWallpapers extends AbstractWallpapers {

    public InterfaceliftWallpapers(int threadCount){
        super(Constant.webSite.INTERFACELIFT.getHost(), threadCount);
    }

    public void prepare(int crawlingPageCount){
        String pageUrl;

        for(int i = 1; i <= crawlingPageCount; ++i) {
            pageUrl =  rootUrl + "/wallpaper/downloads/date/wide_16:9/1920x1080/index" + i + ".html";
            threadPool.addTask(new FindImageUrlTask(rootUrl, pageUrl, threadPool,  Constant.webSite.INTERFACELIFT.getProtocol()));
        }
    }
}

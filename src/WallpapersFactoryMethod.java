/**
 * Created by Татьяна on 24.08.2015.
 */
public class WallpapersFactoryMethod {

    public AbstractWallpapers createWallpapers (Constant.webSite webSite, int threadCount){
        AbstractWallpapers wallpapers;

        switch(webSite){
            case HD_WALLPAPERS:
                wallpapers = new HDWallpapers(threadCount);
                break;
            case INTERFACELIFT:
                wallpapers = new InterfaceliftWallpapers(threadCount);
                break;
            default:
                wallpapers = new HDWallpapers(threadCount);
                break;
        }

        return wallpapers;
    }
}

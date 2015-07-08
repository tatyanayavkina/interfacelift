import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;

/**
 * Created by Татьяна on 08.07.2015.
 */
public class Wallpapers {
    private String webUrl;
    private String webPageMatch;
    private String imgMatch;

    public Wallpapers(String aImgMatch){
        webUrl = "http://www.hdwallpapers.in/";
        webPageMatch = ".html";
        imgMatch = aImgMatch;
    }

    public void makeDownloads (){
        HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(webUrl);
        ArrayList<String> loadPageUrls = getLoadPageUrlList(htmlDoc);

        for (int i = 0; i < loadPageUrls.size(); i ++){
            String pageUrl = loadPageUrls.get(i);
            goToDownloadPage(pageUrl);
        }
    }

    private void goToDownloadPage(String pageUrl){
        String url;

        if(!pageUrl.startsWith("http")){
            url = webUrl + pageUrl;
        } else{
            url = pageUrl;
        }

        HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(url);

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgUrl != null && imgUrl.endsWith(imgMatch)) {
                ImageLoader.downloadImage(webUrl,imgUrl);
            }
        }

    }

    //make list of pages which have href to image download
    private  ArrayList<String> getLoadPageUrlList(HTMLDocument htmlDoc){
        ArrayList<String> loadPageUrls = new ArrayList<>();

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgLoadPageUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgLoadPageUrl != null && imgLoadPageUrl.endsWith(webPageMatch)) {
                loadPageUrls.add(imgLoadPageUrl);
            }
        }

        return loadPageUrls;
    }
}

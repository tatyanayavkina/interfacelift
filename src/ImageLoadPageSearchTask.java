//import javax.swing.text.AttributeSet;
//import javax.swing.text.html.HTML;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//import javax.swing.text.html.HTMLDocument;

public class ImageLoadPageSearchTask implements Runnable{
    private final ThreadPool threadPool;
    private final String webUrl;
    private final String pageUrl;
    private final String pageMatch = "-wallpapers.html";

    public ImageLoadPageSearchTask(String aWebUrl, String aPageUrl, ThreadPool threadPool){
        webUrl = aWebUrl;
        pageUrl = aPageUrl;
        this.threadPool = threadPool;
    }

    public void run(){
            String htmlString = HtmlParser.getHtmlContentStringByUrl(pageUrl);
            findImageLoadPagesUrl(htmlString);
    }

    private void findImageLoadPagesUrl(String htmlString){
        Pattern urlPattern = Pattern.compile(Constant.HTML_A_HREF_TAG_PATTERN);
        Matcher matcherHref = urlPattern.matcher(htmlString);

        while(matcherHref.find()){

            String href = matcherHref.group(1);

            if (href.endsWith(pageMatch)){
                String url;

                if(!href.startsWith("http")){
                    url = webUrl + href;
                }
                else{
                    url = href;
                }

                threadPool.addTask(new FindImageUrlTask(webUrl, url, threadPool));
            }
        }
    }

}

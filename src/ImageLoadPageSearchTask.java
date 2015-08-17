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

    public ImageLoadPageSearchTask(String webUrl, String pageUrl, ThreadPool threadPool){
        this.webUrl = webUrl;
        this.pageUrl = pageUrl;
        this.threadPool = threadPool;
    }

    public void run(){
            String htmlString = HtmlParser.getHtmlContentStringByUrl(pageUrl);
            findImageLoadPagesUrl(htmlString);
    }

    private void findImageLoadPagesUrl(String htmlString){
        Pattern aHrefTagPattern = Pattern.compile(Constant.HTML_A_HREF_TAG_PATTERN);
        Matcher hrefMatcher = aHrefTagPattern.matcher(htmlString);

        while(hrefMatcher.find()){

            String href = hrefMatcher.group(1);

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

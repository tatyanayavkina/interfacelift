import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindImageUrlTask implements Runnable{
    private final ThreadPool threadPool;
    private String webUrl;
    private String pageUrl;
    private String protocol;

    final String imgMatch = "1920x1080.jpg";

    public FindImageUrlTask(String webUrl, String pageUrl, ThreadPool threadPool, String protocol){
        this.webUrl = webUrl;
        this.pageUrl = pageUrl;
        this.threadPool = threadPool;
        this.protocol = protocol;
    }

    public void run(){
        String htmlString = HtmlParser.getHtmlContentStringByUrl(pageUrl);
        findImageLoadUrl(htmlString);
    }

    private void findImageLoadUrl(String htmlString){
        Pattern aHrefTagPattern = Pattern.compile(Constant.HTML_A_HREF_TAG_PATTERN);
        Matcher hrefMatcher = aHrefTagPattern.matcher(htmlString);

        while(hrefMatcher.find()){

            String href = hrefMatcher.group(1);

            if (href.endsWith(imgMatch)){
                String url;

                if(!href.startsWith(protocol)){
                    url = webUrl + href;
                }
                else{
                    url = href;
                }

                threadPool.addTask(new ImageLoaderTask(url));
            }
        }
    }
}




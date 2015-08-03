import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

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
//        try{
            HTMLDocument htmlDoc = HtmlParser.getHtmlDoc(pageUrl);
            findImageLoadPagesUrl(htmlDoc);
//        }
//        catch(InterruptedException ex){
//        }
    }

    private void findImageLoadPagesUrl(HTMLDocument htmlDoc){
        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {

            AttributeSet attributes = iterator.getAttributes();
            String imgLoadPageUrl = (String) attributes.getAttribute(HTML.Attribute.HREF);

            if (imgLoadPageUrl != null && imgLoadPageUrl.endsWith(pageMatch)) {
                String url;

                if(!imgLoadPageUrl.startsWith("http")){
                    url = webUrl + imgLoadPageUrl;
                } else{
                    url = imgLoadPageUrl;
                }

                threadPool.addTask(new FindImageUrlTask(webUrl, url, threadPool));
            }
        }
    }

}

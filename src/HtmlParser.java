//import javax.swing.text.html.HTMLDocument;
//import javax.swing.text.html.HTMLEditorKit;
//import javax.swing.text.html.parser.ParserDelegator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HtmlParser {

    //get content of web page with pageUrl url
    public static String getHtmlContentStringByUrl(String pageUrl){

        String html = Constant.EMPTY_STRING;
        try{
            //create url and open connection
            URL url = new URL(pageUrl);
            URLConnection connection =  url.openConnection();

            //create bufferedReader and read inputStream
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder htmlBuilder = new StringBuilder();
            String thisLine;

            while((thisLine = br.readLine()) != null){
                htmlBuilder.append(thisLine);
            }

            html = htmlBuilder.toString();
            System.out.println(html);
            //parse web page content
//            parser.parse(br, callback, true);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return html;
    }
}

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

        String htmlString = Constant.EMPTY_STRING;

        try{
            //create url and open connection
            URL url = new URL(pageUrl);
            URLConnection connection =  url.openConnection();

            System.setProperty("http.agent", "");
            connection.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.11) ");

            //create bufferedReader and read inputStream
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder htmlBuilder = new StringBuilder();
            String thisLine;

            while((thisLine = br.readLine()) != null){
                htmlBuilder.append(thisLine);
            }

            htmlString = htmlBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return htmlString;
    }
}

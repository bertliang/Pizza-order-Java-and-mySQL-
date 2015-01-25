package pizza;

/**
 * source: Google Maps
 * This revised code calculates the time between two locations.
 **/

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
 


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
 
public class duration {
 
 
    public String calculateDuration(String addressFrom, String addressTo) {
        String outputResult = "";
        String result = null;
        String urlString = "http://maps.googleapis.com/maps/api/directions/xml?sensor=true&origin="
                + addressFrom + "&destination=" + addressTo;
         
        try {
            URL urlGoogleDirService = new URL(urlString);
 
            HttpURLConnection urlGoogleDirCon = (HttpURLConnection) urlGoogleDirService.openConnection();
 
            urlGoogleDirCon.setAllowUserInteraction(false);
            urlGoogleDirCon.setDoInput(true);
            urlGoogleDirCon.setDoOutput(false);
            urlGoogleDirCon.setUseCaches(true);
            urlGoogleDirCon.setRequestMethod("GET");
            urlGoogleDirCon.connect();
 
            try {
                OutputStream output = new OutputStream() {
                    private StringBuilder string = new StringBuilder();
 
                    @Override
                    public void write(int b) throws IOException {
                        this.string.append((char) b);
                    }
 
                    @Override
                    public String toString() {
                        return this.string.toString();
                    }
                };
 
                byte buf[] = new byte[1024];
                int len;
                
                while ((len = urlGoogleDirCon.getInputStream().read(buf)) > 0) {
                	
                    output.write(buf, 0, len);
                }
                output.close();
                urlGoogleDirCon.getInputStream().close();
                outputResult = output.toString();
                // Html result
                
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(outputResult));
 
                Document doc = db.parse(is);
 
                XPath xPath = XPathFactory.newInstance().newXPath();
                XPathExpression expr = xPath.compile("//DirectionsResponse/route/leg/duration/text/text()");
 
                Object durationNodeList = expr.evaluate(doc, XPathConstants.NODESET);
                NodeList durationNodes = (NodeList) durationNodeList;
                int durationLength = durationNodes.getLength();
                result = durationNodes.item(0).getNodeValue();
                //System.out.println("Duration:" + durationNodes.item(0).getNodeValue());
                //for (int i = 0; i < durationLength; i++) {
                	
                //	System.out.println(i);
                //    System.out.println("Duration:" + durationNodes.item(i).getNodeValue());
                	
                //}
 
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            urlGoogleDirCon.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String re[] = result.split("\\s+");
        if(re[1].equals("mins")){
        	return re[0];
        } else {
        	return (null);
        }
       
       
    }
    
    
}

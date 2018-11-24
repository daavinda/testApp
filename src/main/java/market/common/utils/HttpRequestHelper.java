package market.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpRequestHelper {


    private final static Logger logger = LoggerFactory.getLogger(HttpRequestHelper.class);

    public String sendRequest(String urlToSend) {
        try {
            URL url = new URL(urlToSend);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp;
            if (null != (strTemp = br.readLine())) {
                logger.info("Service checking on URL {} [{}]", url, strTemp);
            }
            return strTemp;
        } catch (Exception ex) {
            logger.error("Service checking error on URL {} with error [{}]", urlToSend, ex.getMessage());
            return "-2";
            //return ex.toString();
        }
    }
}

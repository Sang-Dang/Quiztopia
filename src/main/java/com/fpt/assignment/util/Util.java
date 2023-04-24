package com.fpt.assignment.util;

import com.fpt.assignment.exception.runtime.BackendException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author User
 */
public class Util {
    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new BackendException("Critical error: URL encoding failed.");
    }
    
    public static String removeSuccessAndError(String url) {
        return removeFromURL(url, "success", "error");
    }
    
    public static String removeFromURL(String url, String... remove) {
        String returnValue = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            List<NameValuePair> list = builder.getQueryParams();
            builder.removeQuery();
            for (NameValuePair param : list) {
                String name = param.getName();
                String value = param.getValue();
                boolean flag = true;
                for(String i: remove) {
                    if(name.equals(i)) {
                        flag = false;
                    }
                }
                if(flag) {
                    builder.addParameter(name, value);
                }
            }
            URI uriReturn = builder.build();
            returnValue = uriReturn.toString();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnValue;
    }
    
    public static String addURLParameters(String url, String name, String value) {
        String returnValue = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameter(name, value);
            URI uriReturn = builder.build();
            returnValue = uriReturn.toString();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnValue;
    }
}

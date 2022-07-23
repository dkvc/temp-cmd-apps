package WebStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.awt.Desktop;

public class WebStatus {
    public static int status(String site) throws IOException {
        try {
			URL url = new URL(site);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            return connection.getResponseCode();
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL");
		}

		return 0;
    }

    public static String interpret(int httpCode) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(100, "Continue");
        map.put(101, "Switching Protocols");
        map.put(102, "Processing");
        map.put(103, "Early Hints");

        map.put(200, "OK");
        map.put(201, "Created");
        map.put(202, "Accepted");
        map.put(203, "Non-Authoritative Information");
        map.put(204, "No Content");
        map.put(205, "Reset Content");
        map.put(206, "Partial Content");
        map.put(207, "Multi-Status");
        map.put(208, "Already Reported");
        map.put(226, "IM Used");

        map.put(300, "Multiple Choices");
        map.put(301, "Moved Permanently");
        map.put(302, "Found");
        map.put(303, "See Other");
        map.put(304, "Not Modified");
        map.put(305, "Use Proxy");
        map.put(306, "Switch Proxy");
        map.put(307, "Temporary Redirect");
        map.put(308, "Permanent Redirect");
        
        map.put(400, "Bad Request");
        map.put(401, "Unauthorized");
        map.put(402, "Payment Required");
        map.put(403, "Forbidden");
        map.put(404, "Not Found");
        map.put(405, "Method Not Allowed");
        map.put(406, "Not Acceptable");
        map.put(407, "Proxy Authentication Required");
        map.put(408, "Request Timeout");
        map.put(409, "Conflict");
        map.put(410, "Gone");
        map.put(411, "Length Required");
        map.put(412, "Precondition Failed");
        map.put(413, "Payload Too Large");
        map.put(414, "URI Too Long");
        map.put(415, "Unsupported Media Type");
        map.put(416, "Range Not Satisfiable");
        map.put(417, "Expectation Failed");
        map.put(418, "I'm a teapot");
        map.put(421, "Misdirected Request");
        map.put(422, "Unprocessable Entity");
        map.put(423, "Locked");
        map.put(424, "Failed Dependency");
        map.put(426, "Upgrade Required");
        map.put(428, "Precondition Required");
        map.put(429, "Too Many Requests");
        map.put(431, "Request Header Fields Too Large");
        map.put(451, "Unavailable For Legal Reasons");

        map.put(500, "Internal Server Error");
        map.put(501, "Not Implemented");
        map.put(502, "Bad Gateway");
        map.put(503, "Service Unavailable");
        map.put(504, "Gateway Timeout");
        map.put(505, "HTTP Version Not Supported");
        map.put(506, "Variant Also Negotiates");
        map.put(507, "Insufficient Storage");
        map.put(508, "Loop Detected");
        map.put(510, "Not Extended");
        map.put(511, "Network Authentication Required");
        map.put(520, "Web Server Returned an Unknown Error");
        map.put(521, "Web Server Is Down");
        map.put(522, "Connection Timed Out");
        map.put(523, "Origin Is Unreachable");
        map.put(524, "A Timeout Occurred");
        map.put(525, "SSL Handshake Failed");
        map.put(526, "Invalid SSL Certificate");
        map.put(527, "Railway Service Blocked");
        map.put(528, "Domain Blocked");
        map.put(529, "Blocked by Windows Parental Controls");
        map.put(530, "Site Is Blocked");
        map.put(531, "Address Rejected");
        map.put(532, "Bad Behavior");
        map.put(533, "IP Address Blocked");
        
        if (map.containsKey(httpCode)) {
            return map.get(httpCode);
        } else {
            return "Unknown Error";
        }
    }

    public static void getCatPic(int httpCode) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URL("https://http.cat/" + httpCode).toURI());
            } catch (Exception e) {
                System.out.println("Error opening browser");
            }
        }
    }
}
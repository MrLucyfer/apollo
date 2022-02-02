package Network;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Request {
    static final int DEFAULT_PORT = 1965;
    public static Request instance;
    SSLContext context;
    TOFUTrustManager tsManager;

    private Request(){
        try {
            context = SSLContext.getInstance( "TLS" );

            tsManager = new TOFUTrustManager();
            context.init(null, new TrustManager[]{tsManager}, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Request getInstance() {
        if(instance == null) {
            instance = new Request();
        }
        return instance;
    }

    public Response makeRequest(Uri uri) {
        return makeRequest(uri, DEFAULT_PORT);
    }
    public Response makeRequest(Uri url, int port) {
        Socket s;
        try {
            s = ((SSLSocketFactory) context.getSocketFactory()).createSocket(url.getHostname(), port);
            PrintWriter in = new PrintWriter(s.getOutputStream(), true);
            in.println(url.getUrl());
            BufferedReader out = new BufferedReader(new InputStreamReader(s.getInputStream()));
            StringBuilder sb= new StringBuilder();
            String line = "";
            while ((line = out.readLine()) != null) {
                sb.append(line).append("\n");
            }
            Response res = new Response(sb.toString(), url);
            s.close();
            return res;

        } catch(Exception e){
            e.printStackTrace();
            Response res = new Response("", url);
            return res;
        }
    }

}

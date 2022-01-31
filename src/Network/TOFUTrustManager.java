package Network;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class TOFUTrustManager implements X509TrustManager {

    private final Map<Integer, List<X509Certificate>> serverCertificates = new HashMap<>();
    private int counter = 0;

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        serverCertificates.put(counter++, Arrays.asList(x509Certificates));
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public Map<Integer, List<X509Certificate>> getServerCertificates() {
        return Collections.unmodifiableMap(serverCertificates);
    }

}

package Network;

public class Uri {
    String scheme = "gemini://";
    String authority;
    String path;

    public Uri(String url) {
        String[] components = url.split("/");

        authority = components[0];
        StringBuilder newPath = new StringBuilder("/");
        if(components.length > 1) {
            for(int i = 1; i < components.length; i++) {
                newPath.append(components[i]);
                if(i < components.length - 1) {
                    newPath.append("/");
                }
            }
        }
        if(url.endsWith("/")) {
            newPath.append("/");
        }
        path = newPath.toString();
    }

    public String getHostname() {
        return authority;
    }

    public String getUrl() {
        return scheme + authority + path;
    }

    @Override
    public String toString() {
        return getUrl();
    }
}

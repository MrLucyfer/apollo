import Network.*;

public class Main {

    public static void main(String[] args) {
        Response res = Request.getInstance().makeRequest(new Uri("gemini.conman.org/docs"));
        //res.printRaw();
    }
}

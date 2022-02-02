import Network.*;

public class Main {

    public static void main(String[] args) {
        Response res = Request.getInstance().makeRequest(new Uri("gemini.circumlunar.space/docs/"));
        System.out.print(res);
    }
}

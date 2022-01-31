package Network;

import Utils.StringParser;

import java.util.Arrays;
import java.util.stream.Stream;

public class Response {
    private String raw = "";
    private StatusCode statusCode;
    public Response(Stream<String> blob) {
        this.raw = blob.reduce((str, line) -> str + (line + '\n')).get();
        parseResponse();
    }
    public Response(String blob) {
        this.raw = blob;
    }

    public void printRaw() {
        System.out.print(this.raw);
    }

    private boolean parseResponse() {
        StringParser sc = new StringParser(this.raw);
        if(!parseHeader(sc)) {
            return false;
        }

        return true;
    }

    private boolean parseHeader(StringParser sc) {
        String[] line = sc.consumeAllLine().split(" ");
        this.statusCode = StatusCode.fromCode(Integer.parseInt(line[0]));

        return true;
    }
}

package Network;

import Gemini.Document;
import Gemini.HeaderLine;
import Gemini.LinkLine;
import Gemini.TextLine;
import Utils.StringParser;

import java.util.stream.Stream;

public class Response {
    private String raw;
    private StatusCode statusCode;
    private String meta;
    private Document body;
    private Uri url;

    public Response(Stream<String> blob) {
        this.raw = blob.reduce((str, line) -> str + (line + "\n")).get();
        //this.raw += "\n";
        boolean success = parseResponse();

        if(!success) {
            throw new Error("Response failed on parsing");
        }
    }
    public Response(String blob, Uri from) {
        this.raw = blob;
        this.url = from;
        boolean success = parseResponse();

        if(!success) {
            throw new Error("Response failed on parsing");
        }
    }

    public void printRaw() {
        System.out.print(this.raw);
    }

    public Document getBody() {
        if(this.body == null) {
            throw new Error("Status Code was not 20");
        }

        return this.body;
    }

    private boolean parseResponse() {
        StringParser sc = new StringParser(this.raw);
        if(!parseHeader(sc)) {
            return false;
        }

        if(this.statusCode == StatusCode.SUCCESS) {
            this.body = parseBody(sc);
        }
        return true;
    }

    private Document parseBody(StringParser sc) {
        Document doc = new Document();
        boolean preformatState = false;
        while(sc.available(3)) {
            String firstLineChar = sc.peek(3);
            switch(firstLineChar) {
                case "=> ":
                    sc.consume(3);
                    String line = sc.consumeAllLine();
                    doc.add(new LinkLine(line));
                    break;
                case "```":
                    sc.consumeAllLine();
                    break;
                default:
                    if(sc.peek() == '#') {
                        doc.add(new HeaderLine(sc.consumeAllLine()));
                    } else {
                        doc.add(new TextLine(sc.consumeAllLine()));
                    }
                    break;
            }
        }
        return doc;
    }

    private boolean parseHeader(StringParser sc) {
        String line = sc.consumeAllLine();
        if(line.contains(" ")) {
            String[] splitted = line.split(" ");
            this.statusCode = StatusCode.fromCode(Integer.parseInt(splitted[0]));
            if(splitted.length == 2) {
                this.meta = splitted[1];
            }
        } else {
            this.statusCode = StatusCode.fromCode(Integer.parseInt(line));
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder struct = new StringBuilder();
        struct.append("Request to: " + this.url + "\n");
        struct.append("Header: \n");
        struct.append("--> Status Code: " + this.statusCode.getNum() + " " + this.statusCode + "\n");
        if(this.meta != null) {
            struct.append("--> Meta: " + this.meta + "\n");
        }
        if(this.statusCode == StatusCode.SUCCESS) {
            struct.append("Body: \n");
            struct.append(this.body);
        }

        return struct.toString();
    }
}

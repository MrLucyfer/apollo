package Utils;

public class StringParser {
    char[] data;
    String raw;
    int index = 0;

    public StringParser(String data) {
        this.raw = data;
        this.data = data.toCharArray();
    }

    public char peek() {
        if(!available()) {
            return '\0';
        }
        return data[index];
    }

    public char peekAt(int amount) {
        return data[index + amount];
    }

    public String peek(int amount) {
        if(amount < 0 || !available()) {
            return "";
        }

        StringBuilder line = new StringBuilder();
        for(int i = 0; i < amount; i++) {
            line.append(peekAt(i));
        }

        return line.toString();
    }

    public char consume() {
        if(index >= data.length) {
            return '\0';
        }
        return data[index++];
    }

    public String consume(int amount) {
        if(index >= data.length) {
            return "\0";
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < amount; i++) {
            str.append(consume());
        }

        return str.toString();
    }

    public boolean available() {
        return index < data.length;
    }

    public String consumeUntil(char delim) {
        StringBuilder str = new StringBuilder();
        while(peek() != delim) {
            if(peek() != '\0') {
                str.append(consume());
            } else {
                break;
            }
        }
        if(available()) {
            consume();
        }
        return str.toString();
    }

    public String peekUntil(char delim) {
        char current;
        StringBuilder str = new StringBuilder();
        int index = 0;
        while((current = peekAt(index)) != delim) {
            str.append(current);
        }
        return str.toString();
    }

    public String consumeAllLine() {
        return consumeUntil('\n'); //This will remove \r TODO: Find a better way
    }

    public String peekAllLine() {
        return consumeUntil('\n'); //This will remove \r TODO: Find a better way
    }

    public boolean available(int amount) {
        return (index + amount) < this.data.length;
    }

}

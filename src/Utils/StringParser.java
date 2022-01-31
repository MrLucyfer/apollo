package Utils;

public class StringParser {
    char[] data;
    int index = 0;

    public StringParser(String data) {
        this.data = data.toCharArray();
    }

    public char peek() {
        return data[index];
    }

    public char peekAt(int amount) {
        return data[index + amount];
    }

    public String peek(int amount) {
        if(amount < 0) {
            return "";
        }

        StringBuilder line = new StringBuilder();
        for(int i = 0; i < amount; i++) {
            line.append(data[index + amount]);
        }

        return line.toString();
    }

    public char consume() {
        if(index >= data.length) {
            return '\n';
        }
        return data[index++];
    }

    public String consumeUntil(char delim) {
        char current;
        StringBuilder str = new StringBuilder();
        while((current = consume()) != delim) {
            str.append(current);
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
        String line = consumeUntil('\n');
        return line; //This will remove \r TODO: Find a better way
    }

    public String peekAllLine() {
        String line = consumeUntil('\n');
        return line; //This will remove \r TODO: Find a better way
    }

}

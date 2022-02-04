package Gemini;

public class HeaderLine extends Line{
    HeaderType htype = HeaderType.H1;

    public HeaderLine(String rawLine) {
        super(rawLine, LineType.Heading);
        parse();
    }

    @Override
    protected void parse() {
        int hashCount = 0;
        for(int i = 0; i < 3; i++) {
            if(rawLine.charAt(i) != '#') {
                break;
            }
            hashCount++;
        }

        this.htype = HeaderType.values()[hashCount-1];
        this.displayText = rawLine.substring(2);
    }

    @Override
    public String toString() {
        return this.type + " " + this.htype;
    }
}

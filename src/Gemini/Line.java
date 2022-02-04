package Gemini;

public abstract class Line {
    public Line(String rawLine, LineType type) {
       this.type = type;
       this.rawLine = rawLine;
       parse();
    }

    protected final LineType type;
    protected String rawLine;
    protected String displayText;

    public LineType getType() {
        return this.type;
    }

    public String getRawLine() {
        return this.rawLine;
    }

    abstract protected void parse();

    @Override
    public String toString() {
        return type.toString();
    }

}

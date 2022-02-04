package Gemini;

public class TextLine extends Line{
    private boolean preformat;
    public TextLine(String rawLine) {
        super(rawLine, LineType.Text);
        this.preformat = false;
    }
    public TextLine(String rawLine, boolean preformat) {
        super(rawLine, LineType.Text);
        this.preformat = preformat;
    }


    @Override
    protected void parse() {

    }
}

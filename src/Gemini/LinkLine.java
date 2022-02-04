package Gemini;

import java.net.URI;

public class LinkLine extends Line{
    private URI uri;
    private String linkText;

    public LinkLine(String rawLine) {
        super(rawLine, LineType.Link);
    }

    @Override
    protected void parse() {

    }
}

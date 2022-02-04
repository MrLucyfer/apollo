package Gemini;

import java.util.Vector;

public class Document {
    private Vector<Line> lines;

    public Document() {
        this.lines = new Vector<Line>();
    }

    public void add(Line line) {
        lines.add(line);
    }

    @Override
    public String toString() {
        StringBuilder doc = new StringBuilder();
        for(Line line : lines) {
            doc.append("-->").append(line.toString()).append("\n");

        }

        return doc.toString();
    }
}

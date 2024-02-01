package markup;

import java.util.List;
public class Paragraph implements GlobalInterface {
    private final List<AbstractClass> list;
    public Paragraph(List<AbstractClass> list) {
        this.list = list;
    }
    @Override
    public void toMarkdown(StringBuilder str) {
        for (AbstractClass el : list) {
            el.toMarkdown(str);
        }
    }

    @Override
    public void toBBCode(StringBuilder str) {
        for (AbstractClass el : list) {
            el.toBBCode(str);
        }
    }
}
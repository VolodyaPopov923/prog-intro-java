package markup;
import java.util.List;

public abstract class AbstractClass implements GlobalInterface{
    private List<AbstractClass> list;
    private final String symbMd;
    private final String secondSymb;

    public AbstractClass(){this.list = List.of(); symbMd = ""; secondSymb = "";};

    public AbstractClass(List<AbstractClass> list, String firstSymb, String symbBB){
        this.list = list;
        this.symbMd = firstSymb;
        this.secondSymb = symbBB;
    }
    
    @Override
    public void toMarkdown(StringBuilder str){
        str.append(symbMd);
        for (AbstractClass el : list){
            el.toMarkdown(str);
        }
        str.append(symbMd);
    }

    @Override
    public void toBBCode(StringBuilder str) {
        str.append("[").append(secondSymb).append("]");
        for (AbstractClass el : list){
            el.toBBCode(str);
        }
        str.append("[/").append(secondSymb).append("]");
    }
}

package markup;

public class Text extends AbstractClass {
    protected String string;

    public Text(String string){
        this.string = string;
    }
    
    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(this.string);
    }

    @Override
    public void toBBCode(StringBuilder str) {str.append(this.string);}
}
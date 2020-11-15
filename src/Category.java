import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String name;
    private List<Category> children;

    public Category(String name){
        this.name = name;
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addChild(Category child){
        if(children.contains(child)){
            return false;
        }
        children.add(child);
        return true;
    }

    @Override
    public String toString() {
        return "Category " + name;
    }
}

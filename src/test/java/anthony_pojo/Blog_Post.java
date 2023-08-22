package anthony_pojo;

public class Blog_Post {
    public String text;

    public Blog_Post(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Blog_Post) {
            Blog_Post that = (Blog_Post) obj;
            return this.text.equals(that.text);
        }
        return false;
    }
}

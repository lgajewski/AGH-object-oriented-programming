package pl.edu.agh.iet.to2.teams.view;

/**
 * Created by maciek on 20.01.16.
 */
public class CustomTreeObject {

    private int hashcode;
    private String content;

    public CustomTreeObject(int hashcode, String content) {
        this.hashcode = hashcode;
        this.content = content;
    }

    public int getHashcode() {
        return hashcode;
    }

    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString()
    {
        return content;
    }


}

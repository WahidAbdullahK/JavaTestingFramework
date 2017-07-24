package core.testLibrary;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

public abstract class MyBy {
    public static By ShadowElement(String[] args) {
        String id = args[0];
        String classname = args[1];
        String tagname = args[2];
        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("id", id);
        m1.put("classname", classname);
        m1.put("tagname", tagname);
        return new FindShadowDom(m1);
    }
}
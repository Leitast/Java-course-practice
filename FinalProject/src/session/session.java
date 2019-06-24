package session;

import java.util.*;

public class session {
    public session() {

    }

    private static Map<String, 	String> attribute = new HashMap<>();
    
    public void setAttribute(String name, String password) {
        attribute.put(name, password);
    }

    public Object getAttribute(String name) {
        return attribute.getOrDefault(name, "Not Login Yet");
    }
}

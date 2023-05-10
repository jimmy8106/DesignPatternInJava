package creationalpatterns;
//source code from java core java.lang.StringBuilder#append()
public class Builder {
    
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }
}

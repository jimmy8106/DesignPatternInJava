package creationalpatterns;
//source code from java core java.lang.Runtime#getRuntime()
public class Singleton {
    private static final Runtime currentRuntime = new Runtime();
    
    public static Runtime getRuntime() {
        return currentRuntime;
    }
    
    Object a = clone()
}

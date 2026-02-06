package Pattern.Creational.Singleton;

public class Logger {

    private static Logger instance;

    public static synchronized Logger getLoggerInstance(){

        if(instance == null){
            instance = new Logger();
        }

        return instance;
    }
    
}

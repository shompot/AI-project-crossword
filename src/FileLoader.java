/**
 * Created by Shiha on 12/14/2017.
 */
public class FileLoader {

    private ActivityLogger logger;
    public FileLoader(ActivityLogger logger){
        this.logger = logger;
    }

    public void loadFile(){
        // load stuff from file
        logger.logAction("File loaded successfully \n");
    }

}
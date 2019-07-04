package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

public class ConfigHelper {
    public static Properties configs = null;
    private static final Logger log = LogManager.getLogger(ConfigHelper.class);
    private static String env = System.getProperty("environment", "novibetlive");
    private static String configDir = System.getProperty("resourcesPath", "src/main/resources/");
    private static String envConfigPath = configDir + env + ".properties";
    public ConfigHelper(){
        log.info("Test environment: " + env);
        configs = new Properties();
        loadPropertiesFile(envConfigPath);
        dumpConfigs();
    }

    public static void dumpConfigs() {
        log.info("===============================Configuration===============================");
        Set<Object> keys =configs.keySet();
        for (Object k : keys){
            String key = (String) k;
            log.info(key+ ": " + configs.getProperty(key));
        }
        log.info("===========================================================================");
    }

    public static void loadPropertiesFile(String filepath){
        log.info("Loading properties file: " + filepath);
        FileInputStream in;
        try {
            in = new FileInputStream(filepath);
            configs.load(in);
            in.close();
        } catch (java.io.IOException e) {
            log.error("Could not read properties file: " + filepath);
            e.printStackTrace();
        }

    }
}

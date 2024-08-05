package gr.ed.technikon.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "Persistence";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            Properties props = new Properties();
            try {
                props.load(new FileInputStream("resources/persistence.properties"));
            } catch (IOException ex) {
            }
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, props);
        }
        return factory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }
}

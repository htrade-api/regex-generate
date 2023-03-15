package it.units.inginf.male.configuration;

/**
 *
 * @author nvhuy9527
 */
public class ConfigurationException extends RuntimeException {

    /**
     * Creates a new instance of <code>ConfigurationException</code> without detail message.
     */
    public ConfigurationException() {
    }


    /**
     * Constructs an instance of <code>ConfigurationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ConfigurationException(String msg) {
        super(msg);
    }
}

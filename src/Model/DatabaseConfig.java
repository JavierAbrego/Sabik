
package Model;


public class DatabaseConfig {
    
    private String configFileName;
    private String alias;
    private String URL;
    private String port;
    private String user;
    private String password;
    private String databaseName;
    
    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    
   
}

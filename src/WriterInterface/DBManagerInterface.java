package WriterInterface;

import Models.DatabaseConfig;


public interface DBManagerInterface {
    
    public DatabaseConfig getDatabaseConfig(String alias);
    public void addDatabaseConfig(DatabaseConfig dataBaseConfig);
    
}

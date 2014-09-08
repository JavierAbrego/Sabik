package WriterInterface;

import Exception.DBManagerException;
import Model.DatabaseConfig;


public interface DBManagerInterface {
    
    public DatabaseConfig getDatabaseConfig(String alias);
    public void addDatabaseConfig(DatabaseConfig dataBaseConfig) throws DBManagerException;
    
}

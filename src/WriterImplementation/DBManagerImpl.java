package WriterImplementation;

import Exception.DBManagerException;
import Model.DatabaseConfig;
import WriterInterface.DBManagerInterface;
import java.util.HashMap;

public class DBManagerImpl implements DBManagerInterface{
    
    private static DBManagerImpl _dbManagerImpl;
    private final HashMap<String, DatabaseConfig> dbConfigs;
    
    private DBManagerImpl() {
        this.dbConfigs = new HashMap<>();
        if( _dbManagerImpl != null ) {
            throw new InstantiationError( "More instances of this object cannot be created." );
        }
    }
    
    private synchronized  static void createInstance(){
        if(_dbManagerImpl==null){
            _dbManagerImpl  = new DBManagerImpl();
        }
    }        
    
    public static  DBManagerImpl getInstace(){
        if(_dbManagerImpl==null) createInstance();
        return _dbManagerImpl;
    }
        
    
    @Override
    public DatabaseConfig getDatabaseConfig(String alias) {
        return dbConfigs.get(alias);        
    }

    @Override
    public void addDatabaseConfig(DatabaseConfig dataBaseConfig) throws DBManagerException{
        if(databaseAliasExists(dataBaseConfig.getAlias())){
            throw new DBManagerException("A database with the alias "+dataBaseConfig.getAlias()+" already exists.");
        }else{
            dbConfigs.put(dataBaseConfig.getAlias(), dataBaseConfig);        
        }
    }
    
    private boolean databaseAliasExists(String alias){
        return dbConfigs.containsKey(alias);
    }

}

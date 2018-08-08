package SPARQL_JAVA_Example.sparql_java_ex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ConfigProperty {
	 public static String db;
	  public static String queryfile;
	  public static String outputfile;
	  public static String sdbname;
	  public static String tdbname;
	  public static String tout;
	  public static String suser;
	  public static String spassword;
	  public static String surl;
	  public static String stardogfile;
	  public String dbcreation;
	  public static String tdbbatchfilepath;
	  public static String tdbbatchfile;
	  public static String tdbrdfdir;
	  public static String tdboutputdir;
	  public static String serviceURI;
	  String result = "";
	  InputStream inputStream;
	  
	  public ConfigProperty(){}
	  
	  public String getPropValues() throws IOException{
			
			try { 
				Properties prop = new Properties();
				String propFileName = "DBConfig.properties";
			      
				inputStream = new FileInputStream(propFileName);
			      
			    if (inputStream != null) {
			       prop.load(inputStream);
			    } else {
			       throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			    }
			      
			    Date time = new Date(System.currentTimeMillis());
			      

			    db = prop.getProperty("db");
			    System.out.println(db);

			    queryfile = prop.getProperty("queryfile");
			    outputfile = prop.getProperty("outputfile");
			    serviceURI = prop.getProperty("serviceURI");
			      
			    sdbname = prop.getProperty("sdbname");
			    tdbname = prop.getProperty("tdbname");
			      

			    suser = prop.getProperty("suser");
			    spassword = prop.getProperty("spassword");
			    surl = prop.getProperty("surl");
			    stardogfile = prop.getProperty("stardogfile");
			      


			    tdbbatchfilepath = prop.getProperty("tdbbatchfilepath");
			    tdbbatchfile = prop.getProperty("tdbbatchfile");
			    tdbrdfdir = prop.getProperty("tdbrdfdir");
			    tdboutputdir = prop.getProperty("tdboutputdir");
			    tout = prop.getProperty("tout");
			    setDbcreation(prop.getProperty("dbcreation"));
			    }
			    catch (Exception e)
			    {
			      System.out.println("Exception: " + e);
			    }
			    finally
			    {
			      inputStream.close();
			    }
			    
			    return result;
			  }
	public String getDbcreation() {
		return dbcreation;
	}
				  
	public void setDbcreation(String dbcreation) {
		this.dbcreation = dbcreation;
	}

}

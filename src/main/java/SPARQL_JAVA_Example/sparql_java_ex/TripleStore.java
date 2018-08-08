package SPARQL_JAVA_Example.sparql_java_ex;

import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.IO;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
import sun.misc.MessageUtils;

public class TripleStore {	
	 public TripleStore() throws java.io.IOException, InterruptedException
	  {
	    ConfigProperty properties = new ConfigProperty();
	    properties.getPropValues();
	    ProcessBuilder processBuilder;
	    int exitStatus; 
	    File dir;
	    if (ConfigProperty.db.equals("t"))
	    {
	      File batchFile = new File(ConfigProperty.tdbbatchfilepath + ConfigProperty.tdbbatchfile);
	      File outputFile = new File(String.format(ConfigProperty.tdbbatchfilepath + ConfigProperty.tout + "_%tY%<tm%<td_%<tH%<tM%<tS.txt", new Object[] { Long.valueOf(System.currentTimeMillis()) }));
	      processBuilder = new ProcessBuilder(new String[] { batchFile.getAbsolutePath(), ConfigProperty.tdbrdfdir });
	      processBuilder.redirectErrorStream(true);
	      processBuilder.redirectOutput(outputFile);
	      System.out.println("Starting the process.");
	      Process process = processBuilder.start();
	      exitStatus = process.waitFor();
	      File afile = new File("tdb");
	      dir = new File(ConfigProperty.tdbname);
	      if (afile.renameTo(new File(ConfigProperty.tdbname))) {
	        System.out.println("File moved successfully!");
	      } else {
	        System.out.println("File failed to move!");
	      }
	      
	      System.out.println("Processed finished with status: " + exitStatus);
	    }
	    else
	    {
	      Stardog aStardog = Stardog.builder().create();
	      AdminConnection aAdminConnection = AdminConnectionConfiguration.toEmbeddedServer().credentials(ConfigProperty.suser, ConfigProperty.spassword).connect();
	      processBuilder = null;
	      try
	      {
	        if (aAdminConnection.list().contains(ConfigProperty.sdbname)) {
	          aAdminConnection.drop(ConfigProperty.sdbname);
	        }
	        aAdminConnection.disk(ConfigProperty.sdbname).create(new Path[0]);
	        try
	        {
	          Connection aConn = ConnectionConfiguration.to(ConfigProperty.sdbname).credentials(ConfigProperty.suser, ConfigProperty.spassword).connect();
	          exitStatus = 0;
	          try
	          {
	            aConn.begin();
	            File folder = new File(ConfigProperty.stardogfile);
	            for (File fileEntry : folder.listFiles()) {
	              File a = new File(fileEntry.getName());
	              String b = ConfigProperty.stardogfile + "\\" + a;
	              aConn.add().io()
	                .format(org.openrdf.rio.RDFFormat.RDFXML)
	                .stream(new FileInputStream(b));
	            }
	            aConn.commit();
	          }
	          catch (Throwable localThrowable1)
	          {
//	            exitStatus = localThrowable1;
	            throw localThrowable1;
	          }
	          finally {}
	        }
	        catch (FileNotFoundException e)
	        {

	          MessageUtils.err("data file not found: ");
	          e.printStackTrace();
	        } finally {
	          aStardog.shutdown();
	        }
	      }
	      catch (Throwable localThrowable4)
	      {
//	        processBuilder = localThrowable4;
	        try {
				throw localThrowable4;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      finally
	      {
	        if (aAdminConnection != null) 
	        	if (processBuilder != null) 
	        		try {
	        			aAdminConnection.close(); 
	        			} 
	        		catch (Throwable localThrowable5) {
	        	//		processBuilder.addSuppressed(localThrowable5); 
	        			localThrowable5.printStackTrace();
	        			} 
	        	else 
	        		aAdminConnection.close();
	      }
	    }
	  }
}

package SPARQL_JAVA_Example.sparql_java_ex;

import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.SelectQuery;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import org.openrdf.query.TupleQueryResult;

public class runsparql
{
  public runsparql() throws IOException
  {
    ConfigProperty properties = new ConfigProperty();
    properties.getPropValues();
    if (ConfigProperty.db.equals("s"))
    {
      Connection aConn = ConnectionConfiguration.to(ConfigProperty.sdbname).server(ConfigProperty.surl).credentials(ConfigProperty.suser, ConfigProperty.spassword).connect();
      Throwable localThrowable3 = null;
      
      try {
        aConn.begin();      
        System.out.println("connected successfully");
        File file = new File(ConfigProperty.queryfile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          stringBuffer.append(line);
          stringBuffer.append("\n");
        }
        fileReader.close();        
        SelectQuery aQuery = aConn.select(stringBuffer.toString());
        TupleQueryResult aResult = (TupleQueryResult)aQuery.execute();
        OutputStream out = new FileOutputStream(ConfigProperty.outputfile);
        try
        {
          org.openrdf.query.resultio.QueryResultIO.writeTuple(aResult, TextTableQueryResultWriter.FORMAT, out);
        } finally {
          aResult.close();
        }
      }
      catch (Throwable localThrowable1)
      {
        localThrowable3 = localThrowable1;
        try {
			throw localThrowable1;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      finally
      {
        if (aConn != null) 
        	if (localThrowable3 != null)
        		try {
        			aConn.close(); 
        			} 
        		catch (Throwable localThrowable2) {
        			localThrowable3.addSuppressed(localThrowable2); 
        			} 
        	else 
        		aConn.close();
      }
    }
  }
}


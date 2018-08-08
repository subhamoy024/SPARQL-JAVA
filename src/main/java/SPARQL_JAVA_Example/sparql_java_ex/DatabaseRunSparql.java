package SPARQL_JAVA_Example.sparql_java_ex;
import java.io.IOException;


public class DatabaseRunSparql {
	
	public DatabaseRunSparql(){}
	
	public static void main (String[] args) throws IOException, InterruptedException  {
		ConfigProperty cp = new ConfigProperty();
		Object localObject;
		
		if ("Y".equalsIgnoreCase(cp.getDbcreation())){
			localObject = new TripleStore();
		}else{
			localObject = new runsparql();
		}
	}
		
}

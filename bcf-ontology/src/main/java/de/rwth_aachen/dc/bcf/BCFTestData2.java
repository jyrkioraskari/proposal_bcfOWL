package de.rwth_aachen.dc.bcf;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.core.DatasetImpl;

@SuppressWarnings("restriction")
public class BCFTestData2 {
	Model model = ModelFactory.createDefaultModel();
	

	public BCFTestData2() {
		model.read("./src/main/resources/BcfSample.ttl");
		query1();
		query2();
		//model.write(System.out,"TTL");
	}

	
	private void query1()
	{
		String query = 
				"PREFIX bcfOWL: <http://lbd.arch.rwth-aachen.de/bcf/v2.1/>\n" + 
				"\n" + 
				"SELECT ?topic\n" + 
				"WHERE {\n" + 
				"	?topic a bcfOWL:Topic ;\n" + 
				"	bcfOWL:hasViewpoint ?viewpoint .\n" + 
				"	filter (?viewpoint = ?filteredViewpoints) \n" + 
				"{\n" + 
				"	SELECT ?filteredViewpoints\n" + 
				"	WHERE {\n" + 
				"	?filteredViewpoints a bcfOWL:Viewpoint ;\n" + 
				"	bcfOWL:hasComponents ?components .\n" + 
				"	filter(?components = ?filteredComponents)\n" + 
				"{\n" + 
				"	SELECT ?filteredComponents\n" + 
				"	WHERE {\n" + 
				"	?filteredComponents bcfOWL:hasSelection ?selection .\n" + 
				"	filter(?selection = ?filteredSelection)\n" + 
				"{\n" + 
				"	SELECT ?filteredSelection\n" + 
				"	WHERE {\n" + 
				"	?filteredSelection bcfOWL:hasElement ?element .\n" + 
				"	filter(?element = ?filteredElement)\n" + 
				"{\n" + 
				"	SELECT ?filteredElement\n" + 
				"	WHERE {\n" + 
				"	?filteredElement bcfOWL:hasIfcGuid \"1ZwJH$85D3YQG5AK5ER1Wc\"\n" + 
				"}\n" + 
				"}\n" + 
				"}\n" + 
				
				"}\n" + 
				"}\n" + 
				"}\n" + 
				
				"}\n" + 
				"}\n" + 
				"}\n" + 

				" LIMIT 25\n" ;
		 System.out.println(query);
		 QueryExecution exec =  QueryExecutionFactory.create(QueryFactory.create(query), new
				 DatasetImpl(model));
		 ResultSet results = exec.execSelect();
		 System.out.println("results: "+results.hasNext());
		 for ( ; results.hasNext() ; ) {
		      QuerySolution soln = results.nextSolution() ;
		      System.out.println("Solution: "+soln);
		    }
	}

	private void query2()
	{
		String query = 
				"PREFIX bcfOWL: <http://lbd.arch.rwth-aachen.de/bcf/v2.1/>\n" + 
				"\n" + 
				"SELECT ?topic\n" + 
				"WHERE {\n" + 
				"	?topic a bcfOWL:Topic ;\n" + 
				"	bcfOWL:hasViewpoint ?viewpoint .\n" + 
				"	?viewpoint a bcfOWL:Viewpoint ;\n" + 
				"	bcfOWL:hasComponents ?components .\n" + 
				"	?components bcfOWL:hasSelection ?selection .\n" + 
				"	?selection bcfOWL:hasElement ?element .\n" + 
				"	?element bcfOWL:hasIfcGuid \"1ZwJH$85D3YQG5AK5ER1Wc\"\n" + 
				"}\n" + 

				" LIMIT 25\n" ;
		 System.out.println(query);
		 QueryExecution exec =  QueryExecutionFactory.create(QueryFactory.create(query), new
				 DatasetImpl(model));
		 ResultSet results = exec.execSelect();
		 System.out.println("results: "+results.hasNext());
		 for ( ; results.hasNext() ; ) {
		      QuerySolution soln = results.nextSolution() ;
		      System.out.println("Solution: "+soln);
		    }
	}
	
	public static void main(String[] args) {
		new BCFTestData2();
	}
}

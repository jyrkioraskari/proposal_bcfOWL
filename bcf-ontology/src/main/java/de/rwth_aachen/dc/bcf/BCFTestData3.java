package de.rwth_aachen.dc.bcf;

import java.util.Iterator;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.reasoner.ValidityReport;

@SuppressWarnings("restriction")
public class BCFTestData3 {
	final OntModel model;
	

	public BCFTestData3() {
		model = (new BCFOntology()).getOntology_model();
		model.read("./src/main/resources/BcfSample.ttl");
		// print validation report
		final ValidityReport report = model	.validate();
		printIterator(report.getReports(), "Validation Results");

	}

	public static void printIterator(final Iterator<?> i, final String header)
	{
		System.out.println(header);
		for (int c = 0; c < header.length(); c++)
			System.out.print("=");
		System.out.println();

		if (i.hasNext())
			while (i.hasNext())
				System.out.println(i.next());
		else
			System.out.println("<EMPTY>");

		System.out.println();
	}
	
	public static void main(String[] args) {
		new BCFTestData3();
	}
}

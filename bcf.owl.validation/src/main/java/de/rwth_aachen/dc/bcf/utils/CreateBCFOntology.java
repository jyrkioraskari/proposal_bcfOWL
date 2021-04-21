package de.rwth_aachen.dc.bcf.utils;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.vocabulary.XSD;

import de.rwth_aachen.dc.bcf.BCFOntology;

public class CreateBCFOntology {
	private OntModel ontology_model;

	public CreateBCFOntology() {
		try {
			ontology_model = (new BCFOntology()).getOntology_model();
			
			
			ontology_model.write(System.out,"TTL");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		new CreateBCFOntology();
	}
}

package de.rwth_aachen.dc.bcf;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FilenameUtils;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import generated.buildingsmart.bcf.markup.Markup;

@SuppressWarnings("restriction")
public class BCFTestData1 {
	private OntModel ontology_model = ModelFactory.createOntologyModel();
	String bcf_ns = "http://lbd.arch.rwth-aachen.de/bcf/v2.1/";
	

	public BCFTestData1() {
		Model model = ModelFactory.createDefaultModel();
		
		OntClass oc_Topic = ontology_model.createClass(this.bcf_ns + "Topic");
		
		
		String baseurl="https://bcf.example/";
		
		ZipFile zip;
		try {
			zip = new ZipFile("./src/main/resources/Issues_BIMcollab_Example_project.bcfzip");
			for (Enumeration e = zip.entries(); e.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				if (!entry.isDirectory()) {
					System.out.println(entry);
					if (FilenameUtils.getExtension(entry.getName()).equals("bcf")) {
						if(entry.getName().endsWith("markup.bcf"))
						{
							 JAXBContext jaxbContext = JAXBContext.newInstance(Markup.class);
							 Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
							 Markup issue= (Markup) jaxbUnmarshaller.unmarshal(zip.getInputStream(entry));
							 
							 Resource topic=model.createResource(baseurl+issue.getTopic().getGuid());
							 topic.addProperty(RDF.type, oc_Topic);
							 
							 System.out.println(issue.getTopic().getTitle());
							 
							 
						}
					}
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}

		model.write(System.out,"TTL");
	}


	public static void main(String[] args) {
		new BCFTestData1();
	}
}

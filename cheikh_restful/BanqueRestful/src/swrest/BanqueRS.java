package swrest;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import metier.BanqueService;
import metier.Compte;

@Path("comptes")
public class BanqueRS {
	BanqueService bs;
	public BanqueRS(){
		bs=new BanqueService();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Compte> getComptes(){
		return bs.getComptes();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compte> getComptes1(){
		return bs.getComptes();
	}
	
	@GET
	@Path("{numero}")
	@Produces(MediaType.APPLICATION_XML)
	public Compte getCompte(@PathParam("numero")long cpt){
	return bs.getCompte(cpt);
	}
	
	@GET
	@Path("{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Compte getCompte1(@PathParam("numero")long cpt){
	return bs.getCompte(cpt);
	}
	
	@GET
	@Path("EurotoFcfa/{montant}")
	public double conversion(@PathParam("montant")double mt){
	return bs.conversion(mt);
	}
	
	@POST
	@Consumes("application/xml")
	public Response createCompte(InputStream is) {
		Compte compte = readCompte(is);
		bs.addCompte(compte);
		return Response.created(URI.create("/comptes/"+compte.getNumro())).build();
	}
	
	protected Compte readCompte(InputStream is) {
		Compte cpt = new Compte();
		try {
		DocumentBuilder builder =
		DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(is);
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
		Element element = (Element) nodes.item(i);
		if (element.getTagName().equals("numero")) {
		cpt.setNumero( Long.parseLong(element.getTextContent()) );
		}
		else if (element.getTagName().equals("solde")) {
		cpt.setSolde( Double.parseDouble(element.getTextContent()) );
		}
		else if (element.getTagName().equals("datecreation")) {
		cpt.setDatecreation(Long.parseLong(element.getTextContent()));
		}
		}
		}catch(Exception exp){
			throw new WebApplicationException(exp,Response.Status.BAD_REQUEST);
		}
	
		return cpt;
	}
	
	
	
}
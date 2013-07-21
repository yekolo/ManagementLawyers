/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.persistence;

import gestionale.model.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.logging.Logger;
import javax.management.AttributeList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author nicolafelicecapece
 */
public class DAOSalva implements IDAOSalva {

    private Log logger = LogFactory.getLog(this.getClass());

    public void salva(Gestionale gestionale) {

        creaClienteXML(gestionale);

    }

    public void creaClienteXML(Gestionale gestionale) {

        gestionale.ordinaClienti();
        
        Document documet = new Document();

        Element radiceXML = new Element("generale");

        for(int i = 0; i < gestionale.numeroClienti(); i++) {
            ClienteAstratto cliente = gestionale.getClienteAstratto(i);

            if (cliente instanceof Privato) {
                Privato privato = (Privato) cliente;

                Element elementCliente = new Element("cliente");
                List newAttributes = new AttributeList();
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("type", "privato"));
                elementCliente.setAttributes(newAttributes);

                Element subElemId = new Element("id");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", "" + privato.getId()));
                subElemId.setAttributes(newAttributes);

                Element subElemNome = new Element("nome");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getNome()));
                subElemNome.setAttributes(newAttributes);

                Element subElementNumTel = new Element("numTel");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getReato()));
                subElementNumTel.setAttributes(newAttributes);

                Element subElementRecapito = new Element("recapito");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getRecapito()));
                subElementRecapito.setAttributes(newAttributes);

                Element subElementReato = new Element("reato");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getReato()));
                subElementReato.setAttributes(newAttributes);

                Element subElementCellulare = new Element("cellulare");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getCellulare()));
                subElementCellulare.setAttributes(newAttributes);

                Element subElementCognome = new Element("cognome");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getCognome()));
                subElementCognome.setAttributes(newAttributes);
                
                Element subElementCodFisc = new Element("codFisc");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getCodFisc()));
                subElementCodFisc.setAttributes(newAttributes);

                Element subElementLuogoNascita = new Element("luogoNascita");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getLuogoNascita()));
                subElementLuogoNascita.setAttributes(newAttributes);

                Element subElementProvNascita = new Element("provNascita");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.getProvNascita()));
                subElementProvNascita.setAttributes(newAttributes);

                Element subElementDataNascita = new Element("dataNascita");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", privato.formatDataNascita()));
                subElementDataNascita.setAttributes(newAttributes);

                elementCliente.addContent(subElemId);
                elementCliente.addContent(subElemNome);
                elementCliente.addContent(subElementNumTel);
                elementCliente.addContent(subElementRecapito);
                elementCliente.addContent(subElementReato);
                elementCliente.addContent(subElementCellulare);
                elementCliente.addContent(subElementCognome);
                elementCliente.addContent(subElementCodFisc);
                elementCliente.addContent(subElementLuogoNascita);
                elementCliente.addContent(subElementProvNascita);
                elementCliente.addContent(subElementDataNascita);

                radiceXML.addContent(elementCliente);
            } else {
                Ente ente = (Ente) cliente;
                
                Element elementCliente = new Element("cliente");
                List newAttributes = new AttributeList();
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("type", "ente"));
                elementCliente.setAttributes(newAttributes);

                Element subElemId = new Element("id");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", "" + ente.getId()));
                subElemId.setAttributes(newAttributes);

                Element subElemNome = new Element("nome");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", ente.getNome()));
                subElemNome.setAttributes(newAttributes);

                Element subElementNumTel = new Element("numTel");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", ente.getReato()));
                subElementNumTel.setAttributes(newAttributes);

                Element subElementRecapito = new Element("recapito");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", ente.getRecapito()));
                subElementRecapito.setAttributes(newAttributes);

                Element subElementReato = new Element("reato");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", ente.getReato()));
                subElementReato.setAttributes(newAttributes);

                Element subElementCellulare = new Element("cellulare");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", ente.getCellulare()));
                subElementCellulare.setAttributes(newAttributes);

                Element subElementPIva = new Element("partIva");
                newAttributes = new AttributeList();
                newAttributes.add(new Attribute("value", ente.getPartitaIva()));
                subElementPIva.setAttributes(newAttributes);

                elementCliente.addContent(subElemId);
                elementCliente.addContent(subElemNome);
                elementCliente.addContent(subElementNumTel);
                elementCliente.addContent(subElementRecapito);
                elementCliente.addContent(subElementReato);
                elementCliente.addContent(subElementCellulare);
                elementCliente.addContent(subElementPIva);

                radiceXML.addContent(elementCliente);
            }
        }
        documet.addContent(radiceXML);
        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setIndent("   ");
        format.setLineSeparator("\n");
        outputter.setFormat(format);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("cliente.xml");
            outputter.output(documet, fileWriter);
            logger.info("Cliente creato correttamente");

        } catch(IOException ioe) {
            logger.info("IOException: " + ioe.getMessage());
            return;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch(IOException ioe) {
                    logger.info("IOException: " + ioe.getMessage());
                    return;
                }
            }
        }

        creaFascicoli(gestionale.listaFascicoli());
        //creaCause(gestionale.listaCause());
        creaUdienze(gestionale.listaUdienze());
    }

    public void creaFascicoli(List<Fascicolo> listaFascicoli) {

        //listaFascicoli = eliminaDuplicatiFascicoli(listaFascicoli);
        
        ordinaFascicoli(listaFascicoli);
        
        Document documet = new Document();

        Element radiceXML = new Element("generale");

        for(int i = 0; i < listaFascicoli.size(); i++) {
            Fascicolo fascicolo = listaFascicoli.get(i);

            List newAttributes = new AttributeList();

            Element elementFascicolo = new Element("fascicolo");

            Element subElemId = new Element("id");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", "" + fascicolo.getId()));
            subElemId.setAttributes(newAttributes);

            Element subElemExternId = new Element("externId");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", "" + fascicolo.getExternId()));
            subElemExternId.setAttributes(newAttributes);
            
            Element subElementNotizieReato = new Element("notizieReato");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", fascicolo.getNotizieRato()));
            subElementNotizieReato.setAttributes(newAttributes);

            Element subElementRgnr = new Element("rgnr");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", fascicolo.getRgnr()));
            subElementRgnr.setAttributes(newAttributes);
            
            Element subElementRggip = new Element("rggip");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", fascicolo.getRgGip()));
            subElementRggip.setAttributes(newAttributes);
            
            Element subElementNumeroSentenza = new Element("numeroSentenza");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", fascicolo.getNumeroSentenza()));
            subElementNumeroSentenza.setAttributes(newAttributes);
            
            Element subElementAutoritaGiudiziaria = new Element("autoritaGiudiziaria");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", fascicolo.getAutoritaGiudiziaria()));
            subElementAutoritaGiudiziaria.setAttributes(newAttributes);
            
            elementFascicolo.addContent(subElemId);
            elementFascicolo.addContent(subElemExternId);
            elementFascicolo.addContent(subElementNotizieReato);
            elementFascicolo.addContent(subElementRgnr);
            elementFascicolo.addContent(subElementRggip);
            elementFascicolo.addContent(subElementNumeroSentenza);
            elementFascicolo.addContent(subElementAutoritaGiudiziaria);

            radiceXML.addContent(elementFascicolo);
        }

        documet.addContent(radiceXML);
        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setIndent("   ");
        format.setLineSeparator("\n");
        outputter.setFormat(format);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("fascicolo.xml");
            outputter.output(documet, fileWriter);
            logger.info("Fascicolo creato correttamente");

        } catch(IOException ioe) {
            logger.info("IOException: " + ioe.getMessage());
            return;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch(IOException ioe) {
                    logger.info("IOException: " + ioe.getMessage());
                    return;
                }
            }
        }
    }

    public void creaUdienze(List<Udienza> lista) {

        lista= eliminaDuplicatiUdienze(lista);
        
        logger.info("Numero di udienze senza duplicati: " + lista.size());
        
        ordinaListaUdienza(lista);

        Document documet = new Document();

        Element radiceXML = new Element("generale");

        for(Udienza udienza : lista) {

            List newAttributes = new AttributeList();

            Element elemUdienza = new Element("udienza");

            Element subElemId = new Element("id");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", "" + udienza.getId()));
            subElemId.setAttributes(newAttributes);

            Element subElemExternId = new Element("externId");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", "" + udienza.getExternId()));
            subElemExternId.setAttributes(newAttributes);

            Element subElemData = new Element("data");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", udienza.formattaDataShort()));
            subElemData.setAttributes(newAttributes);

            elemUdienza.addContent(subElemId);
            elemUdienza.addContent(subElemExternId);
            elemUdienza.addContent(subElemData);
            radiceXML.addContent(elemUdienza);
        }

        documet.addContent(radiceXML);
        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setIndent("   ");
        format.setLineSeparator("\n");
        outputter.setFormat(format);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("udienza.xml");
            outputter.output(documet, fileWriter);
            logger.info("Udienza creato correttamente");

        } catch(IOException ioe) {
            logger.info("IOException: " + ioe.getMessage());
            return;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch(IOException ioe) {
                    logger.info("IOException: " + ioe.getMessage());
                    return;
                }
            }
        }
    }

    
    private List<Fascicolo> eliminaDuplicatiFascicoli(List<Fascicolo> listaFascicoli) {
        Set<Fascicolo> setPmpListArticle = new HashSet<Fascicolo>();
        return new ArrayList<Fascicolo>(setPmpListArticle);
    }

    private List<Udienza> eliminaDuplicatiUdienze(List<Udienza> lista) {

        Set<Udienza> setPmpListArticle = new HashSet<Udienza>(lista);
        
        return new ArrayList<Udienza>(setPmpListArticle);
    }

    private void ordinaListaUdienza(List<Udienza> lista) {
        ComparatoreUdienza comparatore = new ComparatoreUdienza();
        Collections.sort(lista, comparatore);
    }

    private void ordinaFascicoli(List<Fascicolo> lista) {
        Collections.sort(lista);
    }

    public static String encrypt(String message) {
        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(message.getBytes());
            return String.format("%032x",new BigInteger(1,m.digest()));
        }
        catch(Exception e){
            return null;
        }
    }


    public void scriviFileSalva(int numeroAvii) {
        numeroAvii--;
        if (numeroAvii >= 0) {
            try {
                PrintStream ps = new PrintStream(new FileOutputStream("License.txt"));
                String caratteriCifrati = encrypt(""+numeroAvii);
                //logger.info(caratteriCifrati);
                ps.println(caratteriCifrati);
                ps.close();
            } catch (FileNotFoundException ex) {
                logger.info("FileNotFoundException: " + ex.getMessage());
            }
        }
    }

    public void scriviContratto() {
        File file = new File("ProvaContratto.docx");
        List<String> listaStringhe = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                while (br.ready()) {
                    try {
                        listaStringhe.add(br.readLine());
                    } catch (IOException ex) {
                       logger.info("IOException: " + ex.getMessage());
                    }
                }
                br.close();
            } catch (IOException ex) {
                logger.info("IOException: " + ex.getMessage());
            }

            //PrintStream ps = new PrintStream(new FileOutputStream(file));

            logger.info("Valore di listaStringhe in posizione 3: " + listaStringhe.get(2));
//            for(int i = 0; i < listaStringhe.size(); i++) {
//
//            }

        } catch(FileNotFoundException ex) {
            logger.info("FileNotFoundException: " + ex.getMessage());
        }
    }
}

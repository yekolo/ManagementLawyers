/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionale.persistence;

import gestionale.model.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Nicola
 */
public class DAOCliente implements IDAOCliente {

    private SAXBuilder builder = new SAXBuilder();
    private Log logger = LogFactory.getLog(this.getClass());
    
    public Gestionale caricaClienti() {

        Gestionale gestionale = new Gestionale();
        
        try {
            Document document = builder.build(new File("cliente.xml"));
            Element rootElement = document.getRootElement();

            List children = rootElement.getChildren();

            Iterator iterator = children.iterator();

            while(iterator.hasNext()) {
                Element element = (Element)iterator.next();
                
                if (element.getName().equals("cliente")) {

                    Privato privato = new Privato();

                    if (element.getAttributeValue("type").equals("privato")) {
                        creaPrivato(element, privato);
                        gestionale.addClienteAstratto(privato);
                    }

                    Ente ente = new Ente();

                    if (element.getAttributeValue("type").equals("ente")) {
                        creaEnte(element, ente);
                        gestionale.addClienteAstratto(ente);
                    }
                }
            }

        } catch (JDOMException ex) {
            System.out.println("JDOMException: " + ex.getMessage());
        } catch (IOException ioex) {
            System.out.println("IOException: " + ioex.getMessage());
        }

        //inserire guardie

        List<Fascicolo> listaFascicoli = creaFascicol();

        for(int i = 0; i < gestionale.numeroClienti(); i++) {
            ClienteAstratto cliTemp = gestionale.getClienteAstratto(i);
            for(Fascicolo fascicolo : listaFascicoli) {
                if (cliTemp.getId() == fascicolo.getExternId()) {
                    cliTemp.addFascicolo(fascicolo);
                }
            }
        }

        for (int i = 0; i < listaFascicoli.size(); i++) {
            Fascicolo fascicolo = listaFascicoli.get(i);
            List<Udienza> listaUdienzaTemp = creaUdienze(); 
            for(Udienza udienza : listaUdienzaTemp) {
                if (fascicolo.getId() == udienza.getExternId()) {
                    fascicolo.addUdienza(udienza);
                }
            }
        }
        
        return gestionale;
    }

    public void creaPrivato(Element element, Privato privato) {
        List attributes = element.getChildren();
        Iterator iter = attributes.iterator();
        while(iter.hasNext()) {
            Element attribute = (Element) iter.next();
            if(attribute.getName().equals("id")) {
                privato.setId(Integer.parseInt(attribute.getAttributeValue("value")));
            } else if(attribute.getName().equals("nome")) {
                privato.setNome(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("numTel")) {
                privato.setNumTel(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("recapito")) {
                 privato.setRecapito(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("reato")) {
                 privato.setReato(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("cellulare")) {
                 privato.setCellulare(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("cognome")) {
                 privato.setCognome(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("codFisc")) {
                 privato.setCodFisc(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("luogoNascita")) {
                 privato.setLuogoNascita(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("provNascita")) {
                privato.setProvNascita(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("dataNascita")) {
                privato.setDataNascita(trasformaData(attribute.getAttributeValue("value")));
            }
        }
    }

    public void creaEnte(Element element, Ente ente) {
        List attributes = element.getChildren();
        Iterator iter = attributes.iterator();
        while(iter.hasNext()) {
            Element attribute = (Element) iter.next();
            if(attribute.getName().equals("id")) {
                ente.setId(Integer.parseInt(attribute.getAttributeValue("value")));
            } else if(attribute.getName().equals("nome")) {
                ente.setNome(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("numTel")) {
                ente.setNumTel(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("recapito")) {
                ente.setRecapito(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("reato")) {
                ente.setReato(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("cellulare")) {
                ente.setCellulare(attribute.getAttributeValue("value"));
            } else if (attribute.getName().equals("partIva")) {
                 ente.setPartitaIva(attribute.getAttributeValue("value"));
            }
        }
    }

    public List<Fascicolo> creaFascicol() {

       List<Fascicolo> listaTemp = new ArrayList<Fascicolo>();

       try {
            Document document = builder.build(new File("fascicolo.xml"));
            Element rootElement = document.getRootElement();

            List children = rootElement.getChildren();

            Iterator iterator = children.iterator();

            while(iterator.hasNext()) {
                Fascicolo fascicolo = new Fascicolo();
                Element element = (Element)iterator.next();
                List attributes = element.getChildren();
                Iterator iter = attributes.iterator();
                while(iter.hasNext()) {
                    Element attribute = (Element) iter.next();
                    if(attribute.getName().equals("id")) {
                        fascicolo.setId(Integer.parseInt(attribute.getAttributeValue("value")));
                    } else if (attribute.getName().equals("externId")) {
                        fascicolo.setExternId(Integer.parseInt(attribute.getAttributeValue("value")));
                    } else if (attribute.getName().equals("notizieReato")) {
                        fascicolo.setNotizieRato(attribute.getAttributeValue("value"));
                    } else if (attribute.getName().equals("rgnr")) {
                        fascicolo.setRgnr(attribute.getAttributeValue("value"));
                    } else if (attribute.getName().equals("rggip")) {
                        fascicolo.setRgGip(attribute.getAttributeValue("value"));
                    } else if (attribute.getName().equals("numeroSentenza")) {
                        fascicolo.setNumeroSentenza(attribute.getAttributeValue("value"));
                    } else if (attribute.getName().equals("autoritaGiudiziaria")) {
                        fascicolo.setAutoritaGiudiziaria(attribute.getAttributeValue("value"));
                    }
                }
                listaTemp.add(fascicolo);
            }
        } catch (JDOMException ex) {
            System.out.println("JDOMException: " + ex.getMessage());
        } catch (IOException ioex) {
            System.out.println("IOException: " + ioex.getMessage());
        }

       return listaTemp;
    }
    
    public List<Udienza> creaUdienze() {
        List<Udienza> listaUdienze = new ArrayList<Udienza>();

        try {
            Document document = builder.build(new File("udienza.xml"));
            
            Element rootElement = document.getRootElement();
           
            List children = rootElement.getChildren();

            Iterator iterator = children.iterator();

            while(iterator.hasNext()) {
                Udienza udienza = new Udienza();
                Element element = (Element)iterator.next();
                List attributes = element.getChildren();
                Iterator iter = attributes.iterator();
                while(iter.hasNext()) {
                    Element attribute = (Element) iter.next();
                    if(attribute.getName().equals("id")) {
                        udienza.setId(Integer.parseInt(attribute.getAttributeValue("value")));
                    } else if (attribute.getName().equals("externId")) {
                        udienza.setExternId(Integer.parseInt(attribute.getAttributeValue("value")));
                    } else if (attribute.getName().equals("data")) {
                        String stringa = attribute.getAttributeValue("value");
                        udienza.setData(trasformaData(stringa));
                    }
                }
                listaUdienze.add(udienza);
            }
        } catch (JDOMException ex) {
            System.out.println("JDOMException: " + ex.getMessage());
        } catch (IOException ioex) {
            System.out.println("IOException: " + ioex.getMessage());
        }


        return listaUdienze;
    }

    public Calendar trasformaData(String stringa){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            sdf.setLenient(false);
            Date data = sdf.parse(stringa);
            Calendar c = Calendar.getInstance();
            c.setTime(data);
            return c;

        } catch(ParseException pae) {
            //System.out.println("Si e' verificata una ParseException: " + pae.getMessage());
            return null;
        }
    }

    public String numeroAvvii(){
        File file = new File("License.txt");
        String leggi = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                if (br.ready()) {
                    try {
                        leggi = br.readLine();
                       
                        
                        //logger.info("NumeroAvvii: " + numeroAvvii);
                        br.close();
                    } catch (IOException ex) {
                        logger.info("IOException: " + ex.getMessage());
                    }
                }
            } catch (IOException ex) {
                logger.info("IOException in secnodo catch: " + ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            logger.info("FileNotFoundException: " + ex.getMessage());
        }
        return leggi;

    }

    public static String comuniItaliani(String comune, String provincia) {
        File file = new File("comuni.txt");
        int contatore = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                //boolean stato = true;
                String temp = br.readLine();
                while(br.ready()) {
                    contatore++;
                    try {
                        String nomeCitta = temp.substring(0, 35);
                        String nomeProvincia = temp.substring(35, 38);
                        if (nomeCitta.contains(comune.toUpperCase()) && nomeProvincia.contains(provincia.toUpperCase())) {
                            //System.out.println(temp.substring(69, 74));
                            return temp.substring(69, 74);
                        } else {
                            temp = br.readLine();
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   /* public void generaFileTemp() {
        Document document = new Document();
        Element radiceXML = new Element("generale") {};

        int k = 0;
        int i = 1;
        while(i <= 61) {

        for (int j = 0; j < 1+i; j++) {
            k++;

            boolean dataCreata = false;

            Random generatore = new Random();

            Calendar data = new GregorianCalendar(2011, generatore.nextInt(11), generatore.nextInt(31));
            data.setLenient(true);

            DateFormat df = new SimpleDateFormat().getDateInstance(DateFormat.SHORT);
            String dataFormattata = df.format(data.getTime());

            Element elementCausa = new Element("causa");

            List newAttributes = new AttributeList();
            id
            Element subElemId= new Element("id");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", "" + (k)));
            subElemId.setAttributes(newAttributes);

            Element subElemExternId = new Element("externId");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", "" + i));
            subElemExternId.setAttributes(newAttributes);

             data
            Element subElemData = new Element("data");
            newAttributes = new AttributeList();
            newAttributes.add(new Attribute("value", dataFormattata));
            subElemData.setAttributes(newAttributes);

            elementCausa.addContent(subElemId);
            elementCausa.addContent(subElemExternId);
            elementCausa.addContent(subElemData);
            radiceXML.addContent(elementCausa);
            
        
            }
        i++;
        }
        document.addContent(radiceXML);

        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setIndent("   ");
        format.setLineSeparator("\n");
        outputter.setFormat(format);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("udienza.xml");
            outputter.output(document, fileWriter);
            logger.info("File creato correttamente");

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
    *
    */
}

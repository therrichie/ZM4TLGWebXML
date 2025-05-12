package DOM_ZM4TLG;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyZM4TLG {
	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLZM4TLG.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			//Végigmegy a vendég elemeken for ciklussal és a feltételben megadott
			//nevű Takáts Béla és értékű adatokat átírja a kért tartalommal
			NodeList nList = doc.getElementsByTagName("vendeg");
			
			for(int i = 0; i < nList.getLength(); i++) {
				Node vendeg = doc.getElementsByTagName("vendeg").item(i);
				
				NodeList list = vendeg.getChildNodes();
				
				for(int temp = 0; temp < list.getLength(); temp++) {
					Node node = list.item(temp);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						if("eletkor".equals(eElement.getNodeName())) {
							if("26".equals(eElement.getTextContent())) {
								eElement.setTextContent("22");
							}
						}
						if("nev".equals(eElement.getNodeName())) {
							if("Takáts Béla".equals(eElement.getTextContent())) {
								eElement.setTextContent("Takáts Géza");
							}
						}
					}
				}
			}
			
			//Átírja az adott gyakornok attribútumát a megadott értékre
			Node gyakornok = doc.getElementsByTagName("gyakornok").item(2);
			
			NamedNodeMap attr = gyakornok.getAttributes();
			Node nodeAttr = attr.getNamedItem("e_gy");
			nodeAttr.setTextContent("e3");
			
			
			//Kitörli az összes rendelés elemet az xml fájlból
			Node vendeglatas = doc.getFirstChild();
			NodeList childNodes = vendeglatas.getChildNodes();
			
			for(int count = 0; count < childNodes.getLength(); count++) {
				Node node = childNodes.item(count);
				
				if("rendeles".equals(node.getNodeName()))
					vendeglatas.removeChild(node);
			}
			
			//Kiírja a módosított xml fájlt a konzolra
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			System.out.println("----------Modified File-----------");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
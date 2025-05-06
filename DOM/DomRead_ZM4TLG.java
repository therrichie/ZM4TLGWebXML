package DOM_ZM4TLG;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomRead_ZM4TLG {
	
	public static void main(String[] args ) throws SAXException, IOException, ParserConfigurationException{
		
		//Ebből a fájlból olvas
		File xmlFile = new File("XMLZM4TLG.xml");
		
		//A DocumentBuilderFactory-ből megkapjuk a DocumentBulder-t
		//A DocumentBuilder tartalmazza az API-t a DOM-dokumentum példányok XML-dokumentumból való beszerzéshez
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//A parse() metóduselemzi az XML fájlt és lekérem a documentbe 
		Document zm4tlg = dBuilder.parse(xmlFile);
		
		//A dokumentum normalizálása segít a helyes eredmények elérésében
		zm4tlg.getDocumentElement().normalize();
		
		//Kiíratjuk  a dokumentum gyökérelemét
		System.out.println("Gyökér elem " + zm4tlg.getDocumentElement().getNodeName());
		
		//A getElementsByTagName() metódus segítségével megkapjuk  az etterem elem NodeList-jét
		NodeList nlist = zm4tlg.getElementsByTagName("etterem");
		
		//Végigmegyünk rajta egy ciklussal
		for(int i=0; i < nlist.getLength(); i++) {
			
			//Lekérjük a lista aktuális elemét
			Node nNode = nlist.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				//Elementté konvertáljuk az aktuális elemet
				Element elem = (Element) nNode;
				
				//Lekérjük az aktuális elem attribútumának tartalmát
				String id = elem.getAttribute("ekod");
				
				//Lekérjük az aktuális elem gyerekelemeinek tartalmát
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("varos").item(0);
				String city = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("utca").item(0);
				String street = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("hazszam").item(0);
				String number = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("csillag").item(0);
				String stars = node5.getTextContent();
				
				//A cím gyerekelemeinek összefűzése egy stringbe
				String adr = city + ", " + street + " utca " + number + ".";
				
				//Formázva kiíratjuk a lekért információkat az adott elemről
				System.out.println("Étterem ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Cím: " + adr);
				System.out.println("Csillag: " + stars);
				
			}
		}
		
		nlist = zm4tlg.getElementsByTagName("foszakacs");
		
		for(int i = 0; i < nlist.getLength(); i++) {
			
			Node nNode = nlist.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("fkod");
				String eid = elem.getAttribute("e_f");
				
				String work = "Ez a főszakács a(z) " + eid + " azonosítójú étteremben dolgozik";
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("eletkor").item(0);
				String age = node2.getTextContent();
				
				Node node3;
				String edu = "";
				
				//Több végzettsége is lehet egy szakácsnak, így ezt a formázott kiíratást csináltam,
				//hogy helyesen írassa ki a végzettségek számától függetlenül
				for(int j = 0; j<elem.getElementsByTagName("vegzettseg").getLength(); j++) {
					node3 = elem.getElementsByTagName("vegzettseg").item(j);
					if(j == elem.getElementsByTagName("vegzettseg").getLength() - 1) {
						edu += node3.getTextContent();
					}else {
						edu += node3.getTextContent() + ", ";
					}
				}
				
				System.out.println("Főszakács ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Kor: " + age);
				System.out.println("Végzettségek: " + edu);
				System.out.println(work);
			}
		}
		
		nlist = zm4tlg.getElementsByTagName("szakacs");
		
		for(int i = 0; i < nlist.getLength(); i++) {
			
			Node nNode = nlist.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("szkod");
				String eid = elem.getAttribute("e_sz");
				
				String work = "Ez a szakács a(z) " + eid + " azonosítójú étteremben dolgozik";
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("reszleg").item(0);
				String department = node2.getTextContent();
				
				Node node3;
				String edu = "";
				
				//Több végzettsége is lehet egy szakácsnak, így ezt a formázott kiíratást csináltam,
				//hogy helyesen írassa ki a végzettségek számától függetlenül
				for(int j = 0; j<elem.getElementsByTagName("vegzettseg").getLength(); j++) {
					node3 = elem.getElementsByTagName("vegzettseg").item(j);
					if(j == elem.getElementsByTagName("vegzettseg").getLength() - 1) {
						edu += node3.getTextContent();
					}else {
						edu += node3.getTextContent() + ", ";
					}
				}
				
				System.out.println("Szakács ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Részleg: " + department);
				System.out.println("Végzettségek: " + edu);
				System.out.println(work);
			}
		}
		
		nlist = zm4tlg.getElementsByTagName("gyakornok");
		
		for(int i = 0; i < nlist.getLength(); i++) {
			
			Node nNode = nlist.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("gykod");
				String eid = elem.getAttribute("e_gy");
				
				String work = "Ez a gyakornok a(z) " + eid + " azonosítójú étteremben dolgozik";
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("kezdete").item(0);
				String start = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("idotartama").item(0);
				String duration = node3.getTextContent();
				
				String practical = "kezdete: " + start + ", időtartama: " + duration;
				
				Node node4;
				String shift = "";
				
				for(int j = 0; j<elem.getElementsByTagName("muszak").getLength(); j++) {
					node4 = elem.getElementsByTagName("muszak").item(j);
					if(j == elem.getElementsByTagName("muszak").getLength() - 1) {
						shift += node4.getTextContent();
					}else {
						shift += node4.getTextContent() + ", ";
					}
				}
				
				System.out.println("Gyakornok ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Gyakorlat: " + practical);
				System.out.println("Műszak: " + shift);
				System.out.println(work);
			}
		}
		
		nlist = zm4tlg.getElementsByTagName("vendeg");
		
		for(int i = 0; i < nlist.getLength(); i++) {
			
			Node nNode = nlist.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) nNode;
				
				String id = elem.getAttribute("vkod");
				
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("eletkor").item(0);
				String age = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("varos").item(0);
				String city = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("utca").item(0);
				String street = node4.getTextContent();
				
				Node node5 = elem.getElementsByTagName("hazszam").item(0);
				String number = node5.getTextContent();
				
				String adr = city + ", " + street + " utca " + number + ".";
				
				
				System.out.println("Vendég ID: " + id);
				System.out.println("Név: " + name);
				System.out.println("Kor: " + age);
				System.out.println("Cím: " + adr);
			}
		}
		
		nlist = zm4tlg.getElementsByTagName("rendeles");
		
		for(int i = 0; i < nlist.getLength(); i++) {
			
			Node nNode = nlist.item(i);
			
			System.out.println("\nAktuális elem: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) nNode;
				
				String eid = elem.getAttribute("e_v_e");
				String vid = elem.getAttribute("e_v_v");
				
				String dinner = "A(z) " + eid + "azonosítóval rendelkező étteremből rendelt a(z) " + vid + " azonosítójú vendég.";
				
				Node node1 = elem.getElementsByTagName("osszeg").item(0);
				String price = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("etel").item(0);
				String food = node2.getTextContent();
				
				
				System.out.println(dinner);
				System.out.println("Összeg: " + price);
				System.out.println("Étel: " + food);
			}
		}
		
	}
}

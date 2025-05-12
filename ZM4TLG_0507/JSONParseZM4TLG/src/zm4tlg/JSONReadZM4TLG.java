package zm4tlg;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONReadZM4TLG {
    public static void main(String[] args) {
        
        try (FileReader reader = new FileReader("orarendZM4TLG.json")) {
            // JSON parser létrehozása a fájl tartalmának feldolgozásához
            JSONParser jsonParser = new JSONParser();
            
            // JSON fájl parse-olása és fő JSON objektummá alakítása
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            
            // "ora" tömb kinyerése a JSON-ből - ez tartalmazza az összes óra adatát
            JSONArray lessons = (JSONArray) jsonObject.get("ora");
            
            System.out.println("Órarend 2025/tavaszi félév:\n");
            
            // Végigmegyünk az összes óra adatán a tömbben
            for (Object lessonObj : lessons) {
                // Az aktuális óra JSON objektummá alakítása
                JSONObject lesson = (JSONObject) lessonObj;
                
                // Időpont objektum kinyerése (ami maga is egy JSON objektum)
                JSONObject time = (JSONObject) lesson.get("idopont");
                
                // Óra attribútumainak kinyerése:
                // - @id: az óra egyedi azonosítója
                String id = (String) lesson.get("@id");
                // - @típus: az óra típusa (elmélet/gyakorlat)
                String tipus = (String) lesson.get("@típus");
                
                // Adatok kiírása formázottan:
                System.out.println("Óra ID: " + id);
                System.out.println("Típus: " + tipus);
                // - kurzus: az óra neve
                System.out.println("Tárgy: " + lesson.get("kurzus"));
                // - időpont: nap + kezdés-végzés
                System.out.println("Időpont: " + time.get("nap") + ", " + 
                                 time.get("tol") + "-" + time.get("ig"));
                // - helyszín: az óra helye
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                // - oktató: aki tartja az órát
                System.out.println("Oktató: " + lesson.get("oktato"));
                // - szak: melyik szaknak szól
                System.out.println("Szak: " + lesson.get("szak"));
                System.out.println("-----");  
            }
        } catch (Exception e) {
            // Hiba esetén a stack trace kiírása
            e.printStackTrace();
        }
    }
}


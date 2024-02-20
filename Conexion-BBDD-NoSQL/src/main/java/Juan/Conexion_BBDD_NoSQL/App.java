package Juan.Conexion_BBDD_NoSQL;

//import org.w3c.dom.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//URL de conexion a tu cluster remoto MongoDB
    	//String connectionString = "mongodb+srv://usuario:contraseña@tu-cluster.mongodb.net/tu_base_de_datos=majority"
    	
    	//String connectionString = "mongodb+srv://usuario:contraseña@cluster0.aza83ov.mongodb.net/?retryWrites=true&w=majority";
    	
    	String connectionString = "mongodb://localhost:27017";
    	
    	//Establecer conexion con el servidor MongoDB
    	try(MongoClient mongoClient = MongoClients.create(new ConnectionString(connectionString))){
    		//obtener la base de datos
    		MongoDatabase database = mongoClient.getDatabase("cluster0");
    		
    		//obtener la coleccion
    		MongoCollection<Document> collection = database.getCollection("mi_coleccion");
    		
    		//insertar un documento de ejemplo
    		Document document = new Document("nombre", "Ejemplo")
    								.append("edad", 30)
    								.append("ciudad", "EjemploCity");
    		collection.insertOne(document);
    		
    		
    		//actualizar un documento de ejemplo
    		/*Document filtro = new Document("nombre", "Ejemplo");

    		// Definir la actualización que deseas realizar
    		Document actualizacion = new Document("$set", new Document("edad", 35)
    								.append("ciudad", "Albacete"));

    		// Ejecutar la actualización
    		collection.updateOne(filtro, actualizacion);*/


    		//consultar e imprimir todos los documentos en la coleccion
    		MongoCursor<Document> cursor = collection.find().iterator();
    		try {
    			while(cursor.hasNext()) {
    				System.out.println(cursor.next().toJson());
    			}
    		}finally {
    			cursor.close();
    		}
    	}
        //System.out.println( "Hello World!" );
    }
}
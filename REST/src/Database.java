import java.util.Hashtable;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/mark")
public class Database {
	
	private Hashtable<String,Integer> table = new Hashtable<String,Integer>();

	@POST
	@Path("{id},{mark}")
	public String addRecord(@PathParam("id")String id, @PathParam("mark")String mark) {
		// Add a record using id as key and mark as value
		if (table.containsKey(id))
		{
			return "Record of " + id + " exists ";
		}
		else
		{
			table.put(id, Integer.parseInt(mark));
			return "Record of " + id + " added ";
		}
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	public String getMark(@QueryParam("id")String id) {
		// Retrieve a record using id as key
		if (table.containsKey(id))
		{
			return "Mark of " + id + " : " + table.get(id).toString();
		}else {
			return "Record of " + id + " does not exists ";
		}
	}

	@POST
	@Path("update/{id},{mark}")
	public String updateRecord(@PathParam("id")String id, @PathParam("mark")String mark) {
		// Update a record using id as key and mark as value
		if (!table.containsKey(id))
		{
			return "Record of " + id + " does not exists";
		}
		else
		{
			table.put(id, Integer.parseInt(mark));
			return "Record of " + id + " updated ";
		}
	}
}

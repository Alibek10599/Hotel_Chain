package kz.akama.hotelchainproject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/items")
public class PagingService {

    private List<String> list = new CopyOnWriteArrayList<String>();

    public PagingService() {
        for (int i = 0; i < 100; i++) {
            list.add("Entry " + String.valueOf(i));
        }
    }

    @GET
    public Response getMyList(@QueryParam("page") int page) {
        Gson gson = new Gson();
        String json;
        int t=-1;
        
        PagedHelper p = new PagedHelper();
        

        if(page*10>=list.size()-10) {
        	
        	p.setList(list.subList(page*10, list.size()));
        	t = page;
        	
        }else {
        	
        	p.setList(list.subList(page*10, (page*10)+10));
        	
        }
        int l = page-1;
        int r= page+1;
        if (page == 0 ) {
        	l= page;
        }if (page == t ) {
        	r= page;
        }
        
        p.setNext(Integer.toString(r));
        p.setPrev(Integer.toString(l));
        
        /*if (page == 0) {
            p.setList(list.subList(0, 10));
            p.setNext("1");
            p.setPrev("0");
        } 
        if (page == 1) {
            p.setList(list.subList(10, 20));
            p.setNext("2");
            p.setPrev("0");
        }
        if (page == 2) {
            p.setList(list.subList(20, 30));
            p.setNext("3");
            p.setPrev("1");
        }
        if (page == 3) {
            p.setList(list.subList(30, 40));
            p.setNext("4");
            p.setPrev("2");
        }
        if (page == 4) {
            p.setList(list.subList(40, 50));
            p.setNext("5");
            p.setPrev("3");
        }
        if (page == 5) {
            p.setList(list.subList(50, 60));
            p.setNext("6");
            p.setPrev("7");
        }
        if (page == 6) {
            p.setList(list.subList(60, 70));
            p.setNext("7");
            p.setPrev("5");
        }
        if (page == 7) {
            p.setList(list.subList(70, 80));
            p.setNext("8");
            p.setPrev("6");
        }
        if (page == 8) {
            p.setList(list.subList(80, 90));
            p.setNext("9");
            p.setPrev("7");
        }
        if (page	 == 9) {
            p.setList(list.subList(90, 100));
            p.setNext("9");
            p.setPrev("8");
        }*/

        
        json = gson.toJson(p, PagedHelper.class);
        
        return Response.ok(json).build();
    }
}




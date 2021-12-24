package net.intelie.challenges;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void thisIsAWarning() throws Exception {
    	EventHandler handler = EventHandler.getEventHandler();
    	ArrayList<Event> test1 = new ArrayList<>();
    	ArrayList<Event> test2 = new ArrayList<>();
    	
        Event event = new Event("some_type", 123L);
        handler.insert(event);
        
        event = new Event("regular", 300L);
        handler.insert(event);
        test1.add(event);
        
        event = new Event("irregular", 150L);
        handler.insert(event);
        test1.add(event);
        
        event = new Event("some_type", 300L);
        handler.insert(event);
        
        event = new Event("some_type", 200L);
        handler.insert(event);
        
        event = new Event("irregular", 100L);
        handler.insert(event);
        test1.add(event);
        test2.add(event);
        
        event = new Event("irregular", 110L);
        handler.insert(event);
        test1.add(event);
        test2.add(event);

        event = new Event("irregular", 60L);
        handler.insert(event);
        test1.add(event);
        test2.add(event);
        
        handler.removeAll("some_type");
    
        int i = 0;
        EventBrowser it = handler.getIterator();
        EventBrowser it2 = handler.query("irregular", 50L, 120L);
        while (it.moveNext()) {
        	Event curr = it.current();
        	assertEquals(test1.get(i).type(), curr.type());
        	assertEquals(test1.get(i).timestamp(), curr.timestamp());
        	i += 1;
        }
        
        System.out.println("FILTERED");
        i = 0;
        while (it2.moveNext()) {
        	Event curr = it2.current();
        	assertEquals(test2.get(i).type(), curr.type());
        	assertEquals(test2.get(i).timestamp(), curr.timestamp());
        	i += 1;
        }
        
        while (it.moveNext()) {
        	it.remove();
        }
        while (it2.moveNext()) {
        	it2.remove();
        }
        
//        it.remove();
        
        

        //THIS IS A WARNING:
        //Some of us (not everyone) are coverage freaks.
//        assertEquals(123L, event.timestamp());
//        assertEquals("some_type", event.type());
    }
}
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

class Request {
    private int id;
    private String owner;
    private int priority;

    public Request(int id, String owner, int priority) {
        this.id = id;
        this.owner = owner;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + owner + ", priority " + priority + "]";
    }
}

class RequestPriorityComparator implements Comparator<Request> {
    @Override
    public int compare(Request first, Request second) {
        // TODO Task 1: Compare the requests by priority.
        if(first.getPriority() != second.getPriority()){
            return Integer.compare(first.getPriority(), second.getPriority());

        }else{
            return Integer.compare(first.getId(), second.getId());
        }
    }
}

class HelpDeskManager {
    private ArrayList<Request> requests;
    private HashSet<Integer> activeIds;

    public HelpDeskManager() {
        requests = new ArrayList<Request>();
        activeIds = new HashSet<Integer>();
    }

    public boolean addRequest(Request request) {
        // TODO Task 2:
        // 1. Reject a null request, an invalid priority, or a duplicate ID.
        if(request == null || request.getPriority() < 1 || activeIds.contains(request.getId())){

            return false;
        } else{
            // 2. Add the ID and request to the appropriate collections.

            activeIds.add(request.getId());
            requests.add(request);
            // 3. Sort requests using RequestPriorityComparator.
            requests.sort(new RequestPriorityComparator());
            return  true;
        }

    }

    public HashMap<Integer, Integer> countRequestsByPriority() {
        // TODO Task 3: Count the requests at each priority level.
        HashMap<Integer, Integer> count = new HashMap<>();
        for(Request request : requests){
            if(count.containsKey(request.getPriority())){
                count.put(request.getPriority(),count.get(request.getPriority()) + 1);
            }
            else{
                count.put(request.getPriority(), 1);
            }
        }
        return count;
    }

    public boolean containsUsingList(int requestId) {
        // TODO Task 4: Search requests one by one without using activeIds.
        for(Request request : requests){
            if(request.getId() == requestId){
                return true;
            }
        }
        return false;
    }

    public boolean containsUsingSet(int requestId) {
        // TODO Task 4: Search for requestId using activeIds.
        for(Integer I : activeIds ){
            if(I == requestId){
                return true;
            }
        }
        return false;
    }

    // This helper method is provided for Task 5.
    public void printRequests() {
        for (Request request : requests) {
            System.out.println(request);
        }
    }
}

public class LabWarmUpI {
    public static void main(String[] args) {
        // TODO Task 5:

        // 1. Create a HelpDeskManager.
        HelpDeskManager hhh = new HelpDeskManager();
        // 2. Add at least four requests. At least two must have the same priority.
        Request r1 = new Request(1,"AAA",1);
        Request r2 = new Request(1,"bbb",2);
        Request r3 = new Request(22,"ccc",3);
        Request r4 = new Request(333,"ddd",3);
        hhh.addRequest(r1);
        hhh.addRequest(r4);
        hhh.addRequest(r3);
        hhh.addRequest(r2);
        // 3. Try to add a request with a duplicate ID.
        hhh.printRequests();
        // 4. Print the requests using printRequests().
        System.out.println(hhh.countRequestsByPriority());
        // 5. Print the result of countRequestsByPriority().
        System.out.println(hhh.containsUsingList(22));
        System.out.println(hhh.containsUsingSet(22));
        // 6. Test both search methods with an existing ID.
        System.out.println(hhh.containsUsingSet(222));
        System.out.println(hhh.containsUsingList(222));
        // 7. Test both search methods with an ID that does not exist.
    }
}

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

public class BugTest {
    public static void main(String[] args) {
//        Drawable drawable =   new Drawable(){
//            public  void draw() {
//                System.out.println("-00");
//            }
//        };
//        drawable.draw();

//        Drawable d = ()->{
//            System.out.println("--01");
//        };
//        d.draw();



        Sayable sayable = ()->{
            return "hhhhhh";
        };
        System.out.println(sayable.say());
    }

    @Test
    public void t1() {
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>(3,0.75f,true);
        linkedHashMap.put("1","aaa");
        linkedHashMap.put("2","bbb");
        linkedHashMap.put("3","ccc");
        linkedHashMap.get("1");
        for (String s : linkedHashMap.keySet()) {
            System.out.println(s);
        }

    }
    @Test
    public void t2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for(int i = 0 ; i < list.size() ; i++) {
            list.remove(0);
        }
        System.out.println(list);
    }
}

class  MyThead implements Callable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        MyThead myThead = new MyThead();

        Future future = executorService.submit(myThead);

        executorService.shutdown();

        System.out.println(future.get());

    }

    @Override
    public String call() throws Exception {
        return "child";
    }
}

@FunctionalInterface
interface Drawable{
    public void draw();

}


@FunctionalInterface
interface Sayable {
    public String say ();
}



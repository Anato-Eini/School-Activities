import com.google.gson.Gson;

public class Test {

    record Person(String name, int age, String country){

    }
    public static void main(String[] args) {
//        Person person = new Person("Jake", 12, "PH");
        Gson gson = new Gson();
//        String test = gson.toJson(person);
//        System.out.println(test);

        var JSONString = """
                {
                    "name" : "Jake",
                    "age" : 40,
                    "Country" : "PH" 
                }
                """;
        var person = gson.fromJson(JSONString, Person.class);
        System.out.println(person.name);
    }

    public void login(){

    }


    public static class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;
        BagOfPrimitives() {
            // no-args constructor
        }
    }
}

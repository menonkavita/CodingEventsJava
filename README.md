Section I - Purpose of the App
Build an app to help people keep track of the events they want to attend.

Section II - Describe the current state of the App
The App creates & displays Events, Event Categories & Event Tags.
Currently, it doesn't have the functionality to help a Person keep track of the events they are interested in attending.

Section III - Future improvements you want to make to the app including your notes about the Person class.

K Suggestions -

1.Create a Person class
    public class Person
    {
      private int Person_id;
      private String name;
      private String email;
      private String password;
      private String zipcode;

      getters();    // for all fields
      setters();    // for all except id
    }

2. Create a DTO class - Person_Event_DTO
    public class Person_Event_DTO
   {
     private intPerson_Event_DTO_id;
     private Person person;
     private Event event;
     private Tag tag;
   }
   


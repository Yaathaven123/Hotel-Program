import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Hotel {
    public static Scanner scanner = new Scanner(System.in);    //Calling a Global Scanner so that it doesn't have to be called again in methods
    public static Person[] hotel = new Person[8];
    public static Room[] customer = new Room[8];
    public static ArrayList<String> waitingList = new ArrayList<String>();
    public static int roomNumber = 0;
    public static String roomName;

    public static void initialise() {  //method to set all the rooms as empty
        for (int x = 0; x < 8; x++ ) {
            hotel[x]= new Person("e","","");
            customer[x]=new Room('0');
        }
        System.out.println( "initialise ");
    }

    public static boolean waitingList(){
        boolean isHotelFull=true;
        for (int x = 0; x < hotel.length; x++ ) {
            if (hotel[x].first_Name.equals("e")) {
                isHotelFull=false;
                break;

            }
        }
        return isHotelFull;
    }

    public static void add() {  //Add a customer
        int a = 0;
        for (int i = 0; i < 8; i++) {
            if (!(hotel[i].first_Name.equals("e"))) {
                a += 1;
            }
        }
        if (a == 8) {
            System.out.println("All our rooms are currently full, You will be added to our waiting list.");
            System.out.println("Please enter your name to be added in the waiting list: ");
            waitingList.add(scanner.next().toUpperCase());}

        System.out.print("Enter room number (0-7) :");
        roomNumber = scanner.nextInt();
        if (hotel[roomNumber].first_Name.equals("e")) {
            System.out.print("Enter the no. of guests in your room (in numbers) : ");
            customer[roomNumber].noOfGuests = scanner.nextInt();
            System.out.print("Enter Customer's First name : ");
            roomName = scanner.next();
            hotel[roomNumber].first_Name = roomName;
            System.out.print("Enter Customer's Surname : ");
            hotel[roomNumber].sur_Name = scanner.next();
            System.out.print("Enter Customer's Credit Card Number : ");
            hotel[roomNumber].creditCardNumber = scanner.next();
            System.out.println("Customer information successfully added");

        } else {
            System.out.println("Room is Occupied");
        }

    }

    public static void view() {      //View all rooms
        for (int x = 0; x < hotel.length; x++ ) {
            System.out.println("room " + x + " occupied by " + hotel[x].first_Name + " " + hotel[x].sur_Name);
        }
    }
    //method to delete customer
    public static void remove() { //Delete Customer from room
        System.out.print("Enter the guest's First name: ");
        String CustomerName = scanner.next();
        for (int x = 0; x < hotel.length; x++) {
            if (hotel[x].first_Name.equals(CustomerName)) {
                hotel[x].first_Name = "e";    //assigns the string e to mentioned room to make it empty
                hotel[x].sur_Name = "";
                hotel[x].creditCardNumber = "";
                System.out.println("Guest's name in room number " + x + "  deleted ");
                break;
            }
        }

    }

    public static void displayEmpty(){   //Displays the empty rooms
        for (int x = 0; x < hotel.length; x++ ) {
            if (hotel[x].first_Name.equals("e")){
                System.out.println("room " + x + " is empty");
            }
        }
    }

    public static void find() {   //Find room from customer name
        System.out.print("Enter the guest's first name: ");
        String name = scanner.next();
        for (int x = 0; x < hotel.length; x++) {
            if (hotel[x].first_Name.equals(name)) {
                System.out.println("Guest is in room number " + x );
                break;
            }
        }

    }

    public static void store() throws IOException { //Store program data into file
        File Storage = new File("save.txt");
        Storage.createNewFile();  //A new file is created using the method
        FileWriter writer = new FileWriter(Storage.getName());
        for(int x = 0; x<hotel.length; x++){
            writer.write( hotel[x].first_Name + "\n");
            writer.write( hotel[x].sur_Name + "\n");
            writer.write( hotel[x].creditCardNumber + "\n");
        }
        writer.close();
        System.out.println("Stored successfully");
    }

    public static void load() throws IOException {  //Load program data from file
        int index = 0;
        File Storage = new File("save.txt");
        Scanner reader = new Scanner(Storage);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String data1 = reader.nextLine();
            String data2 = reader.nextLine();
            hotel[index].first_Name= data;
            hotel[index].sur_Name= data1;
            hotel[index].creditCardNumber= data2;
            index++;
        }
        reader.close();
        System.out.println("Loaded successfully");
    }

    public static void sort(){  //View guests Ordered alphabetically by name
        for (int i = 0; i <hotel.length; i++)
        {
            for (int j = i + 1; j < hotel.length; j++)
            {
                if (hotel[i].first_Name.compareTo(hotel[j].first_Name) > 0) //compares two different elements of the array at a time
                {
                    String temp = hotel[i].first_Name;
                    hotel[i].first_Name = hotel[j].first_Name;
                    hotel[j].first_Name = temp;  //rearranged in a way to make sure it is in alphabetical order
                }
            }
        }
        for (int x = 0; x < hotel.length; x++) {
            System.out.println("-"+ hotel[x].first_Name);
        }
    }

    public static String menu() {  //method to display the menu
        System.out.println("\nChoose an option from the menu: \n");
        System.out.println("Enter 'A' - Add a customer");
        System.out.println("Enter 'V' - View all rooms");
        System.out.println("Enter 'E' - Display Empty rooms");
        System.out.println("Enter 'D' - Delete Customer from room");
        System.out.println("Enter 'F' - Find room from customer name");
        System.out.println("Enter 'S' - Store program data into file");
        System.out.println("Enter 'L' - Load program data from file");
        System.out.println("Enter 'O' - View guests Ordered alphabetically by name");
        String option = scanner.next().toLowerCase();  //users choice is converted to lower case and saved in a variable

        return option;  //the saved variable is returned
    }

    public static void main(String[] args) {
        initialise();  //The method setEmpty is called

        while (true) {
            String option = menu(); //users choice from the method menu is saved in variable

            if (option.equals("a")) {  //users choice is checked and methods accordingly called
                add(); //The method to add a customer is called
            } else if (option.equals("v")) {
                view();  //The method to view all rooms is called
            } else if (option.equals("e")) {
                displayEmpty();    //The method to Display Empty rooms is called
            } else if (option.equals("d")) {
                remove();   //The method to delete Customer from room is called
            } else if (option.equals("f")) {
                find();   //The method to find room from customer name is called
            } else if (option.equals("o")) {
                sort();  //The method to View guests ordered alphabetically by name is called
            } else if (option.equals("s")) {
                try {
                    store();    //The method to store program data into file is called
                } catch (IOException e) {
                    e.printStackTrace();  //method to handle exceptions and error
                }
            } else if (option.equals("l")) {
                try {
                    load(); //The method to load program data from file is called
                } catch (IOException e) {
                    e.printStackTrace();  //method to handle exceptions and error
                }
            } else {
                System.out.println("Invalid input");
            }

            System.out.print("\nEnter c to continue or any other key to end program : ");
            String userInput = scanner.next().toLowerCase();   //message prompted to ask user if they want to continue the program

            if (!userInput.equals("c")) {
                System.out.println("Ending program......");   //if the input is not c, the program stops running
                break;
            }
        }
    }
}

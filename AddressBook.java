import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
    private HashMap<String, String> contacts;

    public AddressBook() {
        contacts = new HashMap<>();
    }

    public void load(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                contacts.put(parts[0], parts[1]);
            }
        }
        reader.close();
    }

    public void save(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name);
    }

    public void delete(String number) {
        contacts.remove(number);
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Cargar contactos desde archivo");
            System.out.println("5. Guardar contactos en archivo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (option) {
                    case 1:
                        addressBook.list();
                        break;
                    case 2:
                        System.out.print("Ingrese el número: ");
                        String number = scanner.nextLine();
                        System.out.print("Ingrese el nombre: ");
                        String name = scanner.nextLine();
                        addressBook.create(number, name);
                        break;
                    case 3:
                        System.out.print("Ingrese el número a borrar: ");
                        number = scanner.nextLine();
                        addressBook.delete(number);
                        break;
                    case 4:
                        System.out.print("Ingrese el nombre del archivo: ");
                        String loadFilename = scanner.nextLine();
                        addressBook.load(loadFilename);
                        break;
                    case 5:
                        System.out.print("Ingrese el nombre del archivo: ");
                        String saveFilename = scanner.nextLine();
                        addressBook.save(saveFilename);
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {

    private HashMap<String, String> contacts;
    private final String fileName = "contacts.csv";

    public AddressBook() {
        contacts = new HashMap<>();
    }

    // Cargar contactos desde el archivo CSV
    public void load() {

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No existe un archivo de contactos. Se creará uno nuevo.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", 2);

                if (data.length == 2) {
                    contacts.put(data[0], data[1]);
                }
            }

            System.out.println("Contactos cargados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al cargar contactos: " + e.getMessage());
        }
    }

    // Guardar contactos en el archivo CSV
    public void save() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Map.Entry<String, String> contact : contacts.entrySet()) {

                writer.write(
                        contact.getKey() + "," + contact.getValue()
                );

                writer.newLine();
            }

            System.out.println("Cambios guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }

    // Mostrar contactos
    public void list() {

        System.out.println("\n--- CONTACTOS ---");

        if (contacts.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        for (Map.Entry<String, String> contact : contacts.entrySet()) {

            System.out.println(
                    contact.getKey() + " : " + contact.getValue()
            );
        }
    }

    // Crear contacto
    public void create(String number, String name) {

        if (contacts.containsKey(number)) {
            System.out.println("Ya existe un contacto con ese número.");
            return;
        }

        contacts.put(number, name);

        System.out.println("Contacto creado correctamente.");

        save();
    }

    // Eliminar contacto
    public void delete(String number) {

        if (!contacts.containsKey(number)) {
            System.out.println("No existe un contacto con ese número.");
            return;
        }

        contacts.remove(number);

        System.out.println("Contacto eliminado correctamente.");

        save();
    }
}
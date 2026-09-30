import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AddressBook addressBook = new AddressBook();

        // Cargar contactos existentes
        addressBook.load();

        int option;

        do {

            System.out.println("\n==========================");
            System.out.println("       ADDRESS BOOK");
            System.out.println("==========================");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {

                    case 1:

                        addressBook.list();

                        break;

                    case 2:

                        System.out.print("Nombre del contacto: ");
                        String name = scanner.nextLine();

                        System.out.print("Número telefónico: ");
                        String number = scanner.nextLine();

                        if (name.isBlank() || number.isBlank()) {

                            System.out.println(
                                    "El nombre y el número no pueden estar vacíos."
                            );

                        } else {

                            addressBook.create(number, name);
                        }

                        break;

                    case 3:

                        System.out.print(
                                "Número telefónico que desea eliminar: "
                        );

                        String numberDelete = scanner.nextLine();

                        addressBook.delete(numberDelete);

                        break;

                    case 0:

                        addressBook.save();

                        System.out.println("Agenda cerrada.");

                        break;

                    default:

                        System.out.println("Opción inválida.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Debe ingresar un número válido.");

                option = -1;
            }

        } while (option != 0);

        scanner.close();
    }
}
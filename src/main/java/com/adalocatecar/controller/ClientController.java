package com.adalocatecar.controller;

import com.adalocatecar.dto.ClientDTO;
import com.adalocatecar.service.ClientService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void manageClients(Scanner scanner) throws IOException {
        while (true) {
            System.out.println("\nClient Management");
            System.out.println("1. Register Client");
            System.out.println("2. Update Client Information");
            System.out.println("3. Delete Client");
            System.out.println("4. List All Clients");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerClient(scanner);
                    break;
                case 2:
                    updateClient(scanner);
                    break;
                case 3:
                    deleteClient(scanner);
                    break;
                case 4:
                    listClients();
                    break;
                case 5:
                    System.out.println("Exiting Client Management...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void registerClient(Scanner scanner) {
        System.out.println("Enter client details to register:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the CPF or CNPJ: ");
        String id = scanner.nextLine();

        ClientDTO clientDTO = new ClientDTO(id, name, null);
        ValidationMessages response = clientService.createClient(clientDTO);
        System.out.println(response.getMessage());
    }

    private void updateClient(Scanner scanner) {
        System.out.println("Enter the CPF or CNPJ of the client to update:");
        String id = scanner.nextLine();

        System.out.println("Enter new name (leave blank to not change):");
        String name = scanner.nextLine();
        System.out.println("Enter new type (Individual or Corporate, leave blank to not change):");
        String type = scanner.nextLine();

        ClientDTO clientDTO = new ClientDTO(id, name, type);
        ValidationMessages response = clientService.updateClient(clientDTO);
        System.out.println(response.getMessage());
    }

    private void deleteClient(Scanner scanner) {
        System.out.println("Enter client the CPF or CNPJ to delete:");
        String id = scanner.nextLine();

        ValidationMessages response = clientService.deleteClient(id);
        System.out.println(response.getMessage());
    }

    private void listClients() throws IOException {
        List<ClientDTO> clients = clientService.findAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            clients.forEach(client -> System.out.println(client.toString()));
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Beans;

import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import clinicaveterinaria.Repositorio.BancoDeDados;
import clinicaveterinaria.ValidacoesBuscas.Validation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BrenoLima
 */
public class CadastroBean {

    private final BancoDeDados banco;
    private Cliente cliente;
    private Animal animal;
    private Medico medico;
    private Validation validar;
    private final Scanner sc = new Scanner(System.in);
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public CadastroBean(BancoDeDados banco) {
        this.banco = banco;
    }

    public CadastroBean(BancoDeDados banco, Medico medico) {
        this.banco = banco;
        this.medico = medico;
    }

    CadastroBean(BancoDeDados banco, Cliente cliente) {
        this.banco = banco;
        this.cliente = cliente;
    }

    CadastroBean(BancoDeDados banco, Animal animal) {
        this.banco = banco;
        this.animal = animal;
    }

    public void cadastrarCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        validar = new Validation(banco, cliente);
        System.out.println("Cadastrar Cliente"
                + "\nPreencha o formulário");
        if (cliente.getNome().isEmpty()) {
            System.out.println("nome:");
            cliente.setNome(sc.nextLine());
        }
        if (cliente.getCpf().isEmpty()) {
            System.out.println("cpf:");
            cliente.setCpf(sc.nextLine());
        }
        System.out.println("telefone:");
        cliente.setTelefone(sc.nextLine());
        System.out.println("Data Nascimento: formato[dd/mm/aaaa]");
        String dataNascimento = sc.nextLine();
        try {
            cliente.setDataNascimento(format.parse(dataNascimento));
        } catch (ParseException ex) {
            Logger.getLogger(CadastroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sexo: [1]-Masculino / [2]-Feminino");
        cliente.setSexo(sc.nextInt());
        sc.nextLine();
        if (!validar.verificarCliente()) {
            banco.getClientes().add(cliente);
            System.out.println("Cadastro Realizado Com sucesso!");
        } else {
            System.out.println("Cadastro não finalizado! Cliente já está cadastrado.");
        }
    }

    public void cadastrarAnimal() {
        if (cliente == null ? true : cliente.getNome().isEmpty()) {
            cliente = new Cliente();

            System.out.println("Digite os dados do cuidador do Animal");
            System.out.println("nome:");
            cliente.setNome(sc.nextLine());
            System.out.println("cpf:");
            cliente.setCpf(sc.nextLine());
            validar = new Validation(banco, animal, cliente);
            if (!validar.verificarCliente()) {
                System.out.println("cuidador do animal, ainda não cadastrado"
                        + "\nDeseja fazer o cadastro? [1]-Sim / [2]-Não");
                int cadastro = sc.nextInt();
                sc.nextLine();
                switch (cadastro) {
                    case 1:
                        sc.nextLine();
                        cadastrarCliente();
                        break;
                    case 2:
                        System.out.println("Cadastro não finalizado");
                        return;
                    default:
                        System.out.println("Opção Inválida");
                }
            }
            cliente = validar.getCliente();
        }
        if (animal == null ? true : animal.getNome() == null ? true : animal.getNome().isEmpty()) {
            animal = new Animal(cliente);
            System.out.println("Cadastrar Animal"
                    + "\nPreencha o formulário");
            System.out.println("nome:");
            animal.setNome(sc.nextLine());
            System.out.println("raca:");
            animal.setRaca(sc.nextLine());
            System.out.println("Sexo: [1]-Macho / [2]-Femia");
            animal.setSexo(sc.nextInt());
            sc.nextLine();
        }else{
            animal.setCuidador(cliente);
            
        }        
        System.out.println("espécie:");
        animal.setEspecie(sc.nextLine());
        System.out.println("idade:");
        animal.setIdade(sc.nextInt());
        sc.nextLine();
        if (!validar.verificarAnimal()) {
            banco.getAnimais().add(animal);
            cliente.getListaAnimal().add(animal);
            System.out.println("Cadastro realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o cadastro, animal já cadastrado");
        }
    }

    public void cadastrarMedico() {
        if (medico == null) {
            medico = new Medico();
        }
        validar = new Validation(banco, medico);
        System.out.println("Cadastrar Medico"
                + "\nPreencha o formulário");
        if (medico.getNome().isEmpty()) {
            System.out.println("nome:");
            medico.setNome(sc.nextLine());
        }
        if (medico.getCpf().isEmpty()) {
            System.out.println("cpf:");
            medico.setCpf(sc.nextLine());
        }
        System.out.println("telefone:");
        medico.setTelefone(sc.nextLine());
        System.out.println("Data Nascimento: formato[dd/mm/aaaa]");
        String dataNascimento = sc.nextLine();
        try {
            medico.setDataNascimento(format.parse(dataNascimento));
        } catch (ParseException ex) {
            Logger.getLogger(CadastroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sexo: [1]-Masculino / [2]-Feminino");
        medico.setSexo(sc.nextInt());
        sc.nextLine();
        System.out.println("especialidade");
        medico.setEspecialidade(sc.nextLine());

        if (!validar.verificarMedico()) {
            banco.getMedicos().add(medico);
            System.out.println("Cadastro Realizado Com sucesso!");
        } else {
            System.out.println("Cadastro não finalizado! Cliente já está cadastrado.");
        }

    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setValidar(Validation validar) {
        this.validar = validar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Medico getMedico() {
        return medico;
    }

}

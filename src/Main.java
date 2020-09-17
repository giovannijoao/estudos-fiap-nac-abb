import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ArvoreCandidatos.ABBCandidatos;
import ArvoreCandidatos.Candidato;

public class Main{
	private static ABBCandidatos treeSI = new ABBCandidatos();
	private static ABBCandidatos treeEP = new ABBCandidatos();
	private static Scanner le = new Scanner(System.in);
	
	public static void showOption(int id, String option) {		
		System.out.print(id + ". " + option + "\n");
	}
	
	public static void main(String[] args) {
		boolean running = true;
		int opt = -1;
		do {
			System.out.println();
			showOption(1, "Inscrever candidato");
			showOption(2, "Abertura de Vaga de Emprego");
			opt = le.nextInt();
			le.nextLine();
			System.out.println();
			if (opt == 1) {
				insert();			
			} else if (opt == 2) {
				aberturaVaga();
			} else {
				running = false;
			}
		} while (running != false);
	}
	
	public static void insert() {
		System.out.println("Em qual curso deseja inserir?");
		showOption(1, "Sistemas de Informação");
		showOption(2, "Engenharia de Produção");		
		int dado = le.nextInt();
		le.nextLine();
		if (dado != 1 && dado != 2) {
			System.out.println("Cancelando abertura.");
		} else {
			Candidato candidato = new Candidato();
			System.out.println("\nInsira o nome do candidato");
			candidato.nome = le.nextLine();
			System.out.println("\nInsira o cpf do candidato");
			candidato.cpf = le.nextLine();
			System.out.println("\nInsira o telefone do candidato");
			candidato.telefone = le.nextLine();
			System.out.println("\nInsira o tempo de experiência do candidato");
			candidato.tempoExperiencia = le.nextInt();
			if (dado == 1) {
				treeSI.root = treeSI.inserir(treeSI.root, candidato);				
			} else treeEP.root = treeEP.inserir(treeEP.root, candidato); 
			System.out.println("Inserido");
		}
	}
	
	public static void aberturaVaga() {
		System.out.println("Em qual curso deseja abrir a vaga?");
		showOption(1, "Sistemas de Informação");
		showOption(2, "Engenharia de Produção");
		showOption(3, "Sair");
		int dado = le.nextInt();
		le.nextLine();		
		if (dado != 1 && dado != 2) {
			System.out.println("Cancelando abertura.");
		} else {
			ArrayList<Candidato> lista = new ArrayList<Candidato>();
			if (dado == 1) treeSI.listaMaiorExperiencia(treeSI.root, lista);
			else treeEP.listaMaiorExperiencia(treeEP.root, lista);
			
			boolean contratado = false;
			
			Iterator<Candidato> listIterator = lista.iterator();
			Candidato candidato;
			while (listIterator.hasNext() && !contratado) {
				candidato = listIterator.next();
				System.out.println("\nCandidato: " + candidato.nome);
				showOption(1, "Aceitou vaga");
				showOption(2, "Recusou vaga");
				int opt = le.nextInt();
				if (opt == 1) {
					if (dado == 1) treeSI.root = treeSI.remover(treeSI.root, candidato);
					else treeEP.root = treeEP.remover(treeEP.root, candidato);
					System.out.println("Contratação concluída.");
					contratado = true;
				}
			}
			
			if (!contratado) {
				System.out.println("Lista para contratação esgotou.");
			}
			
		}
	}
}

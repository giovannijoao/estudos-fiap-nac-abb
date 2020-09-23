import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ArvoreCandidatos.ABBCandidatos;
import ArvoreCandidatos.ABBCandidatos.InformacoesMaiorTempoExperiencia;
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
			showOption(3, "Consulta o maior tempo de experiência dos candidatos");
			showOption(9, "Sair");
			opt = le.nextInt();
			le.nextLine();
			System.out.println();
			if (opt == 1) {
				insert();			
			} else if (opt == 2) {
				aberturaVaga();
			} else if (opt == 3) {
				consultaMaiorTempoDeExperiencia();
			} else {
				running = false;
			}
		} while (running != false);
		finalizar();
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
			System.out.println("Exibindo árvore atual em ordem crescente:");
			exibeOrdemCrescente(dado);
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
	
	public static void consultaMaiorTempoDeExperiencia() {
		System.out.println("Em qual curso deseja consultar?");
		showOption(1, "Sistemas de Informação");
		showOption(2, "Engenharia de Produção");
		showOption(3, "Sair");
		int dado = le.nextInt();
		le.nextLine();		
		if (dado != 1 && dado != 2) {
			System.out.println("Cancelando busca.");
		} else {			
			InformacoesMaiorTempoExperiencia tmpInfo;
			if (dado == 1) tmpInfo = treeSI.consultaMaiorTempoExperiencia(treeSI.root);
			else tmpInfo = treeEP.consultaMaiorTempoExperiencia(treeEP.root);
			if (tmpInfo.comparacoesRealizadas == 0) {
				System.out.println("Sem candidatos na árvore");
			} else {
				System.out.println("Candidato com maior tempo de experiência:");
				System.out.println("Tempo de Experiência: " + tmpInfo.candidato.tempoExperiencia);
				System.out.println();
				System.out.println("Nome: " + tmpInfo.candidato.nome);
				System.out.println("CPF: " + tmpInfo.candidato.cpf);
				System.out.println("Telefone: " + tmpInfo.candidato.telefone);
				System.out.println();
				System.out.println("Comparações necessárias para a busca: " + tmpInfo.comparacoesRealizadas);
			}
		}
	}
	
	public static void finalizar() {
		ArrayList<Candidato> listaSI = new ArrayList<Candidato>();
		treeSI.listaMaiorExperiencia(treeSI.root, listaSI);
		ArrayList<Candidato> listaEP = new ArrayList<Candidato>();
		treeEP.listaMaiorExperiencia(treeEP.root, listaEP);
		System.out.println("Finalizando");
		System.out.println("Candidatos de Sistemas de Informação");		
		Iterator<Candidato> listIterator = listaSI.iterator();
		while (listIterator.hasNext()) {
			Candidato candidato = listIterator.next();
			System.out.println("\nNome:\t" + candidato.nome + "\tTempo:\t" + candidato.tempoExperiencia);		
		}
		System.out.println("\nCandidatos de Engenharia de Produção");
		listIterator = listaEP.iterator();
		while (listIterator.hasNext()) {
			Candidato candidato = listIterator.next();
			System.out.println("\nNome:\t" + candidato.nome + "\tTempo: " + candidato.tempoExperiencia);		
		}
		System.out.println("Tchau!");
	}
	
	public static void exibeOrdemCrescente(int dado) {
		// Caso seja preciso no futuro acrescentar um item no menu
		// System.out.println("Qual curso deseja listar os candidatos em ordem crescente?");
		// showOption(1, "Sistemas de Informação");
		// showOption(2, "Engenharia de Produção");
		// showOption(3, "Sair");
		// int dado = le.nextInt();
		// le.nextLine();		
		if (dado != 1 && dado != 2) {
			System.out.println("Cancelando abertura.");
		} else {
			ArrayList<Candidato> lista = new ArrayList<Candidato>();
			if (dado == 1) treeSI.listaEmOrdem(treeSI.root, lista);
			else treeEP.listaEmOrdem(treeEP.root, lista);
			Iterator<Candidato> listIterator = lista.iterator();
			Candidato candidato;
			while (listIterator.hasNext()) {
				candidato = listIterator.next();
				System.out.println("Candidato: " + candidato.nome + " Tempo: " + candidato.tempoExperiencia);				
			}			
		}
	}
}

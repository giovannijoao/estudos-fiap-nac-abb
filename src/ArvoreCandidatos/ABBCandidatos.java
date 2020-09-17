package ArvoreCandidatos;

import java.util.ArrayList;

public class ABBCandidatos {
	private class Folha {
		Candidato candidato;
		Folha dir, esq;
	}
	
	public Folha root = null;
	
	public Folha inserir(Folha p, Candidato candidato) {
		if (p == null) {
			p = new Folha();
			p.candidato = candidato;			
		} else if (candidato.tempoExperiencia < p.candidato.tempoExperiencia) {
			p.esq = inserir(p.esq, candidato);
		} else {
			p.dir = inserir(p.dir, candidato);
		}
		return p;
	}
	
	public Folha remover(Folha p, Candidato candidato) {
		if (p != null) {
			if (candidato.cpf.equals(p.candidato.cpf)) {
				if (p.esq == null && p.dir == null) {
					return null;
				}
				else if (p.esq == null) {
					return p.dir;
				}
				else if (p.dir == null) {
					return p.esq;
				}
				else {
					Folha aux, ref;
					ref = p.dir;
					aux = p.dir;
					while (aux.esq != null) aux = p.esq;
					aux.esq = p.esq;
					return ref;
				}
			} else {
				if (candidato.tempoExperiencia < p.candidato.tempoExperiencia) {
					p.esq = remover(p.esq, candidato);
				} else {
					p.dir = remover(p.dir, candidato);
				}
			}
		}
		return p;		
	}
	
	public void listaMaiorExperiencia(Folha p, ArrayList<Candidato> lista) {		
		if (p != null) {
			if (p.dir != null)
				listaMaiorExperiencia(p.dir, lista);
			lista.add(p.candidato);
			if (p.esq != null)
				listaMaiorExperiencia(p.esq, lista);			
			System.out.println("Lista " + p.candidato.nome);
		}
	}
}

package br.com.kevin.model.bean;

public class Medico {
	private int id;
	private String nome;
	private int crm;
	private Especialidade especialidade;

	public Medico() {
	}

	public Medico(int id) {
		this.id = id;
	}

	public Medico(int id, String nome, int crm, Especialidade especialidade) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}

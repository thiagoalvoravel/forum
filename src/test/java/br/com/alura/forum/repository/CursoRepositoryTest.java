package br.com.alura.forum.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.forum.model.Curso;

@DataJpaTest
//Evitar que o spring sobreponha as config do banco de dados
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void deveriaCarregarUmCursoAoBuscarPeloNome() {
		String nomeCurso = "HTML 5";
		
		//Criar um curso para banco de dados vazio
		Curso html5 = new Curso();
		html5.setNome(nomeCurso);
		html5.setCategoria("Programação");
		testEntityManager.persist(html5);
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assertions.assertNotNull(curso);
		Assertions.assertEquals(nomeCurso, curso.getNome());
	}
	
	@Test
	public void naoDeveriaCarregarUmCursoComNomeNaoCadastrado() {
		String nomeCurso = "JPA";
		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assertions.assertNull(curso);
	}

}

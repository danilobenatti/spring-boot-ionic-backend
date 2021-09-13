package br.com.cursomc.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Endereco;
import br.com.cursomc.domain.Estado;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

@Configuration
@Profile(value = "dev")
public class LoadDatabase {

	@Bean
	CommandLineRunner runner(CategoriaRepository categoriaRepository,
			ProdutoRepository produtoRepository,
			EstadoRepository estadoRepository,
			CidadeRepository cidadeRepository,
			ClienteRepository clienteRepository,
			EnderecoRepository enderecoRepository) {

		var cat1 = Categoria.builder().nome("Informática").build();
		var cat2 = Categoria.builder().nome("Escritório").build();
		var categorias = new ArrayList<Categoria>();
		categorias.addAll(List.of(cat1, cat2));

		var produtos = new ArrayList<Produto>();
		produtos.addAll(List.of(
				Produto.builder().nome("Computador").preco(2000.00)
						.categorias(List.of(cat1)).build(),
				Produto.builder().nome("Impressora").preco(800.00)
						.categorias(List.of(cat1, cat2)).build(),
				Produto.builder().nome("Mouse").preco(80.00)
						.categorias(List.of(cat1)).build()));

		var est1 = Estado.builder().nome("Minas Gerais").sigla("MG").build();
		var est2 = Estado.builder().nome("São Paulo").sigla("SP").build();
		var estados = new ArrayList<Estado>();
		estados.addAll(List.of(est1, est2));

		var cid1 = Cidade.builder().nome("Uberlândia").estado(est1).build();
		var cid2 = Cidade.builder().nome("São Paulo").estado(est2).build();
		var cid3 = Cidade.builder().nome("Campinas").estado(est2).build();
		var cidades = new ArrayList<Cidade>();
		cidades.addAll(List.of(cid1, cid2, cid3));

		var clie1 = Cliente.builder().nome("Maria Silva")
				.email("maria@gmail.com").cpfOuCnpj("363262161-00")
				.tipo(TipoCliente.PESSOAFISICA.getCodigo())
				.telefones(Set.of("98889696", "22223333")).build();
		var clientes = new ArrayList<Cliente>();
		clientes.addAll(List.of(clie1));

		var end1 = Endereco.builder().logradouro("Rua Flores").numero("300")
				.complemento("apt 203").bairro("Jardim").cep("38302000")
				.cliente(clie1).cidade(cid1).build();
		var end2 = Endereco.builder().logradouro("Avenida Matos").numero("105")
				.complemento("sala 800").bairro("Centro").cep("38777012")
				.cliente(clie1).cidade(cid2).build();
		var enderecos = new ArrayList<Endereco>();
		enderecos.addAll(List.of(end1, end2));

		return args -> {
			categoriaRepository.saveAll(categorias);
			produtoRepository.saveAll(produtos);
			estadoRepository.saveAll(estados);
			cidadeRepository.saveAll(cidades);
			clienteRepository.saveAll(clientes);
			enderecoRepository.saveAll(enderecos);
		};
	}
}

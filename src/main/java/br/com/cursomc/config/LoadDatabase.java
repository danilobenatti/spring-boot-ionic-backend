package br.com.cursomc.config;

import static br.com.cursomc.domain.enums.StatusPagamento.PENDENTE;
import static br.com.cursomc.domain.enums.StatusPagamento.QUITADO;
import static br.com.cursomc.domain.enums.TipoCliente.PESSOAFISICA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import br.com.cursomc.domain.ItemPedido;
import br.com.cursomc.domain.Pagamento;
import br.com.cursomc.domain.PagamentoComBoleto;
import br.com.cursomc.domain.PagamentoComCartao;
import br.com.cursomc.domain.Pedido;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

@Configuration
@Profile(value = { "test", "dev", "prod" })
public class LoadDatabase {

	@Bean
	CommandLineRunner runner(CategoriaRepository categoriaRepository,
			ProdutoRepository produtoRepository,
			EstadoRepository estadoRepository,
			CidadeRepository cidadeRepository,
			ClienteRepository clienteRepository,
			EnderecoRepository enderecoRepository,
			PedidoRepository pedidoRepository,
			PagamentoRepository pagamentoRepository,
			ItemPedidoRepository itemPedidoRepository) throws ParseException {

		var cat1 = Categoria.builder().nome("Informática").build();
		var cat2 = Categoria.builder().nome("Escritório").build();
		var cat3 = Categoria.builder().nome("Cama, mesa e banho").build();
		var cat4 = Categoria.builder().nome("Eletrônicos").build();
		var cat5 = Categoria.builder().nome("Decoração").build();
		var cat6 = Categoria.builder().nome("Jardinagem").build();
		var cat7 = Categoria.builder().nome("Perfumaria").build();
		var categorias = new ArrayList<Categoria>();
		categorias.addAll(List.of(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		var prd1 = Produto.builder().nome("Computador").preco(2000.00)
				.categorias(List.of(cat1, cat4)).build();
		var prd2 = Produto.builder().nome("Impressora").preco(800.00)
				.categorias(List.of(cat1, cat2, cat4)).build();
		var prd3 = Produto.builder().nome("Mouse").preco(80.00)
				.categorias(List.of(cat1, cat4)).build();
		var prd4 = Produto.builder().nome("Mesa de Escritório").preco(300.00)
				.categorias(List.of(cat2)).build();
		var prd5 = Produto.builder().nome("Toalha").preco(50.00)
				.categorias(List.of(cat3)).build();
		var prd6 = Produto.builder().nome("Colcha").preco(200.00)
				.categorias(List.of(cat3)).build();
		var prd7 = Produto.builder().nome("TV True Color").preco(1200.00)
				.categorias(List.of(cat4)).build();
		var prd8 = Produto.builder().nome("Roçadeira").preco(800.00)
				.categorias(List.of(cat5)).build();
		var prd9 = Produto.builder().nome("Abajour").preco(100.00)
				.categorias(List.of(cat6)).build();
		var prd10 = Produto.builder().nome("Luminária Pendente").preco(180.00)
				.categorias(List.of(cat6)).build();
		var prd11 = Produto.builder().nome("Shampoo").preco(90.00)
				.categorias(List.of(cat7)).build();
		var produtos = new ArrayList<Produto>();
		produtos.addAll(List.of(prd1, prd2, prd3, prd4, prd5, prd6, prd7, prd8,
				prd9, prd10, prd11));

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
				.tipo(PESSOAFISICA.getCodigo())
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

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		var ped1 = Pedido.builder()
				.instante(dateFormat.parse("30/09/2017 10:32")).cliente(clie1)
				.enderecoDeEntrega(end1).build();
		var ped2 = Pedido.builder()
				.instante(dateFormat.parse("10/10/2017 19:35")).cliente(clie1)
				.enderecoDeEntrega(end2).build();
		var pedidos = new ArrayList<Pedido>();

		var pgto1 = PagamentoComCartao.builder().pedido(ped1)
				.numeroDeParcelas(6).status(QUITADO.getCodigo()).build();
		var pgto2 = PagamentoComBoleto.builder().pedido(ped2)
				.dataVencimento(dateFormat.parse("20/10/2017 00:00"))
				.dataPagamento(null).status(PENDENTE.getCodigo()).build();
		var pagamentos = new ArrayList<Pagamento>();
		pagamentos.addAll(List.of(pgto1, pgto2));

		var itmPd1 = new ItemPedido(ped1, prd1, 0.00, 1, 2000.00);
		var itmPd2 = new ItemPedido(ped1, prd3, 0.00, 2, 80.00);
		var itmPd3 = new ItemPedido(ped2, prd2, 100.00, 1, 800.00);
		var itensPedido = new ArrayList<ItemPedido>();
		itensPedido.addAll(List.of(itmPd1, itmPd2, itmPd3));

		pedidos.addAll(List.of(ped1, ped2));

		return args -> {
			categoriaRepository.saveAll(categorias);
			produtoRepository.saveAll(produtos);
			estadoRepository.saveAll(estados);
			cidadeRepository.saveAll(cidades);
			clienteRepository.saveAll(clientes);
			enderecoRepository.saveAll(enderecos);
			pagamentoRepository.saveAll(pagamentos);
			itemPedidoRepository.saveAll(itensPedido);
			pedidoRepository.saveAll(pedidos);
		};
	}
}

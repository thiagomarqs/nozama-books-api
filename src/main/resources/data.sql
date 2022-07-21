-- CARGA DE DADOS TESTE

INSERT INTO Editora (Nome, Site) VALUES ('Editora Teste', 'https://www.editorateste.com');

INSERT INTO Formato (Formato) VALUES ('Físico');
INSERT INTO Formato (Formato) VALUES ('Digital');

INSERT INTO Autor (Nome) VALUES ('J.K. Rowling');
INSERT INTO Autor (Nome) VALUES ('C.S. Lewis');
INSERT INTO Autor (Nome) VALUES ('Leandro Karnal');
INSERT INTO Autor (Nome) VALUES ('Clarice Lispector');

INSERT INTO Idioma (Idioma, Codigo) VALUES ('Português', 'pt');

INSERT INTO Categoria (Categoria, Descricao, Ativo) VALUES ('Fantasia', 'Fantasia é um gênero da ficção em que se usa geralmente fenômenos sobrenaturais, mágicos e outros como um elemento primário do enredo, tema ou configuração.', true);

INSERT INTO Livro (
    Titulo,
    Descricao,
    UrlImagem,
    Preco,
    FormatoId,
    Paginas,
    IdiomaId,
    EditoraId,
    DataPublicacao,
    ISBN,
    DimensaoAltura,
    DimensaoLargura,
    DimensaoProfundidade,
    QuantidadeDisponivel,
    DataHoraRegistro,
    Ativo
)
VALUES (
    'Harry Potter e a Pedra Filosofal',
    'Harry Potter e a Pedra Filosofal é o primeiro dos sete livros da série de fantasia Harry Potter, escrita por J. K. Rowling. O livro conta a história de Harry Potter, um órfão criado pelos tios que descobre, em seu décimo primeiro aniversário, que é um bruxo.',
    'https://www.imagem.com',
    29.99,
    1,
    208,
    1,
    1,
    '2017-08-19',
    '1234567891234',
    23,
    16,
    1.8,
    239,
    '2022-07-16',
    TRUE
);

INSERT INTO LivroAutor (AutorId, LivroId) VALUES (1, 1);

INSERT INTO LivroCategoria (CategoriaId, LivroId) VALUES (1, 1);
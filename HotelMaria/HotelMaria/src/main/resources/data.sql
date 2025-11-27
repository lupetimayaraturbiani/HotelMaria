-- Inser��o de dados na tabela usuario
-- Inser��o de administradores (3 contas de teste)
-- Inserção de dados na tabela usuario (idempotente com MERGE)
INSERT INTO Usuario (email, nome, telefone, endereco, senha, is_admin) VALUES
('admin1@hotel.com', 'Admin Um', '11900000001', 'Rua Admin 1, 1', 'admin1pass', TRUE),
('admin2@hotel.com', 'Admin Dois', '11900000002', 'Rua Admin 2, 2', 'admin2pass', TRUE),
('admin3@hotel.com', 'Admin Tres', '11900000003', 'Rua Admin 3, 3', 'admin3pass', TRUE);

-- Usuários normais
INSERT INTO Usuario (email, nome, telefone, endereco, senha, is_admin) VALUES
('maria@gmail.com', 'Maria Silva', '11985632214', 'Rua Caju, 123', 'senha443', FALSE),
('joao@gmail.com', 'João Souza', '11974120058', 'Av dos Limões, 8911', 'joao882', FALSE),
('ana@gmail.com', 'Ana Pereira', '11985641022', 'Av Cruzeiro do Sul, 7611', 'ana123', FALSE),
('carlos@gmail.com', 'Carlos Oliveira', '11999999999', 'Rua das Flores, 456', 'carlos456', FALSE);

-- Inser��o de dados na tabela pet
INSERT INTO Pet (nome, raca, genero, idade, historia, foto_url, adotado) VALUES
('Rex', 'Labrador', 'Macho', 3, 'Cachorro muito brincalh�o e fiel, resgatado de rua.', '/img/joca.png', false),
('Mia', 'Persa', 'F�mea', 2, 'Gata carinhosa, ama dormir no colo e ronronar.', '/img/rosinha.png', false),
('Bolt', 'Bulldog', 'Macho', 4, 'Cachorro forte e protetor, �timo para fam�lias.', '/img/chaveco.png', true),
('Luna', 'Siam�s', 'F�mea', 1, 'Gata curiosa e inteligente, adora explorar.', '/img/tobias.png', false),
('Lilo', 'Viralata', 'Macho', 7, 'Cachorro amoroso e leal', '/img/dalila.png', true),
('Lulu', 'Siam�s', 'F�mea', 12, 'Gata calma e protetora', '/img/lulu.png', false),
('Max', 'Golden Retriever', 'Macho', 5, 'Cachorro amig�vel, perfeito para crian�as.', '/img/elvis.png', true),
('Alfie', 'Poodle', 'Macho', 3, 'Cachorro inteligente e bem-treinado, �timo companheiro.', '/img/alfie.png', false),
('Khloe', 'SRD', 'F�mea', 3, 'Cadela doce e carinhosa, adora brincar no parque.', '/img/khloe.png', false),
('Fiapo', 'SRD', 'Macho', 5, 'C�o aventureiro e cheio de energia para explorar.', '/img/fiapo.png', false),
('Madonna', 'SRD', 'F�mea', 1, 'Filhota muito brincalhona e cheia de vida.', '/img/madona.png', false),
('Raul', 'Vira-lata', 'Macho', 5, 'Cachorro d�cil e protetor da sua fam�lia.', '/img/raul.png', false),
('Gamora', 'Pinscher', 'F�mea', 2, 'Gata de ra�a mix, corajosa e independente.', '/img/gamora.png', false),
('Sushi', 'Shih Tzu', 'Macho', 2, 'Cachorro pequeno, peludo e muito ador�vel.', '/img/sushi.png', false);

-- Inser��o de dados na tabela adocao
-- Nota: Os IDs de pet e usuario s�o auto-gerados, ent�o assumimos os valores baseados na ordem de inser��o (1 para o primeiro, etc.).
-- Em um banco real, voc� pode usar SELECT para obter os IDs, mas aqui usamos valores fixos para simplicidade.
INSERT INTO Adocao (data_solicitacao, pet_id, usuario_id, status) VALUES
(CURRENT_TIMESTAMP, 1, 1, 'PENDENTE'),  -- Rex por Maria
(CURRENT_TIMESTAMP, 2, 2, 'APROVADA'),  -- Mia por Jo�o
(CURRENT_TIMESTAMP, 3, 3, 'RECUSADA'),  -- Bolt por Ana (j� adotado)
(CURRENT_TIMESTAMP, 4, 4, 'PENDENTE'),  -- Luna por Carlos
(CURRENT_TIMESTAMP, 5, 1, 'APROVADA');  -- Max por Maria

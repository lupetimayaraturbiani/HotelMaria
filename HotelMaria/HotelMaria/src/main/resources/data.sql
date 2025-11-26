-- Inserção de dados na tabela usuario
-- Inserção de administradores (3 contas de teste)
INSERT INTO Usuario (nome, email, telefone, endereco, senha, is_admin) VALUES
('Admin Um', 'admin1@hotel.com', '11900000001', 'Rua Admin 1, 1', 'admin1pass', TRUE),
('Admin Dois', 'admin2@hotel.com', '11900000002', 'Rua Admin 2, 2', 'admin2pass', TRUE),
('Admin Tres', 'admin3@hotel.com', '11900000003', 'Rua Admin 3, 3', 'admin3pass', TRUE);

-- Inserção de usuários normais
INSERT INTO Usuario (nome, email, telefone, endereco, senha, is_admin) VALUES
('Maria Silva', 'maria@gmail.com', '11985632214', 'Rua Caju, 123', 'senha443', FALSE),
('João Souza', 'joao@gmail.com', '11974120058', 'Av dos Limões, 8911', 'joao882', FALSE),
('Ana Pereira', 'ana@gmail.com', '11985641022', 'Av Cruzeiro do Sul, 7611', 'ana123', FALSE),
('Carlos Oliveira', 'carlos@gmail.com', '11999999999', 'Rua das Flores, 456', 'carlos456', FALSE);

-- Inserção de dados na tabela pet
INSERT INTO Pet (nome, raca, genero, idade, historia, foto_url, adotado) VALUES
('Rex', 'Labrador', 'Macho', 3, 'Cachorro muito brincalhão e fiel, resgatado de rua.', '/img/joca.png', false),
('Mia', 'Persa', 'Fêmea', 2, 'Gata carinhosa, ama dormir no colo e ronronar.', '/img/rosinha.png', false),
('Bolt', 'Bulldog', 'Macho', 4, 'Cachorro forte e protetor, ótimo para famílias.', '/img/chaveco.png', true),
('Luna', 'Siamês', 'Fêmea', 1, 'Gata curiosa e inteligente, adora explorar.', '/img/tobias.png', false),
('Lilo', 'Viralata', 'Macho', 7, 'Cachorro amoroso e leal', '/img/dalila.png', true),
('Lulu', 'Siamês', 'Fêmea', 12, 'Gata calma e protetora', '/img/lulu.png', false),
('Max', 'Golden Retriever', 'Macho', 5, 'Cachorro amigável, perfeito para crianças.', '/img/elvis.png', true),
('Alfie', 'Poodle', 'Macho', 3, 'Cachorro inteligente e bem-treinado, ótimo companheiro.', '/img/alfie.png', false),
('Khloe', 'SRD', 'Fêmea', 3, 'Cadela doce e carinhosa, adora brincar no parque.', '/img/khloe.png', false),
('Fiapo', 'SRD', 'Macho', 5, 'Cão aventureiro e cheio de energia para explorar.', '/img/fiapo.png', false),
('Madonna', 'SRD', 'Fêmea', 1, 'Filhota muito brincalhona e cheia de vida.', '/img/madona.png', false),
('Raul', 'Vira-lata', 'Macho', 5, 'Cachorro dócil e protetor da sua família.', '/img/raul.png', false),
('Gamora', 'Pinscher', 'Fêmea', 2, 'Gata de raça mix, corajosa e independente.', '/img/gamora.png', false),
('Sushi', 'Shih Tzu', 'Macho', 2, 'Cachorro pequeno, peludo e muito adorável.', '/img/sushi.png', false);

-- Inserção de dados na tabela adocao
-- Nota: Os IDs de pet e usuario são auto-gerados, então assumimos os valores baseados na ordem de inserção (1 para o primeiro, etc.).
-- Em um banco real, você pode usar SELECT para obter os IDs, mas aqui usamos valores fixos para simplicidade.
INSERT INTO Adocao (data_solicitacao, pet_id, usuario_id, status) VALUES
(CURRENT_TIMESTAMP, 1, 1, 'PENDENTE'),  -- Rex por Maria
(CURRENT_TIMESTAMP, 2, 2, 'APROVADA'),  -- Mia por João
(CURRENT_TIMESTAMP, 3, 3, 'RECUSADA'),  -- Bolt por Ana (já adotado)
(CURRENT_TIMESTAMP, 4, 4, 'PENDENTE'),  -- Luna por Carlos
(CURRENT_TIMESTAMP, 5, 1, 'APROVADA');  -- Max por Maria

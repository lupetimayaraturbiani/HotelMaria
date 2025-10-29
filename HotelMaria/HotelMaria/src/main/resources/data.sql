-- Inserção de dados na tabela usuario
INSERT INTO Usuario (nome, email, telefone, endereco, senha) VALUES
('Maria Silva', 'maria@gmail.com', '11985632214', 'Rua Caju, 123', 'senha443'),
('João Souza', 'joao@gmail.com', '11974120058', 'Av dos Limões, 8911', 'joao882'),
('Ana Pereira', 'ana@gmail.com', '11985641022', 'Av Cruzeiro do Sul, 7611', 'ana123'),
('Carlos Oliveira', 'carlos@gmail.com', '11999999999', 'Rua das Flores, 456', 'carlos456');

-- Inserção de dados na tabela pet
INSERT INTO Pet (nome, raca, genero, idade, historia, foto_url, adotado) VALUES
('Rex', 'Labrador', 'Macho', 3, 'Cachorro muito brincalhão e fiel, resgatado de rua.', 'https://exemplo.com/rex.jpg', false),
('Mia', 'Persa', 'Fêmea', 2, 'Gata carinhosa, ama dormir no colo e ronronar.', 'https://exemplo.com/mia.jpg', false),
('Bolt', 'Bulldog', 2, 4, 'Cachorro forte e protetor, ótimo para famílias.', 'https://exemplo.com/bolt.jpg', true),
('Luna', 'Siamês', 'Fêmea', 1, 'Gata curiosa e inteligente, adora explorar.', 'https://exemplo.com/luna.jpg', false),
('Max', 'Golden Retriever', 'Macho', 5, 'Cachorro amigável, perfeito para crianças.', 'https://exemplo.com/max.jpg', true);

-- Inserção de dados na tabela adocao
-- Nota: Os IDs de pet e usuario são auto-gerados, então assumimos os valores baseados na ordem de inserção (1 para o primeiro, etc.).
-- Em um banco real, você pode usar SELECT para obter os IDs, mas aqui usamos valores fixos para simplicidade.
INSERT INTO Adocao (data_solicitacao, pet_id, usuario_id, status) VALUES
(CURRENT_TIMESTAMP, 1, 1, 'PENDENTE'),  -- Rex por Maria
(CURRENT_TIMESTAMP, 2, 2, 'APROVADA'),  -- Mia por João
(CURRENT_TIMESTAMP, 3, 3, 'RECUSADA'),  -- Bolt por Ana (já adotado)
(CURRENT_TIMESTAMP, 4, 4, 'PENDENTE'),  -- Luna por Carlos
(CURRENT_TIMESTAMP, 5, 1, 'APROVADA');  -- Max por Maria
# Factory Method Pattern - Hotel Maria

## 📋 Resumo da Implementação

Implementado o padrão de design **Factory Method** no sistema de login do Hotel Maria para criar diferentes tipos de usuários (Administrador e Normal) de forma extensível e segura.

---

## 🏗️ Arquitetura

### 1. **Classe Abstrata: `UsuarioAbstrato.java`**
   - Define o contrato para todos os tipos de usuários
   - Métodos abstratos: `getTipo()` e `getPaginaPrincipal()`
   - Propriedades comuns a todos os usuários

### 2. **Implementações Concretas**

   **`UsuarioAdmin.java`**
   - Tipo: `"ADMIN"`
   - Página principal: `/index-adm`
   - Métodos específicos:
     - `gerarRelatorio()`
     - `gerenciarUsuarios()`
     - `deletarPet(petId)`

   **`UsuarioNormal.java`**
   - Tipo: `"NORMAL"`
   - Página principal: `/home`
   - Métodos específicos:
     - `adotarPet(petId)`
     - `visualizarPerfil()`
     - `consultarAnimaisDisponiveis()`

### 3. **Factory: `UsuarioFactory.java`**
```java
public static UsuarioAbstrato criarUsuario(Usuario usuario) {
    if (usuario.isAdmin()) {
        return new UsuarioAdmin(...);
    } else {
        return new UsuarioNormal(...);
    }
}
```

---

## 🔄 Fluxo de Login com Factory Method

```
1. Usuário preenche email/senha
   ↓
2. Frontend envia POST /login
   ↓
3. LoginController → AuthService.autenticarComFactory(email, senha)
   ↓
4. AuthService valida credenciais no banco
   ↓
5. Se válido → UsuarioFactory.criarUsuario(usuario)
   ↓
6. Factory retorna UsuarioAdmin ou UsuarioNormal
   ↓
7. LoginController retorna JSON com tipo + página
   ↓
8. Frontend redireciona para /home ou /index-adm
```

---

## 📦 Classes Novas

| Arquivo | Tipo | Descrição |
|---------|------|-----------|
| `UsuarioAbstrato.java` | Abstract | Classe base |
| `UsuarioAdmin.java` | Class | Usuário administrador |
| `UsuarioNormal.java` | Class | Usuário comum |
| `UsuarioFactory.java` | Factory | Factory Method |
| `AutenticacaoDTO.java` | DTO | Response JSON |

---

## 🗄️ Alterações no Banco de Dados

Adicionado campo `isAdmin` na tabela `usuario`:

```java
@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
private boolean isAdmin = false;
```

---

## 🔐 Fluxo de Autenticação

### Backend (`LoginController.java`)
```java
@PostMapping("/login")
public ResponseEntity<AutenticacaoDTO> autenticar(
    @RequestParam String email,
    @RequestParam String senha,
    HttpSession session) {
    
    AutenticacaoDTO autenticacao = authService.autenticarComFactory(email, senha);
    
    if (autenticacao.isSucesso()) {
        session.setAttribute("usuarioLogado", usuarioEntity);
        return ResponseEntity.ok(autenticacao);
    }
    return ResponseEntity.status(401).body(autenticacao);
}
```

### Frontend (`login.js`)
```javascript
fetch("/login", { method: "POST", body: formData })
    .then(response => response.json())
    .then(data => {
        if (data.sucesso) {
            // Redireciona para página correta do tipo de usuário
            window.location.href = data.paginaPrincipal;
        }
    });
```

---

## ✨ Benefícios do Factory Method

1. **Extensibilidade**: Fácil adicionar novos tipos de usuários
2. **Separação de responsabilidades**: Cada tipo tem suas próprias regras
3. **Segurança**: Tipo de usuário determinado pelo banco (não pelo frontend)
4. **Manutenibilidade**: Lógica de criação centralizada
5. **Testabilidade**: Fácil mockar diferentes tipos de usuários

---

## 📝 Exemplo de Uso

### Registrar usuário normal:
```
Email: user@example.com
Senha: senha123
isAdmin: false (padrão)
```

### Registrar usuário admin:
```
Email: admin@example.com
Senha: admin123
isAdmin: true (necessário atualizar no banco manualmente ou via admin)
```

### Login com usuário normal:
```
Response: {
  "sucesso": true,
  "tipoUsuario": "NORMAL",
  "paginaPrincipal": "/home"
}
→ Redireciona para /home
```

### Login com usuário admin:
```
Response: {
  "sucesso": true,
  "tipoUsuario": "ADMIN",
  "paginaPrincipal": "/index-adm"
}
→ Redireciona para /index-adm
```

---

## 🚀 Como Testar

1. **Inicie a aplicação:**
   ```bash
   cd "c:\Users\mayar\Documents\HotelMaria\HotelMaria\HotelMaria"
   .\mvnw.cmd spring-boot:run
   ```

2. **Acesse http://localhost:8080/h2-console**
   - Driver Class: `org.h2.Driver`
   - JDBC URL: `jdbc:h2:mem:hoteldamaria`

3. **Execute SQL para adicionar usuários:**
   ```sql
   -- Usuário normal
   INSERT INTO USUARIO (nome, email, senha, telefone, endereco, is_admin)
   VALUES ('João Silva', 'joao@example.com', 'senhaHash', '11999999999', 'Rua A', FALSE);
   
   -- Usuário admin
   INSERT INTO USUARIO (nome, email, senha, telefone, endereco, is_admin)
   VALUES ('Admin Maria', 'admin@example.com', 'senhaHash', '11988888888', 'Rua B', TRUE);
   ```

4. **Teste o login em http://localhost:8080/login**
   - Admin: redireciona para `/index-adm`
   - Normal: redireciona para `/home`

---

## 📚 Referências

- **Padrão Factory Method**: Criational Design Pattern
- **Livro**: Design Patterns: Elements of Reusable Object-Oriented Software (Gang of Four)
- **Benefício Principal**: Delegar a criação de objetos para reduzir acoplamento

---

**Status**: ✅ Implementação Completa
**Testado**: ✅ Compilação e Build bem-sucedidos

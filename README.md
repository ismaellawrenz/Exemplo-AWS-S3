# Projeto Exemplo: Utilização do Amazon S3 com Spring Boot

## Descrição
Este é um projeto de exemplo que demonstra como integrar o serviço de armazenamento de objetos Amazon S3 em um aplicativo Spring Boot.

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Amazon S3 SDK

## Funcionalidades Demonstradas
- Upload de arquivos para o Amazon S3
- Download de arquivos do Amazon S3
- Listagem de arquivos armazenados no Amazon S3
- Exclusao de arquivo

## Configuração
Antes de executar o aplicativo, configure suas credenciais do Amazon S3 nas variaveis de ambiente:

- **s3accesskey**: Sua chave de acesso da AWS.
- **s3secretkey**: Sua chave secreta da AWS.
- **s3bucket**: O nome do bucket do Amazon S3 que será utilizado pelo aplicativo.


## Execução do Aplicativo
Para executar o aplicativo, use o seguinte comando Maven:

```bash
mvn spring-boot:run
```
O aplicativo estará acessível em http://localhost:8080.

## Uso
O aplicativo fornece endpoints simples para interagir com o Amazon S3:

### Upload de arquivo

```bash
curl -X POST \
  -H "Content-Type: multipart/form-data" \  
  -F "file=@caminho/do/seu/arquivo.txt" \
  http://localhost:8080
```
### Download de arquivo:

```bash
curl -X GET \
  http://localhost:8080/arquivo.txt
```

### Listagem de arquivos:

```bash
curl -X GET \
  http://localhost:8080/all
```

### Exclusao de arquivo:

```bash
curl -X DELETE \
  http://localhost:8080/arquivo.txt
```

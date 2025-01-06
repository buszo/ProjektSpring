## Rejestracja uzytkownika

```bash
http://localhost:8080/register
```

```bash
{
    "firstName": "Jan",
    "lastName": "Kowalski",
    "username": "jkowal",
    "password": "1234",
    "role": "USER"
}
```
## Logowanie


```bash
http://localhost:8080/login
```
Wygeneruje sie JWT Token

## GET Logged user

```python
http://localhost:8080/self
```

## All users

```python
http://localhost:8080/admin_only
```

## ToDo

```python
http://localhost:8080/todo/create
```


```python
{
  "title": "Moja lista zadan"
}
```

### Wyswietlanie swoich list
```python
{
http://localhost:8080/todo/all
}
```

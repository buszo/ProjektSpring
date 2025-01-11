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

### Wyswietlanie wszystkich swoich list + tasków
```python
{
http://localhost:8080/todo/all
}
```

### Wyswietlenie konkretnej listy z taskami


```python
{
http://localhost:8080/todo/{id}
}
```

### Edytowanie listy 
```python
{
http://localhost:8080/todo/{id}/edit
}
```

```bash
{
    "description": "Nowa nazwa",
}
```

### Usuwanie listy i wszystkich tasków
```python
{
http://localhost:8080/todo/{id}/delete
}
```

## Task

### Tworzenie taska
```python
{
http://localhost:8080/task/todo/{id listy todo}
}
```

```bash
{
    "description": "Nowy task",
}
```

### Wyswietlanie taska

```python
{
http://localhost:8080/task/1
}
```
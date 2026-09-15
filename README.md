# Coffee_Online_Order

COMP713 Assessment 2 (Option A) - a simple REST API for ordering coffee at a cafe.

Customers can look at the menu and place an order. Staff can check an order and update its status
(PENDING -> PREPARING -> READY -> COMPLETED). Data is saved in a MySQL database.

## What you need

- Java 21
- Maven (or just use mvnw.cmd, it's included so you don't need Maven installed)
- A browser
- curl.exe to test the API (comes with Windows)

## How to run it

1. Clone the repo
2. Set the DB password (not stored in the repo, need to set it yourself):

```powershell
$env:DB_PASSWORD = "db password"
```

3. Run it:

```powershell
.\mvnw.cmd spring-boot:run
```

App runs on http://localhost:8080. Tables get created automatically on first run.

## Using it

- Customer page: http://localhost:8080/menu.html
- Staff page: http://localhost:8080/orders.html

## Testing with curl

Add a coffee to the menu:
```powershell
curl.exe -i -X POST http://localhost:8080/api/v1/menu -H "Content-Type: application/json" --data-binary "@requests/flat-white.json"
```

Place an order:
```powershell
curl.exe -i -X POST http://localhost:8080/api/v1/orders -H "Content-Type: application/json" --data-binary "@requests/place-order.json"
```

Check an order:
```powershell
curl.exe -i http://localhost:8080/api/v1/orders/1
```

Update status:
```powershell
curl.exe -i -X PATCH http://localhost:8080/api/v1/orders/1/status -H "Content-Type: application/json" --data-binary "@requests/update-status.json"
```

Sample JSON files are in the `requests/` folder.

## Endpoints

| Method | Path | What it does |
|---|---|---|
| GET | /api/v1/menu | Show all menu items |
| POST | /api/v1/menu | Add a menu item |
| POST | /api/v1/orders | Place an order |
| GET | /api/v1/orders/{id} | Get one order |
| PATCH | /api/v1/orders/{id}/status | Update order status |

## Config

DB connection stuff is in `application.properties`. Password comes from the `DB_PASSWORD` env variable so it's
not sitting in the repo. `ddl-auto=update` means Hibernate makes the tables for you automatically.

## Known issues / things not done

- Can only order one coffee at a time, no multi-item orders
- No login, anyone can act as staff and change order status
- Can't delete/cancel an order
- No way to list all orders at once, have to check by ID

## What I'd add with more time

- Multiple items per order
- Basic login for staff
- A page that lists every order instead of checking one by one
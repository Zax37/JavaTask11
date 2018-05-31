# Java w zastosowaniach produkcyjnych

## Zadanie 10 - Generator transakcji - Heroku

"Generator transakcji" z poprzednich zajęć należalo udostępnić na heroku oraz:

- Dostarczyć endpoiont http, który będzie zwracał transakcje w formie tablicy jsonowej, lub listy XMLi, lub listy yamlowej w zależnosci od wartości nagłówka http “Accept” (application/json, application/xml, application/yml).

- Endpoint: http(s)://{server-app}/transactions

- Parametry URL: 
customersId=1:2
dateRange="2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100"
itemsCount=1:2
itemsQuantity=1:2
eventsCount=10

- Items należy pobierać z endpointa: https://csv-items-generator.herokuapp.com/items
Dostasrcza on 3 formaty: xml/json/csv, w zależności od wartości nagłówka “Accept”

- Output - już nie plik, ale odpowiedź na request.

- Generator powinien być budowany, testowany, i automatycznie wdrażany po pushu zmian na repozytorium (CI/CD). Forma deploymentu: obraz dockerowy.

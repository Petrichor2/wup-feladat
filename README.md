# Fekete Dániel W.UP feladat

A projekt két microservice-ből és egy mysql adatbázisból áll, melyek indítása `docker-compose up` paranccsal lebuildeli és elindítja.

Sajnos a swagger-ui nem működik ezen docker környezetben.

Az adatbázis generált adatokkal kerül feltöltésre `library-impl` alkalmazás által Liquibase segítségével.

Időhiány miatt a statisztikák nem kerültek perzisztálásra, ezáltal elő se állnak adatfeltöltés esetén, csak REST-ről hívható.

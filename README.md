# soccer-bets


# MySQL

Una vez instalado Docker lo que resta es instalar el contenedor de MySQL para poder empezar a configurar una base de datos con el CLI. (Aquí tienen la documentación oficial aquí). Les explico brevemente los pasos que yo realicé:

Lo primero es descargar la imagen en tu Docker

`docker pull mysql`

El siguiente comando construye un contenedor llamado ‘mysqldb’ con la versión más reciente (mysql:latest) y le asigna al usuario root del sistema, la clave ‘password’

`docker run --name mysqldb -e MYSQL_ROOT_PASSWORD=password -d mysql:latest`

Luego puedes acceder a la línea de comando del contenedor de MySQL usando.

`docker exec -it mysqldb bash`

Finalmente, se ingresa base de datos por medio del siguiente comando

`mysql -u root -ppassword`
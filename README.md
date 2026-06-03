# EventMaster
Esta aplicación permite gestionar un catálogo de eventos (conciertos, conferencias, talleres). La aplicación contará con una vista inicial organizada por categorías, brindando también la opción de crear nuevas categorías personalizadas a través de un formulario.

La aplicación incluye pantallas clave como el formulario para agregar eventos y la vista de detalles, haciendo énfasis en el uso de formularios, navegación, componentes personalizados y el manejo adecuado de estados.

## Funcionalidades
* **Pantalla de Inicio:** Es la primera pantalla que se muestra al entrar a la aplicación, en ella se pueden visualizar todos los eventos agrupados por categoría en tarjetas (cada tarjeta muestra la imagen del evento si es que tiene, el título, descripción si es que tiene, categoría y fecha), y se muestran también los botones para agregar un nuevo evento o categoría. Para el caso de agregar evento, también en cada categoría se muestra un botón para agregar un evento directo a la categoría correspondiente.
* **Detalle del Evento:** Al entrar al detalle de un evento. se puede visualizar el título, categoría, fecha, ubicación, descripción (si tiene) y la imagen (o un texto por defecto si no tiene).
* **Agregar evento:** Corresponde al formulario para agregar evento, en el cual se solicita indicar el título del evento, una descripción, fecha (formato DD/MM/AAAA), ubicación, nombre de la imagen en *drawable* (revisar [consideraciones importantes](https://github.com/AlvaroMolinaCL/Tarea03_DesarrolloAppsAndroid#consideraciones-importantes)), y la categoría. El formulario contiene validación de formato, extensión y mensajes de error.
* **Agregar categoría:** Corresponde al formulario para agregar categoría, en el cual se solicita indicar el nombre de la categoría y una descripción (opcional).

## Instrucciones de instalación
### Instalación de la API
1. Clonar este repositorio (de la aplicación):
```
git clone https://github.com/AlvaroMolinaCL/Tarea04_APP_DesarrolloAppsAndroid.git
```
2. Clonar el repositorio de la API:
```
git clone https://github.com/AlvaroMolinaCL/Tarea04_API_DesarrolloAppsAndroid.git
```
3. En el directorio donde se clonó el repositorio de la API, instalar las dependencias de PHP con Composer:
```
composer install
```
4. Configurar el archivo de entorno (`.env`) y generar el `APP_KEY`:
```
cp .env.example .env
php artisan key:generate
```
5. Verificar en el archivo `.env` que las credenciales de acceso a la base de datos coincidan con su configuración local. Luego, ejecute las migraciones para crear las tablas necesarias junto con datos de ejemplo:
```
php artisan migrate
php artisan db:seed
```
6. Encender el servidor local:
```
php artisan serve
```
7. Si todo salió bien, debería poder cargar y visualizar los endpoints (por ejemplo: http://127.0.0.1:8000/api/events, http://127.0.0.1:8000/api/categories). También debería poder realizar pruebas de peticiones a la API a través de [Scribe](https://scribe.knuckles.wtf) desde http://127.0.0.1:8000/docs.

> **Importante:** El servidor local debe mantenerse encendido, de lo contrario, al entrar a la aplicación no se cargarán datos ni se podrán crear categorías o eventos.

### Instalación de la aplicación
1. Con Android Studio, debe abrir el directorio donde clonó el repositorio de la aplicación.
2. Dentro de Android Studio y con el servidor local encendido, ejecutar un **Build** y luego ejecutar la aplicación (**Run**) en el emulador.
3. Si todo salió bien, la aplicación debería cargar categorías y eventos de ejemplo, y también debería permitir crear nuevas categorías y eventos.

> **Importante:** Si desea probar la aplicación en un dispositivo Android físico, debe conectar el dispositivo al PC mediante depuración USB, y cambiar la `BASE_URL` de `ApiConfig.kt` por la IP de su red local (tanto el PC como el servidor local deben estar conectados a la misma red). Además, en el caso de Windows, se debe verificar que el puerto 8000 no esté siendo bloqueado por el firewall.

## Consideraciones importantes
* Para efectos demostrativos, al entrar a la aplicación por primera vez, se mostrarán inicialmente 3 eventos vinculados a 3 categorías distintas. Dentro de la aplicación es posible agregar tantas categorías o eventos como se desee, los cuales se mantendrán incluso aunque la aplicación se cierre.
* Para agregar una imagen al crear un evento, primero se debe agregar la imagen deseada al directorio *drawable*, y luego en el formulario de la aplicación se debe ingresar el nombre de la imagen sin la extensión. Por ejemplo, si se agrega al directorio *drawable* una imagen llamada **banner_rec_2026.png**, en el formulario como nombre de imagen se debe escribir **banner_rec_2026**.
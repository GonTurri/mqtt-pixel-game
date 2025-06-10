# Versiones
- Java 17 y Springboot 3.4.4
- React 19
- lo corrí con node 23.4.0

# Como instalar y configurar el rabbitMQ
Yo lo tengo con homebrew pero entiendo que con docker también se puede

```brew install rabbitmq```

Para arrancarlo en background ejecutar

```brew services start rabbitmq```

Para frenarlo 

```brew services stop rabbitmq```

## Activar el plugin de stomp
Para poder usar rabbitmq en los webSockets hay que tener este plugin activado

```rabbitmq-plugins enable rabbitmq_stomp```

Para ver si efectivamente funciono se puede correr

```lsof -i :61613```

Y ver si está el comando **bean.smp** escuchando en este puerto

# Levantar el backend
para levantarlo yo hago
```mvn clean install```  seguido de ```mvn clean package```  y se me genera el jar en el directorio target

Después para correrlo hago
```java -jar ./target/mqtt-pixel-game-0.0.1-SNAPSHOT.jar```
Cuando lo corres en algún momento en la consola se imprimen los ids para que los puedas copiar, por ejemplo:

```
Id del canvas equipo 1: 4be21aa2-0bef-41e3-ab15-2888ff835702
Id del canvas equipo 2: 36dd546c-fe0a-4abf-8885-9cf47304c6fe
```

# Levantar el frontend
Parado en el directorio raiz:

```cd pixel-canvas-viewer/```

```npm i```

```npm start```

Y con eso ya debería estar andando e interceptando los mensajes que le mandes al broker: **broker.eqmx.io** al puerto 1883

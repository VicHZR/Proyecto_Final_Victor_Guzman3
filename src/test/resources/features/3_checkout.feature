# language: es
@Checkout
Característica: Flujo completo de compra en Juice Shop

  Como usuario registrado
  Quiero realizar dos pedidos completos
  Para validar el proceso de compra y revisar mi historial

  Antecedentes:
    Dado el usuario abre la aplicación
    Y el usuario navega a la página de login
    Y el usuario ingresa el correo "nuevo.usuario@example.com" y la contraseña "Test1234"

  @setup
  Escenario: Preparar datos: 2 direcciones y 2 métodos de pago
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario asegura que existen dos direcciones de envío
    Y el usuario asegura que existen dos métodos de pago
    Entonces el sistema deja preparado el entorno de compra

  @pedido1
  Escenario: Pedido 1 con productos específicos por búsqueda (manzana, banana, camiseta)
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario agrega los productos "Apple Juice", "Banana Juice" y "T-Shirt" al carrito
    Y el usuario accede al carrito
    Y el usuario realiza el proceso de checkout usando la segunda dirección y el primer método de pago
    Entonces el sistema debe mostrar una confirmación de pedido

  @pedido2
  Escenario: Pedido 2 con productos del catálogo (Fruit Press y Orange)
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario agrega los productos "Fruit Press" y "Orange Juice" al carrito
    Y el usuario accede al carrito
    Y el usuario realiza el proceso de checkout usando la segunda dirección y el primer método de pago
    Entonces el sistema debe mostrar una confirmación de pedido

  @historial
  Escenario: Ver historial de órdenes
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede al historial de órdenes
    Entonces el sistema debe mostrar las órdenes realizadas
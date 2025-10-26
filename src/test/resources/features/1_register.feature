# language: es
@Register
Característica: Nuevo registro
  Como visitante del sitio web de comercio electrónico
  Quiero crear una cuenta para comprar productos

  @reg01
  Escenario: Registro exitoso con información válida
    Dado el usuario abre la aplicación para registrarse
    Cuando el usuario navega a la página de registro
    Y el usuario ingresa el correo "nuevo.usuario@example.com", la contraseña "Test1234" y la respuesta "Lima"
    Entonces el usuario debería ser registrado exitosamente
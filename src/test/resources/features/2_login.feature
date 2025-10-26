# language: es
@Login
Característica: Verificar Login
  Como usuario del sitio web de comercio electrónico
  Quiero iniciar sesión y gestionar mi cuenta

  @acc01
  Escenario: Iniciar sesión con credenciales correctas
    Dado el usuario abre la aplicación
    Cuando el usuario navega a la página de login
    Y el usuario ingresa el correo "nuevo.usuario@example.com" y la contraseña "Test1234"
    Entonces el usuario debería iniciar sesión exitosamente
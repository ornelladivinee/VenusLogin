# 🌸 Venus: Aplicación de Salud Femenina

Venus es una aplicación móvil enfocada en la gestión y apoyo de la salud femenina. Permite a las usuarias registrar información relevante, gestionar reservas de atención y visualizar su historial, ofreciendo una experiencia simple, segura e intuitiva.

---

## Integrantes del equipo

- Ornella Mesina  
- [Nombre Integrante 2]  
- [Nombre Integrante 3]  

---

## Funcionalidades principales

- Registro y gestión de usuarias.
- Reserva de citas médicas.
- Visualización de historial de reservas.
- Validación de datos en formularios (correo, teléfono, etc.).
- Navegación fluida entre pantallas mediante `NavController`.
- Interfaz amigable y consistente.
- Pruebas unitarias para funcionalidades críticas.

---

## Endpoints utilizados

### Endpoints propios (Microservicios)
- `POST /login` – Autenticación de usuarias.
- `POST /reservas` – Creación de reservas.
- `GET /reservas` – Obtención de historial de reservas.
- `PUT /reservas/{id}` – Actualización de reservas.
- `DELETE /reservas/{id}` – Eliminación de reservas.

### Endpoints externos
- API REST simulada / Mock API para pruebas de integración.
- Servicios de soporte para validaciones y pruebas.

---

## Instrucciones para ejecutar el proyecto

### App móvil (Android)
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/venus-app.git

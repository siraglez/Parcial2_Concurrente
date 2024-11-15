# Parcial 2 Concurrente

Link al repositorio: https://github.com/siraglez/Parcial2_concurrente.git

Participantes: Sira González-Madroño y Sonia Tejero Recio

Este proyecto es una aplicación diseñada para simular un tablero de Galton y gestionar componentes de manera concurrente utilizando RabbitMQ como sistema de mensajería y WebSocket para la visualización en tiempo real.

## Funcionalidades Principales
### 1. Producción de Componentes
- **Tipos de componentes:** Se generan dos tipos: *Bolas* y *Clavos*.
- **Fabricación concurrente:** Se utiliza RabbitMQ para gestionar colas separadas para cada tipo de componente.

### 2. Comsumo y Ensamblaje
- Los componentes son consumidos de las colas y ensamblados para crear una máquina virtual.
- Se genera una notificación en tiempo real cuando una nueva máquina es ensamblada.

### 3. Simulación del Tablero de Galton
- **Visualización:** Se utiliza un canvas HTML5 para simular el movimiento de bolas a través del tablero.
- **Interacción en tiempo real:** A medida que se ensamblan máquinas, las bolas se añaden al tablero simulando caídas por el sistema de clavos.

### 4. WebSocket para Notificaciones
- Comunicación bidireccional entre el backend y el frontend para actualizar la simulación en tiempo real.

### 5. Configuración Flexible
- Configuración de colas de RabbitMQ mediante anotaciones de Spring Boot.
- Scheduler para la generación automática de componentes a intervalos regulares.

## Tecnologías Utilizadas
### Backend:
- Spring Boot.
- RabbitMQ para la mensajería.
- WebSocket para notificaciones en tiempo real.
  
### Frontend:
- HTML5, CSS3 y JavaScript para la visualización del tablero.

### Otras:
- Patrones de diseño como Factory para la creación de componentes.
- Gestión concurrente y programación reactiva.

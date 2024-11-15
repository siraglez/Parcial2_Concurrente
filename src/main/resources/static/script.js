const canvas = document.getElementById('galtonBoard');
const ctx = canvas.getContext('2d');
const ballRadius = 5;
const pegRadius = 3;
const rows = 10;
const cols = 11;
const pegs = [];
const balls = [];

// Conexión WebSocket para recibir notificaciones del backend
const socket = new WebSocket(`ws://${window.location.host}/galtonBoard`);

// Escuchar actualizaciones del backend y agregar una bola al tablero
socket.onmessage = function () {
    console.log("Mensaje recibido del servidor, agregando bola.");
    addBall();
};

socket.onerror = function (error) {
    console.error(`WebSocket error: ${error}`);
};

// Configurar los clavos del tablero
function setupPegs() {
    const offsetX = 50;
    const offsetY = 50;
    const spacingX = 50;
    const spacingY = 50;
    for (let row = 0; row < rows; row++) {
        const y = offsetY + row * spacingY;
        for (let col = 0; col < cols; col++) {
            const x = offsetX + col * spacingX + (row % 2 === 0 ? spacingX / 2 : 0);
            pegs.push({ x, y });
        }
    }
}

// Agregar una nueva bola en la parte superior del tablero
function addBall() {
    balls.push({ x: canvas.width / 2, y: 0, vy: 2 + Math.random() * 2 });
}

// Dibujar los clavos
function drawPegs() {
    ctx.fillStyle = 'gray';
    for (const peg of pegs) {
        ctx.beginPath();
        ctx.arc(peg.x, peg.y, pegRadius, 0, Math.PI * 2);
        ctx.fill();
    }
}

// Dibujar las bolas
function drawBalls() {
    ctx.fillStyle = 'red';
    for (const ball of balls) {
        ctx.beginPath();
        ctx.arc(ball.x, ball.y, ballRadius, 0, Math.PI * 2);
        ctx.fill();
    }
}

// Actualizar las posiciones de las bolas y simular interacciones con los clavos
function updateBalls() {
    for (const ball of balls) {
        if (ball.vy > 0) {
            ball.y += ball.vy; // Mover la bola hacia abajo

            // Interacción con los clavos
            for (const peg of pegs) {
                const dx = ball.x - peg.x;
                const dy = ball.y - peg.y;
                const distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < ballRadius + pegRadius) {
                    ball.vy *= 0.9; // Reducir velocidad al chocar
                    ball.x += Math.random() > 0.5 ? -2 : 2; // Desviar la bola a la izquierda o derecha
                    break;
                }
            }

            // Si llega al fondo del tablero, detener la bola
            if (ball.y > canvas.height - ballRadius) {
                ball.y = canvas.height - ballRadius;
                ball.vy = 0; // Detener la bola
            }
        }
    }
}

// Animar el tablero
function animate() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawPegs();
    drawBalls();
    updateBalls();
    requestAnimationFrame(animate);
}

// Inicializar los clavos y empezar la animación
setupPegs();
animate();
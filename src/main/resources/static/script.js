const canvas = document.getElementById('galtonBoard');
const ctx = canvas.getContext('2d');
const startButton = document.getElementById('startButton');

const ballRadius = 5;
const pegRadius = 3;
const rows = 10;
const cols = 11;
const pegs = [];
const balls = [];
const bins = new Array(cols).fill(0);

const targetBallCount = 300;
let totalBalls = 0;
let animationRunning = false;
let ballInterval = null;

// Configurar los clavos del tablero
function setupPegs() {
    const offsetX = canvas.width / cols / 2;
    const offsetY = 50;
    const spacingX = canvas.width / cols;
    const spacingY = 50;

    for (let row = 0; row < rows; row++) {
        const y = offsetY + row * spacingY;
        for (let col = 0; col < cols; col++) {
            const x = col * spacingX + (row % 2 === 0 ? spacingX / 2 : 0);
            pegs.push({ x, y });
        }
    }
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

// Agregar una nueva bola en la parte superior del tablero
function addBall() {
    balls.push({
        x: canvas.width / 2,
        y: 0,
        vy: 2 + Math.random() * 2
    });
    totalBalls++;
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

// Dibujar acumulaciones en la base
function drawBins() {
    ctx.fillStyle = 'blue';
    const binWidth = canvas.width / cols;
    for (let i = 0; i < bins.length; i++) {
        const height = bins[i] * 2 * ballRadius;
        ctx.fillRect(i * binWidth, canvas.height - height, binWidth, height);
    }
}

// Actualizar las posiciones de las bolas y simular interacciones con los clavos
function updateBalls() {
    for (let i = balls.length - 1; i >= 0; i--) {
        const ball = balls[i];

        if (ball.vy > 0) {
            ball.y += ball.vy;
            // Interacción con los clavos
            for (const peg of pegs) {
                const dx = ball.x - peg.x;
                const dy = ball.y - peg.y;
                const distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < ballRadius + pegRadius) {
                    ball.vy *= 0.9; // Reducir velocidad tras colisión
                    ball.x += Math.random() > 0.5 ? -canvas.width / cols / 2 : canvas.width / cols / 2; // Aleatoriedad horizontal
                    break;
                }
            }
            // Si llega al fondo del tablero
            if (ball.y > canvas.height - ballRadius) {
                const column = Math.min(
                    cols - 1,
                    Math.max(0, Math.floor(ball.x / (canvas.width / cols)))
                );
                bins[column] += 1;
                ball.y = canvas.height - (bins[column] * 2 * ballRadius);
                ball.vy = 0;
                ball.x = column * (canvas.width / cols) + (canvas.width / cols) / 2;
                balls.splice(i, 1);
            }
        }
    }
}

// Revisar si la simulación debe detenerse
function checkSimulationEnd() {
    const totalInBins = bins.reduce((a, b) => a + b, 0);
    if (totalInBins >= targetBallCount) {
        clearInterval(ballInterval);
        animationRunning = false;
        alert("¡Distribución normal formada!");
    }
}

// Animar el tablero
function animate() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawPegs();
    drawBalls();
    drawBins();
    updateBalls();
    checkSimulationEnd();
    if (animationRunning) {
        requestAnimationFrame(animate);
    }
}

// Manejar el clic en el botón para iniciar la animación
startButton.addEventListener('click', () => {
    if (!animationRunning) {
        animationRunning = true;
        animate();
        ballInterval = setInterval(() => {
            if (animationRunning) {
                addBall();
            }
        }, 100);
    }
});

// Inicializar los clavos
setupPegs();
drawPegs();

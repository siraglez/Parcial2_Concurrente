const canvas = document.getElementById('galtonBoard');
const ctx = canvas.getContext('2d');

const ballRadius = 5;
const pegRadius = 3;
const rows = 10;
const cols = 11;
const pegs = [];
const balls = [];


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


function addBall() {
    balls.push({
        x: canvas.width / 2,
        y: 0,
        vy: 2 + Math.random() * 2
    });
}


function drawPegs() {
    ctx.fillStyle = 'gray';
    for (const peg of pegs) {
        ctx.beginPath();
        ctx.arc(peg.x, peg.y, pegRadius, 0, Math.PI * 2);
        ctx.fill();
    }
}


function drawBalls() {
    ctx.fillStyle = 'red';
    for (const ball of balls) {
        ctx.beginPath();
        ctx.arc(ball.x, ball.y, ballRadius, 0, Math.PI * 2);
        ctx.fill();
    }
}


function updateBalls() {
    for (const ball of balls) {
        ball.y += ball.vy;


        for (const peg of pegs) {
            const dx = ball.x - peg.x;
            const dy = ball.y - peg.y;
            const distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < ballRadius + pegRadius) {
                ball.vy *= -1; // Rebote simple
                ball.x += Math.random() > 0.5 ? -2 : 2; //Movimiento aleatorio en x tras rebote
                break;
            }
        }


        if (ball.y > canvas.height) {
            ball.y = 0; //Reinicia la posición de la bola
            ball.x = canvas.width / 2; //uelve al centro
        }
    }
}

// Función principal de animación
function animate() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawPegs();
    drawBalls();
    updateBalls();
    requestAnimationFrame(animate);
}


setupPegs();
animate();

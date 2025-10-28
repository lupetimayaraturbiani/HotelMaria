// Carrossel básico
const carousels = document.querySelectorAll(".carousel");

carousels.forEach(carousel => {
    const track = carousel.querySelector(".carousel-track");
    const prev = carousel.querySelector(".prev");
    const next = carousel.querySelector(".next");

    if (prev && next) {
        prev.addEventListener("click", () => {
            track.scrollBy({ left: -200, behavior: "smooth" });
        });
        next.addEventListener("click", () => {
            track.scrollBy({ left: 200, behavior: "smooth" });
        });
    }
});

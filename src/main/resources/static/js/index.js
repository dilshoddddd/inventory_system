document.addEventListener("click", function (event) {
    const clickedButton = event.target.closest(".dropbtn");

    document.querySelectorAll(".dropdown-content.show").forEach(function (menu) {
        if (!clickedButton || !menu.closest(".dropdown").contains(clickedButton)) {
            menu.classList.remove("show");
            menu.closest(".dropdown")
                .querySelector(".dropbtn")
                .setAttribute("aria-expanded", "false");
        }
    });

    if (clickedButton) {
        const dropdown = clickedButton.closest(".dropdown");
        const menu = dropdown.querySelector(".dropdown-content");
        const isOpen = menu.classList.toggle("show");

        clickedButton.setAttribute("aria-expanded", String(isOpen));
    }
});

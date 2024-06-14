function redirectToProfile(korisnikId) {
    window.location.href = '/korisnik-profil/' + korisnikId;
}

function redirectToUceniciList(razredId) {
    window.location.href = '/razred/' + razredId + '/ucenici';
}

function toggleRazredDropdown() {
    var tip = document.getElementById('tip').value;
    var razredDiv = document.getElementById('razredDiv');
    if (tip === 'NASTAVNIK' || tip === 'RAZREDNIK') {
        razredDiv.style.display = 'none';
    } else {
        razredDiv.style.display = 'block';
    }
}